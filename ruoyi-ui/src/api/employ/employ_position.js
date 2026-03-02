import request from '@/utils/request'

// 查询招聘岗位列表
export function listEmploy_position(query) {
  return request({
    url: '/employ/employ_position/list',
    method: 'get',
    params: query
  })
}

// 查询招聘岗位详细
export function getEmploy_position(positionId) {
  return request({
    url: '/employ/employ_position/' + positionId,
    method: 'get'
  })
}

// 新增招聘岗位
export function addEmploy_position(data) {
  return request({
    url: '/employ/employ_position',
    method: 'post',
    data: data
  })
}

// 修改招聘岗位
export function updateEmploy_position(data) {
  return request({
    url: '/employ/employ_position',
    method: 'put',
    data: data
  })
}

// 删除招聘岗位
export function delEmploy_position(positionId) {
  return request({
    url: '/employ/employ_position/' + positionId,
    method: 'delete'
  })
}
