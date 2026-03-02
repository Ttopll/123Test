import request from '@/utils/request'

// 查询教师评价记录列表
export function listEvaluate_record(query) {
  return request({
    url: '/evaluate/evaluate_record/list',
    method: 'get',
    params: query
  })
}

// 查询教师评价记录详细
export function getEvaluate_record(recordId) {
  return request({
    url: '/evaluate/evaluate_record/' + recordId,
    method: 'get'
  })
}

// 新增教师评价记录
export function addEvaluate_record(data) {
  return request({
    url: '/evaluate/evaluate_record',
    method: 'post',
    data: data
  })
}

// 修改教师评价记录
export function updateEvaluate_record(data) {
  return request({
    url: '/evaluate/evaluate_record',
    method: 'put',
    data: data
  })
}

// 删除教师评价记录
export function delEvaluate_record(recordId) {
  return request({
    url: '/evaluate/evaluate_record/' + recordId,
    method: 'delete'
  })
}
