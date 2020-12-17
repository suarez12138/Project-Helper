<template>
  <div class="components-container">

    <div id="border3_2">
      <div @click="hideTooltip">
        <div v-if="show_tooltip" class=" title_3" @click.stop>
          <el-input v-model="text_content" class="juzhong" type="text" />
        </div>
        <div class="title_3" @click.stop="toggleTooltip">{{ text_content }}</div>
      </div>
      <!--          <div class="title">My Group</div>-->
      <!--          <el-popover-->
      <!--            placement="bottom"-->
      <!--            title="标题"-->
      <!--            width="200"-->
      <!--            trigger="click">-->
      <!--            content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。">-->
      <!--            <input-->
      <!--              class="edictInput"-->
      <!--              type="text"-->
      <!--              v-model="editAssumption"-->
      <!--              ref="focusAssumptionInput"-->
      <!--              placeholder="请输入内容"-->
      <!--              autofocus="autofocus"-->
      <!--              v-if="scopeAssumption.row.assumptionFlag"/>-->
      <!--            <el-button slot="reference" style="position: relative;float: left;margin-left: 10px;margin-top: 20px;">修改-->
      <!--            </el-button>-->
      <!--          </el-popover>-->
      <el-table
        ref="filterTable"
        v-loading="listLoading"
        class="juzhong"
        :data="MyGroupTableData"
        style="width: 100%"
      >
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
          prop="skill"
          label="技能"
          sortable
          :formatter="formatter"
        />
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
          <el-select v-model="myGroupForm.population" placeholder="预期答辩时间" />
        </el-form-item>
        <el-form-item label="小组信息" prop="information">
          <el-input
            v-model="myGroupForm.information"
            type="textarea"
            maxlength="50"
            show-word-limit
          />
        </el-form-item>

        <!--            <el-form-item label="提交项目" prop="submitProject">-->
        <!--              <el-upload-->
        <!--                class="upload-demo"-->
        <!--                action="https://jsonplaceholder.typicode.com/posts/"-->
        <!--                :on-preview="handlePreview"-->
        <!--                :on-remove="handleRemove"-->
        <!--                :before-remove="beforeRemove"-->
        <!--                multiple-->
        <!--                :limit="5"-->
        <!--                :on-exceed="handleExceed"-->
        <!--                :file-list="fileList"-->
        <!--              >-->
        <!--                <el-button size="small" plain type="primary">点击上传</el-button>-->
        <!--                &lt;!&ndash;                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>&ndash;&gt;-->
        <!--              </el-upload>-->
        <!--            </el-form-item>-->
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
          @confirm="quit"
        >
          <el-button slot="reference" class="flo" type="danger" style="float: right;" plain>退出该组</el-button>
        </el-popconfirm>
        <!--            <el-dialog-->
        <!--              title="提示"-->
        <!--              :visible.sync="dialogVisible"-->
        <!--              width="30%"-->
        <!--              :before-close="handleClose"-->
        <!--              append-to-body>-->
        <!--              <span>确定退出该组吗？</span>-->
        <!--              <span slot="footer" class="dialog-footer">-->
        <!--                <el-button @click="dialogVisible = false">取 消</el-button>-->
        <!--                <el-button type="primary" @click="dialogVisible = false">确 定</el-button>-->
        <!--              </span>-->
        <!--            </el-dialog>-->

      </el-form>
    </div>
  </div>
</template>

<script>

import { fetchMyGroup } from '@/api/student/group'
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
      myGroupForm: {
        population: '',
        information: ''
      }
    }
  },
  created() {
    this.getMyGroup()
  },
  methods: {

    getMyGroup() { // 应该传一些学号什么的回去
      this.listLoading = true
      console.log(getToken())
      console.log('aaaaaaaaaaaaaa')
      // fetchMyGroup(getToken(), 1).then(response => {
      fetchMyGroup(getToken(), localStorage.getItem('current_project_id')).then(response => {
        this.MyGroupTableData = response.myGroups
        this.listLoading = false
      })
    },
    handleChange(value) {
      console.log(value)
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
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    quit() {
    }

    // submitForm(formName) {
    //   this.$refs[formName].validate((valid) => {
    //     if (valid) {
    //       alert('submit!')
    //     } else {
    //       console.log('error submit!!')
    //       return false
    //     }
    //   })
    // }
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
