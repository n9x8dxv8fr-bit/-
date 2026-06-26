import request from '@/utils/request'

// 查询钢球进出库流水（含统计字段）列表
export function listFlow(query) {
  return request({
    url: '/system/flow/list',
    method: 'get',
    params: query
  })
}

// 查询钢球进出库流水（含统计字段）详细
export function getFlow(id) {
  return request({
    url: '/system/flow/' + id,
    method: 'get'
  })
}

// 新增钢球进出库流水（含统计字段）
export function addFlow(data) {
  return request({
    url: '/system/flow',
    method: 'post',
    data: data
  })
}

// 修改钢球进出库流水（含统计字段）
export function updateFlow(data) {
  return request({
    url: '/system/flow',
    method: 'put',
    data: data
  })
}

// 删除钢球进出库流水（含统计字段）
export function delFlow(id) {
  return request({
    url: '/system/flow/' + id,
    method: 'delete'
  })
}
