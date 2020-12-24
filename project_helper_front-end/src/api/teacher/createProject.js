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



export function get_projectInfo(project_id) {
  return request({
      url: '/vue-element-admin/teacher/project/get_proj_info',
      method: 'get',
      params: {project_id}
  })
}


export function update_projectInfo(data) {
  return request({
      url: '/vue-element-admin/teacher/project/update_project',
      method: 'post',
      data
  })
}