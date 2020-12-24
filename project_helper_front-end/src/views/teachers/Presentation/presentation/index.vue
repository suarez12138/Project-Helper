<template>
  <div class="components-container">
    <div id="t_border3_1">
      <div class="create_title">Presentation Setting</div>
      <div class="inf">There are {{ groupNumber }} groups in this project.</div>
      <el-form
        ref="ruleForm"
        :model="pre_setting"
        status-icon
        :rules="rules"
        label-width="150px"
        class="demo-ruleForm"
      >
        <el-form-item label="答辩时间段个数" prop="number">
          <el-select v-model="pre_setting.number" placeholder="答辩时间段个数" @change="setlist(pre_setting.number)">
            <el-option label="1" value="1" />
            <el-option label="2" value="2" />
            <el-option label="3" value="3" />
            <el-option label="4" value="4" />
            <el-option label="5" value="5" />
          </el-select>
        </el-form-item>

        <el-form-item label="单时间段上限组数" prop="upperbound">
          <el-input v-model="pre_setting.upperbound" style="width: 30%" placeholder="请输入上限组数" />
        </el-form-item>

        <el-form-item label="单组答辩时长" prop="duration">
          <el-input-number v-model="pre_setting.duration" :step="5" step-strictly />
        </el-form-item>
        <!--eslint-disable-next-line-->
        <el-form :model="item" v-for="item in this.list" label-width="150px">
          <el-form-item :label="'时间段'+item.index">
            <el-date-picker
              v-model="item.time"
              format="yyyy-MM-dd HH:mm"
              type="datetime"
              placeholder="选择日期时间"
              style="float: left;"
              @change="cal_end_time(item.time,item.index)"
            />
            <div style="float: left;margin-left: 20px;"> 预计最晚结束时间：{{ item.endTime }}</div>

            <el-input
              v-model="item.remark"
              style="padding-right: 200px;padding-top: 5px;"
              placeholder="可以具体描述此时间段"
            />
          </el-form-item>
        </el-form>

        <el-form-item>
          <el-button type="primary" plain @click="postpreTimeTable()">确认</el-button>
          <el-button type="primary" @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>

    </div>
  </div>
</template>

<script>
import get_preTimeChunk from '@/utils/permission' // 权限判断函数
import { fetchGroupsList } from '@/api/teacher/group'
import { post_preTime } from '@/api/teacher/presentation'
import { get_preList } from '@/api/teacher/presentation'
import { get_preTime } from '@/api/teacher/presentation'




export default {
  data() {
    var checkdate1 = (rule, value, callback) => {
      // console.log(this.groupNumber/this.pre_setting.number)
      setTimeout(() => {
        if (value < this.groupNumber / this.pre_setting.number) {
          return callback(new Error('组数上限过小，无法容纳' + this.groupNumber + '组！'))
        } else {
          callback()
        }
      }, 500
      )
    }
    return {
      list: [],
      groupNumber: '39',
      props: { multiple: true },
      pre_setting: {
        number: '',
        upperbound: '',
        duration: 15
      },
      rules: {
        number: [
          { required: true, message: '请选择时间段个数', trigger: 'blur' }
        ],
        upperbound: [
          { required: true, message: '请输入上限组数', trigger: 'blur' },
          { validator: checkdate1, trigger: 'blur' }
        ],
        duration: [
          { required: true, message: '请输入单组答辩时长', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {},
  created() {
    this.get_peopleNumber()
    this.get_pre_list()
  },
  methods: {
    get_pre_list() {
      get_preList(localStorage.getItem('current_project_id')).then(response => {
        this.pre_setting.number = response.data.length
        this.pre_setting.upperbound = response.data[0].limit_group
        this.pre_setting.duration = response.data[0].limit_time
        for(var i = 0; i<response.data.length; i++){
          list.push({
            time: response.data[i].start_time,
            text: response.data[i].text
          })
        }

      })
    },
    get_peopleNumber() {
      fetchGroupsList(localStorage.getItem('current_project_id')).then(response => {
        // alert(response.allGroups.length)
        this.groupNumber = response.allGroups.length
      })
    },
    postpreTimeTable() {
      // alert(this.list[0].remark)
      var start_timeList = []
      var the_text = []
      for(var i = 0; i < this.pre_setting.number; i++){
        start_timeList.push(this.list[i].time)
        the_text.push(this.list[i].remark)
      }
      post_preTime({
        project_id: localStorage.getItem('current_project_id'),
        time_limit: this.pre_setting.duration,
        group_limit:this.pre_setting.upperbound,
        start_time: start_timeList,
        the_text: the_text
        
      }).then(response => {

      })
    },
    cal_end_time(time, index) {
      console.log(time)
      var minute = this.pre_setting.upperbound * this.pre_setting.duration
      minute += time.getMinutes()
      console.log(Math.floor(minute / 60))
      console.log(minute % 60)
      var minutetmp = minute % 60
      var hours = time.getHours() + Math.floor(minute / 60)
      var hour = (hours < 10 ? '0' + (hours) : hours)
      var minutes = (minutetmp < 10 ? '0' + (minutetmp) : minutetmp)
      this.list[index - 1].endTime = hour + ':' + minutes
      // console.log(minute)
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!')
          console.log(this.list)
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    setlist(val) {
      this.list = []
      for (var i = 0; i < val; i++) {
        const obj = {
          index: i + 1,
          time: '',
          remark: '',
          endTime: ''
        }
        this.list.push(obj)
      }
      console.log(this.list)
    }
  }
}
</script>

<style lang="scss">
@import "~@/styles/variables.scss";

.components-container {
  position: relative;
  height: 120vh;
}

.el-tag, .el-button, .el-input__inner, .el-textarea__inner {
  border-radius: 20px !important;
}

.el-popover {
  border-radius: 20px;
  box-shadow: 0 0 40px #CCCCCC;
}

#t_border3_1 {
  height: 100%;
  width: 60%;
  border: 2px solid $primary;
  margin-left: 300px;
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

.create_title {
  font-weight: bold;
  color: $primary;
  font-size: 40px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 20px;
  padding-bottom: 40px;
}

.inf {
  color: $primary;
  font-size: 20px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-bottom: 30px;
}

</style>
