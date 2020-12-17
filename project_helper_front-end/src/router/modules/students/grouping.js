/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const groupRouter = {
  path: '/studentGroup',
  component: Layout,
  children: [
    {
      path: 'studentGroup',
      component: () => import('@/views/students/Group/group/index'), // Parent router-view
      name: 'studentGroup',
      meta: { title: 'Grouping', icon: 'el-icon-search', roles: ['student'] }
    }
  ]
}

export default groupRouter
