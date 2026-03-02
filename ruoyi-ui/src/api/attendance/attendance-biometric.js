import request from '@/utils/request'

// 查询考勤人脸指纹记录列表
export function listAttendanceBiometric(query) {
  return request({
    url: '/attendance/attendance-biometric/list',
    method: 'get',
    params: query
  })
}

// 查询考勤人脸指纹记录详细
export function getAttendanceBiometric(infoId) {
  return request({
    url: '/attendance/attendance-biometric/' + infoId,
    method: 'get'
  })
}

// 新增考勤人脸指纹记录
export function addAttendanceBiometric(data) {
  return request({
    url: '/attendance/attendance-biometric',
    method: 'post',
    data: data
  })
}

// 修改考勤人脸指纹记录
export function updateAttendanceBiometric(data) {
  return request({
    url: '/attendance/attendance-biometric',
    method: 'put',
    data: data
  })
}

// 删除考勤人脸指纹记录
export function delAttendanceBiometric(infoId) {
  return request({
    url: '/attendance/attendance-biometric/' + infoId,
    method: 'delete'
  })
}
