/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const teacher_groupRouter = {
  path: '/group',
  component: Layout,
  name: 'group',
  meta: {
    title: 'Group',
    icon: 'tree',
    roles: ['teacher']
  },
  children: [
    {
      path: 'groupInformation',
      component: () => import('@/views/teachers/Project/group/index'), // Parent router-view
      name: 'groupInformation',
      meta: { title: 'Group Information', icon: 'peoples' }
    },
    {
      path: 'personInformation',
      component: () => import('@/views/teachers/Project/personal/index'),
      name: 'personInformation',
      meta: { title: 'Personal Information', icon: 'user' }
    }
  ]
}

export default teacher_groupRouter
