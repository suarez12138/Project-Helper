import request from '@/utils/request'

export function get_profile(token) {
  return request({
    url: '/vue-element-admin/student/profile/detail_profile',
    method: 'get',
    params: { token }
  })
}
export function get_score(token) {
  return request({
    url: '/vue-element-admin/student/profile/get_score',
    method: 'get',
    params: { token }
  })
}

export function update_profile(data) {
  return request({
    url: '/vue-element-admin/student/profile/update_profile',
    method: 'post',
    data
  })
}

