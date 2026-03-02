import request from '@/utils/request'

// 查询就业合作企业列表
export function listEmploy_enterprise(query) {
  return request({
    url: '/employ/employ_enterprise/list',
    method: 'get',
    params: query
  })
}

// 查询就业合作企业详细
export function getEmploy_enterprise(enterpriseId) {
  return request({
    url: '/employ/employ_enterprise/' + enterpriseId,
    method: 'get'
  })
}

// 新增就业合作企业
export function addEmploy_enterprise(data) {
  return request({
    url: '/employ/employ_enterprise',
    method: 'post',
    data: data
  })
}

// 修改就业合作企业
export function updateEmploy_enterprise(data) {
  return request({
    url: '/employ/employ_enterprise',
    method: 'put',
    data: data
  })
}

// 删除就业合作企业
export function delEmploy_enterprise(enterpriseId) {
  return request({
    url: '/employ/employ_enterprise/' + enterpriseId,
    method: 'delete'
  })
}
