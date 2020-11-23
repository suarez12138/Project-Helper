/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const announcementRouter = {
  path: '/announcement',
  component: Layout,
  children: [
    {
      path: 'announcement',
      component: () => import('@/views/students/Announcement/announcement/index'), // Parent router-view
      name: 'announcement',
      meta: { title: 'Announcement', icon: 'message', roles: ['student'] }
    }
  ]
}

export default announcementRouter
