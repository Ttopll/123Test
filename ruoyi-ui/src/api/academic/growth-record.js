import request from '@/utils/request'

// 查询学生成长记录列表
export function listGrowthRecord(query) {
  return request({
    url: '/academic/growth-record/list',
    method: 'get',
    params: query
  })
}

// 查询学生成长记录详细
export function getGrowthRecord(recordId) {
  return request({
    url: '/academic/growth-record/' + recordId,
    method: 'get'
  })
}

// 新增学生成长记录
export function addGrowthRecord(data) {
  return request({
    url: '/academic/growth-record',
    method: 'post',
    data: data
  })
}

// 修改学生成长记录
export function updateGrowthRecord(data) {
  return request({
    url: '/academic/growth-record',
    method: 'put',
    data: data
  })
}

// 删除学生成长记录
export function delGrowthRecord(recordId) {
  return request({
    url: '/academic/growth-record/' + recordId,
    method: 'delete'
  })
}
