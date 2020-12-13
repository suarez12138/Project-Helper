<template>
  <div class="components-container">
    <div id="border_large">
      <div class="title">Submission Information</div>
      <div class="search-Box" style="margin-top: 20px;margin-left: 20px;">
        <svg-icon class="search_icon2" icon-class="search" />
        <el-input
          v-model="search"
          placeholder="请输入关键字"
          class="search2"
          style="width: 15%;margin-left: 5px;"
        />
        <el-button plain type="primary" style="float: right;margin-right: 20px;">更新<i
          class="el-icon-upload el-icon--right"
        /></el-button>
        <el-button type="primary" style="margin-left: 30px;margin-right: 50px;" plain @click="auto_download">下载所有提交
        </el-button>
        <BookTypeOption v-model="bookType" />
        <el-button
          :loading="downloadLoading"
          style="margin:0 0 20px 20px;"
          type="primary"
          icon="el-icon-document"
          @click="handleDownload"
        >
          Export
        </el-button>
        <el-button type="primary" icon="el-icon-document">
          Upload
        </el-button>
      </div>

      <el-table
        ref="filterTable"
        :data="tableData3"
        style="width: 100%"
      >
        <el-table-column
          align="center"
          prop="name"
          label="组名"
          sortable
          width="100px;"
        >
          <template slot-scope="scope">
            <el-popover trigger="click" placement="right" class="pout">
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
          align="center"
          prop="status"
          label="状态"
          sortable
          :filters="[{ text: '已提交', value: '已提交' }, { text: '未提交', value: '未提交' }]"
          :filter-method="filterstatus"
          filter-placement="bottom-end"
          width="100"
        >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.status === '未提交' ? 'primary' : 'success'"
              disable-transitions
            >{{ scope.row.status }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="操作"
          width="120px"
        >
          <template slot-scope="scope">
            <el-dropdown trigger="click">
              <el-button type="primary" size="small" @click="try1()">
                查看提交<i class="el-icon-arrow-down el-icon--right" />
              </el-button>
              <el-dropdown-menu slot="dropdown">
                <!--eslint-disable-next-line-->
                <el-dropdown-item v-for="item in scope.row.submission">{{ item.name }}</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </template>

          <!--          <template slot-scope="scope">-->
          <!--            <el-button type="primary" size="small" @click="lookup(scope.row)">查看提交</el-button>-->
          <!--          </template>-->
        </el-table-column>
        <el-table-column align="center" prop="score" sortable label="Score" width="90">
          <template slot-scope="scope">
            <el-input v-model.trim="scope.row.score" />
          </template>
        </el-table-column>
        <el-table-column align="center" label="Comments">
          <template slot-scope="scope">
            <el-input
              v-model="scope.row.comments"
              type="textarea"
              autosize
              placeholder="请输入评语"
            />
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
// import { fetchList } from '@/api/article'
import BookTypeOption from './components/BookTypeOption'

const groupOptions = ['只看有效组', '只看无效组']
export default {
  name: 'DndListDemo',
  components: { BookTypeOption },
  // components: { splitPane },
  data() {
    return {
      checkedgroups: [],
      groups: groupOptions,
      downloadLoading: false,
      show_tooltip: false,
      value2: true,
      lowerBound: 4,
      list1: [],
      list2: [],
      bookType: 'xlsx',
      textarea: '',
      tableData33: [{
        name: '组1',
        status: '已提交',
        submission: [{
          name: 'food.jpeg',
          url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        }, {
          name: 'food2.jpeg',
          url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        }]
      }, {
        name: '组13',
        status: '未提交'
      }, {
        name: '组32',
        status: '已提交'
      }, {
        name: '组3',
        status: '未提交'
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
      // if (!this.checkedgroups.length) {
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
  // created() {
  //   this.getData()
  // },
  methods: {
    try1() {
      console.log(this.tableData33[0].submission)
    },
    changeView() {
      // console.log(this.checkedgroups)
      // if (!this.checkedgroups.length) {
      //
      // }
    },
    auto_download() {

    },
    lookup(row) {

    },
    hideTooltip: function() {
      // 在模型改变时，视图也会自动更新
      this.show_tooltip = false
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['组名']
        const filterVal = ['name']
        const scorelist = this.tableData33
        const data = this.formatJson(filterVal, scorelist)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: this.filename,
          autoWidth: this.autoWidth,
          bookType: this.bookType
        })
        this.downloadLoading = false
      })
    },
    formatJson(filterVal, jsonData) {
      var count = 1
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'index') {
          return count++
        } else {
          // console.log(v[j])
          return v[j]
        }
      }))
    },
    toggleTooltip: function() {
      this.show_tooltip = !this.show_tooltip
    },
    // getData() {
    //   this.listLoading = true
    //   fetchList().then(response => {
    //     this.list1 = response.data.items.splice(0, 5)
    //     this.list2 = response.data.items
    //   })
    // },
    indexMethod(index) {
      return index
    },
    // handleCurrentChange(val) {
    //   this.currentRow = val
    //   console.log(this.currentRow)
    // },
    // handleChange(value) {
    //   console.log(value)
    // },
    formatter(row, column) {
      return row.skill
    },
    filterstatus(value, row) {
      return row.status === value
    },
    filterHandler(value, row, column) {
      const property = column['property']
      return row[property] === value
    }
  }
}
</script>

<style lang="scss">
@import "~@/styles/variables.scss";

.components-container {
  position: relative;
  height: 100vh;
  //background-color: $primary;
}

.el-dropdown {
  vertical-align: top;
}

.el-card, .el-input__inner, .el-textarea__inner, .el-button, .el-select-dropdown, .el-select-dropdown__list, .el-select-dropdown__item.hover {
  border-radius: 30px;
}

.el-tag, .el-button, .el-input__inner, .el-textarea__inner {
  border-radius: 20px !important;
}

.el-popover {
  border: 1px dashed $primary;
  border-radius: 20px;
  box-shadow: 0 0 10px $primary;
  padding-bottom: 50px;
}

#border_large {
  height: 100%;
  width: 80%;
  border: 2px solid $primary;
  margin-left: 150px;
  padding-left: 20px;
  padding-right: 20px;
}

#border_large {
  border-radius: 50px;
  transform: translate(0, 0);
  transition: all 0.3s ease-in-out;
  box-shadow: 10px 10px 20px $primary;
}

#border_large:hover {
  box-shadow: 20px 20px 20px $primary;
  transform: translate(-5px, -5px);
  transition: 0.3s ease-in-out;
}

.el-table .cell {
  white-space: pre-line !important; //保留空白符序列，但是正常地进行换行。
  //white-space: pre-wrap; //保留空白符序列，但是正常地进行换行。
}

.el-textarea__inner {
  white-space: pre-line !important;
}

.title {
  color: $primary;
  font-size: 40px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 20px;
  padding-bottom: 40px;
}

.juzhong {
  margin-left: 100px;
  margin-right: 100px;
  width: 75% !important;
}

.search_icon2 {
  color: $primary;
}
</style>
