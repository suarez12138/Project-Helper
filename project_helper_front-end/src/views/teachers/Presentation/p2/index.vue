<template>
  <div class="components-container">
    <div class="t_border3_1">
      <div class="personal_title"> Presentation Registration</div>
      <el-tabs style="height: 200px;">
        <!--eslint-disable-next-line-->
        <el-tab-pane v-for="index in timeNumber" :label="'时间段'+index">
          <div class="mid">{{ list[index-1].date }}</div>
          <div class="mid">{{ list[index-1].remark }}</div>
          <el-table
            v-loading="listLoading"
            width="650"
            :data="list[index-1].form"
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
  </div>
</template>

<script>
export default {
  data() {
    return {
      timeNumber: 4, // 这里传入时必须转换为数字
      maxGroup: 20,
      list: [
        { remark: 'Lab1上课时间', date: '2020-12-27', form: [{ time: '16:20-16:30', groupname: '' }, { time: '16:30-16:40', groupname: '' }] },
        { remark: 'Lab2上课时间', date: '2020-12-28', form: [{ time: '15:20-15:30', groupname: '' }, { time: '15:30-15:40', groupname: '' }] },
        { remark: 'Lab3上课时间', date: '2020-12-29', form: [{ time: '16:20-16:30', groupname: '' }, { time: '16:30-16:40', groupname: '' }] },
        { remark: 'Lab4上课时间', date: '2020-12-29', form: [{ time: '16:20-16:30', groupname: '' }, { time: '16:30-16:40', groupname: '' }] }
      ],

      search: ''
    }
  },
  methods: {
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
</style>
