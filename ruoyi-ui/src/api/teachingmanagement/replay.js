import request from '@/utils/request'

// 查询课程回放列表
export function listReplay(query) {
  return request({
    url: '/teachingmanagement/replay/list',
    method: 'get',
    params: query
  })
}

// 查询课程回放详细
export function getReplay(replayId) {
  return request({
    url: '/teachingmanagement/replay/' + replayId,
    method: 'get'
  })
}

// 新增课程回放
export function addReplay(data) {
  return request({
    url: '/teachingmanagement/replay',
    method: 'post',
    data: data
  })
}

// 修改课程回放
export function updateReplay(data) {
  return request({
    url: '/teachingmanagement/replay',
    method: 'put',
    data: data
  })
}

// 删除课程回放
export function delReplay(replayId) {
  return request({
    url: '/teachingmanagement/replay/' + replayId,
    method: 'delete'
  })
}
