<template>
  <div class="components-container">
    <div v-if="this.beforeddl" id="border3_1" ref="show">
      <div class="title_3">Grouping Information</div>
      <div class="search-Box" style="padding-top: 20px;padding-left: 40px;">
        <svg-icon icon-class="search" class="search_icon5" />
        <el-input
          v-model="search"
          placeholder="请输入关键字"
          class="search2"
          style="width: 30%;"
        />
      </div>
      <el-table
        ref="filterTable"
        v-loading="listLoading"
        :data="tableData3"
        style="width: 100%;overflow-y: auto;"
        class="table1"
        :height="hei"
        highlight-current-row
        @current-change="handleCurrentChange"
      >
        <el-table-column
          align="center"
          type="index"
          :index="indexMethod"
        />
        <el-table-column
          align="center"
          prop="name"
          label="组名"
          sortable
          width="130"
        >
          <template slot-scope="scope">
            <el-popover trigger="click" placement="right" width="600px">
              <!--                  <div id="border3_3">-->
              <div class="title_3">{{ sel_name }}</div>
              <el-table
                ref="filterTable"
                v-loading="listLoading"
                class="juzhong"
                :data="tableData_of_OneGroup"
                style="width: 100%"
              >
                <el-table-column
                  align="center"
                  prop="stu_id"
                  label="SID"
                  sortable
                  width="100"
                />
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
                  prop="lab"
                  label="Lab"
                  sortable
                  width="100"
                />
                <el-table-column
                  align="center"
                  prop="dorm"
                  label="宿舍"
                  sortable
                  width="120"
                />
                <el-table-column
                  align="center"
                  prop="tags"
                  label="技能"
                >
                  <template slot-scope="scope">
                    <!--eslint-disable-next-line-->
                    <el-tag v-for="item in scope.row.tags" effect="dark">{{ item }}</el-tag>
                  </template>
                </el-table-column>
              </el-table>
              <div class="juzhong" style="font-size: 20px;margin-top: 30px;margin-bottom: 15px;">小组信息：</div>
              <div class="juzhong">{{ this_group_information }}</div>

              <div slot="reference" class="name-wrapper">
                <el-tag size="medium" @click="getGroup_by_name(scope.row.name,scope.row.information)">{{
                  scope.row.name
                }}
                </el-tag>
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
              :type="scope.row.valid === 'F' ? 'danger' : 'success'"
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
          width="120"
          :filters="[{ text: '不可加入', value: '不可加入' }, { text: '可加入', value: '可加入' }]"
          :filter-method="filterstatus"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.status === '不可加入' ? 'danger' : 'success'"
              disable-transitions
            >{{ scope.row.status }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          fixed="right"
          label="操作"
          width="100"
        >
          <template slot-scope="scope">
            <el-button type="primary" plain size="small" @click="handleClick(scope.row)">加入</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div v-if="!this.beforeddl" class="guanbi">组队功能已关闭</div>

  </div>
</template>

<script>
// import splitPane from 'vue-splitpane'
// import DndList from '@/components/DndList'
import { fetchGroupsListState } from '@/api/student/group'
import { fetchGroupsList } from '@/api/student/group'
import { fetchMyGroup } from '@/api/student/group'
import { fetchTheGroup } from '@/api/student/group'
import { joinGroup } from '@/api/student/group'
import { getToken } from '@/utils/auth'

export default {
  name: 'DndListDemo',
  // components: { splitPane },
  data() {
    return {
      show_tooltip: false,
      sel_name: 'Title',
      hei: window.innerHeight * 0.80,
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
      project_dict: {},
      current_project: localStorage.getItem('current_project').substring(1, (localStorage.getItem('current_project').length - 1)),
      textarea: '',
      tableData33: [],
      MyGroupTableData:
        null,
      // [
      //   {
      //   name: '张小虎',
      //   gender: '男',
      //   skill: 'SPRING BOOT'
      // }
      // ],
      tableData_of_OneGroup:
        null,
      groupList: null,
      groupStates: null,
      // [
      //   {
      //   name: '张小虎',
      //   gender: '男',
      //   skill: 'SPRING BOOT'
      // }
      // ],
      ruleForm: {
        population: '',
        information: ''
      },
      listLoading: false,
      search: '',
      beforeddl: true,
      this_group_information: ''
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
      console.log(localStorage)

      return this.tableData33
    }
  },
  created() {
    // this.getMyGroup()
    this.getAllGroups()
  },
  methods: {
    getAllGroups() {
      this.listLoading = true
      fetchGroupsList(getToken(), localStorage.getItem('current_project_id')).then(response => {
        this.groupList = response.data
        // alert(response.allGroups[0].pre_time)
        // alert("miao")
        this.listLoading = false
        this.$refs.show.style.display = this.beforeddl
      })
      fetchGroupsListState(getToken(), localStorage.getItem('current_project_id')).then(response => {
        this.groupStates = response.data
        this.set_table33()
      })
    },
    set_table33() {
      // alert(this.groupList[0].pre_time)
      var length = this.groupList.length
      // alert(length)
      for (var i = 0; i < length; i++) {
        this.tableData33.push({
          id: this.groupList[i].id,
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
    getMyGroup() { // 应该传一些学号什么的回去
      this.listLoading = true
      console.log(getToken())
      console.log('aaaaaaaaaaaaaa')
      // fetchMyGroup(getToken(), 1).then(response => {
      fetchMyGroup(getToken(), localStorage.getItem('current_project_id')).then(response => {
        this.MyGroupTableData = response.myGroups
        this.listLoading = false
      })
    },
    getGroup_by_name(name, infor) {
      this.sel_name = name
      this.this_group_information = infor
      fetchTheGroup(name).then(response => {
        this.tableData_of_OneGroup = response.myGroups
        this.listLoading = false
        // console.log(this.tableData_of_OneGroup)
      })
    },
    hideTooltip: function() {
      // 在模型改变时，视图也会自动更新
      this.show_tooltip = false
    },
    returnrow(row) {
      console.log(row)
      alert(row)
    },
    toggleTooltip: function() {
      this.show_tooltip = !this.show_tooltip
    },
    getData() {
      this.listLoading = true
      fetchList().then(response => {
        this.list1 = response.data.items
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
    filtervalid(value, row) {
      return row.valid === value
    },
    open() {
      this.$alert('此组已不可加入，尝试加入其他组吧！', '组队失败', {
        confirmButtonText: '确定'
      })
    },
    quit() {
    },
    handleClick(row) {
      if (row.status === '不可加入') {
        alert('此组不可加入')
      } else {
        // alert(row.id)
        joinGroup(getToken(), row.id).then(response => {
          // alert(response.message)
          this.$message({
            message: '加入成功',
            type: 'success'
          })
          this.$router.push({ path: '/studentMyGroup/studentMyGroup' })
        })
      }
    },
    handleChange(value) {
      console.log(value)
    },
    formatter(row, column) {
      return row.tags
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

<style lang="scss">
@import "~@/styles/variables.scss";

.components-container {
  position: relative;
  height: 100vh;
}

.el-tag, .el-button, .el-input__inner, .el-textarea__inner {
  border-radius: 20px !important;
}

.guanbi {
  color: $primary;
  text-align: center;
  font-weight: bold;
  font-size: 40px;
}

.el-popover {
  border: 1px dashed $primary;
  border-radius: 20px;
  box-shadow: 0 0 10px $primary;
  padding-bottom: 50px;
}

#border3_2 {
  height: 100%;
  margin-left: 15px;
}

#border3_1 {
  height: 101%;
  overflow-y: auto;
  margin-right: 15px;
}

#border3_1, #border3_2 {
  border-radius: 50px;
  transform: translate(0, 0);
  transition: all 0.3s ease-in-out;
  box-shadow: 0 0 40px $primary;
  border: 2px solid $primary;
}

#border3_1:hover, #border3_2:hover {
  transform: translate(-2px, -8px);
  transition: all 0.3s ease-in-out;
  box-shadow: 12px 20px 20px $primary;
}

.pout {
  width: 80%;
}

//#border3_1::-webkit-scrollbar { /*滚动条整体*/
//  width: 10px;
//}
//
//#border3_1::-webkit-scrollbar-track { /*滚动条轨道*/
//  /*background:#999;*/
//  background: #ffffff;
//  border-radius: 20px;
//  margin-top: 40px;
//  margin-bottom: 40px;
//}
//
//#border3_1::-webkit-scrollbar-thumb { /*滚动条里面的滑块*/
//  background: $primary;
//  border-radius: 10px;
//}

/*#border3_1::-webkit-scrollbar-thumb:hover{!*滚动条鼠标事件，鼠标放上去出现的事件*!*/
/*  background:#ffffff;*/
/*}*/
//#border3_1::-webkit-scrollbar-corner { /*滚动条边角*/
//  background: $primary;
//}

.title_3 {
  font-weight: bold;
  color: $primary;
  font-size: 40px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 20px;
  padding-bottom: 40px;
  /*float: left;*/
  /*margin-left: 300px;*/
  /*position: relative;*/
}

/*.el-input__inner{*/
/*  transition: 0.2s ease-in-out;*/
/*}*/
.flo {
  position: relative;
  float: left;
}

.juzhong {
  margin-left: 100px;
  margin-right: 100px;
  width: 81% !important;
}

.search_icon5 {
  margin-right: 10px;
  margin-top: 10px;
  color: $primary;
}

.table1::-webkit-scrollbar {
  width: 0 !important;
}

#border3_1::-webkit-scrollbar {
  width: 0 !important;
}

.el-table__body-wrapper::-webkit-scrollbar { /*滚动条整体*/
  width: 10px !important;
}

.el-table__body-wrapper::-webkit-scrollbar-track { /*滚动条轨道*/
  background: #ffffff;
  border-radius: 20px !important;
  //margin-top: 30px;
  margin-bottom: 30px;

}

.el-table__body-wrapper::-webkit-scrollbar-thumb { /*滚动条里面的滑块*/
  background: $primary !important;
  border-radius: 10px !important;
}

.el-table__body-wrapper::-webkit-scrollbar-corner { /*滚动条边角*/
  background: $primary;
}

.el-tag + .el-tag {
  margin-left: 10px;
  margin-bottom: 5px;
}
</style>
