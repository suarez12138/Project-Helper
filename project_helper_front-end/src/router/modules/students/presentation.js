/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const student_presentationRouter = {
  path: '/student_presentation',
  component: Layout,
  children: [
    {
      path: 'presentation',
      component: () => import('@/views/students/Presentation/select time/index'), // Parent router-view
      name: 'Presentation',
      meta: { title: 'Presentation', 
              icon: 'people',
              roles: ['student'] 
      }
    }
  ]
}

export default student_presentationRouter


