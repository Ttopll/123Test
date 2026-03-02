import request from '@/utils/request'

// 查询组卷模板列表
export function listAipaper_template(query) {
  return request({
    url: '/aipaper/aipaper_template/list',
    method: 'get',
    params: query
  })
}

// 查询组卷模板详细
export function getAipaper_template(templateId) {
  return request({
    url: '/aipaper/aipaper_template/' + templateId,
    method: 'get'
  })
}

// 新增组卷模板
export function addAipaper_template(data) {
  return request({
    url: '/aipaper/aipaper_template',
    method: 'post',
    data: data
  })
}

// 修改组卷模板
export function updateAipaper_template(data) {
  return request({
    url: '/aipaper/aipaper_template',
    method: 'put',
    data: data
  })
}

// 删除组卷模板
export function delAipaper_template(templateId) {
  return request({
    url: '/aipaper/aipaper_template/' + templateId,
    method: 'delete'
  })
}
