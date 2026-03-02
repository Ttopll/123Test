import request from '@/utils/request'

// 查询学业预警列表
export function listWarning(query) {
  return request({
    url: '/academic/warning/list',
    method: 'get',
    params: query
  })
}

// 查询学业预警详细
export function getWarning(warningId) {
  return request({
    url: '/academic/warning/' + warningId,
    method: 'get'
  })
}

// 新增学业预警
export function addWarning(data) {
  return request({
    url: '/academic/warning',
    method: 'post',
    data: data
  })
}

// 修改学业预警
export function updateWarning(data) {
  return request({
    url: '/academic/warning',
    method: 'put',
    data: data
  })
}

// 删除学业预警
export function delWarning(warningId) {
  return request({
    url: '/academic/warning/' + warningId,
    method: 'delete'
  })
}
