/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout/index'

const announcementsRouter = {
  path: '/announcements',
  component: Layout,
  // name: 'announcements',
  meta: {
    title: 'Announcements',
    icon: 'message',
    roles: ['teacher', 'student']
  },
  children: [
    {
      path: 'announcements',
      component: () => import('@/views/teachers/Announcements/announcements/index'),
      name: 'announcements',
      meta: { title: 'Announcements', roles: ['teacher'] }
    },
    {
      path: 'Content',
      component: () => import('@/views/teachers/Announcements/special_one/index'),
      name: 'Content',
      meta: { title: 'Content', roles: ['student'] }
    },
    {
      path: 'create',
      component: () => import('@/views/teachers/Announcements/Create/index'),
      name: 'create',
      meta: { title: 'Creating', roles: ['teacher'] }
    }
  ]
}

export default announcementsRouter
