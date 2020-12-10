/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout/index'

const teacher_presentationRouter = {
  path: '/teacher_presentation',
  component: Layout,
  name: 'Presentation',
  meta: {
    title: 'Presentation',
    icon: 'people'
    // roles: ['teacher']
  },
  children: [
    {
      path: 'presentation',
      component: () => import('@/views/teachers/Presentation/presentation/index'), // Parent router-view
      name: 'P1',
      meta: { title: 'P1', icon: 'people', roles: ['teacher'] }
    }, {
      path: 'presentation2',
      component: () => import('@/views/teachers/Presentation/p2/index'), // Parent router-view
      name: 'P2',
      meta: { title: 'P2', icon: 'people' }
    }
  ]
}

export default teacher_presentationRouter

