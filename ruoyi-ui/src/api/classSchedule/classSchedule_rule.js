import request from '@/utils/request'

// 查询排课规则列表
export function listClassSchedule_rule(query) {
  return request({
    url: '/classSchedule/classSchedule_rule/list',
    method: 'get',
    params: query
  })
}

// 查询排课规则详细
export function getClassSchedule_rule(ruleId) {
  return request({
    url: '/classSchedule/classSchedule_rule/' + ruleId,
    method: 'get'
  })
}

// 新增排课规则
export function addClassSchedule_rule(data) {
  return request({
    url: '/classSchedule/classSchedule_rule',
    method: 'post',
    data: data
  })
}

// 修改排课规则
export function updateClassSchedule_rule(data) {
  return request({
    url: '/classSchedule/classSchedule_rule',
    method: 'put',
    data: data
  })
}

// 删除排课规则
export function delClassSchedule_rule(ruleId) {
  return request({
    url: '/classSchedule/classSchedule_rule/' + ruleId,
    method: 'delete'
  })
}
