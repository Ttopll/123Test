import request from '@/utils/request'

// 查询实践活动评价列表
export function listPractice_evaluation(query) {
  return request({
    url: '/practice/practice_evaluation/list',
    method: 'get',
    params: query
  })
}

// 查询实践活动评价详细
export function getPractice_evaluation(evaluationId) {
  return request({
    url: '/practice/practice_evaluation/' + evaluationId,
    method: 'get'
  })
}

// 新增实践活动评价
export function addPractice_evaluation(data) {
  return request({
    url: '/practice/practice_evaluation',
    method: 'post',
    data: data
  })
}

// 修改实践活动评价
export function updatePractice_evaluation(data) {
  return request({
    url: '/practice/practice_evaluation',
    method: 'put',
    data: data
  })
}

// 删除实践活动评价
export function delPractice_evaluation(evaluationId) {
  return request({
    url: '/practice/practice_evaluation/' + evaluationId,
    method: 'delete'
  })
}
