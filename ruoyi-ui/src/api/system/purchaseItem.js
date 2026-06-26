import request from '@/utils/request'

// 查询采购明细列表
export function listPurchaseItem(query) {
    return request({
        url: '/system/purchaseItem/list',
        method: 'get',
        params: query
    })
}

// 查询采购明细详细
export function getPurchaseItem(id) {
    return request({
        url: '/system/purchaseItem/' + id, // 👈 改成小写 p
        method: 'get'
    })
}

// 新增采购明细
export function addPurchaseItem(data) {
    return request({
        url: '/system/purchaseItem', // 👈 改成小写 p
        method: 'post',
        data: data
    })
}

// 修改采购明细
export function updatePurchaseItem(data) {
    return request({
        url: '/system/purchaseItem', // 👈 改成小写 p
        method: 'put',
        data: data
    })
}

// 删除采购明细
export function delPurchaseItem(id) {
    return request({
        url: '/system/purchaseItem/' + id, // 👈 改成小写 p
        method: 'delete'
    })
}