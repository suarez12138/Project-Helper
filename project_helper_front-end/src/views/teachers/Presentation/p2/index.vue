<template>
  <div class="components-container">
    <div class="t_border3_1_large">
      <div class="personal_title"> Presentation Registration</div>
      <el-tabs style="height: 200px;">
        <!--eslint-disable-next-line-->
        <el-tab-pane v-for="index in timeNumber" :label="'时间段'+index">
          <div>{{ list[index-1].remark }}</div>
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
        { time: 'Thu Dec 24 2020 16:20:00 GMT+0800 (中国标准时间)', remark: 'Lab1上课时间', endTime: '17:10' },
        { time: 'Thu Dec 24 2020 16:20:00 GMT+0800 (中国标准时间)', remark: 'Lab2上课时间', endTime: '17:10' },
        { time: 'Thu Dec 24 2020 16:20:00 GMT+0800 (中国标准时间)', remark: 'Lab3上课时间', endTime: '17:10' },
        { time: 'Thu Dec 24 2020 16:20:00 GMT+0800 (中国标准时间)', remark: 'Lab4上课时间', endTime: '17:10' }
      ],
      tableData22: [{
        name: '张小虎',
        SID: '11812100',
        gender: '男',
        lab: 2,
        skill: 'SPRING BOOT',
        hope: '不搞基',
        status: '已组队'
      }, {
        name: '王小虎',
        gender: '女',
        skill: 'VUE',
        hope: '不划水',
        status: '未组队'
      }, {
        name: '王小虎',
        status: '已组队'
      }, {
        name: '王小虎',
        status: '未组队'
      }],
      form: {
        skill: '',
        expect: ''
      },
      search: ''
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
      return this.tableData22
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

.t_border3_1_large::-webkit-scrollbar { /*滚动条整体*/
  width: 10px;
}

.t_border3_1_large::-webkit-scrollbar-track { /*滚动条轨道*/
  background: #ffffff;
  border-radius: 20px;
  margin-top: 240px;
  margin-bottom: 40px;
}

.t_border3_1_large::-webkit-scrollbar-thumb { /*滚动条里面的滑块*/
  background: $primary;
  border-radius: 10px;
}

.t_border3_1_large::-webkit-scrollbar-corner { /*滚动条边角*/
  background: $primary;
}

.t_border3_1_large {
  //height: 900px;
  height: 100%;
  width: 80%;
  border: 2px solid $primary;
  margin-left: 150px;
  border-radius: 50px;
  transform: translate(0, 0);
  transition: all 0.3s ease-in-out;
  box-shadow: 10px 10px 20px $primary;
  margin-top: 50px;
  padding-left: 20px;
  padding-right: 20px;
}

.t_border3_1_large:hover {
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
</style>
