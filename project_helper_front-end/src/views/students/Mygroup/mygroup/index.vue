<template>
  <div class="components-container">

    <div id="border3_2">
      <div @click="hideTooltip">
        <div v-if="show_tooltip" class=" title_3" @click.stop>
          <el-input v-model="text_content" class="juzhong" type="text" />
        </div>
        <div class="title_3" @click.stop="toggleTooltip">{{ text_content }}</div>
      </div>
      <el-table
        ref="filterTable"
        v-loading="listLoading"
        class="juzhong"
        :data="MyGroupTableData"
        style="width: 100%"
      >
        <el-table-column
          align="center"
          prop="stu_id"
          label="学号"
          sortable
          width="100"
        />
        <el-table-column
          align="center"
          prop="name"
          label="姓名"
          sortable
          width="100"
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
          prop="tags"
          label="技能"
          sortable
        >
          <template slot-scope="scope">
            <!--eslint-disable-next-line-->
            <el-tag v-for="item in scope.row.tags" effect="dark">{{ item }}</el-tag>
          </template>
        </el-table-column>
      </el-table>
      <el-form class="juzhong" style="margin-top: 40px;">
        <el-form-item label="开放加入">
          <el-switch
            v-model="value2"
            active-color="#13ce66"
            inactive-color="#ff4949"
            active-text="是"
            inactive-text="否"
            @change="handleChange(value2)"
          />
        </el-form-item>
        <el-form-item label="预期答辩时间">
          <el-select v-model="myGroupForm.pre_week" placeholder="预期答辩时间">
            <el-option
              v-for="item in options"
              :key="item.check_point_id"
              :label="item.week"
              :value="item.checkPoint_id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="小组信息" prop="information">
          <el-input
            v-model="myGroupForm.text"
            type="textarea"
            maxlength="50"
            show-word-limit
          />
        </el-form-item>

        <el-form-item class="flo" style="margin-left: 20px">
          <el-button type="primary" plain @click="submitForm('myGroupForm')">确认</el-button>
        </el-form-item>

        <el-popconfirm
          confirm-button-text="确定"
          cancel-button-text="取消"
          icon="el-icon-info"
          icon-color="red"
          title="确定退出该组吗？"
          placement="top"
          @onConfirm="miao()"
        >
          <el-button slot="reference" class="flo" type="danger" style="float: right;" plain>退出该组</el-button>
        </el-popconfirm>

      </el-form>
    </div>
  </div>
</template>

<script>

import { fetchMyGroup } from '@/api/student/group'
import { fetchMyGroup_state } from '@/api/student/group'
// import { fetchTheGroup } from '@/api/student/group'
import { updateMyGroup_state } from '@/api/student/group'
import { dropMyGroup } from '@/api/student/group'
import { get_availableWeek } from '@/api/student/creatGroup'
import { getToken } from '@/utils/auth'

export default {
  data() {
    return {
      show_tooltip: false,
      sel_name: 'Title',
      text_content: 'My Group',
      dialogVisible: false,
      value2: true,
      list1: [],
      list2: [],
      project_dict: {},
      current_project: localStorage.getItem('current_project').substring(1, (localStorage.getItem('current_project').length - 1)),
      textarea: '',
      MyGroupTableData:
        null,
      listLoading: false,
      myGroupForm: null,
      myGroupState: null,
      options: null
    }
  },
  created() {
    this.getMyGroup()
    this.getMyGropuState()
    this.get_availableWeekList()
  },
  methods: {
    miao() {
      dropMyGroup(getToken(), this.myGroupForm.gro_id).then(response => {
        // alert(response.message)
      })
    },
    get_availableWeekList() {
      get_availableWeek(localStorage.getItem('current_project_id')).then(response => {
        this.options = response.data
      })
    },
    getMyGroup() { // 应该传一些学号什么的回去
      this.listLoading = true
      fetchMyGroup(getToken(), localStorage.getItem('current_project_id')).then(response => {
        this.MyGroupTableData = response.myGroups
        this.listLoading = false
        console.log(this.MyGroupTableData)
      })
    },
    getMyGropuState() {
      fetchMyGroup_state(getToken(), localStorage.getItem('current_project_id')).then(response => {
        this.myGroupForm = response.data[0]
        this.text_content = response.data[0].group_name
      })
    },
    handleChange(value) {
      if (value == true) {
        this.myGroupForm.group_status = '可加入'
      } else if (value == false) {
        this.myGroupForm.group_status = '不可加入'
      }
    },
    submitForm(formName) {
      if (this.myGroupForm.pre_week[0] != 'W') {
        this.myGroupForm.check_point_id = this.myGroupForm.pre_week
      }
      alert(this.myGroupForm.check_point_id)
      updateMyGroup_state(
        this.myGroupForm.gro_id,
        this.myGroupForm.group_status,
        this.myGroupForm.text,
        this.myGroupForm.check_point_id,
        this.text_content
      ).then(response => {
        alert('Update Success!')
      })
    },
    hideTooltip: function() {
      // 在模型改变时，视图也会自动更新
      this.show_tooltip = false
    },
    returnrow(row) {
      console.log(row)
      alert(row)
    },
    toggleTooltip: function() {
      this.show_tooltip = !this.show_tooltip
    },
    getData() {
      this.listLoading = true
      fetchList().then(response => {
        this.list1 = response.data.items
        this.list2 = response.data.items
      })
    },
    quit() {
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

.el-tag, .el-button, .el-input__inner, .el-textarea__inner {
  border-radius: 20px !important;
}

.el-popover {
  border: 1px dashed $primary;
  border-radius: 20px;
  box-shadow: 0 0 10px $primary;
  padding-bottom: 50px;
}

#border3_2 {
  height: 100%;
  width: 60%;
  border: 2px solid $primary;
  margin-left: 300px;
  border-radius: 50px;
  transform: translate(0, 0);
  transition: all 0.3s ease-in-out;
  box-shadow: 10px 10px 20px $primary;
}

 #border3_2:hover {
  transform: translate(-2px, -8px);
  transition: all 0.3s ease-in-out;
  box-shadow: 12px 20px 20px $primary;
}

/*#border3_1::-webkit-scrollbar-thumb:hover{!*滚动条鼠标事件，鼠标放上去出现的事件*!*/
/*  background:#ffffff;*/
/*}*/
//#border3_1::-webkit-scrollbar-corner { /*滚动条边角*/
//  background: $primary;
//}

.title_3 {
  font-weight: bold;
  color: $primary;
  font-size: 40px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 20px;
  padding-bottom: 40px;

}

.flo {
  position: relative;
  float: left;
}

.juzhong {
  margin-left: 100px;
  margin-right: 100px;
  width: 75% !important;
}

</style>
