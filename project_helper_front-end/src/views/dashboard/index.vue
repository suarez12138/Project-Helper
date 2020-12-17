<template>
  <div v-if="checkPermission(['student'])" class="components-container">
    <split-pane split="vertical" @resize="resize">
      <template slot="paneL">
        <div class="border1">
          <div class="home_title">Projects</div>

          <!--eslint-disable-next-line-->
          <a v-for="project in projects" @click="miao(project.project_name, project.project_id)" href="/#/overview/overview">
            <div class="projectmenu">
              <div class="coursename">{{ project.course }}</div>
              <br>
              <div class="projectname">{{ project.project_name }}</div>
            </div>
          </a>
          <a href="/#/join/join">
            <div class="addprojectmenu">
              <div class="photo" />
            </div>
          </a>
        </div>
      </template>
      <template slot="paneR">
        <div class="border2">
          <div class="home_title">Announcements</div>
          <!--eslint-disable-next-line-->
          <div v-for="announcement in announcements" class="announcetitle">
            <a href="/#/announcement/announcement">
              Title: {{ announcement.name }} <br> Project: {{ announcement.project }}<br>By:{{
                announcement.by
              }}<br>{{ announcement.time }}

            </a>
          </div>
          <div>
            <!--            <el-table-->
            <!--              :data="tableData"-->
            <!--              class="an-table"-->
            <!--              height="250"-->
            <!--              border-->
            <!--              style="width: 100%">-->
            <!--              <el-table-column-->
            <!--                prop="date"-->
            <!--                label="日期"-->
            <!--                width="180">-->
            <!--              </el-table-column>-->
            <!--              <el-table-column-->
            <!--                prop="name"-->
            <!--                label="姓名"-->
            <!--                width="180">-->
            <!--              </el-table-column>-->
            <!--              <el-table-column-->
            <!--                prop="address"-->
            <!--                label="地址">-->
            <!--              </el-table-column>-->
            <!--            </el-table>-->
          </div>
        </div>
        <div class="border3">
          <el-calendar v-model="value" class="calen" />
        </div>
      </template>
    </split-pane>
  </div>
  <div v-else-if="checkPermission(['teacher'])" class="components-container">
    <split-pane split="vertical" @resize="resize">
      <template slot="paneL">
        <div class="border1">
          <div class="home_title">Projects</div>
          <!--eslint-disable-next-line-->
          <a v-for="project in projects" @click="miao(project.project_name, project.project_id)" href="/#/overview/overview">
            <div class="projectmenu">
              <div class="coursename">{{ project.course }}</div>
              <br>
              <div class="projectname">{{ project.project_name }}</div>
            </div>
          </a>
          <a href="/#/createProject/createProject">
            <div class="addprojectmenu">
              <div class="photo" />
            </div>
          </a>
        </div>
      </template>
      <template slot="paneR">
        <a href="/#/createProject/createProject">
          <div class="border2">
            <div class="center">Create a new Project</div>
          </div>
        </a>
        <div class="border3">
          <el-calendar v-model="value" class="calen" />
        </div>
      </template>
    </split-pane>
  </div>
  <div v-else-if="checkPermission(['controller'])" class="components-container">
    <!--    <el-menu-->
    <!--      :default-active="activeIndex2"-->
    <!--      class="el-menu-demo"-->
    <!--      mode="horizontal"-->
    <!--      @select="handleSelect"-->
    <!--      text-color="#fff"-->
    <!--      active-text-color="#ffd04b">-->
    <!--      <el-menu-item index="1">处理中心</el-menu-item>-->
    <!--      <el-submenu index="2">-->
    <!--        <template slot="title">我的工作台</template>-->
    <!--      </el-submenu>-->
    <!--      <el-menu-item index="3" >消息中心</el-menu-item>-->
    <!--      <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item>-->
    <!--    </el-menu>-->
    <el-tabs type="border-card">
      <el-tab-pane label="User Creating">
        <el-form
          ref="ruleForm"
          :model="create_Form"
          status-icon
          label-width="120px"
          class="demo-ruleForm"
          style="margin-top: 50px;"
        >
          <el-form-item label="用户名" prop="name" required>
            <el-input v-model="create_Form.name" style="width: 30%" />
          </el-form-item>
          <el-form-item label="真名" prop="name" required>
            <el-input v-model="create_Form.truename" style="width: 30%" />
          </el-form-item>
          <el-form-item label="初始密码" prop="name" required>
            <el-input v-model="create_Form.password" style="width: 30%" />
          </el-form-item>

          <el-form-item label="权限" prop="privilege" required>
            <el-select v-model="create_Form.role" placeholder="请选择相应权限">
              <el-option label="teacher" value="teacher" />
              <el-option label="student" value="student" />
              <el-option label="admin" value="controller" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" plain @click="submitForm1('ruleForm')">确认生成</el-button>
            <el-button plain @click="resetForm1('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
        <el-divider />
        <div class="pi">批量生成:</div>
        <upload-excel-component :on-success="handleSuccess1" :before-upload="beforeUpload1" />
      </el-tab-pane>
      <el-tab-pane label="User Privilege">

        <el-form
          ref="ruleForm"
          :model="Privilege_Form"
          status-icon
          label-width="120px"
          class="demo-ruleForm"
          style="margin-top: 50px;"
        >
          <el-form-item label="用户名" prop="name" required>
            <el-input v-model="Privilege_Form.name" style="width: 30%" />
          </el-form-item>

          <el-form-item label="权限" prop="privilege" required>
            <el-select v-model="Privilege_Form.role" placeholder="请选择相应权限">
              <el-option label="teacher" value="teacher" />
              <el-option label="student" value="student" />
              <el-option label="admin" value="controller" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" plain @click="submitForm2('ruleForm')">确认分配</el-button>
            <el-button plain @click="resetForm2('ruleForm')">重置</el-button>
          </el-form-item>

        </el-form>
        <el-divider />
        <div class="pi">批量调整:</div>

        <upload-excel-component :on-success="handleSuccess2" :before-upload="beforeUpload2" />
      </el-tab-pane>
      <el-tab-pane label="Course Assignment">
        <el-form
          :model="course_Form"
          status-icon
          label-width="15%"
          class="demo-ruleForm"
          style="margin-top: 50px;"
        >
          <el-form-item label="课程名称" prop="name" required>
            <el-input v-model="course_Form.name" style="width: 30%" />
          </el-form-item>
          <div class="search-Box" style=" margin-left: 15%;width: 30%; ">
            <svg-icon icon-class="search" class="search_icon" />
            <el-input v-model="search" placeholder="请输入关键字" class="search" />
          </div>
          <el-table
            ref="filterTable"
            :data="tableData33"
            style="width: 80%;margin-left: 10%;"
            @selection-change="handleSelectionChange"
          >
            <el-table-column
              align="center"
              prop="select"
              type="selection"
              width="55"
            />
            <el-table-column
              align="center"
              prop="ID"
              label="ID"
              sortable
            />

            <el-table-column
              align="center"
              prop="truename"
              label="姓名"
              sortable
            />
            <el-table-column
              align="center"
              prop="role"
              label="角色"
              sortable
            />

          </el-table>

          <el-form-item style="margin-top: 50px;">
            <el-button type="primary" plain @click="submitForm3('ruleForm')">确认加入</el-button>
          </el-form-item>
        </el-form>

        <el-divider />
        <div class="pi">批量加入:</div>

        <upload-excel-component :on-success="handleSuccess3" :before-upload="beforeUpload3" />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import splitPane from 'vue-splitpane'
import permission from '@/directive/permission/index.js' // 权限判断指令
import checkPermission from '@/utils/permission' // 权限判断函数
import { fetchMyProjectList_student } from '@/api/student/dashboard'
import { fetchMyProjectList_teacher } from '@/api/teacher/dashboard'
import { fetchMyAnnouncementList } from '@/api/student/dashboard'
import { fetchAllProject } from '@/api/student/group'
import { getToken } from '@/utils/auth'
import UploadExcelComponent from '@/components/UploadExcel/index'
import store from '@/store'

const STORAGE_KEY = 'current_project_id'
export default {
  name: 'SplitpaneDemo',
  components: { splitPane, UploadExcelComponent },
  directives: { permission },
  data() {
    return {
      search: '',
      create_Form: {
        name: '',
        truename: '',
        password: '',
        privilege: ''
      },
      Privilege_Form: {
        name: '',
        privilege: ''
      },
      course_Form: {
        name: ''
      },
      tableData33: [{
        ID: '11812925',
        truename: '黄子健',
        role: 'student'
      }],
      tableData1: [],
      tableData2: [],
      tableHeader1: [],
      tableHeader2: [],
      projects:
        null, // [
      //   { course: 'OOAD', p_name: 'Project Helper' },
      //   { course: 'AI', p_name: 'Reversi' },
      //   { course: 'AI', p_name: 'Reversi' },
      //   { course: 'Computer Network', p_name: 'RDT' },
      //   { course: 'AI', p_name: 'IMP' },
      //   { course: 'AI', p_name: 'Reversi' },
      //   { course: 'AI', p_name: 'Reversi' },
      //   { course: 'AI', p_name: 'Reversi' },
      //   { course: 'AI', p_name: 'IMP' }
      // ]

      announcements:
        null, // [
      //   { name: 'check your progress', project: 'project3', by: 'teacher A', time: '2020.11.21' },
      //   { name: 'tips', project: 'project4', by: 'teacher B', time: '2020.11.12' },
      //   { name: 'announce DDL', project: 'project1', by: 'teacher A', time: '2020.10.11' },
      //   { name: 'announce DDL', project: 'project1', by: 'teacher A', time: '2020.10.11' },
      //   { name: 'announce DDL', project: 'project1', by: 'teacher A', time: '2020.10.11' },
      //   { name: 'announce DDL', project: 'project1', by: 'teacher A', time: '2020.10.11' },
      //   { name: 'announce DDL', project: 'project1', by: 'teacher A', time: '2020.10.11' },
      //   { name: 'announce DDL', project: 'project1', by: 'teacher A', time: '2020.10.11' }
      // ]

      project_dict: {},
      value: new Date()
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
    this.getMyProjects()
    this.getMyAnnouncement()
    this.getAllProject()
  },
  methods: {
    miao(name, id) {
      window.localStorage.setItem(STORAGE_KEY, JSON.stringify(this.project_dict[name]))
      window.localStorage.setItem('current_project', JSON.stringify(name))
      // alert(id)
      window.localStorage.setItem('current_project_id', id)
      window.dispatchEvent(new Event('setItemEvent'))
      // alert(localStorage.getItem("current_project_id"))
      // console.log(localStorage.getItem("current_project"))
      // console.log(this.project_dict)
      // console.log(this.project_dict[name])
      // console.log(localStorage)
    },
    getMyProjects() {
      // alert(store.getters.roles);
      if (store.getters.roles == 'teacher') {
        // alert("miao");
        fetchMyProjectList_teacher(getToken()).then(response => {
          this.projects = response.data
        })
      } else if (store.getters.roles == 'student') {
        fetchMyProjectList_student(getToken()).then(response => {
          this.projects = response.data
        })
      }
    },
    getMyAnnouncement() {
      fetchMyAnnouncementList(getToken()).then(response => {
        this.announcements = response.data
      })
    },
    beforeUpload1(file) {
      const isLt1M = file.size / 1024 / 1024 < 1
      if (isLt1M) {
        return true
      }
      this.$message({
        message: 'Please do not upload files larger than 1m in size.',
        type: 'warning'
      })
      return false
    },
    handleSuccess1({ results, header }) {
      this.tableData1 = results
      this.tableHeader1 = ['name', 'truename', 'password', 'role']
    },
    beforeUpload2(file) {
      const isLt1M = file.size / 1024 / 1024 < 1
      if (isLt1M) {
        return true
      }
      this.$message({
        message: 'Please do not upload files larger than 1m in size.',
        type: 'warning'
      })
      return false
    },
    handleSuccess3({ results, header }) {
      this.tableData2 = results
      this.tableHeader2 = ['id']
    }, beforeUpload3(file) {
      const isLt1M = file.size / 1024 / 1024 < 1
      if (isLt1M) {
        return true
      }
      this.$message({
        message: 'Please do not upload files larger than 1m in size.',
        type: 'warning'
      })
      return false
    },
    handleSuccess2({ results, header }) {
      this.tableData2 = results
      this.tableHeader2 = ['name', 'role']
    },
    resetForm1(formName) {
      this.$refs[formName].resetFields()
    },
    submitForm1(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm2(formName) {
      this.$refs[formName].resetFields()
    },
    submitForm2(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    submitForm3() {
      if (this.multipleSelection == null) {
        this.$alert('请选择想加入的成员！', '加入失败', {
          confirmButtonText: '确定'
        })
        return
      }
      // 处理加入
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
      console.log(this.multipleSelection)
    },
    checkPermission,
    resize() {
      console.log('resize')
    },
    getAllProject() {
      fetchAllProject().then(response => {
        // this.project_dict
        // alert(response.data.length)
        for (var i = 0; i < response.data.length; i++) {
          this.project_dict[response.data[i].project] = response.data[i].project_id
          // alert(response.data[i].project)
          // alert(this.project_dict[response.data[i].project])
        }
      })
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

.el-menu-demo {
  background-color: $primary;
}

.search_icon {
  float: left;
  margin-right: 10px;
  margin-top: 10px;
  color: $primary;
}

.search {
  float: left;
  margin-right: 10%;
  width: 80% !important;
}

.search > .el-input__inner {
  border-radius: 30px;
}

.border1, .border2, .border3 {
  width: 100%;
  border-radius: 50px;
  transition: all 0.3s ease-in-out;
  transform: translate(0, 0);
  overflow-y: auto;
}

.border2,.border3{
  width: 95%;
  float: right;
}

.border1 {
  height: 100%;
  box-shadow: 0 0 40px #151516;
  background: linear-gradient(to bottom, #203025 0%, #151516 100%);
}

.border2 {
  height: 49%;
  box-shadow: 0 0 40px #c52d47;
  background: linear-gradient(215deg, #c52d47 0%, #a5325c 30%, #8b366d 100%);
  margin-bottom: 20px;
}

.border3 {
  height: 49%;
  box-shadow: 0 0 40px #1623A6;
  background: linear-gradient(225deg, #1623A6 0%, #3343D4 29%, #175EAC 73%, #176682 100%);
}

.projectname {
  color: #cccccc;
  font-size: 25px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 20px;
}

.coursename {
  color: #cccccc;
  font-size: 25px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 30px;
}

.border1:hover, .border2:hover, .border3:hover {
  transform: translate(-2px, -8px);
  transition: all 0.3s ease-in-out;
}

.border1:hover {
  box-shadow: 12px 20px 20px #151516;
}

.border2:hover {
  box-shadow: 12px 20px 20px #c52d47;
}

.border3:hover {
  box-shadow: 12px 20px 20px #1623A6;
}

/*.projectmenu ::after {*/
/*-webkit-filter: blur(0px);*/
/*-moz-filter: blur(0px);*/
/*-ms-filter: blur(0px);*/
/*-o-filter: blur(0px);*/
/*filter: blur(0px);*/
/*  z-index: -1;*/
/*  -webkit-filter: blur(5px);*/
/*  -moz-filter: blur(5px);*/
/*  -ms-filter: blur(5px);*/
/*  -o-filter: blur(5px);*/
/*  filter: blur(5px) !important;*/
/*}*/

.projectmenu:hover, .addprojectmenu:hover {
  box-shadow: 12px 20px 20px #000000;
  background-color: #cccccc;
  border: #ffffff;
  transform: translate(-2px, -8px);
  transition: 0.2s ease-in-out;
}

.pi {
  color: $primary;
  font-size: 20px;
  margin-top: 30px;
  margin-left: 230px;
}

.center {
  color: #ffffff;
  font-size: 55px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 182px;
}

div {
  display: block;
}

.projectmenu:hover .projectname, .projectmenu:hover .coursename {
  color: #203025;
  transition: 0.2s ease-in-out;
  filter: none;
}

.calen {
  border-radius: 50px;
  background: linear-gradient(225deg, #1623A6 0%, #3343D4 29%, #175EAC 73%, #176682 100%);
  height: 100%;
  /*height: 100%;*/
}

/*.el-table, .el-table__header-wrapper {*/
/*  background-color: #ff7300;*/
/*  border-color: #ff7300;*/
/*}*/

/*#border2 > div {*/
/*  margin: 0;*/
/*}*/

/*.el-calendar__body,.el-calendar-table,tbody {*/
/*  height: 100%;*/
/*}*/

/*.el-calendar-table:not(.is-range) td.prev, .el-calendar-table:not(.is-range) td.next{*/
/*  height: 10%;*/
/*}*/

/*.el-calendar-table__row{*/
/*  height: 10% !important;*/
/*}*/

.el-calendar-table .el-calendar-day {
  height: 15%;
}

.projectmenu, .addprojectmenu {
  background-color: #203025;
  margin: 10%;
  height: 20%;
  width: 30%;
  border-radius: 50px;
  box-shadow: 10px 10px 20px #000000;
  float: left;
  transition: 0.3s ease-in-out;
  margin-bottom: 0;
}

.addprojectmenu {
  /*filter: blur(1px);*/
  background-color: rgba(0, 0, 0, 0.1);
}

.photo {
  background: url("add.png") no-repeat;
  margin-top: 23px;
  margin-left: 45px;
  width: 100%;
  height: 100%;
}

.border1::-webkit-scrollbar, .border2::-webkit-scrollbar { /*滚动条整体*/
  width: 10px;
}

.border1::-webkit-scrollbar-track, .border2::-webkit-scrollbar-track { /*滚动条轨道*/
  background: #999;
  border-radius: 20px;
  margin-top: 40px;
  margin-bottom: 40px;
}

.border1::-webkit-scrollbar-thumb {
  background: #151516;

}

.border2::-webkit-scrollbar-thumb {
  background: #c52d47;
}

.border1::-webkit-scrollbar-thumb, .border2::-webkit-scrollbar-thumb { /*滚动条里面的滑块*/
  border-radius: 10px;
}

.announcetitle, .home_title {
  color: #dddddd;
  font-size: 20px;
  transition: 0.2s ease-in-out;
}

.announcetitle {
  text-align: left;
  margin-left: 30%;
  padding-top: 10px;
}

.home_title {
  padding-top: 30px;
  font-size: 40px;
  color: #ffffff;
  text-align: center;
}

/*.hasTagsView .app-main[data-v-078753dd]{*/
/*  background-color: #000000;*/
/*  color: #000000;*/
/*}*/

/*#app .main-container{*/
/*  background-color: #000000;*/
/*}*/

</style>
