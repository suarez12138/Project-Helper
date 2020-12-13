<template>
  <div v-if="checkPermission(['student'])" class="border_p">
    <div class="personal_title"> Submission</div>
    <div>
      <div class="search-Box" style=" margin-right: 30px;width: 30%; float: right">
        <el-input v-model="search" placeholder="请输入关键字" class="search" />
        <svg-icon icon-class="search" class="search_icon" />
      </div>

      <el-table
        ref="filterTable"
        :data="tableData2"
        style="width: 100%"
      >
        <el-table-column
          prop="title"
          label="标题"
          sortable
        >
          <template slot-scope="scope">
            <el-link href="/#/submission/submission4" class="buttonText" type="primary" :underline="false">
              {{ scope.row.title }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          prop="release_time"
          label="发布时间"
          sortable
          width="190"
        />

        <el-table-column
          prop="ddl"
          label="截止时间"
          sortable
          width="190"
        />
        <el-table-column
          prop="student_status"
          label="状态"
          sortable
          width="150"
          :filters="[{ text:'未提交', value: '未提交' }, { text: '已提交', value: '已提交' }]"
          :filter-method="filterstatus"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.student_status === '未提交' ? 'primary' : 'success'"
              disable-transitions
            >{{ scope.row.student_status }}
            </el-tag>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
  <div v-else-if="checkPermission(['teacher'])" class="border_p">
    <div class="personal_title"> Submission</div>
    <div>
      <el-button type="text" style="float: left;margin-left: 20px;" size="large" @click="handleClick()"><i
        class="el-icon-circle-plus"
      /> 新建
      </el-button>
      <div class="search-Box" style=" margin-right: 30px;width: 30%; float: right">
        <el-input v-model="search" placeholder="请输入关键字" class="search" />
        <svg-icon icon-class="search" class="search_icon" />
      </div>

      <el-table
        ref="filterTable"
        :data="tableData2"
        style="width: 100%"
      >
        <el-table-column
          prop="title"
          label="标题"
          sortable
        >
          <template slot-scope="scope">
            <el-link href="/#/submission/submission2" class="buttonText" type="primary" :underline="false">
              {{ scope.row.title }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          prop="release_time"
          label="发布时间"
          sortable
          width="190"
        />

        <el-table-column
          prop="ddl"
          label="截止时间"
          sortable
          width="190"
        />
        <el-table-column
          prop="status"
          label="状态"
          sortable
          width="100"
          :filters="[{ text: '进行中', value: '进行中' }, { text: '已结束', value: '已结束' }]"
          :filter-method="filterstatus"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.status === '进行中' ? 'primary' : 'success'"
              disable-transitions
            >{{ scope.row.status }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="180"
        >
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="small"
              @click.native.prevent="edit_submission(scope.row)"
            >编辑
            </el-button>
            <el-button
              type="danger"
              size="small"
              @click.native.prevent="delete_submission(scope.row)"
            > 删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import checkPermission from '@/utils/permission'

export default {
  name: 'DragSelectDemo',
  data() {
    return {
      tableData22: [{
        title: '初期答辩要求',
        release_time: 'Dec 2, 2020 5:15 PM', // 时间可以用其他格式
        ddl: '2020-12-16-23:00',
        status: '进行中',
        student_status: '未提交'// 这个需要对当前组是否提交进行判断
      }, {
        title: '中期答辩要求',
        release_time: 'Dec 2, 2020 5:15 PM', // 时间可以用其他格式
        ddl: 'Dec 16, 2020 11:00 PM',
        status: '已结束',
        student_status: '已提交'// 这个需要对当前组是否提交进行判断
      }, {
        title: '初期答辩要求',
        release_time: 'Dec 2, 2020 5:15 PM', // 时间可以用其他格式
        ddl: 'Dec 16, 2020 11:00 PM',
        status: '已结束'
      }, {
        title: '初期答辩要求',
        release_time: 'Dec 2, 2020 5:15 PM', // 时间可以用其他格式
        ddl: 'Dec 16, 2020 11:00 PM',
        status: '已结束'
      }],
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
  methods: {
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
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
      console.log(this.multipleSelection)
    },
    edit_submission(row) {

    },
    delete_submission(row) {

    }
  }
}
</script>

<style lang="scss">
@import "~@/styles/variables.scss";

.search {
  float: right;
  width: 80% !important;
}

.search > .el-input__inner {
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
  color: $primary;
  font-size: 40px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 20px;
  padding-bottom: 40px;
}
</style>
