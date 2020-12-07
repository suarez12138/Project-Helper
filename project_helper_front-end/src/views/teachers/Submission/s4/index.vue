<template>
  <div class="components-container">
    <div id="t_border3_1">
      <div class="title">{{ title }}</div>
      <div class="ddl">DDL: {{ ddl }}</div>
      <div class="content2">{{ content }}</div>
      <div class="score">Score: {{ score }}</div>
      <div class="remark">Remark: {{ remark }}</div>
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
        style="margin-left: 60px;margin-right: 60px;"
      >
        <el-button size="small" plain type="primary">上传</el-button>
      </el-upload>
    </div>
  </div>
</template>

<script>
// import { fetchList } from '@/api/article'

export default {
  name: 'DndListDemo',
  data() {
    return {
      title: 'This is title',
      ddl: '2020-12-9 22:00',
      content: 'this is content',
      score: '90',
      remark: 'this is remark.',
      fileList: [{
        name: 'food.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
      }, {
        name: 'food2.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
      }]
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
  methods: {
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

#t_border3_1 {
  height: 100%;
  width: 60%;
  border: 2px solid $primary;
  margin-left: 300px;
  padding-left: 20px;
  padding-right: 20px;
}

#t_border3_1 {
  border-radius: 50px;
  transform: translate(0, 0);
  transition: all 0.3s ease-in-out;
  box-shadow: 10px 10px 20px $primary;
}

#t_border3_1:hover {
  box-shadow: 20px 20px 20px $primary;
  transform: translate(-5px, -5px);
  transition: 0.3s ease-in-out;
}

.title {
  color: $primary;
  font-size: 40px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 20px;
  padding-bottom: 40px;
}

.juzhong {
  margin-left: 100px;
  margin-right: 100px;
  width: 75% !important;
}

.content2 {
  font-size: 25px;
  margin: 20px 60px 60px 60px;
  overflow-y: auto;
  height: 200px;
  //border: #2e1341 1px;
}

.score, .remark {
  //margin-left: 60px;
  margin: 20px 60px 30px 60px;
  font-size: 25px;
}

.content2::-webkit-scrollbar { /*滚动条整体*/
  width: 10px;
}

.content2::-webkit-scrollbar-track { /*滚动条轨道*/
  /*background:#999;*/
  background: #999;
  border-radius: 20px;

}

.content2::-webkit-scrollbar-thumb { /*滚动条里面的滑块*/
  background: $primary;
  border-radius: 10px;
}

.search_icon2 {
  color: $primary;
}

.ddl {
  color: $primary;
  font-size: 20px;
  text-align: center;
  padding-bottom: 30px;
}
</style>
