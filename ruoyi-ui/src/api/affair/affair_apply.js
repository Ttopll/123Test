import request from '@/utils/request'

// 查询事务申请列表
export function listAffair_apply(query) {
  return request({
    url: '/affair/affair_apply/list',
    method: 'get',
    params: query
  })
}

// 查询事务申请详细
export function getAffair_apply(applyId) {
  return request({
    url: '/affair/affair_apply/' + applyId,
    method: 'get'
  })
}

// 新增事务申请
export function addAffair_apply(data) {
  return request({
    url: '/affair/affair_apply',
    method: 'post',
    data: data
  })
}

// 修改事务申请
export function updateAffair_apply(data) {
  return request({
    url: '/affair/affair_apply',
    method: 'put',
    data: data
  })
}

// 删除事务申请
export function delAffair_apply(applyId, updateUserId) {
  return request({
    url: '/affair/affair_apply/' + applyId + '?updateUserId=' + updateUserId,
    method: 'delete'
  })
}
