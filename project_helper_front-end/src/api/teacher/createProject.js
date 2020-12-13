import request from '@/utils/request'


export function new_project_insert(data) {
  return request({
      url: '/vue-element-admin/teacher/project/new_project_insert',
      method: 'post',
      data
  })
}

export function new_project_insert2(data) {
  return request({
      url: '/vue-element-admin/teacher/project/new_project_insert2',
      method: 'post',
      data
  })
}

export function new_project_update(data) {
  return request({
      url: '/vue-element-admin/teacher/project/new_project_update',
      method: 'post',
      data
  })
}

export function get_allCourse(token) {
  return request({
      url: '/vue-element-admin/teacher/project/get_course',
      method: 'get',
      params: {token}
  })
}