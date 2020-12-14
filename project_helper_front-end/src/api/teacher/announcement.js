import request from '@/utils/request'


export function get_announcement_teacher(token) {
  return request({
    url: '/vue-element-admin/teacher/dashboard/announcement_list',
    method: 'get',
    params: {token}
  })
}

export function get_announcement_content(announcement_id) {
    return request({
      url: '/vue-element-admin/teacher/dashboard/announcement_content',
      method: 'get',
      params: {announcement_id}
    })
}

export function post_announcement_teacher(data) {
    return request({
      url: '/vue-element-admin/teacher/dashboard/update_announcement_list',
      method: 'post',
      data
    })
  }

export function get_announcement_lab(token) {
    return request({
      url: '/vue-element-admin/teacher/dashboard/get_lab',
      method: 'get',
      params: {token}
    })
  }







