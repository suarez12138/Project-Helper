<template>
  <div style="padding:30px;">
    <upload-excel-component :on-success="handleSuccess" :before-upload="beforeUpload" />
    <el-button plain type="primary" style="float:right; margin:20px 680px 30px 100px;" @click="miao()">确认上传<i class="el-icon-upload el-icon--right" /></el-button>
    <el-table :data="tableData" border highlight-current-row style="width: 100%;margin-top:20px;">
      <el-table-column v-for="item of tableHeader" :key="item" :prop="item" :label="item" />
    </el-table>
  </div>
</template>

<script>
import UploadExcelComponent from '@/components/UploadExcel/index'
import { post_scoreList } from '@/api/teacher/onlineGrading'

export default {
  name: 'UploadExcel',
  components: { UploadExcelComponent },
  data() {
    return {
      tableData: [],
      tableHeader: [],
      modified_person_ids: [],
      modified_group_ids: [],
      modified_scores: [],
      modified_comments: []
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
      this.tableHeader = ['Index', 'SID', 'Name', 'GroupName', 'Score', 'Comments']
    },
    miao() {
      console.log(this.tableData.length)
      var length = this.tableData.length
      console.log(this.tableData)
      console.log(this.tableData[0].GroupName)
      for(var i = 0; i < length; i++) {
        this.modified_person_ids.push(this.tableData[i].SID)
        this.modified_group_ids.push(this.tableData[i].GroupName)
        this.modified_scores.push(this.tableData[i].Score)
        this.modified_comments.push(this.tableData[i].Comments)
      }
      this.handleUpdate()
    },
    handleUpdate() {
      
      post_scoreList2({
        person_name: this.modified_person_ids,
        group_name: this.modified_group_ids,
        grades: this.modified_scores,
        comments: this.modified_comments
      }).then(response => {
        alert("miao")
      })
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
