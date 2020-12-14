<template>
  <!-- <div class="frame">
    <div class="an_title">{{ ann.ann_name }}</div>
    <div class="by_time">{{ ann.teacher_name }}</div>
    <div class="by_time">{{ ann.release_time }}</div>
    <div class="content">{{ ann.text }}</div>
  </div> -->


  <div class="frame">
        <!--eslint-disable-next-line-->
        <div v-for="an in ann"  class="announcetitle">

          <div class="an_title">{{ an.ann_name }}</div>
          <div class="by_time">{{ an.teacher_name }}</div>
          <div class="by_time">{{ an.release_time }}</div>
          <div class="content">{{ an.text }}</div>

        </div>
      </div>
</template>

<script>
import checkPermission from '@/utils/permission'
import { get_announcement_content } from '@/api/teacher/announcement/'

export default {
  data() {
    return {
      ann: 
      // null
      {
        ann_name: 'Title',
        teacher_name: 'Teacher 1',
        release_time: '2020.11.23',
        text: 'This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.'
      }
    }
  },
  
  created() {
    this.show_announcement_content()
  },
  methods: {
    checkPermission,
    show_announcement_content(){
      get_announcement_content(localStorage.getItem("current_announcement")).then(response => {
        this.ann = response.data
      })
    }

  }
}
</script>

<style>

.an_title, .by_time, .content {
  color: #ffffff;
}

.content {
  font-size: 25px;
  margin: 20px 60px 60px 60px;
  overflow-y: auto;
  height: 600px;
}

.content::-webkit-scrollbar { /*滚动条整体*/
  width: 10px;
}

.content::-webkit-scrollbar-track { /*滚动条轨道*/
  /*background:#999;*/
  background: #999;
  border-radius: 20px;

}

.content::-webkit-scrollbar-thumb { /*滚动条里面的滑块*/
  background: #c52d47;
  border-radius: 10px;
}

/*.content::-webkit-scrollbar-thumb:hover { !*滚动条鼠标事件，鼠标放上去出现的事件*!*/
/*  background: #8b366d;*/
/*}*/

.content::-webkit-scrollbar-corner { /*滚动条边角*/
  background: #999;
}

.by_time {
  font-size: 20px;
  padding-bottom: 20px;
  text-align: center;
}

.frame {
  height: 900px;
  box-shadow: 0 0 40px #c52d47;
  background: linear-gradient(215deg, #c52d47 0%, #a5325c 30%, #8b366d 100%);
  margin: 50px 15% 100px 10%;
  border-radius: 50px;
  transition: all 0.3s ease-in-out;
  transform: translate(0, 0);
}

.frame:hover {
  box-shadow: 12px 20px 20px #c52d47;
  transform: translate(-2px, -8px);
  transition: all 0.3s ease-in-out;
}

.an_title {
  font-size: 55px;
  text-align: center;
  padding-top: 40px;
  padding-bottom: 30px;
}
</style>
