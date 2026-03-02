import request from '@/utils/request'

// 查询事务处理日志列表
export function listAffair_log(query) {
  return request({
    url: '/affair/affair_log/list',
    method: 'get',
    params: query
  })
}

// 查询事务处理日志详细
export function getAffair_log(logId) {
  return request({
    url: '/affair/affair_log/' + logId,
    method: 'get'
  })
}

// 新增事务处理日志
export function addAffair_log(data) {
  return request({
    url: '/affair/affair_log',
    method: 'post',
    data: data
  })
}

// 删除事务处理日志
export function delAffair_log(logId, updateUserId) {
  return request({
    url: '/affair/affair_log/' + logId + '?updateUserId=' + updateUserId,
    method: 'delete'
  })
}
