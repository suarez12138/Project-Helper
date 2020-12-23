import request from '@/utils/request'

export function get_preList(project_id) {
    return request({
      url: '/vue-element-admin/teacher/pre/get_pre_list',
      method: 'get',
      params: { project_id } 
      // time_limit
      // group_limit
      // start_time
      // the_text
    })
  }

export function post_preTime(data) {
  return request({
    url: '/vue-element-admin/teacher/pre/post_pre',
    method: 'post',
    data
    // project_id
    // time_limit
    // group_limit
    // start_time
    // the_text
  })
}