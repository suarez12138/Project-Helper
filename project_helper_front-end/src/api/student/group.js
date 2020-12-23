import request from '@/utils/request'

export function fetchAllProject() {
  return request({
    url: '/vue-element-admin/student/group/all_project',
    method: 'get'
  })
}

export function fetchGroupsList(token, project_id) {
  return request({
    url: '/vue-element-admin/student/group/group_list',
    method: 'get',
    // params: {project_id}
    params: { token, project_id }
  })
}

export function fetchGroupsListState(token, project_id) {
  return request({
    url: '/vue-element-admin/student/group/group_list_state',
    method: 'get',
    params: { token, project_id }
  })
}

export function fetchMyGroup(token, project_id) {
  return request({
    url: '/vue-element-admin/student/group/my_group',
    method: 'get',
    params: { token, project_id }
  })
}

export function fetchMyGroup_state(token, project_id) {
  return request({
    url: '/vue-element-admin/student/group/my_group_state',
    method: 'get',
    params: { token, project_id }
  })
}

export function fetchTheGroup(group_name) {
  return request({
    url: '/vue-element-admin/student/group/the_group',
    method: 'get',
    params: { group_name }
  })
}
export function beforeDDL(project_id) {
  return request({
    url: '/vue-element-admin/student/group/beforeDDL',
    method: 'get',
    params: { project_id }
  })
}

export function updateMyGroup_state(gro_id, gro_status, text, check_point_id, gro_name) {
  return request({
    url: '/vue-element-admin/student/group/update_group_state',
    method: 'post',
    params: { gro_id, gro_status, text, check_point_id, gro_name }
  })
}

export function dropMyGroup(token, gro_id) {
  return request({
    url: '/vue-element-admin/student/group/drop_group',
    method: 'post',
    params: { token, gro_id }
  })
}

export function joinGroup(token, gro_id) {
  return request({
    url: '/vue-element-admin/student/group/join_group',
    method: 'post',
    params: { token, gro_id }
  })
}

