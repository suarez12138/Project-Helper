import request from '@/utils/request'

export function fetchGroupsList() {
  return request({
    url: '/vue-element-admin/group/group_list',
    method: 'get'

  })
}


export function fetchMyGroup() {
  return request({
    url: '/vue-element-admin/group/my_group',
    method: 'get'
  })
}
