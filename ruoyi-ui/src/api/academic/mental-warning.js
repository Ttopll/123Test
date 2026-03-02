import request from '@/utils/request'

// 查询心理健康预警列表
export function listMentalWarning(query) {
  return request({
    url: '/academic/mental-warning/list',
    method: 'get',
    params: query
  })
}

// 查询心理健康预警详细
export function getMentalWarning(warningId) {
  return request({
    url: '/academic/mental-warning/' + warningId,
    method: 'get'
  })
}

// 新增心理健康预警
export function addMentalWarning(data) {
  return request({
    url: '/academic/mental-warning',
    method: 'post',
    data: data
  })
}

// 修改心理健康预警
export function updateMentalWarning(data) {
  return request({
    url: '/academic/mental-warning',
    method: 'put',
    data: data
  })
}

// 删除心理健康预警
export function delMentalWarning(warningId) {
  return request({
    url: '/academic/mental-warning/' + warningId,
    method: 'delete'
  })
}
