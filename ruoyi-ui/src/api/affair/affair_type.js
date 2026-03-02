import request from '@/utils/request'

// 查询事务类型列表
export function listAffair_type(query) {
  return request({
    url: '/affair/affair_type/list',
    method: 'get',
    params: query
  })
}

// 查询事务类型详细
export function getAffair_type(affairTypeId) {
  return request({
    url: '/affair/affair_type/' + affairTypeId,
    method: 'get'
  })
}

// 新增事务类型
export function addAffair_type(data) {
  return request({
    url: '/affair/affair_type',
    method: 'post',
    data: data
  })
}

// 修改事务类型
export function updateAffair_type(data) {
  return request({
    url: '/affair/affair_type',
    method: 'put',
    data: data
  })
}

// 删除事务类型
export function delAffair_type(affairTypeId, updateUserId) {
  return request({
    url: '/affair/affair_type/' + affairTypeId + '?updateUserId=' + updateUserId,
    method: 'delete'
  })
}
