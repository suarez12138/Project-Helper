<template>
  <div class="components-container">
    <div id="border3_1">
      <div class="title">Group Information</div>
      <div class="search-Box" style="margin-top: 50px;margin-left: 40px;">
        <svg-icon icon-class="search" />
        <el-input
          v-model="search"
          placeholder="请输入关键字"
          class="search2"
          style="width: 30%;margin-left: 10px;"
        />
      </div>
      <el-table
        ref="filterTable"
        :data="tableData3"
        style="width: 100%"
        highlight-current-row
        @current-change="handleCurrentChange"
      >
        <el-table-column
          type="index"
          :index="indexMethod"
        />
        <el-table-column
          prop="name"
          label="组名"
          sortable
          width="100"
        >
          <template slot-scope="scope">
            <el-popover trigger="hover" placement="right" class="pout">
              <!--                  <div id="border3_3">-->
              <div class="title">Selected Group</div>
              <el-table
                ref="filterTable"
                class="juzhong"
                :data="tableData2"
                style="width: 100%"
              >
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
              </el-table>
              <div class="juzhong" style="font-size: 20px;margin-top: 30px;margin-bottom: 15px;">小组信息：</div>
              <div class="juzhong">不搞基</div>
              <!--                  </div>-->
              <!--                  <p>张三 SPRINGBOOT</p>-->
              <!--                  <p>李四 VUE</p>-->
              <div slot="reference" class="name-wrapper">
                <el-tag size="medium">{{ scope.row.name }}</el-tag>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column
          prop="pre_time"
          label="预期答辩时间"
          sortable
          width="130"
        />
        <el-table-column
          prop="information"
          label="小组信息"
          sortable
          width="250"
        />
        <el-table-column
          prop="status"
          label="状态"
          sortable
          width="120"
          :filters="[{ text: '完成组队', value: '完成组队' }, { text: '未完成组队', value: '未完成组队' }]"
          :filter-method="filterstatus"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.status === '未完成组队' ? 'primary' : 'success'"
              disable-transitions
            >{{ scope.row.status }}
            </el-tag>
          </template>
        </el-table-column>
        <!--        <el-table-column-->
        <!--          fixed="right"-->
        <!--          label="操作"-->
        <!--          width="100"-->
        <!--        >-->
        <!--          <template slot-scope="scope">-->
        <!--            <el-button type="text" size="small" @click="handleClick(scope.row)">加入</el-button>-->
        <!--          </template>-->
        <!--        </el-table-column>-->
      </el-table>
    </div>
  </div>
</template>

<script>
// import splitPane from 'vue-splitpane'
// import DndList from '@/components/DndList'
import { fetchList } from '@/api/article'

export default {
  name: 'DndListDemo',
  // components: { splitPane },
  data() {
    return {
      show_tooltip: false,
      text_content: 'My Group',
      fileList: [{
        name: 'food.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
      }, {
        name: 'food2.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
      }],
      dialogVisible: false,
      value2: true,
      list1: [],
      list2: [],
      textarea: '',
      tableData33: [{
        name: '组1',
        pre_time: '周五上午',
        information: '不搞基',
        status: '完成组队'
      }, {
        name: '组13',
        pre_time: '周四下午',
        information: '不划水',
        status: '未完成组队'
      }, {
        name: '组32',
        pre_time: '周五上午',
        status: '完成组队'
      }, {
        name: '组3',
        pre_time: '周四下午',
        status: '未完成组队'
      }],
      tableData2: [{
        name: '张小虎',
        gender: '男',
        skill: 'SPRING BOOT'
      }, {
        name: '王小虎',
        gender: '女',
        skill: 'VUE'
      }, {
        name: '王小虎'
      }, {
        name: '王小虎'
      }],
      ruleForm: {
        population: '',
        information: ''
      },
      search: ''
    }
  },
  computed: {
    tableData3: function() {
      var search = this.search
      if (search) {
        return this.tableData33.filter(function(dataNews) {
          return Object.keys(dataNews).some(function(key) {
            return String(dataNews[key]).toLowerCase().indexOf(search) > -1
          })
        })
      }
      return this.tableData33
    }
  },
  created() {
    this.getData()
  },
  methods: {
    hideTooltip: function() {
      // 在模型改变时，视图也会自动更新
      this.show_tooltip = false
    },
    toggleTooltip: function() {
      this.show_tooltip = !this.show_tooltip
    },
    getData() {
      this.listLoading = true
      fetchList().then(response => {
        this.list1 = response.data.items.splice(0, 5)
        this.list2 = response.data.items
      })
    },
    indexMethod(index) {
      return index
    },
    handleCurrentChange(val) {
      this.currentRow = val
      console.log(this.currentRow)
    },
    open() {
      this.$alert('此组已完成组队，尝试加入其他组吧！', '组队失败', {
        confirmButtonText: '确定'
        // callback: action => {
        //   this.$message({
        //     type: 'info',
        //     message: `action: ${action}`
        //   });
        // }
      })
    },
    quit() {
    },
    handleClick(row) {
      if (row.status === '完成组队') {
        this.open()
      }
    },
    handleChange(value) {
      console.log(value)
    },
    formatter(row, column) {
      return row.skill
    },
    filterstatus(value, row) {
      return row.tag === value
    },
    resize() {
      console.log('resize')
    },
    filterHandler(value, row, column) {
      const property = column['property']
      return row[property] === value
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePreview(file) {
      console.log(file)
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
    }
  }
}
</script>

<style>
.components-container {
  position: relative;
  height: 100vh;
}

.el-tag, .el-button, .el-input__inner, .el-textarea__inner {
  border-radius: 20px !important;
}

.el-popover {
  border-radius: 20px;
  box-shadow: 0 0 40px #CCCCCC;
}

#border3_2 {
  height: 100%;
}

#border3_1 {
  height: 100%;
  width: 60%;
  border: 2px solid #1890ff;
  margin-left: 300px;
}

#border3_1, #border3_2 {
  border-radius: 50px;
  transform: translate(0, 0);
  transition: all 0.3s ease-in-out;
  box-shadow: 10px 10px 20px #1890ff;
}

#border3_1:hover, #border3_2:hover {
  box-shadow:20px 20px 20px #1890ff;
  transform: translate(-5px,-5px);
  transition: 0.3s ease-in-out;
}

.title {
  color: #1890ff;
  font-size: 30px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 20px;

}

.flo {
  position: relative;
  float: left;
}

.juzhong {
  margin-left: 100px;
  margin-right: 100px;
  width: 75% !important;
}
</style>
