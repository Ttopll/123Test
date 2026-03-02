import request from '@/utils/request'

// 查询教室资源列表
export function listClassSchedule_classroom(query) {
  return request({
    url: '/classSchedule/classSchedule_classroom/list',
    method: 'get',
    params: query
  })
}

// 查询教室资源详细
export function getClassSchedule_classroom(classroomId) {
  return request({
    url: '/classSchedule/classSchedule_classroom/' + classroomId,
    method: 'get'
  })
}

// 新增教室资源
export function addClassSchedule_classroom(data) {
  return request({
    url: '/classSchedule/classSchedule_classroom',
    method: 'post',
    data: data
  })
}

// 修改教室资源
export function updateClassSchedule_classroom(data) {
  return request({
    url: '/classSchedule/classSchedule_classroom',
    method: 'put',
    data: data
  })
}

// 删除教室资源
export function delClassSchedule_classroom(classroomId) {
  return request({
    url: '/classSchedule/classSchedule_classroom/' + classroomId,
    method: 'delete'
  })
}
