import request from '@/utils/request'

/**
 * 客户提交订单
 * @param {Object} data 订单信息
 */
export function submitOrder(data) {
  return request({
    url: '/api/order/submit',
    method: 'post',
    data: data
  })
}

/**
 * 管理员查询待出库审核订单数
 */
export function countPendingAuditOrders() {
  return request({
    url: '/api/order/countPending',
    method: 'get'
  })
}

/**
 * 出库审核列表
 */
export function listOrderAudit(query) {
  return request({
    url: '/api/order/audit/list',
    method: 'get',
    params: query
  })
}

/**
 * 审核通过
 */
export function approveOrder(id, remark) {
  return request({
    url: `/api/order/audit/${id}/approve`,
    method: 'post',
    data: { remark }
  })
}

/**
 * 审核驳回
 */
export function rejectOrder(id, remark) {
  return request({
    url: `/api/order/audit/${id}/reject`,
    method: 'post',
    data: { remark }
  })
}

/**
 * 首页业务速览
 */
export function getOrderOverview() {
  return request({
    url: '/api/order/overview',
    method: 'get'
  })
}