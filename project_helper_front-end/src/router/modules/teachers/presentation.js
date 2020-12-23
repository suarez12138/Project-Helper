import request from '@/utils/request'


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