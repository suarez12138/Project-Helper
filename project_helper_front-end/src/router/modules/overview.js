/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout/index'

const overviewRouter = {
  path: '/overview',
  component: Layout,
  children: [
    {
      path: 'overview',
      component: () => import('@/views/students/Overview/overview/index'), // Parent router-view
      name: 'overview',
      meta: { title: 'Overview', icon: 'eye-open' }
    }
  ]
}

export default overviewRouter
