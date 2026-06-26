import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

// 公共路由（只放系统默认，不放业务菜单！）
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login'),
    hidden: true
  },
  {
    path: '/register',
    component: () => import('@/views/register'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/index'),
      meta: { title: '首页', icon: 'dashboard' }
    }]
  },
  {
    path: '/qcStandard',
    component: Layout,
    redirect: '/qcStandard/index',
    meta: { title: '质检标准', icon: 'documentation' },
    children: [{
      path: 'index',
      name: 'QcStandard',
      component: () => import('@/views/system/qcStandard/index'),
      meta: { title: '质检标准', icon: 'documentation' }
    }]
  },
  {
    path: '/order/submit',
    component: () => import('@/views/order/OrderSubmit'),
    hidden: false,
    meta: {
      title: '产品订购',
      icon: 'el-icon-shopping-cart-full',
      noAuth: true
    }
  },
  {
    path: '/system/order-audit',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'index',
        component: () => import('@/views/system/order/audit'),
        name: 'OrderAuditPage',
        meta: { title: '出库审核', activeMenu: '/system/order/list' }
      }
    ]
  },
  {
    path: '/system/order-audit/index',
    component: () => import('@/views/system/order/audit'),
    hidden: true
  },
  {
    path: '/system/order/audit',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '',
        component: () => import('@/views/system/order/audit'),
        name: 'OrderAuditDirect',
        meta: { title: '出库审核', activeMenu: '/system/order/list' }
      }
    ]
  },
  {
    path: '/system/order/audit/index',
    component: () => import('@/views/system/order/audit'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error/401'),
    hidden: true
  },
  {
    path: '/user',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'profile',
        component: () => import('@/views/system/user/profile/index'),
        name: 'Profile',
        meta: { title: '个人中心', icon: 'user' }
      }
    ]
  }
]

// 动态路由（业务菜单全部放这里！）
export const dynamicRoutes = [
  // ================== 采购业务 ==================
  {
    path: '/system/purchaseOrder',
    component: Layout,
    name: 'PurchaseOrder',
    meta: { title: '采购订单', icon: 'documentation' },
    children: [
      {
        path: 'list',
        component: () => import('@/views/system/purchaseOrder/order'),
        name: 'PurchaseOrderList',
        meta: { title: '采购订单列表', permissions: ['system:purchaseOrder:list'] }
      }
    ]
  },
  {
    path: '/system/purchaseItem',
    component: Layout,
    name: 'PurchaseItem',
    meta: { title: '采购明细', icon: 'tree' },
    children: [
      {
        path: 'list',
        component: () => import('@/views/system/purchaseItem/item'),
        name: 'PurchaseItemList',
        meta: { title: '采购明细列表', permissions: ['system:purchaseItem:list'] }
      }
    ]
  },

  // ================== 销售业务 ==================
  {
    path: '/system/order',
    component: Layout,
    name: 'Order',
    meta: { title: '销售订单', icon: 'shopping-cart' },
    children: [
      {
        path: 'list',
        component: () => import('@/views/system/order/index'),
        name: 'OrderList',
        meta: { title: '销售订单列表', permissions: ['system:order:list'] }
      },
      {
        path: 'audit',
        component: () => import('@/views/system/order/audit'),
        name: 'OrderAudit',
        meta: { title: '出库审核', permissions: ['system:order:list'] }
      }
    ]
  },
  {
    path: '/system/item',
    component: Layout,
    name: 'Item',
    meta: { title: '销售明细', icon: 'tree-table' },
    children: [
      {
        path: 'list',
        component: () => import('@/views/system/item/index'),
        name: 'ItemList',
        meta: { title: '销售明细列表', permissions: ['system:item:list'] }
      }
    ]
  },
  {
    path: '/system/qcStandard',
    component: Layout,
    name: 'QcStandardModule',
    meta: { title: '质检标准', icon: 'documentation' },
    permissions: ['system:qcStandard:list'],
    children: [
      {
        path: 'index',
        component: () => import('@/views/system/qcStandard/index'),
        name: 'QcStandardList',
        meta: { title: '质检标准', permissions: ['system:qcStandard:list'] }
      }
    ]
  },

  // ================== 系统原有路由 ==================
  {
    path: '/system/user-auth',
    component: Layout,
    hidden: true,
    permissions: ['system:user:edit'],
    children: [
      {
        path: 'role/:userId(\\d+)',
        component: () => import('@/views/system/user/authRole'),
        name: 'AuthRole',
        meta: { title: '分配角色', activeMenu: '/system/user' }
      }
    ]
  },
  {
    path: '/system/role-auth',
    component: Layout,
    hidden: true,
    permissions: ['system:role:edit'],
    children: [
      {
        path: 'user/:roleId(\\d+)',
        component: () => import('@/views/system/role/authUser'),
        name: 'AuthUser',
        meta: { title: '分配用户', activeMenu: '/system/role' }
      }
    ]
  },
  {
    path: '/system/dict-data',
    component: Layout,
    hidden: true,
    permissions: ['system:dict:list'],
    children: [
      {
        path: 'index/:dictId(\\d+)',
        component: () => import('@/views/system/dict/data'),
        name: 'Data',
        meta: { title: '字典数据', activeMenu: '/system/dict' }
      }
    ]
  },
  {
    path: '/monitor/job-log',
    component: Layout,
    hidden: true,
    permissions: ['monitor:job:list'],
    children: [
      {
        path: 'index/:jobId(\\d+)',
        component: () => import('@/views/monitor/job/log'),
        name: 'JobLog',
        meta: { title: '调度日志', activeMenu: '/monitor/job' }
      }
    ]
  },
  {
    path: '/tool/gen-edit',
    component: Layout,
    hidden: true,
    permissions: ['tool:gen:edit'],
    children: [
      {
        path: 'index/:tableId(\\d+)',
        component: () => import('@/views/tool/gen/editTable'),
        name: 'GenEdit',
        meta: { title: '修改生成配置', activeMenu: '/tool/gen' }
      }
    ]
  }
]

// 防止重复点击路由报错
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

export default new Router({
  mode: 'hash',
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})