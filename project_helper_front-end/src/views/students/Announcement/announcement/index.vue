<template>
  <div class="frame">
    <div class="an_title">{{ an.name }}</div>
    <div class="by_time">{{ an.by }}</div>
    <div class="by_time">{{ an.time }}</div>
    <div class="content">{{ an.message }}</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      an: { name: 'Title', by: 'Teacher 1', time: '2020.11.23', message: 'This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.This is content.' }
    }
  }
}
</script>

<style>

.an_title,.by_time,.content{
  color: #ffffff;
}
.content{
  font-size: 25px;
  margin: 20px 60px 60px 60px;
  overflow-y: auto;
  height: 600px;
}

.content::-webkit-scrollbar{/*滚动条整体*/
  width:10px;
}
.content::-webkit-scrollbar-track{/*滚动条轨道*/
  /*background:#999;*/
  background:#c52d47;
  border-radius:20px;

}
.content::-webkit-scrollbar-thumb{/*滚动条里面的滑块*/
  background:#999;
  border-radius:10px;
}
/*.content::-webkit-scrollbar-thumb:hover{!*滚动条鼠标事件，鼠标放上去出现的事件*!*/
/*  background:#ffffff;*/
/*}*/

.content::-webkit-scrollbar-corner{/*滚动条边角*/
  background:#c52d47;
}

.by_time{
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
