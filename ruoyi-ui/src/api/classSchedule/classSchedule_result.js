import request from '@/utils/request'

// 查询排课结果列表
export function listClassSchedule_result(query) {
  return request({
    url: '/classSchedule/classSchedule_result/list',
    method: 'get',
    params: query
  })
}

// 查询排课结果详细
export function getClassSchedule_result(resultId) {
  return request({
    url: '/classSchedule/classSchedule_result/' + resultId,
    method: 'get'
  })
}

// 新增排课结果
export function addClassSchedule_result(data) {
  return request({
    url: '/classSchedule/classSchedule_result',
    method: 'post',
    data: data
  })
}

// 修改排课结果
export function updateClassSchedule_result(data) {
  return request({
    url: '/classSchedule/classSchedule_result',
    method: 'put',
    data: data
  })
}

// 删除排课结果
export function delClassSchedule_result(resultId) {
  return request({
    url: '/classSchedule/classSchedule_result/' + resultId,
    method: 'delete'
  })
}
