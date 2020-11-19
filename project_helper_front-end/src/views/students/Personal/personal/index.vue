<template>
  <div class="components-container">
    <el-button type="text" style="float: left;" @click="dialogFormVisible1 = true">修改个人相关信息</el-button>

    <el-dialog title="关于此project" :visible.sync="dialogFormVisible1" width="500px">
      <el-form :model="form">
        <el-form-item label="你的技能" :label-width="formLabelWidth">
          <el-input v-model="form.skill" autocomplete="off" />
        </el-form-item>
        <el-form-item label="期待队友类型" :label-width="formLabelWidth">
          <el-input v-model="form.expect" autocomplete="off" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="dialogFormVisible1 = false">确 定</el-button>
      </div>
    </el-dialog>
    <div>
      <div class="search-Box">
        <el-input v-model="search" placeholder="请输入关键字" icon="search" class="search" />
      </div>
      <el-table
        ref="filterTable"
        :data="tableData2"
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column
          type="index"
          :index="index"
        />
        <el-table-column
          prop="select"
          type="selection"
          width="55"
        />
        <el-table-column
          prop="name"
          label="姓名"
          sortable
          width="180"
        />
        <el-table-column
          prop="gender"
          label="性别"
          sortable
          width="180"
          :filters="[{ text: '女', value: '女' },{ text: '男', value: '男' }, { text: '其他', value: '其他' }]"
          :filter-method="filterHandler"
        />
        <el-table-column
          prop="skill"
          label="技能"
          sortable
          :formatter="formatter"
        />
        <el-table-column
          prop="hope"
          label="期待队友类型"
          sortable
        />
        <el-table-column
          prop="status"
          label="状态"
          sortable
          width="100"
          :filters="[{ text: '未组队', value: '未组队' }, { text: '已组队', value: '已组队' }]"
          :filter-method="filterstatus"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.status === '未组队' ? 'primary' : 'success'"
              disable-transitions
            >{{ scope.row.status }}
            </el-tag>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="text" size="small" @click="handleClick()">邀请组队</el-button>
    </div>
    <el-dialog title="创建小组" :visible.sync="dialogFormVisible" width="500px">
      <el-form :model="createGroupForm">
        <el-form-item label="小组名称" :label-width="formLabelWidth">
          <el-input v-model="createGroupForm.name" autocomplete="off" />
        </el-form-item>
        <!--        <el-form-item label="意向人数" :label-width="formLabelWidth">-->
        <!--          <el-select v-model="form.population" placeholder="请选择意向人数">-->
        <!--            <el-option label="区域一" value="shanghai"></el-option>-->
        <!--            <el-option label="区域二" value="beijing"></el-option>-->
        <!--          </el-select>-->
        <!--        </el-form-item>-->
        <el-form-item label="预期答辩时间" :label-width="formLabelWidth">
          <el-select v-model="createGroupForm.population" placeholder="预期答辩时间" />
        </el-form-item>
        <el-form-item label="小组信息" :label-width="formLabelWidth">
          <el-input
            v-model="createGroupForm.information"
            autocomplete="off"
            maxlength="50"
            show-word-limit
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('createGroupForm',createGroupForm)">确 定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import ElDragSelect from '@/components/DragSelect' // base on element-ui

export default {
  name: 'DragSelectDemo',
  // components: { ElDragSelect },
  data() {
    return {
      index: 0,
      createGroupForm: {
        name: '',
        population: '',
        information: ''
      },
      tableData22: [{
        name: '张小虎',
        gender: '男',
        skill: 'SPRING BOOT',
        hope: '不搞基',
        status: '已组队'
      }, {
        name: '王小虎',
        gender: '女',
        skill: 'VUE',
        hope: '不划水',
        status: '未组队'
      }, {
        name: '王小虎',
        status: '已组队'
      }, {
        name: '王小虎',
        status: '未组队'
      }],
      form: {
        skill: '',
        expect: ''
      },
      dialogFormVisible1: false,
      dialogFormVisible: false,
      formLabelWidth: '120px',
      search: ''
    }
  },
  computed: {
    tableData2: function() {
      var search = this.search
      if (search) {
        return this.tableData22.filter(function(dataNews) {
          return Object.keys(dataNews).some(function(key) {
            return String(dataNews[key]).toLowerCase().indexOf(search) > -1
          })
        })
      }
      return this.tableData22
    }
  },
  methods: {
    resetDateFilter() {
      this.$refs.filterTable.clearFilter('date')
    },
    clearFilter() {
      this.$refs.filterTable.clearFilter()
    },
    formatter(row, column) {
      return row.skill
    },
    filterstatus(value, row) {
      return row.status === value
    },
    filterHandler(value, row, column) {
      const property = column['property']
      return row[property] === value
    },
    open() {
      this.$alert('有人已经组队成功啦，尝试邀请其他人吧！', '组队失败', {
        confirmButtonText: '确定'
      })
    },
    handleClick() {
      // console.log(this.multipleSelection)
      // console.log(this.multipleSelection == null)
      if (this.multipleSelection == null) {
        this.$alert('请选择想组队的同学！', '组队失败', {
          confirmButtonText: '确定'
        })
        return
      }
      for (var i = 0; i < this.multipleSelection.length; i++) {
        if (this.multipleSelection[i].status === '已组队') {
          this.open()
          return
        }
      }
      this.dialogFormVisible = true
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
      console.log(this.multipleSelection)
    },
    submitForm(ruleForm, ruleForm2) {
      this.dialogFormVisible = false
    }
  }
}
</script>
<style>
.search {
  float: right;
  margin-right: 100px;
  width: 20%;
}

.search > .el-input__inner {
  border-radius: 30px;
}

.el-tag, .el-button, .el-dialog, .el-input__inner,.el-select-dropdown {
  border-radius: 20px;
}
</style>
