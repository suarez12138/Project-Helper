/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const personalRouter = {
  path: '/personal',
  component: Layout,
  children: [
    {
      path: 'personal',
      component: () => import('@/views/students/Personal/personal/index'), // Parent router-view
      name: 'personal',
      meta: { title: 'Personal', icon: 'people', roles: ['student'] }
    }
  ]
}

export default personalRouter
