import request from '@/utils/request'

// 查询试卷列表
export function listAipaper_paper(query) {
  return request({
    url: '/aipaper/aipaper_paper/list',
    method: 'get',
    params: query
  })
}

// 查询试卷详细
export function getAipaper_paper(paperId) {
  return request({
    url: '/aipaper/aipaper_paper/' + paperId,
    method: 'get'
  })
}

// 新增试卷
export function addAipaper_paper(data) {
  return request({
    url: '/aipaper/aipaper_paper',
    method: 'post',
    data: data
  })
}

// 修改试卷
export function updateAipaper_paper(data) {
  return request({
    url: '/aipaper/aipaper_paper',
    method: 'put',
    data: data
  })
}

// 删除试卷
export function delAipaper_paper(paperId) {
  return request({
    url: '/aipaper/aipaper_paper/' + paperId,
    method: 'delete'
  })
}
