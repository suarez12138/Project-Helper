import request from '@/utils/request'


export function fetchMyProjectList(token) {
  return request({
    url: '/vue-element-admin/student/dashboard/project_list',
    method: 'get',
    params: {token}
  })
}


export function fetchMyAnnouncementList(token) {
  return request({
    url: '/vue-element-admin/student/dashboard/announcement_list',
    method: 'get',
    params: {token}
  })
}








