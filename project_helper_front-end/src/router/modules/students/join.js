/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const joinRouter = {
  path: '/join',
  component: Layout,
  children: [
    {
      path: 'join',
      component: () => import('@/views/students/Join/join/index'), // Parent router-view
      name: 'studentGroup',
      meta: { title: 'Join Project', icon: 'el-icon-circle-plus-outline', roles: ['student'] }
    }]
}

export default joinRouter
