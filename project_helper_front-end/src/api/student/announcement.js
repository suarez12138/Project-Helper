import request from '@/utils/request'


export function get_announcement_student(token) {
  return request({
    url: '/vue-element-admin/teacher/announcment/announcement_list',
    method: 'get',
    params: {token}
  })
}

export function post_announcement_student(data) {
    return request({
      url: '/vue-element-admin/teacher/announcment/update_announcement_list',
      method: 'post',
      data
    })
  }







