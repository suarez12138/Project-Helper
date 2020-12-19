import request from '@/utils/request'


export function fetchAllProject() {
  return request({
    url: '/vue-element-admin/student/group/all_project',
    method: 'get'
  })
}


export function fetchGroupsList(project_id) {
  return request({
    url: '/vue-element-admin/student/group/group_list',
    method: 'get',
    // params: {project_id}
    params: {project_id}
  })
}

export function fetchGroupsListState(project_id) {
  return request({
    url: '/vue-element-admin/student/group/group_list_state',
    method: 'get',
    params: {project_id}
  })
}


export function fetchMyGroup(token, project_id) {
  return request({
    url: '/vue-element-admin/student/group/my_group',
    method: 'get',
    params: {token, project_id}
  })
}


export function fetchTheGroup(group_name) {
  return request({
    url: '/vue-element-admin/student/group/the_group',
    method: 'get',
    params: {group_name}
  })
}







