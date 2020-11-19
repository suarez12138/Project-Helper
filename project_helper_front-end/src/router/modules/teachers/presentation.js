/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const teacher_presentationRouter = {
  path: '/teacher_presentation',
  component: Layout,
  children: [
    {
      path: 'presentation',
      component: () => import('@/views/teachers/Presentation/presentation/index'), // Parent router-view
      name: 'Presentation',
      meta: { 
        title: 'Presentation', 
        icon: 'people',
        roles: ['teacher'] 
      }
    }
  ]
}

export default teacher_presentationRouter


