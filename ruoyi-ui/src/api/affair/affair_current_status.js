import request from '@/utils/request'

// 查询用户当前状态记录（全局可查）列表
export function listAffair_current_status(query) {
  return request({
    url: '/affair/affair_current_status/list',
    method: 'get',
    params: query
  })
}

// 查询用户当前状态记录（全局可查）详细
export function getAffair_current_status(currentStatusId) {
  return request({
    url: '/affair/affair_current_status/' + currentStatusId,
    method: 'get'
  })
}

// 新增用户当前状态记录（全局可查）
export function addAffair_current_status(data) {
  return request({
    url: '/affair/affair_current_status',
    method: 'post',
    data: data
  })
}

// 修改用户当前状态记录（全局可查）
export function updateAffair_current_status(data) {
  return request({
    url: '/affair/affair_current_status',
    method: 'put',
    data: data
  })
}

// 删除用户当前状态记录（全局可查）
export function delAffair_current_status(currentStatusId) {
  return request({
    url: '/affair/affair_current_status/' + currentStatusId,
    method: 'delete'
  })
}
