import request from '@/utils/request'

// 查询排课任务列表
export function listClassSchedule_task(query) {
  return request({
    url: '/classSchedule/classSchedule_task/list',
    method: 'get',
    params: query
  })
}

// 查询排课任务详细
export function getClassSchedule_task(taskId) {
  return request({
    url: '/classSchedule/classSchedule_task/' + taskId,
    method: 'get'
  })
}

// 新增排课任务
export function addClassSchedule_task(data) {
  return request({
    url: '/classSchedule/classSchedule_task',
    method: 'post',
    data: data
  })
}

// 修改排课任务
export function updateClassSchedule_task(data) {
  return request({
    url: '/classSchedule/classSchedule_task',
    method: 'put',
    data: data
  })
}

// 删除排课任务
export function delClassSchedule_task(taskId) {
  return request({
    url: '/classSchedule/classSchedule_task/' + taskId,
    method: 'delete'
  })
}
