/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const MygroupRouter = {
  path: '/studentMyGroup',
  component: Layout,
  children: [
    {
      path: 'studentMyGroup',
      component: () => import('@/views/students/Mygroup/mygroup/index'), // Parent router-view
      name: 'studentMyGroup',
      meta: { title: 'My Group', icon: 'peoples', roles: ['student'] }
    }
  ]
}

export default MygroupRouter
