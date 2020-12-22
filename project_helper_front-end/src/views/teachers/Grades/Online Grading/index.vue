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
        <el-button plain type="primary" @click="handleUpdate">上传成绩<i class="el-icon-upload el-icon--right" /></el-button>
      </div>
      <el-table
        v-loading="listLoading"
        :data="scorelist2"
        element-loading-text="Loading..."
      >
        <el-table-column align="center" label="Index" width="60" prop="index">
          <template slot-scope="scope">
            {{ scope.$index }}
          </template>
        </el-table-column>
        <el-table-column align="center" prop="stu_id" sortable label="SID" width="100">
          <template slot-scope="scope">
            {{ scope.row.stu_id }}
          </template>
        </el-table-column>
        <el-table-column label="Name" prop="person_name" sortable align="center" width="100">
          <template slot-scope="scope">
            {{ scope.row.person_name }}
          </template>
        </el-table-column>
        <el-table-column label="Group Name" prop="group_name" sortable width="150" align="center">
          <template slot-scope="scope">
            <el-tag>{{ scope.row.group_name }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="score" sortable label="Score" width="90">
          <template slot-scope="scope">
            <el-input v-model.trim="scope.row.score" />
          </template>
        </el-table-column>
        <el-table-column align="center" label="Comments">
          <template slot-scope="scope">
            <el-input
              v-model="scope.row.comments"
              type="textarea"
              autosize
              placeholder="请输入评语"
            />
          </template>
        </el-table-column>
        <el-table-column align="center" prop="confirm" sortable label="Confirm" width="100">
          <template slot-scope="scope">
            <el-button
              type="success"
              size="mini"
              round
              @click="miao(scope.row.person_id, scope.row.group_id, scope.row.score, scope.row.comments)"
            >确认
            </el-button>
          </template>
        </el-table-column>
        <el-table-column prop="change" align="center" width="40">
          <template slot-scope="scope">
            <i v-if="scope.row.change" ref="test" class="el-icon-finished theme" />
            <!--          <i   class="el-icon-finished theme" ></i>-->
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
import { post_scoreList } from '@/api/teacher/onlineGrading'

export default {
  components: { BookTypeOption },
  data() {
    return {
      // hei: '500px',
      // hei: window.innerHeight * 0.75,
      tableHeader: [],
      downloadLoading: false,
      listLoading: false,
      bookType: 'xlsx',
      scorelist:
        {
          person_id: '',
          group_id: '',
          stu_id: '',
          person_name: '',
          group_name: '',
          score: '',
          comments: ''
        },
      modified_person_ids: [],
      modified_group_ids: [],
      modified_scores: [],
      modified_comments: [],
      search: '',
      count: 0
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
    miao(person_id, group_id, score, comments, change) {
      this.modified_person_ids[this.count] = person_id
      this.modified_group_ids[this.count] = group_id
      this.modified_scores[this.count] = score
      this.modified_comments[this.count] = comments
      // alert(this.modified_person_ids[this.count])
      this.count = this.count + 1
      // alert(this.count)
      for (let i = 0; i < this.scorelist.length; i++) {
        if (this.scorelist[i].person_id == person_id) {
          this.scorelist[i].change = true
        }
      }
      this.$refs.test.style.display = 'true'
      // console.log(this.scorelist)
    },
    handleUpdate() {
      // alert(this.scorelist[1].stu_id)
      // this.modified_person_ids = this.scorelist.person_id
      // this.modified_group_ids = this.scorelist.group_id
      // this.modified_scores = this.scorelist.score
      // this.modified_comments = this.scorelist.comments

      post_scoreList({
        person_ids: this.modified_person_ids,
        group_ids: this.modified_group_ids,
        grades: this.modified_scores,
        comments: this.modified_comments
      }).then(response => {

      })

      this.$message({
        message: '更新成功！',
        type: 'success'
      })
    },
    fetchData() {
      this.listLoading = true
      get_scoreList(localStorage.getItem('current_project_id')).then(response => {
        this.scorelist = response.data
        this.scorelist_for_post = response.data
        // console.log(this.scorelist)
        this.listLoading = false
        for (let i = 0; i < this.scorelist.length; i++) {
          this.$set(this.scorelist[i], 'change', false)
        }
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

.el-card, .el-input__inner,.el-tag, .el-textarea__inner, .el-button, .el-select-dropdown, .el-select-dropdown__list, .el-select-dropdown__item.hover {
  border-radius: 30px!important;
}

.el-table .cell {
  white-space: pre-line; //保留空白符序列，但是正常地进行换行。
}

.el-input__inner {
  padding-left: 20px;
}

.search3 {
  float: left;
  width: 30%!important;
}

#t_border_online {
  height: 100%;
  width: 100%;
  border: 2px solid $primary;
  //margin-left: 150px;
  border-radius: 50px;
  transform: translate(0, 0);
  transition: all 0.3s ease-in-out;
  box-shadow: 10px 10px 20px $primary;
  padding: 20px 85px 85px 85px;
  overflow-y: auto;
}

#t_border_online:hover {
  box-shadow: 20px 20px 20px $primary;
  transform: translate(-5px, -5px);
  transition: 0.3s ease-in-out;
}

.theme {
  color: $primary;
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

.search_icon3 {
  float: left;
  margin-right: 10px;
  margin-top: 10px;
  color: $primary;
}

#t_border_online::-webkit-scrollbar { /*滚动条整体*/
  width: 10px;
}

#t_border_online::-webkit-scrollbar-track { /*滚动条轨道*/
  background: #ffffff;
  border-radius: 20px;
  margin-top: 45px;
  margin-bottom: 45px;

}

#t_border_online::-webkit-scrollbar-thumb { /*滚动条里面的滑块*/
  background: $primary;
  border-radius: 10px;
}

#t_border_online::-webkit-scrollbar-corner { /*滚动条边角*/
  background: $primary;
}
</style>
