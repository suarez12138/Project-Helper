/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const announcementRouter = {
  path: '/profile',
  component: Layout,
  children: [
    {
      path: 'profile',
      component: () => import('@/views/profile/index'), // Parent router-view
      name: 'profile',
      meta: { title: 'Profile', icon: 'people', roles: ['student'] }
    }
  ]
}

export default announcementRouter
