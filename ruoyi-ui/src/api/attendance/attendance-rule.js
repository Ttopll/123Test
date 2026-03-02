import request from '@/utils/request'

// 查询考勤规则列表
export function listAttendanceRule(query) {
  return request({
    url: '/attendance/attendance-rule/list',
    method: 'get',
    params: query
  })
}

// 查询考勤规则详细
export function getAttendanceRule(ruleId) {
  return request({
    url: '/attendance/attendance-rule/' + ruleId,
    method: 'get'
  })
}

// 新增考勤规则
export function addAttendanceRule(data) {
  return request({
    url: '/attendance/attendance-rule',
    method: 'post',
    data: data
  })
}

// 修改考勤规则
export function updateAttendanceRule(data) {
  return request({
    url: '/attendance/attendance-rule',
    method: 'put',
    data: data
  })
}

// 删除考勤规则
export function delAttendanceRule(ruleId) {
  return request({
    url: '/attendance/attendance-rule/' + ruleId,
    method: 'delete'
  })
}
