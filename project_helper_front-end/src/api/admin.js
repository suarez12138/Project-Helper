import request from '@/utils/request'

export function createUser(username, password, role) {
  return request({
    url: '/vue-element-admin/admin/createUser',
    method: 'post',
    params: { username, password, role }
  })
}

export function changeUser(username, role) {
  return request({
    url: '/vue-element-admin/admin/changeUser',
    method: 'post',
    params: { username, role }
  })
}
