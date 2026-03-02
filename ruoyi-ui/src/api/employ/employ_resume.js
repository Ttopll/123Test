import request from '@/utils/request'

// 查询学生简历管理列表
export function listEmploy_resume(query) {
  return request({
    url: '/employ/employ_resume/list',
    method: 'get',
    params: query
  })
}

// 查询学生简历管理详细
export function getEmploy_resume(resumeId) {
  return request({
    url: '/employ/employ_resume/' + resumeId,
    method: 'get'
  })
}

// 新增学生简历管理
export function addEmploy_resume(data) {
  return request({
    url: '/employ/employ_resume',
    method: 'post',
    data: data
  })
}

// 修改学生简历管理
export function updateEmploy_resume(data) {
  return request({
    url: '/employ/employ_resume',
    method: 'put',
    data: data
  })
}

// 删除学生简历管理
export function delEmploy_resume(resumeId) {
  return request({
    url: '/employ/employ_resume/' + resumeId,
    method: 'delete'
  })
}
