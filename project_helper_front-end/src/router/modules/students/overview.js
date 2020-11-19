/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const overviewRouter = {
  path: '/overview',
  component: Layout,
  // redirect: '/students/Overview/overview',
  // name: 'overview',
  // meta: {
  //   title: 'Overview',
  //   icon: 'eye-open',
  //   roles: ['student']
  // },
  children: [
    {
      path: 'overview',
      component: () => import('@/views/students/Overview/overview/index'), // Parent router-view
      name: 'overview',
      meta: { title: 'Overview', icon: 'eye-open', roles: ['student'] }
    }
  ]
}

export default overviewRouter
