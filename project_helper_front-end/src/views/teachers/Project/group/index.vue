<template>
  <div class="components-container">
    <div id="t_border3_1_large">
      <div class="title">Group Information</div>
      <div class="search-Box" style="margin-top: 20px;margin-left: 20px;">
        <svg-icon class="search_icon2" icon-class="search" />
        <el-input
          v-model="search"
          placeholder="请输入关键字"
          style="width: 30%;margin-left: 10px;"
        />
        <BookTypeOption v-model="bookType" style="margin:0 0 0 60px;" />
        <el-button
          :loading="downloadLoading"
          style="margin:0 0 0 10px;"
          type="primary"
          icon="el-icon-document"
          @click="handleDownload"
        >
          Export Excel
        </el-button>
        <el-button type="primary" style="float: right;margin-right: 20px;" plain @click="auto_grouping">无效组一键组队
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
        style="width: 100%;"
        highlight-current-row
        :height="hei"
        @current-change="handleCurrentChange"
      >
        <el-table-column
          align="center"
          prop="name"
          label="组名"
          sortable
          width="115"
        >
          <template slot-scope="scope">
            <el-popover trigger="click" placement="right" class="pout">
              <!--                  <div id="border3_3">-->
              <div class="title">{{ sel_name }}</div>
              <el-table
                ref="filterTable"
                class="juzhong"
                :data="tableData2"
                style="width: 100%"
              >
                <el-table-column
                  align="center"
                  prop="name"
                  label="姓名"
                  sortable
                  width="120"
                />
                <el-table-column
                  align="center"
                  prop="gender"
                  label="性别"
                  sortable
                  width="120"
                  :filters="[{ text: '女', value: '女' },{ text: '男', value: '男' }, { text: '其他', value: '其他' }]"
                  :filter-method="filterHandler"
                />
                <el-table-column
                  align="center"
                  prop="skill"
                  width="200"
                  label="技能"
                  sortable
                  :formatter="formatter"
                />
              </el-table>
              <div class="juzhong" style="font-size: 20px;margin-top: 30px;margin-bottom: 15px; margin-left: 30px;">小组信息：</div>
              <div class="juzhong" style="margin-left: 30px;">不搞基</div>
              <!--                  </div>-->
              <!--                  <p>张三 SPRINGBOOT</p>-->
              <!--                  <p>李四 VUE</p>-->
              <div slot="reference" class="name-wrapper">
                <el-tag size="medium" @click="getGroup_by_name(scope.row.name)">{{ scope.row.name }}</el-tag>
              </div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          prop="pre_time"
          label="预期答辩时间"
          sortable
          width="130"
        />
        <el-table-column
          align="center"
          prop="group_info"
          label="小组信息"
          sortable
        />

        <el-table-column
          align="center"
          prop="people_number"
          label="小组人数"
          sortable
          width="110"
        />
        <el-table-column
          align="center"
          prop="valid"
          label="是否有效"
          :filters="[{ text: '是', value: 'T' }, { text: '否', value: 'F' }]"
          :filter-method="filtervalid"
          sortable
          width="130"
        >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.valid === 'F' ? 'primary' : 'success'"
              disable-transitions
            >{{ scope.row.valid }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
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
          align="center"
          fixed="right"
          label="操作"
          width="170px"
        >
          <template slot-scope="scope">
            <el-button type="success" size="small" @click="add_member(scope.row), getGroup_by_name(scope.row.name)">选人加入</el-button>
            <el-button type="danger" size="small" @click="drop_a_group(scope.row,scope.$index)">解散</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog
        title="请选择"
        :visible.sync="dialogVisible"
        width="70%"
        append-to-body
      >
        <div>

          <div class="search-Box" style=" margin-right: 30px;width: 30%; float: right">
            <!--            <svg-icon icon-class="search" class="search_icon"  />-->
            <el-input v-model="search" prefix-icon="search_icon" placeholder="请输入关键字" style="float:right;" />
          </div>

          <el-table
            ref="filterTable"
            :data="tableData_of_OneGroup"
            style="width: 100%"
          >
            <el-table-column
              align="center"
              prop="id"
              label="SID"
              sortable
              width="100"
            />
            <el-table-column
              align="center"
              prop="name"
              label="姓名"
              sortable
              width="100"
            />
            <el-table-column
              align="center"
              prop="gender"
              label="性别"
              sortable
              width="100"
              :filters="[{ text: '女', value: '女' },{ text: '男', value: '男' }, { text: '其他', value: '其他' }]"
              :filter-method="filterHandler"
            />
            <el-table-column
              align="center"
              prop="lab"
              label="Lab"
              sortable
              width="70"
            />
            <el-table-column
              align="center"
              prop="tags"
              label="技能"
              sortable
              :formatter="formatter"
            />
            <el-table-column
              align="center"
              prop="hope"
              label="期待队友类型"
              sortable
              width="130"
            />
            <el-table-column
              align="center"
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
              align="center"
              fixed="right"
              label="操作"
              width="120"
            >
              <template slot-scope="scope">
                <el-button
                  type="primary"
                  size="small"
                  @click.native.prevent="add_member2(scope.row)"
                >
                  拉 入
                </el-button>
              </template>
            </el-table-column>

          </el-table>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button plain type="primary" @click="dialogVisible = false">关 闭</el-button>
          <!--          <el-button type="primary" @click="add_member2()">确 定</el-button>-->
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
// import {fetchList} from '@/api/article'
import BookTypeOption from './components/BookTypeOption'
import { getToken } from '@/utils/auth'
import { fetchGroupsListState } from '@/api/teacher/group'
import { fetchGroupsList } from '@/api/teacher/group'
// import { fetchGroupsListState } from '@/api/student/group'
// import { fetchGroupsList } from '@/api/student/group'
import { dropGroup } from '@/api/teacher/group'
import { fetchTheGroup } from '@/api/student/group'
import { autoform_Group } from '@/api/teacher/group'

const groupOptions = ['只看有效组', '只看无效组']
export default {
  // name: 'DndListDemo',
  components: { BookTypeOption },
  data() {
    return {
      hei: window.innerHeight * 0.75,
      sel_name: 'Title',
      selectRow: '',
      dialogVisible: false,
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
      groupList: [],
      groupStates: [],
      tableData33:[],
      // [{
      //   name: '组1',
      //   pre_time: '周五上午',
      //   information: '不搞基',
      //   population: 4,
      //   valid: '是',
      //   status: '完成组队'
      // }],
      tableData2:
      // [{
      //   name: '张小虎',
      //   gender: '男',
      //   skill: 'SPRING BOOT'
      // }]
      null,
      tableData_of_OneGroup:
      null,
      all_student:
      null,
      ruleForm: {
        population: '',
        information: ''
      },
      tableData_inside2:
      null,
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
    },
    tableData_inside: function() {
      var search = this.search
      if (search) {
        return this.tableData_inside2.filter(function(dataNews) {
          return Object.keys(dataNews).some(function(key) {
            return String(dataNews[key]).toLowerCase().indexOf(search) > -1
          })
        })
      }
      return this.tableData_inside2
    }
  },

  created() {
    this.getAllGroups()

  },
  methods: {
    getStudents() {
      fetchStudent().then(response => {
        this.tableData_of_OneGroup = response.data
      })
    },
    getAllGroups() {
      this.listLoading = true
      // alert(localStorage.getItem('current_project_id'))
      fetchGroupsList(getToken(), localStorage.getItem('current_project_id')).then(response => {
        this.groupList = response.data
        this.listLoading = false
      })
      fetchGroupsListState(getToken(), localStorage.getItem('current_project_id')).then(response => {
        this.groupStates = response.data
        this.set_table33()
      })
    },
    set_table33() {
      // alert(this.groupList[0].pre_time)
      var length = this.groupList.length
      alert(length)
      for (var i = 0; i < length; i++) {
        this.tableData33.push({
          name: this.groupList[i].name,
          pre_time: this.groupList[i].pre_time,
          status: this.groupList[i].status,
          valid: this.groupStates[i].is_valid,
          can_join: this.groupStates[i].can_join,
          people_number: this.groupStates[i].people_number,
          max_people: this.groupStates[i].max_people,
          min_people: this.groupStates[i].min_people,
          group_info: this.groupStates[i].group_info
        })
        // alert(this.tableData33)
        // alert(this.groupList[0].name)
        // alert(this.tableData33[0].pre_time)
        // alert(this.tableData33[i].status)
        // alert(this.tableData33[i].is_valid)
        // alert(this.tableData33[i].can_join)
        // alert(this.tableData33[i].people_number)
        // alert(this.tableData33[i].max_people)
        // alert(this.tableData33[i].min_people)
        // alert(this.tableData33[i].group_info)
      }
    },
    getGroup_by_name(name) {
      this.sel_name = name
      fetchTheGroup(name).then(response => {
        this.tableData2 = response.myGroups
        this.listLoading = false
      })
    },
    auto_grouping() {
      autoform_Group(localStorage.getItem('current_project_id')).then(response => {

      })
    },
    add_member(row) {
      




    },
    add_member2() {
      this.dialogVisible = false
    },
    drop_a_group(row, index) {
      this.tableData33.splice(index, 1)
      dropGroup(row.id).then(response => {
        // alert(response.message)
         this.$message({
        message: response.message,
        type: 'success'
      })
      })
     
    },
    hideTooltip: function() {
      // 在模型改变时，视图也会自动更新
      this.show_tooltip = false
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['组名', '学号', '姓名', 'Lab班级'] // 这个需要多表查询
        const filterVal = ['name']// 这里需要补全变量名
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

.el-popover,.el-dialog {
  border: 1px dashed $primary;
  border-radius: 20px;
  box-shadow: 0 0 10px $primary;
  padding-bottom: 50px;
}

#t_border3_1_large {
  height: 100%;
  width: 100%;
  border: 2px solid $primary;
  //margin-left: 150px;
  padding-left: 20px;
  padding-right: 20px;
  //overflow-y: auto;
}

#t_border3_1_large {
  border-radius: 50px;
  transform: translate(0, 0);
  transition: all 0.3s ease-in-out;
  box-shadow: 10px 10px 20px $primary;
}

#t_border3_1_large:hover {
  box-shadow: 20px 20px 20px $primary;
  transform: translate(-5px, -5px);
  transition: 0.3s ease-in-out;
}

.title {
  color: $primary;
  font-size: 40px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 20px;
  padding-bottom: 40px;
}
.search_icon {
  float: right;
  margin-right: 10px;
  margin-top: 10px;
  color: $primary;
}
.search_icon2 {
  color: $primary;
}

.el-table__body-wrapper::-webkit-scrollbar { /*滚动条整体*/
  width: 10px;
}

.el-table__body-wrapper::-webkit-scrollbar-track { /*滚动条轨道*/
  background: #ffffff;
  border-radius: 20px;
  //margin-top: 30px;
  margin-bottom: 30px;

}

.el-table__body-wrapper::-webkit-scrollbar-thumb { /*滚动条里面的滑块*/
  background: $primary;
  border-radius: 10px;
}

.el-table__body-wrapper::-webkit-scrollbar-corner { /*滚动条边角*/
  background: $primary;
}
</style>
