<template>
  <div v-if="checkPermission(['student'])" class="components-container">
    <split-pane split="vertical" @resize="resize">
      <template slot="paneL">
        <div id="border2_1">
          <div class="title1">Announcements</div>
          <!--          <ol class="title">-->
          <!--eslint-disable-next-line-->
          <div v-for="an in announcements" @click="miao(an.ann_id)" class="announcetitle">
            <a href="/#/announcements/Content">
              Title: {{ an.ann_name }} <br> By:{{ an.teacher_name }}<br>{{ an.release_time }}
            </a>
          </div>
          <!--          </ol>-->
        </div>
      </template>
      <template slot="paneR">
        <a href="/#/studentMyGroup/studentMyGroup">
          <div id="border2_2">
            <div class="title2">My Group</div>
          </div>
        </a>
        <a href="/#/studentGroup/studentGroup">
          <div id="border2_3">
            <div class="title2">Grouping Information</div>
          </div>
        </a>
        <a href="/#/personal/personal">
          <div id="border2_4">
            <div class="title2">Personal Information</div>
          </div>
        </a>
      </template>
    </split-pane>
  </div>
  <div v-else-if="checkPermission(['teacher'])" class="components-container">
    <split-pane split="vertical" @resize="resize">
      <template slot="paneL">
        <a href="/#/createProject/createProject">
          <div id="t_border2_1">
            <div class="juzhong2">Create New Project</div>
          </div>
        </a>
        <a href="/#/announcements/announcements">
          <div id="t_border2_2">
            <div class="juzhong2">Announcements</div>
          </div>
        </a>
        <a href="/#/email/email">
          <div id="t_border2_3">
            <div class="juzhong2">Email</div>
          </div>
        </a>
        <a href="/#/submission/submission1">
          <div id="t_border2_4">
            <div class="juzhong2">Submission information</div>
          </div>
        </a>
      </template>
      <template slot="paneR">
        <a href="/#/group/groupInformation">
          <div id="t_border2_5">
            <div class="juzhong2">Grouping</div>
          </div>
        </a>
        <a href="/#/teacher_presentation/presentation">
          <div id="t_border2_6">
            <div class="juzhong2">Presentation</div>
          </div>
        </a>
        <a href="/#/grades/grading">
          <div id="t_border2_7">
            <div class="juzhong2">Grades</div>
          </div>
        </a>
        <a href="/#/authority/authority">
          <div id="t_border2_8">
            <div class="juzhong2">Authority</div>
          </div>
        </a>
      </template>
    </split-pane>
  </div>
</template>

<script>
// import elDragDialog from '@/directive/el-drag-dialog' // base on element-ui

import splitPane from 'vue-splitpane'
import checkPermission from '@/utils/permission'
import { getToken } from '@/utils/auth'
// import { get_announcement_teacher } from '@/api/teacher/announcement/'
import { fetchMyAnnouncementList_student } from '@/api/student/dashboard'

export default {
  name: 'DragDialogDemo',
  // directives: { elDragDialog },
  components: { splitPane },
  data() {
    return {
      dialogTableVisible: false,
      announcements:
      // [
      // { name: 'check your progress', project: 'project3', by: 'teacher A', time: '2020.11.21' }
      // ]
      null
    }
  },

  created() {
    this.get_announcementList()
  },

  methods: {
    miao(data) {
      // alert(data)
      window.localStorage.setItem('current_announcement', JSON.stringify(data))
    },
    get_announcementList() {
      fetchMyAnnouncementList_student(getToken()).then(response => {
        this.announcements = response.data
      })
    },
    checkPermission,
    // v-el-drag-dialog onDrag callback function
    handleDrag() {
      this.$refs.select.blur()
    },
    resize() {
      console.log('resize')
    }
  }
}
</script>

<style>

.components-container {
  position: relative;
  height: 100vh;
}

#border2_1 {
  height: 100%;
  box-shadow: 0 0 40px #257983;
  background: linear-gradient(to right top, #604C7C 0%, #257983 50%, #20825A 100%);
  overflow-y: auto;
}

#border2_1::-webkit-scrollbar { /*滚动条整体*/
  width: 10px;
}

#border2_1::-webkit-scrollbar-track { /*滚动条轨道*/
  background: #ffffff;
  border-radius: 20px;
  margin-top: 40px;
  margin-bottom: 40px;
}

#border2_1::-webkit-scrollbar-thumb {
  background: #257983;
  border-radius: 10px;
}

#border2_2 {
  height: 31%;
  box-shadow: 0 0 40px #2d5d85;
  background: linear-gradient(135deg, #1f224b 0%, #2d5d85 100%);
  margin-bottom: 20px;
}

#border2_3 {
  height: 31%;
  box-shadow: 0 0 40px #673dc2;
  background: linear-gradient(135deg, #c6368a 0%, #673dc2 100%);
  margin-bottom: 20px;
}

#border2_4 {
  height: 31%;
  box-shadow: 0 0 40px #10071c;
  background: linear-gradient(135deg, #590a0d 0%, #10071c 100%);
}

.title2, .title1 {
  color: #cccccc;
  font-size: 40px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 15px;
  margin-bottom: 40px;
}

.title2 {
  padding-top: 100px;
}

.announcetitle {
  color: #dddddd;
  font-size: 20px;
  transition: 0.2s ease-in-out;
  text-align: left;
  margin-top: 30px;
  margin-left: 200px;
}

#border2_1, #border2_2, #border2_3, #border2_4 {
  width: 98%;
  border-radius: 50px;
  transition: all 0.3s ease-in-out;
  transform: translate(0, 0);
}
#border2_2, #border2_3, #border2_4{
  float: right;
}

#border2_1:hover .title1, #border2_1:hover .announcetitle, #border2_2:hover .title2, #border2_3:hover .title2, #border2_4:hover .title2 {
  color: #ffffff;

}

#border2_1:hover, #border2_2:hover, #border2_3:hover, #border2_4:hover {
  transform: translate(-2px, -8px);
  transition: all 0.3s ease-in-out;
}

#border2_1:hover {
  box-shadow: 12px 20px 20px #257983;
}

#border2_2:hover {
  box-shadow: 12px 20px 20px #2d5d85;
}

#border2_3:hover {
  box-shadow: 12px 20px 20px #673dc2;
}

#border2_4:hover {
  box-shadow: 12px 20px 20px #10071c;
}

#t_border2_1, #t_border2_2, #t_border2_3, #t_border2_4, #t_border2_5, #t_border2_6, #t_border2_7, #t_border2_8 {
  height: 23%;
  margin-bottom: 20px;
  width: 96%;
  border-radius: 80px;
  transition: all 0.3s ease-in-out;
  transform: translate(0, 0);
}

#t_border2_1 {
  box-shadow: 0 0 40px #257983;
  background: linear-gradient(to right top, #604C7C 0%, #257983 50%, #20825A 100%);
}

#t_border2_3 {
  box-shadow: 0 0 40px #2d5d85;
  background: linear-gradient(135deg, #1f224b 0%, #2d5d85 100%);
}

#t_border2_2 {
  box-shadow: 0 0 40px #673dc2;
  background: linear-gradient(135deg, #c6368a 0%, #673dc2 100%);
}

#t_border2_4 {
  box-shadow: 0 0 40px #10071c;
  background: linear-gradient(135deg, #590a0d 0%, #10071c 100%);
}

#t_border2_5 {
  box-shadow: 0 0 40px #1623A6;
  background: linear-gradient(225deg, #1623A6 0%, #3343D4 29%, #175EAC 73%, #176682 100%);
  margin-left: 30px;
}

#t_border2_6 {
  box-shadow: 0 0 40px #151516;
  background: linear-gradient(to bottom, #203025 0%, #151516 100%);
  margin-left: 30px;
}

#t_border2_7 {
  box-shadow: 0 0 40px #c52d47;
  background: linear-gradient(215deg, #c52d47 0%, #a5325c 30%, #8b366d 100%);
  margin-left: 30px;
}

#t_border2_8 {
  box-shadow: 0 0 40px #3B4980;
  background: linear-gradient(to right top, #194189 0%, #3B4980 25%, #8738A7 100%);
  margin-left: 30px;
}

.juzhong2 {
  color: #cccccc;
  font-size: 50px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 65px;
  margin-bottom: 40px;
}

#t_border2_1:hover .juzhong2, #t_border2_1:hover, #t_border2_2:hover .juzhong2, #t_border2_3:hover .juzhong2, #t_border2_4:hover .juzhong2, #t_border2_5:hover .juzhong2, #t_border2_6:hover .juzhong2, #t_border2_7:hover .juzhong2, #t_border2_8:hover .juzhong2 {
  color: #ffffff;

}

#t_border2_1:hover, #t_border2_2:hover, #t_border2_3:hover, #t_border2_4:hover, #t_border2_5:hover, #t_border2_6:hover, #t_border2_7:hover, #t_border2_8:hover {
  transform: translate(-2px, -8px);
  transition: all 0.3s ease-in-out;
}

#t_border2_1:hover {
  box-shadow: 12px 20px 20px #257983;
}

#t_border2_3:hover {
  box-shadow: 12px 20px 20px #2d5d85;
}

#t_border2_2:hover {
  box-shadow: 12px 20px 20px #673dc2;
}

#t_border2_4:hover {
  box-shadow: 12px 20px 20px #10071c;
}

#t_border2_5:hover {
  box-shadow: 12px 20px 20px #1623A6;
}

#t_border2_6:hover {
  box-shadow: 12px 20px 20px #151516;
}

#t_border2_7:hover {
  box-shadow: 12px 20px 20px #c52d47;
}

#t_border2_8:hover {
  box-shadow: 12px 20px 20px #3B4980;
}

</style>
