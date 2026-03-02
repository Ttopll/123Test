import request from '@/utils/request'

// 查询签到设备列表
export function listSigninDevice(query) {
  return request({
    url: '/attendance/signin-device/list',
    method: 'get',
    params: query
  })
}

// 查询签到设备详细
export function getSigninDevice(deviceId) {
  return request({
    url: '/attendance/signin-device/' + deviceId,
    method: 'get'
  })
}

// 新增签到设备
export function addSigninDevice(data) {
  return request({
    url: '/attendance/signin-device',
    method: 'post',
    data: data
  })
}

// 修改签到设备
export function updateSigninDevice(data) {
  return request({
    url: '/attendance/signin-device',
    method: 'put',
    data: data
  })
}

// 删除签到设备
export function delSigninDevice(deviceId) {
  return request({
    url: '/attendance/signin-device/' + deviceId,
    method: 'delete'
  })
}
