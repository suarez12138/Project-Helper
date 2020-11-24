<template>
  <div style="padding:30px;">
    <div class="search-Box" style=" margin-right: 30px;">
      <svg-icon icon-class="search" style="float: left;margin-right: 10px;margin-top: 10px; color: #1890ff;" />
      <el-input v-model="search" placeholder="请输入关键字" class="search" />
    </div>
    <div style="float: right;">
      <el-button
        :loading="downloadLoading"
        style="margin:0 0 20px 20px;"
        type="primary"
        icon="el-icon-document"
        @click="handleDownload"
      >
        Export Excel
      </el-button>
      <el-button type="primary">上传<i class="el-icon-upload el-icon--right" /></el-button>
    </div>
    <el-table
      v-loading="listLoading"
      width="650"
      :data="scorelist2"
      element-loading-text="Loading..."
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="Index" width="90">
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="SID" width="110">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="Name" width="150" align="center">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="Group Name" width="150" align="center">
        <template slot-scope="scope">
          <el-tag>{{ scope.row.groupName }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="Score" width="150">
        <template slot-scope="scope">
          <!--          {{ scope.row.score }}-->
          <!--          <el-input>{{ scope.row.score }}</el-input>-->
          <el-input v-model.trim="scope.row.score" />
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { fetchList } from '@/api/article'

export default {
  data() {
    return {
      tableHeader: [],
      downloadLoading: false,
      listLoading: true,
      scorelist: [
        { id: '11812925', name: 'jianjian', groupName: '大佬组', score: '99' },
        { id: '11812912', name: 'hyq', groupName: '抱大腿', score: '98' },
        { id: '11813301', name: 'li', groupName: '疯狂划水', score: '' }
      ],
      search: ''
    }
  },
  computed: {
    scorelist2: function() {
      var search = this.search
      if (search) {
        return this.scorelist.filter(function(dataNews) {
          return Object.keys(dataNews).some(function(key) {
            return String(dataNews[key]).toLowerCase().indexOf(search) > -1
          })
        })
      }
      return this.scorelist
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      fetchList().then(response => {
        this.list = response.data.items
        this.listLoading = false
      })
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['Index', 'SID', 'Name', 'Group Name', 'Score']
        const filterVal = ['index', 'id', 'name', 'groupName', 'score']
        const scorelist = this.scorelist
        const data = this.formatJson(filterVal, scorelist)
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: this.filename,
          autoWidth: this.autoWidth,
          bookType: this.bookType
        })
        this.downloadLoading = false
      })
    },
    formatJson(filterVal, jsonData) {
      var count = 1
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'index') {
          return count++
        } else {
          // console.log(v[j])
          return v[j]
        }
      }))
    }
  }
}
</script>

<style>
.el-card, .el-input__inner, .el-button, .el-select-dropdown, .el-select-dropdown__list, .el-select-dropdown__item.hover {
  border-radius: 30px;
}

.el-input__inner {
  padding-left: 50px;
}

.search {
  float: left;
  width: 30%;
}

</style>
