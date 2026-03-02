import request from '@/utils/request'

// 查询院系管理列表
export function listDepartment(query) {
  return request({
    url: '/teachingmanagement/department/list',
    method: 'get',
    params: query
  })
}

// 查询院系管理详细
export function getDepartment(deptId) {
  return request({
    url: '/teachingmanagement/department/' + deptId,
    method: 'get'
  })
}

// 新增院系管理
export function addDepartment(data) {
  return request({
    url: '/teachingmanagement/department',
    method: 'post',
    data: data
  })
}

// 修改院系管理
export function updateDepartment(data) {
  return request({
    url: '/teachingmanagement/department',
    method: 'put',
    data: data
  })
}

// 删除院系管理
export function delDepartment(deptId) {
  return request({
    url: '/teachingmanagement/department/' + deptId,
    method: 'delete'
  })
}
