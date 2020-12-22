<template>
  <div class="components-container">

    <div id="main" style="width: 1200px;height: 800px;" />
  </div>
</template>

<script>
import echarts from 'echarts'
import { get_grades_distribution } from '@/api/teacher/onlineGrading'
export default {
  name: '',
  data() {
    return {
      charts: '',
      opinion: ['<60', '60-70', '70-80', '80-90', '90-100'],
      opinionData: [
        { value: 1151, name: '<60' },
        { value: 1110, name: '60-70' },
        { value: 1000, name: '70-80' },
        { value: 1000, name: '80-90' },
        { value: 1000, name: '90-100' }

      ]

    }
  },
  mounted() {
    this.$nextTick(function() {
      this.drawPie('main')
    })
  },

  methods: {
    get_data() {
      get_grades_distribution(localStorage.getItem('current_project_id')).then(response => {

      })
    },
    drawPie(id) {
      this.charts = echarts.init(document.getElementById(id))
      this.charts.setOption({
        tooltip: {
          trigger: 'item'

        },
        legend: {
          orient: 'vertical',
          x: 'left',
          data: this.opinion
        },
        series: [
          {
            name: 'Project 成绩分布',
            type: 'pie',
            radius: ['10%', '70%'],
            avoidLabelOverlap: false,
            label: {
              normal: {
                show: false,
                position: 'center'
              },
              emphasis: {
                show: true,
                textStyle: {
                  fontSize: '30',
                  fontWeight: 'blod'
                }
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data: this.opinionData
          }
        ]
      })
    }

  }

}
</script>

<style>
.components-container {
  position: relative;
  height: 100vh;
}
</style>
