import request from '@/utils/request'


export function fetchGroupsList() {
  return request({
    url: '/vue-element-admin/student/group/group_list',
    method: 'get'
  })
}


export function fetchMyGroup(token) {
  return request({
    url: '/vue-element-admin/student/group/my_group',
    method: 'get',
    params: {token}
  })
}








