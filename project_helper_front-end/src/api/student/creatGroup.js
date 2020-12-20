import request from '@/utils/request'

export function get_availableWeek(project_id) {
    return request({
      url: '/vue-element-admin/student/group/get_weeks',
      method: 'get',
      params: {project_id}
    })
  }

  export function create_group(data) {
    return request({
      url: '/vue-element-admin/student/group/create_group',
      method: 'post',
      data
    })
  }