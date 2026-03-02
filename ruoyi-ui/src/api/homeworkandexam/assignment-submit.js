import request from '@/utils/request'

// 查询作业提交/批改列表
export function listAssignmentSubmit(query) {
  return request({
    url: '/homeworkandexam/assignment-submit/list',
    method: 'get',
    params: query
  })
}

// 查询作业提交/批改详细
export function getAssignmentSubmit(submitId) {
  return request({
    url: '/homeworkandexam/assignment-submit/' + submitId,
    method: 'get'
  })
}

// 新增作业提交/批改
export function addAssignmentSubmit(data) {
  return request({
    url: '/homeworkandexam/assignment-submit',
    method: 'post',
    data: data
  })
}

// 修改作业提交/批改
export function updateAssignmentSubmit(data) {
  return request({
    url: '/homeworkandexam/assignment-submit',
    method: 'put',
    data: data
  })
}

// 删除作业提交/批改
export function delAssignmentSubmit(submitId) {
  return request({
    url: '/homeworkandexam/assignment-submit/' + submitId,
    method: 'delete'
  })
}
