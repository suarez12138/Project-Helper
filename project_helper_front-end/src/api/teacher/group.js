import request from '@/utils/request'


export function fetchGroupsList(project_id) {
  return request({
    url: '/vue-element-admin/teacher/group/group_list',
    method: 'get',
    params: {project_id}
  })
}


export function fetchGroupsListState(project_id) {
  return request({
    url: '/vue-element-admin/teacher/group/group_list_state',
    method: 'get',
    params: {project_id}
  })
}


export function fetchMyGroup(token, project_id) {
  return request({
    url: '/vue-element-admin/teacher/group/my_group',
    method: 'get',
    params: {token, project_id}
  })
}


export function dropGroup(gro_id) {
  return request({
    url: '/vue-element-admin/teacher/group/drop_group',
    method: 'post',
    params: {gro_id}
  })
}


export function autoform_Group(project_id) {
  return request({
    url: '/vue-element-admin/teacher/group/group_autoform',
    method: 'post',
    params: {project_id}
  })
}