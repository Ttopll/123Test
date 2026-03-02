import request from '@/utils/request'

// 查询预警处理列表
export function listWarningHandle(query) {
  return request({
    url: '/academic/warning-handle/list',
    method: 'get',
    params: query
  })
}

// 查询预警处理详细
export function getWarningHandle(handleId) {
  return request({
    url: '/academic/warning-handle/' + handleId,
    method: 'get'
  })
}

// 新增预警处理
export function addWarningHandle(data) {
  return request({
    url: '/academic/warning-handle',
    method: 'post',
    data: data
  })
}

// 修改预警处理
export function updateWarningHandle(data) {
  return request({
    url: '/academic/warning-handle',
    method: 'put',
    data: data
  })
}

// 删除预警处理
export function delWarningHandle(handleId) {
  return request({
    url: '/academic/warning-handle/' + handleId,
    method: 'delete'
  })
}
