<template>
  <div class="border_p">
    <div class="personal_title"> Join New Project</div>
    <div>
      <div style=" margin-right: 30px;width: 30%; float: right">
        <el-input v-model="search" placeholder="请输入关键字" class="search2" />
        <svg-icon icon-class="search" class="search_icon" />
      </div>

      <el-table
        ref="filterTable"
        :data="tableData2"
        style="width: 100%"
      >
        <el-table-column
          align="center"
          prop="project_name"
          label="项目名称"
          sortable
        />

        <el-table-column
          align="center"
          prop="course"
          label="所属课程"
          sortable
        />
        <el-table-column
          align="center"
          fixed="right"
          label="操作"
          width="100"
        >
          <template slot-scope="scope">
            <el-button type="primary" plain size="small" @click="handleClick(scope.row)">加入</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import checkPermission from '@/utils/permission'
import { get_joinableProject } from '@/api/student/join'
import { join_project } from '@/api/student/join'
import { getToken } from '@/utils/auth'

export default {
  name: 'DragSelectDemo',
  data() {
    return {
      tableData22: null,
      search: ''
    }
  },
  computed: {
    tableData2: function() {
      var search = this.search
      if (search) {
        return this.tableData22.filter(function(dataNews) {
          return Object.keys(dataNews).some(function(key) {
            return String(dataNews[key]).toLowerCase().indexOf(search) > -1
          })
        })
      }
      return this.tableData22
    }
  },
  created() {
    this.getJoinableProjectList()
  },
  methods: {
    getJoinableProjectList() {
      get_joinableProject(getToken()).then(response => {
        this.tableData22 = response.data
      })
    },
    handleClick(row) {
      // alert(row.project_id)
      join_project(
        getToken(),
        row.project_id
      ).then(response => {
        this.$message({
          message: '加入课程成功！',
          type: 'success'
        })
      })
      location.reload(true)
    },
    checkPermission,
    resetDateFilter() {
      this.$refs.filterTable.clearFilter('date')
    },
    clearFilter() {
      this.$refs.filterTable.clearFilter()
    },
    formatter(row, column) {
      return row.skill
    },
    filterstatus(value, row) {
      return row.status === value
    },
    filterHandler(value, row, column) {
      const property = column['property']
      return row[property] === value
    }
  }
}
</script>

<style lang="scss">
@import "~@/styles/variables.scss";

.search2 {
  float: right;
  width: 80% !important;
}

.search2 > .el-input__inner {
  border-radius: 30px;
}

.el-tag, .el-button, .el-dialog, .el-input__inner, .el-select-dropdown {
  border-radius: 20px;
}

.border_p::-webkit-scrollbar { /*滚动条整体*/
  width: 10px;
}

.border_p::-webkit-scrollbar-track { /*滚动条轨道*/
  background: #ffffff;
  border-radius: 20px;
  margin-top: 240px;
  margin-bottom: 40px;
}

.border_p::-webkit-scrollbar-thumb { /*滚动条里面的滑块*/
  background: $primary;
  border-radius: 10px;
}

.border_p::-webkit-scrollbar-corner { /*滚动条边角*/
  background: $primary;
}

.border_p {
  height: 900px;
  width: 60%;
  border: 2px solid $primary;
  margin-left: 300px;
  border-radius: 50px;
  transform: translate(0, 0);
  transition: all 0.3s ease-in-out;
  box-shadow: 10px 10px 20px $primary;
  margin-top: 50px;
  padding-left: 20px;
  padding-right: 20px;
}

.border_p:hover {
  box-shadow: 20px 20px 20px $primary;
  transform: translate(-5px, -5px);
  transition: 0.3s ease-in-out;
}

.search_icon {
  float: right;
  margin-right: 10px;
  margin-top: 10px;
  color: $primary;
}

.personal_title {
  font-weight: bold;
  color: $primary;
  font-size: 40px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 20px;
  padding-bottom: 40px;
}
</style>
