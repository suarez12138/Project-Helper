<template>
  <div v-if="checkPermission(['student'])" class="components-container">
    <split-pane split="vertical" @resize="resize">
      <template slot="paneL">
        <div class="border1">
          <div class="title">Projects</div>
          <a href="/#/overview/overview">
            <div class="projectmenu">
              <div class="projectname">course: OOAD</div>
              <br>
              <div class="projectname">project: Project Helper</div>
            </div>
          </a>
          <div class="projectmenu" />
          <div class="projectmenu" />
          <div class="projectmenu" />
        </div>
      </template>
      <template slot="paneR">
        <div class="border2">
          <div class="title">Announcements</div>
          <!--eslint-disable-next-line-->
          <div v-for="site in announcements" class="announcetitle">
            Title: {{ site.name }} <br> Project: {{ site.project }}
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
          <div class="title">Projects</div>
          <a href="/#/overview/overview">
            <div class="projectmenu">
              <div class="projectname">course: OOAD</div>
              <br>
              <div class="projectname">project: Project Helper</div>
            </div>
          </a>
          <div class="projectmenu" />
          <div class="projectmenu" />
          <div class="projectmenu" />
        </div>
      </template>
      <template slot="paneR">
        <div class="border2">
          <div class="center">Create a new group</div>
        </div>
        <div class="border3">
          <el-calendar v-model="value" class="calen" />
        </div>
      </template>
    </split-pane>
  </div>
</template>

<script>
import splitPane from 'vue-splitpane'
import permission from '@/directive/permission/index.js' // 权限判断指令
import checkPermission from '@/utils/permission' // 权限判断函数

export default {
  name: 'SplitpaneDemo',
  components: { splitPane },
  directives: { permission },
  data() {
    return {
      announcements: [
        { name: 'check your progress', project: 'project3' },
        { name: 'tips', project: 'project4' },
        { name: 'announce DDL', project: 'project1' }
      ],
      value: new Date()
    }
  },
  methods: {
    checkPermission,
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

.left-container {
  background-color: #F38181;
  height: 100%;
}

.right-container {
  background-color: #FCE38A;
  height: 200px;
}

.top-container {
  background-color: #FCE38A;
  width: 100%;
  height: 100%;
}

.bottom-container {
  width: 100%;
  background-color: #95E1D3;
  height: 100%;
}

.border1, .border2, .border3 {
  width: 100%;
  border-radius: 50px;
  transition: all 0.3s ease-in-out;
  transform: translate(0, 0);
}

.border1 {
  height: 100%;
  box-shadow: 0 0 40px #151516;
  background: linear-gradient(to bottom, #203025 0%, #151516 100%)
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
  font-size: 30px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 15px;
}

.border1:hover, .border2:hover, .border3:hover  {
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

.projectmenu:hover {
  box-shadow: 12px 20px 20px #000000;
  background-color: #cccccc;
  border: #ffffff;
  transform: translate(-2px, -8px);
  transition: 0.2s ease-in-out;
}
.center{
  color: #ffffff;
  font-size: 55px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 182px;
}

div {
  display: block;
}

.projectmenu:hover .projectname {
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

.projectmenu {
  /*border: 2px solid #2CAD6F;*/
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

.announcetitle, .title {
  color: #dddddd;
  font-size: 20px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 30px;
}

.title {
  font-size: 40px;
  color: #ffffff;
}

/*.hasTagsView .app-main[data-v-078753dd]{*/
/*  background-color: #000000;*/
/*  color: #000000;*/
/*}*/

/*#app .main-container{*/
/*  background-color: #000000;*/
/*}*/

</style>
