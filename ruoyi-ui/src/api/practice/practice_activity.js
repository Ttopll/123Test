import request from '@/utils/request'

// 查询实践活动列表
export function listPractice_activity(query) {
  return request({
    url: '/practice/practice_activity/list',
    method: 'get',
    params: query
  })
}

// 查询实践活动详细
export function getPractice_activity(activityId) {
  return request({
    url: '/practice/practice_activity/' + activityId,
    method: 'get'
  })
}

// 新增实践活动
export function addPractice_activity(data) {
  return request({
    url: '/practice/practice_activity',
    method: 'post',
    data: data
  })
}

// 修改实践活动
export function updatePractice_activity(data) {
  return request({
    url: '/practice/practice_activity',
    method: 'put',
    data: data
  })
}

// 删除实践活动
export function delPractice_activity(activityId) {
  return request({
    url: '/practice/practice_activity/' + activityId,
    method: 'delete'
  })
}
