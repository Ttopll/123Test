import request from '@/utils/request'

// 查询学生课程关联列表
export function listStudentCourse(query) {
  return request({
    url: '/teachingmanagement/student-course/list',
    method: 'get',
    params: query
  })
}

// 查询学生课程关联详细
export function getStudentCourse(scId) {
  return request({
    url: '/teachingmanagement/student-course/' + scId,
    method: 'get'
  })
}

// 新增学生课程关联
export function addStudentCourse(data) {
  return request({
    url: '/teachingmanagement/student-course',
    method: 'post',
    data: data
  })
}

// 修改学生课程关联
export function updateStudentCourse(data) {
  return request({
    url: '/teachingmanagement/student-course',
    method: 'put',
    data: data
  })
}

// 删除学生课程关联
export function delStudentCourse(scId) {
  return request({
    url: '/teachingmanagement/student-course/' + scId,
    method: 'delete'
  })
}
