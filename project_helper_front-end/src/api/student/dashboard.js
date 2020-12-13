import request from '@/utils/request'


export function fetchMyProjectList_student(token) {
  return request({
    url: '/vue-element-admin/student/dashboard/project_list',
    method: 'get',
    params: {token}
  })
}


export function fetchMyAnnouncementList_student(token) {
  return request({
    url: '/vue-element-admin/student/dashboard/announcement_list',
    method: 'get',
    params: {token}
  })
}








