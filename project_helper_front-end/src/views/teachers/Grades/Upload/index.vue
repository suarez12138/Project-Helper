<template>
  <div style="padding:30px;">
    <upload-excel-component :on-success="handleSuccess" :before-upload="beforeUpload" />
    <el-button plain type="primary" style="float:right; margin:20px 680px 30px 100px;" @click="handleUpdate">确认上传<i class="el-icon-upload el-icon--right" /></el-button>
    <el-table :data="tableData" border highlight-current-row style="width: 100%;margin-top:20px;">
      <el-table-column v-for="item of tableHeader" :key="item" :prop="item" :label="item" />
    </el-table>
  </div>
</template>

<script>
import UploadExcelComponent from '@/components/UploadExcel/index'

export default {
  name: 'UploadExcel',
  components: { UploadExcelComponent },
  data() {
    return {
      tableData: [],
      tableHeader: []
    }
  },
  methods: {
    beforeUpload(file) {
      const isLt1M = file.size / 1024 / 1024 < 1
      if (isLt1M) {
        return true
      }
      this.$message({
        message: 'Please do not upload files larger than 1m in size.',
        type: 'warning'
      })
      return false
    },
    handleSuccess({ results, header }) {
      this.tableData = results
      this.tableHeader = ['Index', 'SID', 'Name', 'Group Name', 'Score', 'Comments']
    },
    handleUpdate() {

    }
  }
}
</script>

<style>
.el-card, .el-input__inner, .el-button, .el-select-dropdown, .el-select-dropdown__list, .el-select-dropdown__item.hover {
  border-radius: 30px;
}

/*.el-input__inner {*/
/*  padding-left: 50px;*/
/*}*/

</style>
