import request from '@/utils/request'

// 查询学生就业信息列表
export function listEmploy_studentInfo(query) {
  return request({
    url: '/employ/employ_studentInfo/list',
    method: 'get',
    params: query
  })
}

// 查询学生就业信息详细
export function getEmploy_studentInfo(employmentId) {
  return request({
    url: '/employ/employ_studentInfo/' + employmentId,
    method: 'get'
  })
}

// 新增学生就业信息
export function addEmploy_studentInfo(data) {
  return request({
    url: '/employ/employ_studentInfo',
    method: 'post',
    data: data
  })
}

// 修改学生就业信息
export function updateEmploy_studentInfo(data) {
  return request({
    url: '/employ/employ_studentInfo',
    method: 'put',
    data: data
  })
}

// 删除学生就业信息
export function delEmploy_studentInfo(employmentId) {
  return request({
    url: '/employ/employ_studentInfo/' + employmentId,
    method: 'delete'
  })
}
