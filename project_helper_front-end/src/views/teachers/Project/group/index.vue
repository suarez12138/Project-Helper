<template>
  <div class="components-container">
    <div id="t_border3_1">
      <div class="title">Group Information</div>
      <div class="search-Box" style="margin-top: 20px;margin-left: 40px;">
        <svg-icon class="search_icon2" icon-class="search" />
        <el-input
          v-model="search"
          placeholder="请输入关键字"
          class="search2"
          style="width: 30%;margin-left: 10px;"
        />
        <el-button
          :loading="downloadLoading"
          style="margin:0 0 0 60px;"
          type="primary"
          icon="el-icon-document"
          @click="handleDownload"
        >
          Export Excel
        </el-button>
        <!--        <el-checkbox-group-->
        <!--          v-model="checkedgroups"-->
        <!--          style="float: right;margin-right: 50px;"-->
        <!--          :min="0"-->
        <!--          :max="1"-->
        <!--        >-->
        <!--          <el-checkbox-button v-for="group in groups" :key="group" :label="group" @change="changeView()">{{-->
        <!--              group-->
        <!--            }}-->
        <!--          </el-checkbox-button>-->
        <!--        </el-checkbox-group>-->
      </div>

      <el-table
        ref="filterTable"
        :data="tableData3"
        style="width: 100%"
        highlight-current-row
        @current-change="handleCurrentChange"
      >
        <!--        <el-table-column-->
        <!--          type="index"-->
        <!--          :index="indexMethod"-->
        <!--        />-->
        <el-table-column
          prop="name"
          label="组名"
          sortable
          width="90"
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
        />

        <el-table-column
          prop="population"
          label="小组人数"
          sortable
          width="110"
        />
        <el-table-column
          prop="valid"
          label="是否有效"
          :filters="[{ text: '是', value: '是' }, { text: '否', value: '否' }]"
          :filter-method="filtervalid"
          sortable
          width="130"
        >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.valid === '否' ? 'primary' : 'success'"
              disable-transitions
            >{{ scope.row.valid }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          prop="status"
          label="状态"
          sortable
          :filters="[{ text: '完成组队', value: '完成组队' }, { text: '未完成组队', value: '未完成组队' }]"
          :filter-method="filterstatus"
          filter-placement="bottom-end"
          width="100"
        >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.status === '未完成组队' ? 'primary' : 'success'"
              disable-transitions
            >{{ scope.row.status }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="170px"
        >
          <template slot-scope="scope">
            <el-button type="success" size="small" @click="handleClick(scope.row)">选人加入</el-button>
            <el-button type="danger" size="small" @click="handleClick(scope.row)">解散</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { fetchList } from '@/api/article'

const groupOptions = ['只看有效组', '只看无效组']
export default {
  name: 'DndListDemo',
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
      textarea: '',
      tableData33: [{
        name: '组1',
        pre_time: '周五上午',
        information: '不搞基',
        population: 4,
        valid: '是',
        status: '完成组队'
      }, {
        name: '组13',
        pre_time: '周四下午',
        information: '不划水',
        population: 3,
        valid: '否',
        status: '未完成组队'
      }, {
        name: '组32',
        pre_time: '周五上午',
        status: '完成组队'
      }, {
        name: '组3',
        pre_time: '周四下午',
        population: 3,
        valid: '否',
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
      // if (!this.checkedgroups.length) {
      if (search) {
        return this.tableData33.filter(function(dataNews) {
          return Object.keys(dataNews).some(function(key) {
            return String(dataNews[key]).toLowerCase().indexOf(search) > -1
          })
        })
      }
      return this.tableData33
      // } else if (this.checkedgroups[0] === '只看有效组') {
      //   if (search) {
      //     return this.tableData33.filter(function(dataNews) {
      //       return Object.keys(dataNews).some(function(key) {
      //         return String(dataNews[key]).toLowerCase().indexOf(search) > -1
      //       })
      //     })
      //   }
      //   return this.tableData33
      // } else {
      //
      // }
    }
  },
  created() {
    this.getData()
  },
  methods: {
    changeView() {
      // console.log(this.checkedgroups)
      // if (!this.checkedgroups.length) {
      //
      // }
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
    handleChange(value) {
      console.log(value)
    },
    formatter(row, column) {
      return row.skill
    },
    filterstatus(value, row) {
      return row.status === value
    },
    filtervalid(value, row) {
      return row.valid === value
    },
    resize() {
      console.log('resize')
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

#t_border3_1 {
  height: 100%;
  width: 80%;
  border: 2px solid $primary;
  margin-left: 150px;
  padding-left: 20px;
  padding-right: 20px;
}

#t_border3_1 {
  border-radius: 50px;
  transform: translate(0, 0);
  transition: all 0.3s ease-in-out;
  box-shadow: 10px 10px 20px $primary;
}

#t_border3_1:hover {
  box-shadow: 20px 20px 20px $primary;
  transform: translate(-5px, -5px);
  transition: 0.3s ease-in-out;
}

.title {
  color: $primary;
  font-size: 30px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 20px;
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
