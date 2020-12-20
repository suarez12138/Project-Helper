import request from '@/utils/request'


export function get_AllStudents(token, project_id) {
  return request({
    url: '/vue-element-admin/student/personal/all_students',
    method: 'get',
    params: {token, project_id}
  })
}

export function update_MyInformation(data) {
    return request({
        url: '/vue-element-admin/student/personal/update_my_info',
        method: 'post',
        data
    })
}


export function get_AllTags(project_id) {
    return request({
        url: '/vue-element-admin/student/personal/all_tags',
        method: 'get',
        params:{project_id}
    })
}
