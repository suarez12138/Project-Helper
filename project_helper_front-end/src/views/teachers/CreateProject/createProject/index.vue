<template>
  <div class="components-container">
    <div id="t_border3_1">
      <div class="create_title">Create A New Project</div>

      <el-form
        ref="ruleForm"
        :model="create_ruleForm"
        status-icon
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
      >
        <el-form-item label="课程" prop="course">
          <el-select v-model="create_ruleForm.course" placeholder="请选择相应课程">
            <el-option label="课程一" value="shanghai" />
            <el-option label="课程二" value="beijing" />
          </el-select>
        </el-form-item>

        <el-form-item label="项目名称" prop="name">
          <el-input v-model="create_ruleForm.name" style="width: 60%" />
        </el-form-item>

        <el-form-item label="小组人数" prop="population" required>
          <el-slider
            v-model="create_ruleForm.population"
            range
            show-stops
            input-size="medium"
            :min="1"
            :max="6"
            style="padding-right: 200px;"
          />
        </el-form-item>

        <el-form-item label="答辩周" prop="time">
          <el-select v-model="create_ruleForm.time" multiple placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>

          <!--          <el-cascader-->
          <!--            v-model="create_ruleForm.time"-->
          <!--            :options="options"-->
          <!--            :props="props"-->
          <!--            clearable-->
          <!--          />-->
        </el-form-item>

        <el-form-item label="跨班组队" prop="across_lab" required>
          <el-switch
            v-model="create_ruleForm.across_lab"
            style="display: block; margin-top: 7px;"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-text="允许"
            inactive-text="不允许"
          />
        </el-form-item>
        <el-form-item label="技能设定" prop="skills">
          <el-tag
            v-for="tag in dynamicTags"
            :key="tag"
            v-model="create_ruleForm.skills"
            closable
            :disable-transitions="false"
            @close="handleClose(tag)"
          >
            {{ tag }}
          </el-tag>
          <el-input
            v-if="inputVisible"
            ref="saveTagInput"
            v-model="inputValue"
            class="input-new-tag"
            size="small"
            @keyup.enter.native="handleInputConfirm"
            @blur="handleInputConfirm"
          />
          <el-button v-else class="button-new-tag el-icon-circle-plus" plain type="primary" size="small" @click="showInput">  Add</el-button>
        </el-form-item>

        <el-form-item
          label="停止组队时间"
          style="padding-right: 200px;"
        >
          <el-col :span="11">
            <el-form-item prop="groupingEndTime">
              <el-date-picker
                v-model="create_ruleForm.groupingEndTime"
                type="date"
                placeholder="选择日期"
                style="width: 90%;"
              />
            </el-form-item>
          </el-col>
          <el-col class="line" :span="2"> ---</el-col>
          <el-col :span="11">
            <el-time-picker
              v-model="create_ruleForm.groupingEndTime2"
              placeholder="选择时间"
              style="width: 100%;"
            />
          </el-col>
        </el-form-item>

        <el-form-item label="自由组队" prop="grouping">
          <el-switch
            v-model="create_ruleForm.grouping"
            style="display: block; margin-top: 7px;"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-text="立即开启"
            inactive-text="暂不开启"
          />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" plain @click="submitForm('ruleForm')">立即创建</el-button>
          <el-button plain @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { new_Project } from '@/api/teacher/createProject/'
import { getToken } from '@/utils/auth'

export default {
  name: 'DndListDemo',
  data() {
    var checkdate1 = (rule, value, callback) => {
      // console.log(value)
      const d = new Date()
      setTimeout(() => {
        if (value < d) {
          return callback(new Error('请选择当前时间之后的时间'))
        } else {
          callback()
        }
      }, 500
      )
    }

    return {
      props: { multiple: true },
      dynamicTags: [],
      inputVisible: false,
      inputValue: '',
      create_ruleForm: {
        course: '',
        name: '',
        population: [2, 4],
        time: [],
        groupingEndTime: '',
        groupingEndTime2: '',
        across_lab: true,
        grouping: true,
        skills: []
      },
      rules: {
        course: [
          { required: true, message: '请输入课程名称', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入项目名称', trigger: 'blur' }
        ],
        time: [
          { required: true, message: '请选择可答辩时间', trigger: 'blur' }
        ],
        groupingEndTime: [
          { validator: checkdate1, trigger: 'blur' }
        ]
      },
      options: [{
        value: 1,
        label: 'Week 1'
      }, {
        value: 2,
        label: 'Week 2'
      }, {
        value: 3,
        label: 'Week 3'
      }, {
        value: 4,
        label: 'Week 4'
      }, {
        value: 5,
        label: 'Week 5'
      }, {
        value: 6,
        label: 'Week 6'
      }, {
        value: 7,
        label: 'Week 7'
      }, {
        value: 8,
        label: 'Week 8'
      }, {
        value: 9,
        label: 'Week 9'
      }, {
        value: 10,
        label: 'Week 10'
      }, {
        value: 11,
        label: 'Week 11'
      }, {
        value: 12,
        label: 'Week 12'
      }, {
        value: 13,
        label: 'Week 13'
      }, {
        value: 14,
        label: 'Week 14'
      }, {
        value: 15,
        label: 'Week 15'
      }, {
        value: 16,
        label: 'Week 16'
      }, {
        value: 17,
        label: 'Week 17'
      }, {
        value: 18,
        label: 'Week 18'
      }]
    }
  },
  computed: {},
  methods: {
    new_Project_table() {
      new_Project({
        token:                getToken(),
        course_id:            getCourseid(),
        project_name:         this.create_ruleForm.course,
        project_member_limit: this.create_ruleForm.population,
        project_pre_week:     this.create_ruleForm.time,
        project_grouping_endDay: this.create_ruleForm.groupingEndTime,
        project_grouping_endHms: this.create_ruleForm.groupingEndTime2,
        across_lab:           this.create_ruleForm.across_lab,
        grouping_freely:      this.create_ruleForm.grouping,
        all_tags:             this.create_ruleForm.skills,
        choosable_proj:       this.create_ruleForm.

      }).then(response => {

      })
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
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1)
    },

    showInput() {
      this.inputVisible = true
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus()
      })
    },

    handleInputConfirm() {
      const inputValue = this.inputValue
      if (inputValue) {
        this.dynamicTags.push(inputValue)
      }
      this.inputVisible = false
      this.inputValue = ''
      this.create_ruleForm.skills = this.dynamicTags
      // console.log(this.create_ruleForm.skills)
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

.el-popover {
  border-radius: 20px;
  box-shadow: 0 0 40px $primary;
}

.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
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
  color: $primary;
  font-size: 40px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 20px;
  padding-bottom: 40px;

}

</style>
