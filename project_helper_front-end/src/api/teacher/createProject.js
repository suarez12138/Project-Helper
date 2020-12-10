import request from '@/utils/request'


export function new_Project(data) {
  return request({
      url: '/vue-element-admin/teacher/project/new_project',
      method: 'post',
      data
  })
}