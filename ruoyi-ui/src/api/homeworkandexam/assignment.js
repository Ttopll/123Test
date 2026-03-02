import request from '@/utils/request'

// 查询作业管理列表
export function listAssignment(query) {
  return request({
    url: '/homeworkandexam/assignment/list',
    method: 'get',
    params: query
  })
}

// 查询作业管理详细
export function getAssignment(assignId) {
  return request({
    url: '/homeworkandexam/assignment/' + assignId,
    method: 'get'
  })
}

// 新增作业管理
export function addAssignment(data) {
  return request({
    url: '/homeworkandexam/assignment',
    method: 'post',
    data: data
  })
}

// 修改作业管理
export function updateAssignment(data) {
  return request({
    url: '/homeworkandexam/assignment',
    method: 'put',
    data: data
  })
}

// 删除作业管理
export function delAssignment(assignId) {
  return request({
    url: '/homeworkandexam/assignment/' + assignId,
    method: 'delete'
  })
}
