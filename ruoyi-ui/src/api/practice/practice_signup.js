import request from '@/utils/request'

// 查询实践活动报名列表
export function listPractice_signup(query) {
  return request({
    url: '/practice/practice_signup/list',
    method: 'get',
    params: query
  })
}

// 查询实践活动报名详细
export function getPractice_signup(signupId) {
  return request({
    url: '/practice/practice_signup/' + signupId,
    method: 'get'
  })
}

// 新增实践活动报名
export function addPractice_signup(data) {
  return request({
    url: '/practice/practice_signup',
    method: 'post',
    data: data
  })
}

// 修改实践活动报名
export function updatePractice_signup(data) {
  return request({
    url: '/practice/practice_signup',
    method: 'put',
    data: data
  })
}

// 删除实践活动报名
export function delPractice_signup(signupId) {
  return request({
    url: '/practice/practice_signup/' + signupId,
    method: 'delete'
  })
}
