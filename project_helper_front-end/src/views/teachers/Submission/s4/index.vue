<template>
  <div class="components-container">
    <div v-if="checkPermission(['student'])" class="t_border3_1">
      <div class="title">{{ title }}</div>
      <div class="ddl">DDL: {{ ddl }}</div>
      <div class="content2">{{ content }}</div>
      <div class="attachment">Attachments:</div>
      <!--eslint-disable-next-line-->
      <a v-for="item in tea_fileList" @click="download()">
        <div class="filename">{{ item.name }}</div>
      </a>
      <el-upload
        class="upload-demo"
        action="http://localhost:8443/upload/singlefile"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :before-remove="beforeRemove"
        multiple
        :limit="5"
        :on-exceed="handleExceed"
        :on-change="fileChange"
        :on-success="handleSuccess"
        :file-list="stu_fileList"
        style="margin: 30px 60px 30px 60px;"
      >
        <el-button size="small" plain type="primary">上传</el-button>
      </el-upload>
      <div class="score">Score: {{ score }}</div>
      <div class="remark">Remark: {{ remark }}</div>

    </div>
    <div v-else-if="checkPermission(['teacher'])" class="t_border3_1">
      <div class="title">{{ title }}</div>
      <div class="ddl">DDL: {{ ddl }}</div>
      <div class="content2">{{ content }}</div>
      <div class="attachment">Attachments:</div>
      <!--eslint-disable-next-line-->
      <a v-for="item in tea_fileList" @click="download()">
        <div class="filename">{{ item.name }}</div>
      </a>
      <!--      <div class="score">Score: {{ score }}</div>-->
      <!--      <div class="remark">Remark: {{ remark }}</div>-->

    </div>
  </div>
</template>

<script>
// import { fetchList } from '@/api/article'

import checkPermission from '@/utils/permission'
import axios from 'axios'

export default {
  name: 'DndListDemo',
  data() {
    return {
      title: 'This is title',
      ddl: '2020-12-9 22:00',
      content: 'this is content',
      score: '90',
      remark: 'this is remark.',
      tea_fileList: [{
        name: 'food.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
      }, {
        name: 'food2.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
      }],
      stu_fileList: [{
        name: 'food.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
      }],
      msg: 'Welcome to Your Vue.js App',
      file: ''
    }
  },
  methods: {
    getFile: function (event) {
      this.file = event.target.files[0];
      console.log(this.file);
    },
    submit: function (event) {
      //阻止元素发生默认的行为
      event.preventDefault();
      let formData = new FormData();
      formData.append("file", this.file);
      axios.post('/vue-element-admin/student/project/upload_file', formData)
        .then(function (response) {
          alert(response.data);
          console.log(response);
          window.location.reload();
        })
        .catch(function (error) {
          alert("上传失败");
          console.log(error);
          window.location.reload();
        });
    },
    handleRemove(file, stu_fileList) {
      console.log(file, stu_fileList)
    },
    checkPermission,
    handlePreview(file) {
      console.log(file)
    },
    handleExceed(files, stu_fileList) {
      this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + stu_fileList.length} 个文件`)
    },
    beforeRemove(file, stu_fileList) {
      return this.$confirm(`确定移除 ${file.name}？`)
    },
    handleSuccess(file) {

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

.t_border3_1 {
  height: 100%;
  width: 60%;
  border: 2px solid $primary;
  margin-left: 300px;
  padding-left: 20px;
  padding-right: 20px;
}

.t_border3_1 {
  border-radius: 50px;
  transform: translate(0, 0);
  transition: all 0.3s ease-in-out;
  box-shadow: 10px 10px 20px $primary;
}

.t_border3_1:hover {
  box-shadow: 20px 20px 20px $primary;
  transform: translate(-5px, -5px);
  transition: 0.3s ease-in-out;
}

.title {
  color: $primary;
  font-weight: bold;
  font-size: 40px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 20px;
  padding-bottom: 10px;
}

.juzhong {
  margin-left: 100px;
  margin-right: 100px;
  width: 75% !important;
}

.content2 {
  color: $primary;
  font-size: 25px;
  margin: 20px 60px 60px 60px;
  overflow-y: auto;
  height: 200px;
  //border: #2e1341 1px;
}

.attachment{
  color: $primary;
  font-size: 25px;
  font-weight: bold;
  margin: 20px 10px 10px 60px;
}
.filename{
  color: $primary;
  font-size: 20px;
  margin: 5px 10px 5px 60px;
}

.score, .remark {
  //margin-left: 60px;
  color: $primary;
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
  padding-bottom: 15px;
}
</style>
