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
      opinionData : [
          // {value: 0,
          //  name: '<60'},
          // {value: 0,
          //  name: '60-70'},
          // {value: 0,
          //  name: '70-80'},
          // {value: 0,
          //  name: '80-90'},
          // {value: 500,
          //  name: '90-100'}
          ]

    }
  },
  // mounted() {
  //   this.$nextTick(function() {
  //     this.drawPie('main')
  //   })
  // },
  created() {
    this.get_data()
  },
  methods: {
    get_data() {
      get_grades_distribution(localStorage.getItem('current_project_id')).then(response => {
        // alert(response.data[0].grade_E)
        this.opinionData.push({
          value: response.data[0].grade_E,
          name: '<60'
          })
        this.opinionData.push({
          value: response.data[0].grade_D,
          name: '60-70'
          })
        this.opinionData.push({
          value: response.data[0].grade_C,
          name: '70-80'
          })
        this.opinionData.push({
          value: response.data[0].grade_B,
          name: '80-90'
          })
        this.opinionData.push({
          value: response.data[0].grade_A,
          name: '90-100'
          })
        this.drawPie('main')
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
