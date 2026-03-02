import request from '@/utils/request'

// 查询教师评价配置列表
export function listEvaluate_config(query) {
  return request({
    url: '/evaluate/evaluate_config/list',
    method: 'get',
    params: query
  })
}

// 查询教师评价配置详细
export function getEvaluate_config(configId) {
  return request({
    url: '/evaluate/evaluate_config/' + configId,
    method: 'get'
  })
}

// 新增教师评价配置
export function addEvaluate_config(data) {
  return request({
    url: '/evaluate/evaluate_config',
    method: 'post',
    data: data
  })
}

// 修改教师评价配置
export function updateEvaluate_config(data) {
  return request({
    url: '/evaluate/evaluate_config',
    method: 'put',
    data: data
  })
}

// 删除教师评价配置
export function delEvaluate_config(configId) {
  return request({
    url: '/evaluate/evaluate_config/' + configId,
    method: 'delete'
  })
}
