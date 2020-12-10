import request from '@/utils/request'


export function get_scoreList(project_id) {
  return request({
    url: '/vue-element-admin/teacher/grade/onlineGrading',
    method: 'get',
    params: {project_id}
  })
}









