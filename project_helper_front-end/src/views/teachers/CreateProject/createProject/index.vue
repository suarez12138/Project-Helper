<template>
  <div class="components-container">
    <div id="border3_1">
      <div class="create_title">Create A New Project</div>

      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" class="demo-ruleForm">
        <el-form-item label="课程" prop="course">
          <el-select v-model="ruleForm.course" placeholder="请选择相应课程">
            <el-option label="课程一" value="shanghai" />
            <el-option label="课程二" value="beijing" />
          </el-select>
        </el-form-item>

        <el-form-item label="项目名称" prop="name">
          <el-input v-model="ruleForm.name" style="padding-right: 200px;" />
        </el-form-item>

        <el-form-item label="小组人数" prop="population">
          <el-slider
            v-model="ruleForm.population"
            range
            show-stops
            input-size="medium"
            :min="1"
            :max="6"
            style="padding-right: 200px;"
          />
        </el-form-item>

        <el-form-item label="有效答辩周" prop="time">
          <el-cascader
            v-model="ruleForm.time"
            :options="options"
            :props="props"
            clearable
          />
        </el-form-item>

        <el-form-item label="自由组队" prop="grouping">
          <el-switch
            v-model="ruleForm.grouping"
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

export default {
  name: 'DndListDemo',
  data() {
    return {
      props: { multiple: true },
      ruleForm: {
        course: '',
        name: '',
        population: [2, 4],
        time: [],
        grouping: false
      },
      options: [{
        value: 1,
        label: '第一周'
      }, {
        value: 2,
        label: '第二周'
      }],
      rules: {
        // name: [
        //   {required: true, message: '请输入活动名称', trigger: 'blur'},
        //   {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        // ],
        // region: [
        //   {required: true, message: '请选择活动区域', trigger: 'change'}
        // ],
        // date1: [
        //   {type: 'date', required: true, message: '请选择日期', trigger: 'change'}
        // ],
        // date2: [
        //   {type: 'date', required: true, message: '请选择时间', trigger: 'change'}
        // ],
        // type: [
        //   {type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change'}
        // ],
        // resource: [
        //   {required: true, message: '请选择活动资源', trigger: 'change'}
        // ],
        // desc: [
        //   {required: true, message: '请填写活动形式', trigger: 'blur'}
        // ]
      }
    }
  },
  computed: {},
  methods: {
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
    }
  }
}
</script>

<style>
.components-container {
  position: relative;
  height: 100vh;
}

.el-tag, .el-button, .el-input__inner, .el-textarea__inner {
  border-radius: 20px !important;
}

.el-popover {
  border-radius: 20px;
  box-shadow: 0 0 40px #CCCCCC;
}

#border3_1 {
  height: 100%;
  width: 60%;
  border: 2px solid #1890ff;
  margin-left: 300px;
}

#border3_1 {
  border-radius: 50px;
  transform: translate(0, 0);
  transition: all 0.3s ease-in-out;
  box-shadow: 10px 10px 20px #1890ff;
}

#border3_1:hover {
  box-shadow: 20px 20px 20px #1890ff;
  transform: translate(-5px, -5px);
  transition: 0.3s ease-in-out;
}

.create_title {
  color: #1890ff;
  font-size: 40px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 20px;
  padding-bottom: 40px;

}

.flo {
  position: relative;
  float: left;
}

.juzhong {
  margin-left: 100px;
  margin-right: 100px;
  width: 75% !important;
}
</style>
