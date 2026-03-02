import request from '@/utils/request'

// 查询实践活动过程列表
export function listPractice_process(query) {
  return request({
    url: '/practice/practice_process/list',
    method: 'get',
    params: query
  })
}

// 查询实践活动过程详细
export function getPractice_process(processId) {
  return request({
    url: '/practice/practice_process/' + processId,
    method: 'get'
  })
}

// 新增实践活动过程
export function addPractice_process(data) {
  return request({
    url: '/practice/practice_process',
    method: 'post',
    data: data
  })
}

// 修改实践活动过程
export function updatePractice_process(data) {
  return request({
    url: '/practice/practice_process',
    method: 'put',
    data: data
  })
}

// 删除实践活动过程
export function delPractice_process(processId) {
  return request({
    url: '/practice/practice_process/' + processId,
    method: 'delete'
  })
}
