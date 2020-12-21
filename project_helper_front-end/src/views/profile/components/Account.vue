<template>
  <el-form label-width="100px">
    <el-form-item label="SID">
      <el-input v-model.trim="user.id" style="width: 20%;" :disabled="true" />
    </el-form-item>
    <el-form-item label="Name">
      <el-input v-model.trim="user.name" style="width: 20%;" :disabled="true" />
    </el-form-item>
    <el-form-item label="Password">
      <el-input v-model.trim="user.password" style="width: 20%;" />
    </el-form-item>

    <el-form-item label="Gender">
      <el-select v-model="user.gender" clearable placeholder="请选择性别">
        <el-option label="女" value="女" />
        <el-option label="男" value="男" />
        <el-option label="其他" value="其他" />
      </el-select>
    </el-form-item>
    <el-form-item label="Location">
      <!--      <el-input v-model.trim="user.location" />-->
      <el-select v-model="user.location" clearable placeholder="请选择宿舍区域">
        <el-option label="湖畔" value="湖畔" />
        <el-option label="荔园" value="荔园" />
        <el-option label="欣园" value="欣园" />
      </el-select>
    </el-form-item>
    <el-form-item label="Email">
      <el-input v-model.trim="user.email" style="width: 40%;" />
    </el-form-item>
    <el-form-item label="Introduction">
      <el-input
        v-model="user.introduction"
        type="textarea"
        :autosize="{ minRows: 2}"
        placeholder="请输入个人介绍"
      />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" plain @click="submit">Update</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { getToken } from '@/utils/auth'
import { update_profile } from '@/api/student/profile'

export default {
  props: {
    user: {
      type: Object,
      default: () => {
        return {
          id: '',
          name: '',
          password: '',
          gender: '',
          email: getToken()+"@mail.sustech.edu.cn",
          location: '',
          introduction: '我bu是我'// 这里调用了上一级index里的数据
        }
      }
    }
  },
  methods: {
    submit() {
      update_profile({
        token: getToken(),
        Password: this.user.password,
        Gender: this.user.gender,
        location: this.user.location,
        introduction: this.user.introduction
      }).then(response => {
          
      })
      this.$message({
        message: 'User information has been updated successfully',
        type: 'success',
        duration: 5 * 1000
      })
    }
  }
}
</script>
