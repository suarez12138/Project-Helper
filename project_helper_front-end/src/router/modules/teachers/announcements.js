/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const announcementsRouter = {
  path: '/announcements',
  component: Layout,
  // name: 'announcements',
  meta: {
    title: 'Announcements',
    icon: 'message',
    roles: ['teacher']
  },
  children: [
    {
      path: 'announcements',
      component: () => import('@/views/teachers/Announcements/announcements/index'),
      name: 'announcements',
      meta: { title: 'Announcements' }
    },
    {
      path: 'Content',
      component: () => import('@/views/teachers/Announcements/special_one/index'),
      name: 'Content',
      meta: { title: 'Content' }
    },
    {
      path: 'create',
      component: () => import('@/views/teachers/Announcements/Create/index'),
      name: 'create',
      meta: { title: 'Creating' }
    }
  ]
}

export default announcementsRouter
