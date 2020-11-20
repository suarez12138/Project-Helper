/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const authorityRouter = {
  path: '/authority',
  component: Layout,
  children: [
    {
      path: 'authority',
      component: () => import('@/views/teachers/Authority/authority/index'), // Parent router-view
      name: 'authority',
      meta: { title: 'Authority', icon: 'lock', roles: ['teacher'] }
    }
  ]
}

export default authorityRouter
