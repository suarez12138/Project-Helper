<template>
  <div class="components-container">
    <div class="border_great">
      <div class="personal_title"> Personal information</div>
      <el-button type="text" style="float: left;margin-left: 50px;" size="large" @click="handleClick()"><i
        class="el-icon-circle-plus"
      /> 邀请组队
      </el-button>

      <el-button type="text" style="float: left;margin-left: 35%;" @click="dialogFormVisible1 = true">
        <svg-icon icon-class="edit" />
        修改我的信息
      </el-button>
      <div class="search-Box" style=" margin-right: 30px; width: 30%; float: right">
        <el-input v-model="search" placeholder="请输入关键字" class="search" />
        <svg-icon icon-class="search" class="search_icon4" />
      </div>

      <el-dialog title="关于此project" :visible.sync="dialogFormVisible1" width="500px" append-to-body>
        <el-form :model="form">
          <!-- <el-form-item label="你的技能" :label-width="formLabelWidth">
            <el-input v-model="form.skill" autocomplete="off" />
          </el-form-item> -->
          <el-form-item label="你的技能" :label-width="formLabelWidth">
            <el-select v-model="form.skill" multiple placeholder="请选择你的技能">
              <!--            <el-option v-for="(user,i) in skill_form" key = label='i' value='user' />-->
              <el-option
                v-for="item in skill_form"
                :key="item.tag_id"
                :label="item.tag"
                :value="item.tag_id"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="期待队友类型" :label-width="formLabelWidth">
            <el-input v-model="form.expect" autocomplete="off" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible1 = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible1 = false, update_MyInformation_table()">确 定</el-button>
        </div>
      </el-dialog>
      <div>
        <el-table
          ref="filterTable"
          :data="tableData2"
          :height="hei"
          style="width: 100%;overflow-y: auto;"
          class="table1"
          @selection-change="handleSelectionChange"
        >
          <!--        <el-table-column-->
          <!--          type="index"-->
          <!--          :index="index"-->
          <!--        />-->
          <el-table-column
            align="center"
            prop="select"
            type="selection"
            width="55"
          />
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
            prop="skill"
            label="技能"
            sortable
          >
            <template slot-scope="scope">
              <!--eslint-disable-next-line-->
              <el-tag v-for="item in scope.row.skill" effect="dark">{{ item }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            prop="hope"
            label="期待队友类型"
            sortable
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
        </el-table>
      </div>
      <el-dialog title="创建小组" :visible.sync="dialogFormVisible" width="500px" append-to-body>
        <el-form :model="createGroupForm">
          <el-form-item label="小组名称" :label-width="formLabelWidth">
            <el-input v-model="createGroupForm.name" autocomplete="off" />
          </el-form-item>
          <el-form-item label="预期答辩时间" :label-width="formLabelWidth">
            <el-select v-model="createGroupForm.pre_week" placeholder="预期答辩时间">
              <el-option
                v-for="item in options"
                :key="item.checkPoint_id"
                :label="item.week"
                :value="item.checkPoint_id"
              />
            </el-select>
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
  </div>
</template>

<script>
import { get_AllStudents } from '@/api/student/personal'
import { update_MyInformation } from '@/api/student/personal'
import { get_AllTags } from '@/api/student/personal'
import { get_availableWeek } from '@/api/student/creatGroup'
import { create_group } from '@/api/student/creatGroup'
import { getToken } from '@/utils/auth'

export default {
  name: 'DragSelectDemo',
  data() {
    return {
      index: 0,
      hei: window.innerHeight * 0.75,
      createGroupForm: {
        name: '',
        pre_week: '',
        information: ''
      },
      tableData22:
        null, // [{
      //   select: false,
      //   name: '张小虎',
      //   gender: '男',
      //   lab: 2,
      //   skill: 'SPRING BOOT',
      //   hope: '不搞基',
      //   status: '已组队'
      // }]

      form: {
        skill: [],
        expect: ''
      },
      skill_form:
        null, // [
      //   { tag_id: 1, tag: '前端' },
      //   { tag_id: 2, tag: '后端' },
      //   { tag_id: 3, tag: 'miaomiao叫' }
      // ]
      grouping_members: [],
      dialogFormVisible1: false,
      dialogFormVisible: false,
      formLabelWidth: '120px',
      search: '',
      options: [{
        value: 'Week 1',
        label: 'Week 1'
      }]
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
      console.log(this.tableData22)
      return this.tableData22
    }
  },
  created() {
    this.get_AllStudent_table()
    this.get_AllTags_table()
    this.get_preWeek()
  },
  methods: {
    get_AllStudent_table() {
      get_AllStudents(getToken(), localStorage.getItem('current_project_id')).then(response => {
        this.tableData22 = response.data
      })
    },
    get_AllTags_table() {
      get_AllTags(localStorage.getItem('current_project_id')).then(response => {
        this.skill_form = response.data
      })
      // alert(this.skill_form)
    },
    get_preWeek() {
      get_availableWeek(localStorage.getItem('current_project_id')).then(response => {
        this.options = response.data
      })
      // alert(this.options[0].week)
    },
    submitForm(ruleForm, ruleForm2) {
      create_group({
        project_id: localStorage.getItem('current_project_id'),
        group_name: this.createGroupForm.name,
        check_point_id: this.createGroupForm.pre_week,
        text: this.createGroupForm.information,
        person_id: this.grouping_members,
        self_id: getToken()
      }).then(response => {
        this.dialogFormVisible = false
        if (response.message != 'Success!') {
          this.$message.error(response.message)
        } else {
          this.$message({
            message: '组队成功！',
            type: 'success'
          })
        }
      })
    },
    update_MyInformation_table() {
      // alert(this.form.skill)
      // alert(this.form.expect)
      update_MyInformation({
        token: getToken(),
        project_id: localStorage.getItem('current_project_id'),
        skill: this.form.skill,
        expect: this.form.expect
      }).then(response => {
        // update_MyInformation({project_id: "miao", skill: 'sdsd', expect: "miao"}).then(response => {
        // alert("miao")
      })
      this.$message({
        message: '设置成功！',
        type: 'success'
      })
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
      this.$alert('有人已经组队成功啦，尝试邀请其他人吧！', '组队失败', {
        confirmButtonText: '确定'
      })
    },
    handleClick() {
      // console.log(this.multipleSelection)
      console.log(this.skill_form)
      for (var item in this.skill_form) {
        console.log(item)
      }
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
      // alert(this.multipleSelection[0].id)
      console.log(this.multipleSelection)
      this.grouping_members = []
      var length = this.multipleSelection.length
      for (var i = 0; i < length; i++) {
        this.grouping_members.push(this.multipleSelection[i].id)
      }
    }

    // 重置table高度
    // resetHeight() {
    //   return new Promise((resolve, reject) => {
    //     this.hei = 0
    //     resolve()
    //   })
    // },
    // // 设置table高度
    // fetTableHeight() {
    //   this.resetHeight().then(res => {
    //     this.hei = this.$refs.tableWrapper.getBoundingClientRect().height - 10
    //   })
    // }
  }
}
</script>
<style lang="scss">
@import "~@/styles/variables.scss";

.components-container {
  position: relative;
  height: 100vh;
}

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

.table1::-webkit-scrollbar {
  width: 0;
}

.el-table__body-wrapper::-webkit-scrollbar { /*滚动条整体*/
  width: 10px;
}

.el-table__body-wrapper::-webkit-scrollbar-track { /*滚动条轨道*/
  background: #ffffff;
  border-radius: 20px;
  //margin-top: 30px;
  //margin-bottom: 30px;

}

.el-table__body-wrapper::-webkit-scrollbar-thumb { /*滚动条里面的滑块*/
  background: $primary;
  border-radius: 10px;
}

.el-table__body-wrapper::-webkit-scrollbar-corner { /*滚动条边角*/
  background: $primary;
}

.border_great {
  height: 100%;
  width: 100%;
  border: 2px solid $primary;
  border-radius: 50px;
  transform: translate(0, 0);
  transition: all 0.3s ease-in-out;
  box-shadow: 10px 10px 20px $primary;
  margin-top: 50px;
  padding-left: 20px;
  padding-right: 20px;
}

.border_great:hover {
  box-shadow: 20px 20px 20px $primary;
  transform: translate(-5px, -5px);
  transition: 0.3s ease-in-out;
}

.personal_title {
  font-weight: bold;
  color: $primary;
  font-size: 40px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 20px;
  padding-bottom: 40px;
}

.search_icon4 {
  float: right;
  margin-right: 10px;
  margin-top: 10px;
  color: $primary;
}

.el-tag + .el-tag {
  margin-left: 10px;
  margin-bottom: 5px;
}
</style>
