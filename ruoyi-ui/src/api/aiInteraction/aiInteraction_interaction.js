import request from '@/utils/request'

// 查询智能交互记录列表
export function listAiInteraction_interaction(query) {
  return request({
    url: '/aiInteraction/aiInteraction_interaction/list',
    method: 'get',
    params: query
  })
}

// 查询智能交互记录详细
export function getAiInteraction_interaction(interactionId) {
  return request({
    url: '/aiInteraction/aiInteraction_interaction/' + interactionId,
    method: 'get'
  })
}

// 新增智能交互记录
export function addAiInteraction_interaction(data) {
  return request({
    url: '/aiInteraction/aiInteraction_interaction',
    method: 'post',
    data: data
  })
}

// 修改智能交互记录
export function updateAiInteraction_interaction(data) {
  return request({
    url: '/aiInteraction/aiInteraction_interaction',
    method: 'put',
    data: data
  })
}

// 删除智能交互记录
export function delAiInteraction_interaction(interactionId) {
  return request({
    url: '/aiInteraction/aiInteraction_interaction/' + interactionId,
    method: 'delete'
  })
}
