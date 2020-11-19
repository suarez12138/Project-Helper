/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const groupRouter = {
  path: '/studentGroup',
  component: Layout,
  // redirect: '/students/Overview/overview',
  // name: 'studentGroup',
  // meta: {
  //   title: 'Group',
  //   icon: 'peoples',
  //   roles: ['student']
  // },
  children: [
    {
      path: 'studentGroup',
      component: () => import('@/views/students/Group/group/index'), // Parent router-view
      name: 'studentGroup',
      meta: { title: 'Group', icon: 'peoples', roles: ['student'] }
    }
  ]
}

export default groupRouter
