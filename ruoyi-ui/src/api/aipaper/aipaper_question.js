import request from '@/utils/request'

// 查询试题列表
export function listAipaper_question(query) {
  return request({
    url: '/aipaper/aipaper_question/list',
    method: 'get',
    params: query
  })
}

// 查询试题详细
export function getAipaper_question(questionId) {
  return request({
    url: '/aipaper/aipaper_question/' + questionId,
    method: 'get'
  })
}

// 新增试题
export function addAipaper_question(data) {
  return request({
    url: '/aipaper/aipaper_question',
    method: 'post',
    data: data
  })
}

// 修改试题
export function updateAipaper_question(data) {
  return request({
    url: '/aipaper/aipaper_question',
    method: 'put',
    data: data
  })
}

// 删除试题
export function delAipaper_question(questionId) {
  return request({
    url: '/aipaper/aipaper_question/' + questionId,
    method: 'delete'
  })
}
