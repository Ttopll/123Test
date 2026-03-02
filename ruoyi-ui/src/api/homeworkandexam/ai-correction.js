import request from '@/utils/request'

// 查询AI 批改记录列表
export function listAiCorrection(query) {
  return request({
    url: '/homeworkandexam/ai-correction/list',
    method: 'get',
    params: query
  })
}

// 查询AI 批改记录详细
export function getAiCorrection(correctionId) {
  return request({
    url: '/homeworkandexam/ai-correction/' + correctionId,
    method: 'get'
  })
}

// 新增AI 批改记录
export function addAiCorrection(data) {
  return request({
    url: '/homeworkandexam/ai-correction',
    method: 'post',
    data: data
  })
}

// 修改AI 批改记录
export function updateAiCorrection(data) {
  return request({
    url: '/homeworkandexam/ai-correction',
    method: 'put',
    data: data
  })
}

// 删除AI 批改记录
export function delAiCorrection(correctionId) {
  return request({
    url: '/homeworkandexam/ai-correction/' + correctionId,
    method: 'delete'
  })
}
