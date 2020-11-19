<template>
  <div class="components-container">
    <split-pane split="vertical" @resize="resize">
      <template slot="paneL">
        <div id="border2_1">
          <div class="title1">Announcements</div>
          <!--          <ol class="title">-->
          <!--eslint-disable-next-line-->
          <div v-for="site in sites" class="announcetitle">
            Title: {{ site.name }} <br>
          </div>
          <!--          </ol>-->
        </div>
      </template>
      <template slot="paneR">
        <a href="/#/">
          <div id="border2_2">
            <div class="title2">My Group</div>
          </div>
        </a>
        <a href="/#/studentGroup/studentGroup">
          <div id="border2_3">
            <div class="title2">Grouping Information</div>
          </div>
        </a>
        <a href="/#/personal/personal">
          <div id="border2_4">
            <div class="title2">Personal Information</div>
          </div>
        </a>
      </template>
    </split-pane>
  </div>
</template>

<script>
// import elDragDialog from '@/directive/el-drag-dialog' // base on element-ui

import splitPane from 'vue-splitpane'

export default {
  name: 'DragDialogDemo',
  // directives: { elDragDialog },
  components: { splitPane },
  data() {
    return {
      dialogTableVisible: false,
      sites: [
        { name: 'check your progress', project: 'project3' },
        { name: 'tips', project: 'project4' },
        { name: 'announce DDL', project: 'project1' }
      ]
    }
  },
  methods: {
    // v-el-drag-dialog onDrag callback function
    handleDrag() {
      this.$refs.select.blur()
    },
    resize() {
      console.log('resize')
    }
  }
}
</script>

<style>

.components-container {
  position: relative;
  height: 100vh;
}

#border2_1 {
  height: 100%;
  box-shadow: 0 0 40px #257983;
  background: linear-gradient(to right top, #604C7C 0%, #257983 50%, #20825A 100%);

}

#border2_2 {
  height: 31%;
  box-shadow: 0 0 40px #2d5d85;
  background: linear-gradient(135deg, #1f224b 0%, #2d5d85 100%);
  margin-bottom: 20px;
}

#border2_3 {
  height: 31%;
  box-shadow: 0 0 40px #673dc2;
  background: linear-gradient(135deg, #c6368a 0%, #673dc2 100%);
  margin-bottom: 20px;
}

#border2_4 {
  height: 31%;
  box-shadow: 0 0 40px #10071c;
  background: linear-gradient(135deg, #590a0d 0%, #10071c 100%);
}

.title2, .title1 {
  color: #cccccc;
  font-size: 40px;
  transition: 0.2s ease-in-out;
  text-align: center;
  padding-top: 15px;
  margin-bottom: 40px;
}

.title2 {
  padding-top: 100px;
}

.announcetitle {
  color: #dddddd;
  font-size: 20px;
  transition: 0.2s ease-in-out;
  text-align: center;
  margin-top: 30px;
}

#border2_1, #border2_2, #border2_3, #border2_4 {
  width: 100%;
  border-radius: 50px;
  transition: all 0.3s ease-in-out;
  transform: translate(0, 0);
}

#border2_1:hover .title1, #border2_1:hover .announcetitle, #border2_2:hover .title2, #border2_3:hover .title2, #border2_4:hover .title2 {
  color: #ffffff;

}

#border2_1:hover, #border2_2:hover, #border2_3:hover, #border2_4:hover {
  transform: translate(-2px, -8px);
  transition: all 0.3s ease-in-out;
}

#border2_1:hover {
  box-shadow: 12px 20px 20px #257983;
}

#border2_2:hover {
  box-shadow: 12px 20px 20px #2d5d85;
}

#border2_3:hover {
  box-shadow: 12px 20px 20px #673dc2;
}

#border2_4:hover {
  box-shadow: 12px 20px 20px #10071c;
}

</style>
