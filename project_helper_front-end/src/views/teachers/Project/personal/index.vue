<template>
  <div class="border_p">
    <div class="personal_title"> Personal information</div>
    <div>
      <el-button type="text" style="float: left;margin-left: 50px;" size="large" @click="handleClick()"><i
        class="el-icon-circle-plus"
      /> 组队
      </el-button>
      <div class="search-Box" style=" margin-right: 30px;width: 30%; float: right">
        <el-input v-model="search" placeholder="请输入关键字" class="search" />
        <svg-icon icon-class="search" class="search_icon" />
      </div>
      <el-dialog title="创建小组" :visible.sync="dialogFormVisible" width="500px" append-to-body>
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

      <el-table
        ref="filterTable"
        :data="tableData2"
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <!--        <el-table-column-->
        <!--          type="index"-->
        <!--          :index="index"-->
        <!--        />-->
        <el-table-column
          prop="select"
          type="selection"
          width="55"
        />
        <el-table-column
          prop="name"
          label="姓名"
          sortable
          width="100"
        />
        <el-table-column
          prop="gender"
          label="性别"
          sortable
          width="100"
          :filters="[{ text: '女', value: '女' },{ text: '男', value: '男' }, { text: '其他', value: '其他' }]"
          :filter-method="filterHandler"
        />
        <el-table-column
          prop="lab"
          label="Lab"
          sortable
          width="70"
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
          width="130"
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
        <el-table-column
          fixed="right"
          label="操作"
          width="120"
        >
          <template slot-scope="scope">
            <el-button
              type="danger"
              size="small"
              @click.native.prevent="remove_from_group(scope.row)"
            >
              移出小组
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: 'DragSelectDemo',
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
        lab: 2,
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
    remove_from_group(row) {
      // console.log(row)
      if (row.status === '未组队') {
        this.$alert('该同学还未组队，尝试选择其他人吧！', '移出失败', {
          confirmButtonText: '确定'
        })
        return
      }
    },
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
      this.$alert('有人已经组队成功啦，尝试选择其他人吧！', '组队失败', {
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

<style lang="scss">
@import "~@/styles/variables.scss";

.search {
  float: right;
  width: 80% !important;
}

.search > .el-input__inner {
  border-radius: 30px;
}

.el-tag, .el-button, .el-dialog, .el-input__inner, .el-select-dropdown {
  border-radius: 20px;
}

.border_p::-webkit-scrollbar { /*滚动条整体*/
  width: 10px;
}

.border_p::-webkit-scrollbar-track { /*滚动条轨道*/
  background: #ffffff;
  border-radius: 20px;
  margin-top: 240px;
  margin-bottom: 40px;
}

.border_p::-webkit-scrollbar-thumb { /*滚动条里面的滑块*/
  background: $primary;
  border-radius: 10px;
}

.border_p::-webkit-scrollbar-corner { /*滚动条边角*/
  background: $primary;
}

.border_p {
  height: 900px;
  width: 60%;
  border: 2px solid $primary;
  margin-left: 300px;
  border-radius: 50px;
  transform: translate(0, 0);
  transition: all 0.3s ease-in-out;
  box-shadow: 10px 10px 20px $primary;
  margin-top: 50px;
  padding-left: 20px;
  padding-right: 20px;
}

.border_p:hover {
  box-shadow: 20px 20px 20px $primary;
  transform: translate(-5px, -5px);
  transition: 0.3s ease-in-out;
}

.search_icon {
  float: right;
  margin-right: 10px;
  margin-top: 10px;
  color: $primary;
}

.personal_title {
  color: $primary;
  font-size: 40px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 20px;
  padding-bottom: 40px;
}
</style>
