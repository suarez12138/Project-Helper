import request from '@/utils/request'


export function fetchGroupsList(project_id) {
  return request({
    url: '/vue-element-admin/teacher/group/group_list',
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


export function fetchTheGroup(group_name) {
  return request({
    url: '/vue-element-admin/teacher/group/the_group',
    method: 'get',
    params: {group_name}
  })
}


export function fetchAllStudent(project_id, group_name) {
  return request({
    url: '/vue-element-admin/teacher/group/all_student',
    method: 'get',
    params: {project_id, group_name}
  })
}





