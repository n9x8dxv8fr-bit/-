import request from '@/utils/request'

export function listQcStandard(query) {
  return request({
    url: '/system/qcStandard/list',
    method: 'get',
    params: query
  })
}

export function getQcStandard(id) {
  return request({
    url: '/system/qcStandard/' + id,
    method: 'get'
  })
}

export function addQcStandard(data) {
  return request({
    url: '/system/qcStandard',
    method: 'post',
    data: data
  })
}

export function updateQcStandard(data) {
  return request({
    url: '/system/qcStandard',
    method: 'put',
    data: data
  })
}

export function delQcStandard(id) {
  return request({
    url: '/system/qcStandard/' + id,
    method: 'delete'
  })
}
