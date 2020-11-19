<template>
  <el-form>
    <el-form-item label="Name">
      <el-input v-model.trim="user.name" />
    </el-form-item>
    <el-form-item label="Gender">
      <el-input v-model.trim="user.gender" />
    </el-form-item>
    <el-form-item label="Email">
      <el-input v-model.trim="user.email" />
    </el-form-item>
    <el-form-item label="Location">
      <!--      <el-input v-model.trim="user.location" />-->
      <el-select v-model="user.location" clearable placeholder="请选择宿舍区域">
        <el-option label="湖畔" value="湖畔" />
        <el-option label="荔园" value="荔园" />
        <el-option label="欣园" value="欣园" />
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" plain @click="submit">Update</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  props: {
    user: {
      type: Object,
      default: () => {
        return {
          name: '',
          gender: '',
          email: '',
          location: ''
        }
      }
    }
  },
  methods: {
    submit() {
      this.$message({
        message: 'User information has been updated successfully',
        type: 'success',
        duration: 5 * 1000
      })
    }
  }
}
</script>
