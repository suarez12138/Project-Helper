<template>
  <div class="components-container">

    <div id="main" style="width: 1200px;height: 800px;" />
  </div>
</template>

<script>
import echarts from 'echarts'
export default {
  name: '',
  data() {
    return {
      charts: '',
      opinion: ['<60', '>=60'],
      opinionData: [
        { value: 35, name: '<60' },
        { value: 70, name: '>=60' }

      ]

    }
  },
  mounted() {
    this.$nextTick(function() {
      this.drawPie('main')
    })
  },

  methods: {
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
