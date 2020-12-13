import request from '@/utils/request'


export function fetchMyProjectList_teacher(token) {
  return request({
    url: '/vue-element-admin/teacher/dashboard/project_list',
    method: 'get',
    params: {token}
  })
}


export function fetchMyAnnouncementList_teacher(token) {
  return request({
    url: '/vue-element-admin/teacher/dashboard/announcement_list',
    method: 'get',
    params: {token}
  })
}








