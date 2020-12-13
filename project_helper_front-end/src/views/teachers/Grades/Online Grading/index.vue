<template>
  <div class="components-container">
    <div id="t_border_online">
      <div class="create_title">Project Online Grading</div>
      <div class="search-Box" style=" margin-right: 30px;">
        <svg-icon icon-class="search" class="search_icon3" />
        <el-input v-model="search" placeholder="请输入关键字" class="search3" />
      </div>
      <div style="float: right;">
        <BookTypeOption v-model="bookType" />
        <el-button
          :loading="downloadLoading"
          style="margin:0 0 20px 20px;"
          type="primary"
          icon="el-icon-document"
          @click="handleDownload"
        >
          Export
        </el-button>
        <el-button plain type="primary">上传成绩<i class="el-icon-upload el-icon--right" /></el-button>
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
        <el-table-column align="center" label="Index" width="80">
          <template slot-scope="scope">
            {{ scope.$index }}
          </template>
        </el-table-column>
        <el-table-column align="center" prop="id" sortable label="SID" width="100">
          <template slot-scope="scope">
            {{ scope.row.id }}
          </template>
        </el-table-column>
        <el-table-column label="Name" prop="name" sortable align="center" width="120">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>
        <el-table-column label="Group Name" prop="groupName" sortable width="150" align="center">
          <template slot-scope="scope">
            <el-tag>{{ scope.row.groupName }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="score" sortable label="Score" width="90">
          <template slot-scope="scope">
            <!--          {{ scope.row.score }}-->
            <!--          <el-input>{{ scope.row.score }}</el-input>-->
            <el-input v-model.trim="scope.row.score" />
          </template>
        </el-table-column>
        <el-table-column align="center" label="Comments">
          <template slot-scope="scope">
            <!--          {{ scope.row.score }}-->
            <!--          <el-input>{{ scope.row.score }}</el-input>-->
            <el-input
              v-model="scope.row.comments"
              type="textarea"
              autosize
              placeholder="请输入评语"
            />
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
// import { fetchList } from '@/api/article'
import BookTypeOption from './components/BookTypeOption'
import { get_scoreList } from '@/api/teacher/onlineGrading'
export default {
  components: { BookTypeOption },
  data() {
    return {
      tableHeader: [],
      downloadLoading: false,
      listLoading: false,
      bookType: 'xlsx',
      scorelist: 
      // null
      [
        { id: '11812925', name: 'jianjian', groupName: '大佬组', score: '99', comments: 'Good job!' },
        { id: '11812912', name: 'hyq', groupName: '抱大腿', score: '98' },
        { id: '11813301', name: 'li', groupName: '疯狂划水', score: '' }
      ]
      ,
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
      // this.listLoading = true
      // get_scoreList(project_id).then(response => {
      get_scoreList(localStorage.getItem('current_project_id')).then(response => {
        this.scorelist = response.data
        this.listLoading = false
      })
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['Index', 'SID', 'Name', 'Group Name', 'Score', 'Comments']
        const filterVal = ['index', 'id', 'name', 'groupName', 'score', 'comments']
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

<style lang="scss">
@import "~@/styles/variables.scss";

.components-container {
  position: relative;
  height: 100vh;
}

.el-card, .el-input__inner, .el-textarea__inner,.el-button, .el-select-dropdown, .el-select-dropdown__list, .el-select-dropdown__item.hover {
  border-radius: 30px;
}
.el-table .cell {
  white-space: pre-line; //保留空白符序列，但是正常地进行换行。
}

.el-input__inner {
  padding-left: 20px;
}

.search3 {
  float: left;
  width: 30%;
}

#t_border_online {
  height: 100%;
  width: 80%;
  border: 2px solid $primary;
  margin-left: 150px;
  border-radius: 50px;
  transform: translate(0, 0);
  transition: all 0.3s ease-in-out;
  box-shadow: 10px 10px 20px $primary;
  padding: 20px 85px 85px 85px;
}

#t_border_online:hover {
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

.search_icon3 {
  float: left;
  margin-right: 10px;
  margin-top: 10px;
  color: $primary;
}
</style>
