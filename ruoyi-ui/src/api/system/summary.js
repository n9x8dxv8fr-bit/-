import request from '@/utils/request'

// 查询钢球月度销售汇总（图专用）列表
export function listSummary(query) {
  return request({
    url: '/system/summary/list',
    method: 'get',
    params: query
  })
}

// 查询钢球月度销售汇总（图专用）详细
export function getSummary(id) {
  return request({
    url: '/system/summary/' + id,
    method: 'get'
  })
}

// 新增钢球月度销售汇总（图专用）
export function addSummary(data) {
  return request({
    url: '/system/summary',
    method: 'post',
    data: data
  })
}

// 修改钢球月度销售汇总（图专用）
export function updateSummary(data) {
  return request({
    url: '/system/summary',
    method: 'put',
    data: data
  })
}

// 删除钢球月度销售汇总（图专用）
export function delSummary(id) {
  return request({
    url: '/system/summary/' + id,
    method: 'delete'
  })
}
