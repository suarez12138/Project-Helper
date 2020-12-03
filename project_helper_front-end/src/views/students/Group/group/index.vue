<template>
  <div class="components-container">
    <split-pane split="vertical" @resize="resize">
      <template slot="paneL">
        <div id="border3_1">
          <div class="title_3">Grouping Information</div>
          <div class="search-Box" style="padding-top: 20px;padding-left: 40px;">
            <svg-icon icon-class="search" class="search_icon5" />
            <el-input
              v-model="search"
              placeholder="请输入关键字"
              class="search2"
              style="width: 30%;"
            />
          </div>
          <el-table
            ref="filterTable"
            v-loading="listLoading"
            :data="tableData3"
            style="width: 100%"
            highlight-current-row
            @current-change="handleCurrentChange"
          >
            <el-table-column
              type="index"
              :index="indexMethod"
            />
            <el-table-column
              prop="name"
              label="组名"
              sortable
              width="100"
            >
              <template slot-scope="scope">
                <el-popover trigger="click" placement="right" class="pout">
                  <!--                  <div id="border3_3">-->
                  <div class="title">Selected Group</div>
                  <el-table
                    ref="filterTable"
                    v-loading="listLoading"
                    class="juzhong"
                    :data="tableData2"
                    style="width: 100%"
                  >
                    <el-table-column
                      prop="name"
                      label="姓名"
                      sortable
                      width="180"
                    />
                    <el-table-column
                      prop="gender"
                      label="性别"
                      sortable
                      width="180"
                      :filters="[{ text: '女', value: '女' },{ text: '男', value: '男' }, { text: '其他', value: '其他' }]"
                      :filter-method="filterHandler"
                    />
                    <el-table-column
                      prop="skill"
                      label="技能"
                      sortable
                      :formatter="formatter"
                    />
                  </el-table>
                  <div class="juzhong" style="font-size: 20px;margin-top: 30px;margin-bottom: 15px;">小组信息：</div>
                  <div class="juzhong">不搞基</div>
                  <!--                  </div>-->
                  <!--                  <p>张三 SPRINGBOOT</p>-->
                  <!--                  <p>李四 VUE</p>-->
                  <div slot="reference" class="name-wrapper">
                    <el-tag size="medium" @click="returnrow()">{{ scope.row.name }}</el-tag>
                  </div>
                </el-popover>
              </template>
            </el-table-column>
            <el-table-column
              prop="pre_time"
              label="预期答辩时间"
              sortable
              width="130"
            />
            <el-table-column
              prop="information"
              label="小组信息"
              sortable
            />
            <!--              width="250"-->
            <el-table-column
              prop="status"
              label="状态"
              sortable
              width="120"
              :filters="[{ text: '完成组队', value: '完成组队' }, { text: '未完成组队', value: '未完成组队' }]"
              :filter-method="filterstatus"
              filter-placement="bottom-end"
            >
              <template slot-scope="scope">
                <el-tag
                  :type="scope.row.status === '未完成组队' ? 'primary' : 'success'"
                  disable-transitions
                >{{ scope.row.status }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column
              fixed="right"
              label="操作"
              width="100"
            >
              <template slot-scope="scope">
                <el-button type="text" size="small" @click="handleClick(scope.row)">加入</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </template>
      <template slot="paneR">
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
            :data="tableData2"
            style="width: 100%"
          >
            <el-table-column
              prop="name"
              label="姓名"
              sortable
              width="100"
            />
            <el-table-column
              prop="gender"
              label="性别"
              sortable
              width="100"
              :filters="[{ text: '女', value: '女' },{ text: '男', value: '男' }, { text: '其他', value: '其他' }]"
              :filter-method="filterHandler"
            />
            <el-table-column
              prop="skill"
              label="技能"
              sortable
              :formatter="formatter"
            />
          </el-table>
          <el-form class="juzhong" style="margin-top: 40px;">
            <el-form-item label="预期答辩时间">
              <el-select v-model="ruleForm.population" placeholder="预期答辩时间" />
            </el-form-item>
            <el-form-item label="小组信息" prop="information">
              <el-input
                v-model="ruleForm.information"
                type="textarea"
                maxlength="50"
                show-word-limit
              />
            </el-form-item>
            <el-form-item label="提交项目" prop="submitProject">
              <el-upload
                class="upload-demo"
                action="https://jsonplaceholder.typicode.com/posts/"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :before-remove="beforeRemove"
                multiple
                :limit="5"
                :on-exceed="handleExceed"
                :file-list="fileList"
              >
                <el-button size="small" plain type="primary">点击上传</el-button>
                <!--                <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
              </el-upload>
            </el-form-item>
            <el-form-item class="flo" style="margin-left: 20px">
              <el-button type="primary" plain @click="submitForm('ruleForm')">确认</el-button>
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
            <el-switch
              v-model="value2"
              class="flo"
              style="display: block;  margin-top: 10px;  margin-right: 70px; float: right;"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-text="开放加入"
              inactive-text="不开放加入"
              @change="handleChange(value2)"
            />
          </el-form>
        </div>
      </template>
    </split-pane>

    <!--    <aside>drag-list base on-->
    <!--      <a href="https://github.com/SortableJS/Vue.Draggable" target="_blank">Vue.Draggable</a>-->
    <!--    </aside>-->
    <!--    <div class="editor-container">-->
    <!--      <dnd-list :list1="list1" :list2="list2" list1-title="List" list2-title="Article pool" />-->
    <!--    </div>-->
  </div>
</template>

<script>
import splitPane from 'vue-splitpane'
// import DndList from '@/components/DndList'
import { fetchGroupsList } from '@/api/group'
import { fetchMyGroup } from '@/api/group'
import { fetchList } from '@/api/article'
import { getToken } from '@/utils/auth'

export default {
  name: 'DndListDemo',
  components: { splitPane },
  data() {
    return {
      show_tooltip: false,
      text_content: 'My Group',
      fileList: [{
        name: 'food.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
      }, {
        name: 'food2.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
      }],
      dialogVisible: false,
      value2: true,
      list1: [],
      list2: [],
      textarea: '',
      tableData33: [
        //   {
        //   name: '组1',
        //   pre_time: '周五上午',
        //   information: '不搞基',
        //   status: '完成组队'
        // }, {
        //   name: '组13',
        //   pre_time: '周四下午',
        //   information: '不划水',
        //   status: '未完成组队'
        // }, {
        //   name: '组32',
        //   pre_time: '周五上午',
        //   status: '完成组队'
        // }, {
        //   name: '组3',
        //   pre_time: '周四下午',
        //   status: '未完成组队'
        // }
        null
      ],
      tableData2:
        null,
      // [
      //   {
      //   name: '张小虎',
      //   gender: '男',
      //   skill: 'SPRING BOOT'
      // }, {
      //   name: '王小虎',
      //   gender: '女',
      //   skill: 'VUE'
      // }, {
      //   name: '王小虎'
      // }, {
      //   name: '王小虎'
      // }
      // ],
      ruleForm: {
        population: '',
        information: ''
      },
      listLoading: false,
      search: ''
    }
  },
  computed: {
    tableData3: function() {
      var search = this.search
      if (search) {
        return this.tableData33.filter(function(dataNews) {
          return Object.keys(dataNews).some(function(key) {
            return String(dataNews[key]).toLowerCase().indexOf(search) > -1
          })
        })
      }
      return this.tableData33
    }
  },
  created() {
    this.getAllGroups()
    this.getMyGroup()
  },
  methods: {
    getAllGroups() {
      this.listLoading = true
      fetchGroupsList().then(response => {
        this.tableData33 = response.data.items
        this.listLoading = false
      })
    },
    getMyGroup() { // 应该传一些学号什么的回去
      this.listLoading = true
      console.log(getToken())
      console.log('aaaaaaaaaaaaaa')
      fetchMyGroup(getToken()).then(response => {
        this.tableData2 = response.data.items
        this.listLoading = false
      })
    },
    hideTooltip: function() {
      // 在模型改变时，视图也会自动更新
      this.show_tooltip = false
    },
    returnrow(row) {
      console.log(row)
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
    indexMethod(index) {
      return index
    },
    handleCurrentChange(val) {
      this.currentRow = val
      console.log(this.currentRow)
    },
    open() {
      this.$alert('此组已完成组队，尝试加入其他组吧！', '组队失败', {
        confirmButtonText: '确定'
      })
    },
    quit() {
    },
    handleClick(row) {
      if (row.status === '完成组队') {
        this.open()
      }
    },
    handleChange(value) {
      console.log(value)
    },
    formatter(row, column) {
      return row.skill
    },
    filterstatus(value, row) {
      return row.tag === value
    },
    resize() {
      console.log('resize')
    },
    filterHandler(value, row, column) {
      const property = column['property']
      return row[property] === value
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
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    handlePreview(file) {
      console.log(file)
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`)
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
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
  margin-left: 15px;
}

#border3_1 {
  height: 100%;
  overflow-y: auto;
  margin-right: 15px;
}

#border3_1, #border3_2 {
  border-radius: 50px;
  transform: translate(0, 0);
  transition: all 0.3s ease-in-out;
  box-shadow: 0 0 40px $primary;
  border: 2px solid $primary;
}

#border3_1:hover, #border3_2:hover {
  transform: translate(-2px, -8px);
  transition: all 0.3s ease-in-out;
  box-shadow: 12px 20px 20px $primary;
}

#border3_1::-webkit-scrollbar { /*滚动条整体*/
  width: 10px;
}

#border3_1::-webkit-scrollbar-track { /*滚动条轨道*/
  /*background:#999;*/
  background: #ffffff;
  border-radius: 20px;
  margin-top: 40px;
  margin-bottom: 40px;
}

#border3_1::-webkit-scrollbar-thumb { /*滚动条里面的滑块*/
  background: $primary;
  border-radius: 10px;
}

/*#border3_1::-webkit-scrollbar-thumb:hover{!*滚动条鼠标事件，鼠标放上去出现的事件*!*/
/*  background:#ffffff;*/
/*}*/
#border3_1::-webkit-scrollbar-corner { /*滚动条边角*/
  background: $primary;
}

.title_3 {
  color: $primary;
  font-size: 30px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 20px;
  /*float: left;*/
  /*margin-left: 300px;*/
  /*position: relative;*/
}

/*.el-input__inner{*/
/*  transition: 0.2s ease-in-out;*/
/*}*/
.flo {
  position: relative;
  float: left;
}

.juzhong {
  margin-left: 100px;
  margin-right: 100px;
  width: 75% !important;
}

.search_icon5 {
  margin-right: 10px;
  margin-top: 10px;
  color: $primary;

}
</style>
