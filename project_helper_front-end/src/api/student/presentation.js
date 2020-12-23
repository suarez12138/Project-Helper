import request from '@/utils/request'


export function get_preTimeChunk(project_id) {
  return request({
    url: '/vue-element-admin/student/pre/get_time_suite',
    method: 'get',
    params:{project_id}
    // preTime_id
    // start_time
  })
}


export function get_preTimeTitle(project_id, preTime_id) {
  return request({
    url: '/vue-element-admin/student/pre/get_time_title',
    method: 'get',
    params:{project_id, preTime_id}
    // project_name
    // start_time
    // pretime_text
  })
}


export function get_preTimeDetail(project_id, preTime_id) {
  return request({
    url: '/vue-element-admin/student/pre/get_time_detail',
    method: 'get',
    params:{project_id, preTime_id}
    // time_range
    // time_id
    // grope_name
  })
}


  export function update_preTime(token, project_id, preTime_id, time_id) {
    return request({
      url: '/vue-element-admin/student/pre/update_preTime',
      method: 'post',
      params:{token, project_id, preTime_id, time_id}
    })
  }












