<template>
  <div class="components-container">
    <div class="t_border3_1_large">
      <div class="personal_title"> Personal information</div>
      <div>
        <el-button type="text" style="float: left;margin-left: 50px;" size="large" @click="handleClick()"><i
          class="el-icon-circle-plus"
        /> 组队
        </el-button>
        <div class="search-Box" style=" margin-right: 30px;width: 30%; float: right">
          <el-input v-model="search" placeholder="请输入关键字" class="search" />
          <svg-icon icon-class="search" class="search_icon" />
        </div>
        <el-dialog title="创建小组" :visible.sync="dialogFormVisible" width="500px" append-to-body>
          <el-form :model="createGroupForm">
            <el-form-item label="小组名称" :label-width="formLabelWidth">
              <el-input v-model="createGroupForm.name" autocomplete="off" />
            </el-form-item>
            <!--        <el-form-item label="意向人数" :label-width="formLabelWidth">-->
            <!--          <el-select v-model="form.population" placeholder="请选择意向人数">-->
            <!--            <el-option label="区域一" value="shanghai"></el-option>-->
            <!--            <el-option label="区域二" value="beijing"></el-option>-->
            <!--          </el-select>-->
            <!--        </el-form-item>-->
            <el-form-item label="预期答辩时间" :label-width="formLabelWidth">
              <el-select v-model="createGroupForm.population" placeholder="预期答辩时间" />
            </el-form-item>
            <el-form-item label="小组信息" :label-width="formLabelWidth">
              <el-input
                v-model="createGroupForm.information"
                autocomplete="off"
                maxlength="50"
                show-word-limit
              />
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="submitForm('createGroupForm',createGroupForm)">确 定
            </el-button>
          </div>
        </el-dialog>

        <el-table
          ref="filterTable"
          :data="tableData2"
          style="width: 100%"
          :height="hei"
          @selection-change="handleSelectionChange"
        >
          <!--        <el-table-column-->
          <!--          type="index"-->
          <!--          :index="index"-->
          <!--        />-->

          <el-table-column
            align="center"
            prop="select"
            type="selection"
            width="55"
          />
          <el-table-column
            align="center"
            prop="stu_id"
            label="学号"
            sortable
            width="90"
          />
          <el-table-column
            align="center"
            prop="name"
            label="姓名"
            sortable
            width="80"
          />
          <el-table-column
            align="center"
            prop="gender"
            label="性别"
            sortable
            width="100"
            :filters="[{ text: '女', value: '女' },{ text: '男', value: '男' }, { text: '其他', value: '其他' }]"
            :filter-method="filterHandler"
          />
          <el-table-column
            align="center"
            prop="lab"
            label="Lab"
            sortable
            width="70"
          />
          <el-table-column
            align="center"
            prop="dorm"
            label="宿舍"
            sortable
            width="110"
          />
          <el-table-column
            align="center"
            prop="skill"
            label="技能"
            sortable
          > <template slot-scope="scope">
            <!--eslint-disable-next-line-->
            <el-tag v-for="item in scope.row.skill" effect="dark">{{ item }}</el-tag>
          </template>
          </el-table-column>
          <el-table-column
            align="center"
            prop="hope"
            label="期待队友类型"
            sortable
            width="130"
          />
          <el-table-column
            align="center"
            prop="status"
            label="状态"
            sortable
            width="100"
            :filters="[{ text: '未组队', value: '未组队' }, { text: '已组队', value: '已组队' }]"
            :filter-method="filterstatus"
            filter-placement="bottom-end"
          >
            <template slot-scope="scope">
              <el-tag
                :type="scope.row.status === '未组队' ? 'primary' : 'success'"
                disable-transitions
              >{{ scope.row.status }}
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column
            align="center"
            fixed="right"
            label="操作"
            width="120"
          >
            <template slot-scope="scope">
              <el-button
                type="danger"
                size="small"
                @click.native.prevent="miao(scope.row.stu_id, scope.row.gro_id, scope.row.status)"
              >移出小组
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import { get_AllStudents } from '@/api/teacher/personal'
import { get_AllUngroupedStudents } from '@/api/teacher/personal'
// import { getToken } from '@/utils/auth'
import { fetchTheGroup } from '@/api/teacher/group'
import { dropMyGroup } from '@/api/student/group'

export default {
  name: 'DragSelectDemo',
  data() {
    return {
      hei: window.innerHeight * 0.75,
      index: 0,
      createGroupForm: {
        name: '',
        population: '',
        information: ''
      },
      tableData22:
            [],
      //       [{
      //   name: '张小虎',
      //   SID: '11812100',
      //   gender: '男',
      //   lab: 2,
      //   skill: ['SPRING BOOT', 'lala', 'testlongest', 'lalalala', 'disanhang'],
      //   hope: '不搞基',
      //   status: '已组队'
      // }],

      tableData_of_OneGroup:
      null,
      form: {
        skill: '',
        expect: ''
      },
      dialogFormVisible1: false,
      dialogFormVisible: false,
      formLabelWidth: '120px',
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
    this.get_AllStudent_table()
    this.get_AllTags_table()
  },
  methods: {
    miao(stu_id, group_id, status) {
      // alert(group_id)
      if (status == '未组队') {
        this.$message({
          message: '移出失败，该同学未进组',
          type: 'success'// ////////////////////////////////////////////////////
        })
      } else {
        dropMyGroup(stu_id, group_id).then(response => {
          console.log(response.message)
          if (response.message == 'Success!') {
            this.$message({
              message: '退出成功',
              type: 'success'
            })
          } else {
            this.$message.error(response.message)
          }
        })
      }
    },
    getGroup_by_name(name) {
      fetchTheGroup(name).then(response => {
        this.tableData_of_OneGroup = response.myGroups
        this.listLoading = false
      })
    },
    get_AllStudent_table() {
      get_AllStudents(localStorage.getItem('current_project_id')).then(response => {
        this.tableData22 = response.data
        this.get_AllUngroupedStudent_table()
      })
    },
    get_AllUngroupedStudent_table() {
      get_AllUngroupedStudents(localStorage.getItem('current_project_id')).then(response => {
        var length = response.data.length
        for (var i = 0; i < length; i++) {
          this.tableData22.push(response.data[i])
        }
      })
    },
    remove_from_group(row) {
      // console.log(row)
      if (row.status === '未组队') {
        this.$alert('该同学还未组队，尝试选择其他人吧！', '移出失败', {
          confirmButtonText: '确定'
        })
        return
      } else {
        // 先post改后端，最后改前端
        row.status = '未组队'
        this.$message({
          message: '移出成功！',
          type: 'success'
        })
      }
    },
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
    open() {
      this.$alert('有人已经组队成功啦，尝试选择其他人吧！', '组队失败', {
        confirmButtonText: '确定'
      })
    },
    handleClick() {
      // console.log(this.multipleSelection)
      // console.log(this.multipleSelection == null)
      if (this.multipleSelection == null) {
        this.$alert('请选择想组队的同学！', '组队失败', {
          confirmButtonText: '确定'
        })
        return
      }
      for (var i = 0; i < this.multipleSelection.length; i++) {
        if (this.multipleSelection[i].status === '已组队') {
          this.open()
          return
        }
      }
      this.dialogFormVisible = true
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
      console.log(this.multipleSelection)
    },
    submitForm(ruleForm, ruleForm2) {
      this.dialogFormVisible = false
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
//
//.t_border3_1_large::-webkit-scrollbar { /*滚动条整体*/
//  width: 10px;
//}
//
//.t_border3_1_large::-webkit-scrollbar-track { /*滚动条轨道*/
//  background: #ffffff;
//  border-radius: 20px;
//  margin-top: 240px;
//  margin-bottom: 40px;
//}
//
//.t_border3_1_large::-webkit-scrollbar-thumb { /*滚动条里面的滑块*/
//  background: $primary;
//  border-radius: 10px;
//}
//
//.t_border3_1_large::-webkit-scrollbar-corner { /*滚动条边角*/
//  background: $primary;
//}

.t_border3_1_large {
  //height: 900px;
  height: 100%;
  width: 100%;
  border: 2px solid $primary;
  //margin-left: 150px;
  border-radius: 50px;
  transform: translate(0, 0);
  transition: all 0.3s ease-in-out;
  box-shadow: 10px 10px 20px $primary;
  margin-top: 50px;
  padding-left: 20px;
  padding-right: 20px;
}

.t_border3_1_large:hover {
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
.el-tag + .el-tag {
  margin-left: 10px;
  margin-bottom: 5px;
}
.el-table__body-wrapper::-webkit-scrollbar { /*滚动条整体*/
  width: 10px;
}

.el-table__body-wrapper::-webkit-scrollbar-track { /*滚动条轨道*/
  background: #ffffff;
  border-radius: 20px;
  //margin-top: 30px;
  margin-bottom: 30px;

}

.el-table__body-wrapper::-webkit-scrollbar-thumb { /*滚动条里面的滑块*/
  background: $primary;
  border-radius: 10px;
}

.el-table__body-wrapper::-webkit-scrollbar-corner { /*滚动条边角*/
  background: $primary;
}
</style>
