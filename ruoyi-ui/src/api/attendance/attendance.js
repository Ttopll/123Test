import request from '@/utils/request'

// 查询考勤管理主表列表
export function listAttendance(query) {
  return request({
    url: '/attendance/attendance/list',
    method: 'get',
    params: query
  })
}

// 查询考勤管理主表详细
export function getAttendance(attendId) {
  return request({
    url: '/attendance/attendance/' + attendId,
    method: 'get'
  })
}

// 新增考勤管理主表
export function addAttendance(data) {
  return request({
    url: '/attendance/attendance',
    method: 'post',
    data: data
  })
}

// 修改考勤管理主表
export function updateAttendance(data) {
  return request({
    url: '/attendance/attendance',
    method: 'put',
    data: data
  })
}

// 删除考勤管理主表
export function delAttendance(attendId) {
  return request({
    url: '/attendance/attendance/' + attendId,
    method: 'delete'
  })
}
