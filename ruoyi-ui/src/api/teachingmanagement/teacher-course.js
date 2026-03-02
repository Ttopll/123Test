import request from '@/utils/request'

// 查询教师课程关联列表
export function listTeacherCourse(query) {
  return request({
    url: '/teachingmanagement/teacher-course/list',
    method: 'get',
    params: query
  })
}

// 查询教师课程关联详细
export function getTeacherCourse(tcId) {
  return request({
    url: '/teachingmanagement/teacher-course/' + tcId,
    method: 'get'
  })
}

// 新增教师课程关联
export function addTeacherCourse(data) {
  return request({
    url: '/teachingmanagement/teacher-course',
    method: 'post',
    data: data
  })
}

// 修改教师课程关联
export function updateTeacherCourse(data) {
  return request({
    url: '/teachingmanagement/teacher-course',
    method: 'put',
    data: data
  })
}

// 删除教师课程关联
export function delTeacherCourse(tcId) {
  return request({
    url: '/teachingmanagement/teacher-course/' + tcId,
    method: 'delete'
  })
}
