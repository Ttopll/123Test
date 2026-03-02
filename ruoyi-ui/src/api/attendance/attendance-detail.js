import request from '@/utils/request'

// 查询考勤详情列表
export function listAttendanceDetail(query) {
  return request({
    url: '/attendance/attendance-detail/list',
    method: 'get',
    params: query
  })
}

// 查询考勤详情详细
export function getAttendanceDetail(detailId) {
  return request({
    url: '/attendance/attendance-detail/' + detailId,
    method: 'get'
  })
}

// 新增考勤详情
export function addAttendanceDetail(data) {
  return request({
    url: '/attendance/attendance-detail',
    method: 'post',
    data: data
  })
}

// 修改考勤详情
export function updateAttendanceDetail(data) {
  return request({
    url: '/attendance/attendance-detail',
    method: 'put',
    data: data
  })
}

// 删除考勤详情
export function delAttendanceDetail(detailId) {
  return request({
    url: '/attendance/attendance-detail/' + detailId,
    method: 'delete'
  })
}
