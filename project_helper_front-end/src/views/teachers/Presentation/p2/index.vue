<template>
  <div class="components-container">
    <div v-if="checkPermission(['teacher'])" class="t_border3_1">
      <div class="personal_title"> Presentation Registration</div>
      <el-tabs style="height: 200px;"  @tab-click="handleClick">
        <!--eslint-disable-next-line-->
        <el-tab-pane v-for="index in timeNumber" :label="'时间段'+index">
          <div class="mid">{{ list[0].date }}</div>
          <div class="mid">{{ list[0].remark }}</div>
          <el-table
            v-loading="listLoading"
            width="650"
            :data="list[0].form"
            element-loading-text="Loading..."
            border
            fit
            highlight-current-row
            style="margin-top: 50px;"
          >
            <el-table-column align="center" label="Time">
              <template slot-scope="scope">
                {{ scope.row.time }}
              </template>
            </el-table-column>

            <el-table-column align="center" prop="groupname" sortable label="Group name">
              <template slot-scope="scope">
                <!--          {{ scope.row.score }}-->
                <!--          <el-input>{{ scope.row.score }}</el-input>-->
                <el-input v-model.trim="scope.row.groupname" />
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </div>
    <div v-else-if="checkPermission(['student'])" class="t_border3_1">
      <div class="personal_title"> Presentation Registration</div>
      <el-tabs style="height: 200px;" @tab-click="handleClick">
        <!-- <div ref="tt"> -->
        <!--eslint-disable-next-line-->
        <el-tab-pane v-for="index in timeNumber"  :label="'时间段'+index" >
          <div class="mid">{{ list[0].remark }}</div>
          <div class="mid">{{ list[0].date }}</div>
          <el-table
            v-loading="listLoading"
            width="650"
            :data="list[0].form"
            element-loading-text="Loading..."
            border
            fit
            highlight-current-row
            style="margin-top: 50px;"
          >
            <el-table-column align="center" label="Time">
              <template slot-scope="scope">
                {{ scope.row.time }}
              </template>
            </el-table-column>

            <el-table-column align="center" prop="groupname" sortable label="Group name">
              <template slot-scope="scope">
                <!--          {{ scope.row.score }}-->
                <!--          <el-input>{{ scope.row.score }}</el-input>-->
                {{ scope.row.groupname }}
              </template>
            </el-table-column>
            <el-table-column align="center" prop="confirm" label="Operation" width="150">
              <template slot-scope="scope">
                <el-button
                  type="success"
                  size="mini"
                  round
                  @click="miao(scope.row.time_id)"
                >选中
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <!-- </div> -->
      </el-tabs>
    </div>
  </div>
</template>

<script>
// import permission from '@/directive/permission/index.js' // 权限判断指令
import checkPermission from '@/utils/permission' // 权限判断函数
import { get_preTimeChunk } from '@/api/student/presentation'
import { get_preTimeTitle } from '@/api/student/presentation'
import { get_preTimeDetail } from '@/api/student/presentation'
import { update_preTime } from '@/api/student/presentation'
import { getToken } from '@/utils/auth'

export default {
  data() {
    return {
      listLoading: false,
      timeNumber: 4, // 这里传入时必须转换为数字
      maxGroup: 20,
      list:
      [
        { remark: 'Lab1上课时间', date: '2020-12-27', form: [{ time: '16:20-16:30', groupname: '' }, { time: '16:30-16:40', groupname: '' }] },
        { remark: 'Lab2上课时间', date: '2020-12-28', form: [{ time: '15:20-15:30', groupname: '' }, { time: '15:30-15:40', groupname: '' }] },
        { remark: 'Lab3上课时间', date: '2020-12-29', form: [{ time: '16:20-16:30', groupname: '' }, { time: '16:30-16:40', groupname: '' }] },
        { remark: 'Lab4上课时间', date: '2020-12-29', form: [{ time: '16:20-16:30', groupname: '' }, { time: '16:30-16:40', groupname: '' }] }
      ],
      preTime_id_list: [],
      preTime_list: [],
      preTime_title_list: [],
      preTime: '',
      preTime_title: '',
      form: [],
      search: '',
      current_time_id: 0
    }
  },
  created() {
    this.getTime()
  },
  methods: {
    handleClick(tab, event) {
      this.current_time_id = this.preTime_id_list[tab.index]
      console.log(this.preTime_id_list[tab.index])
      get_preTimeTitle(localStorage.getItem('current_project_id'), this.preTime_id_list[tab.index]).then(response => {
        this.preTime = response.data[0].start_time
        this.preTime_title = response.data[0].pretime_text
        console.log(this.preTime)
      })
      get_preTimeDetail(localStorage.getItem('current_project_id'), this.preTime_id_list[tab.index]).then(response => {
        this.groupname = response.data[0].group_name
        this.form = []
        for (var i = 0; i < response.data.length; i++) {
          this.form.push({
            time: response.data[i].time_range,
            groupname: response.data[i].group_name,
            time_id: response.data[i].time_id
          })
        }
        console.log(this.list)
        console.log(this.preTime + 'www')
        console.log(this.preTime_title + 'www')
        this.list = []
        this.list = [{ remark: this.preTime_title, date: this.preTime, form: this.form }]
        // console.log(this.list)
      // this.$refs.tt.style.display='true'
      })
    },

    miao(time) {
      update_preTime(getToken(), localStorage.getItem('current_project_id'), this.current_time_id, time).then(response => {
        alert('miao')
      })
    },
    getTime() {
      get_preTimeChunk(localStorage.getItem('current_project_id')).then(response => {
        // alert(response.data.length)
        this.timeNumber = response.data.length
        for (var i = 0; i < this.timeNumber; i++) {
          this.preTime_id_list.push(response.data[i].preTime_id)
          this.preTime_list.push(response.data[i].start_time)
          get_preTimeTitle(localStorage.getItem('current_project_id'), this.preTime_id_list[i]).then(response => {
            this.preTime_title_list.push(response.data.pretime_text)
          })
        }
      })

      // get_preTimeDetail(localStorage.getItem('current_project_id'),1).then(response => {

      // })
      // update_preTime(getToken(), localStorage.getItem('current_project_id'), 1, 1).then(response => {

      // })

      // })
    },
    checkPermission,
    view(index) {
      console.log(index)
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

.t_border3_1::-webkit-scrollbar { /*滚动条整体*/
  width: 10px;
}

.t_border3_1::-webkit-scrollbar-track { /*滚动条轨道*/
  background: #ffffff;
  border-radius: 20px;
  margin-top: 240px;
  margin-bottom: 40px;
}

.t_border3_1::-webkit-scrollbar-thumb { /*滚动条里面的滑块*/
  background: $primary;
  border-radius: 10px;
}

.t_border3_1::-webkit-scrollbar-corner { /*滚动条边角*/
  background: $primary;
}

.t_border3_1 {
  //height: 900px;
  height: 100%;
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
  overflow-y: auto;
}

.t_border3_1:hover {
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
  font-weight: bold;
  color: $primary;
  font-size: 40px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 20px;
  padding-bottom: 40px;
}
.mid{
  color: $primary;
  font-size: 20px;
  text-align: center;
  padding-top: 20px;
}

.t_border3_1::-webkit-scrollbar { /*滚动条整体*/
  width: 10px;
}

.t_border3_1::-webkit-scrollbar-track { /*滚动条轨道*/
  background: #ffffff;
  border-radius: 20px;
  //margin-top: 30px;
  margin-bottom: 30px;

}

.t_border3_1::-webkit-scrollbar-thumb { /*滚动条里面的滑块*/
  background: $primary;
  border-radius: 10px;
}

//.t_border3_1::-webkit-scrollbar-corner { /*滚动条边角*/
//  background: $primary;
//}

</style>
