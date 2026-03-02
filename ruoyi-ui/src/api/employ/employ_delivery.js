import request from '@/utils/request'

// 查询简历投递记录列表
export function listEmploy_delivery(query) {
  return request({
    url: '/employ/employ_delivery/list',
    method: 'get',
    params: query
  })
}

// 查询简历投递记录详细
export function getEmploy_delivery(deliveryId) {
  return request({
    url: '/employ/employ_delivery/' + deliveryId,
    method: 'get'
  })
}

// 新增简历投递记录
export function addEmploy_delivery(data) {
  return request({
    url: '/employ/employ_delivery',
    method: 'post',
    data: data
  })
}

// 修改简历投递记录
export function updateEmploy_delivery(data) {
  return request({
    url: '/employ/employ_delivery',
    method: 'put',
    data: data
  })
}

// 删除简历投递记录
export function delEmploy_delivery(deliveryId) {
  return request({
    url: '/employ/employ_delivery/' + deliveryId,
    method: 'delete'
  })
}
