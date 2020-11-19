/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const gradesRouter = {
  path: '/grade',
  component: Layout,
  redirect: '/students/Grade/Check',
  name: 'Grades',
  meta: {
    title: 'Grades',
    icon: 'form',
    roles: ['student'] 
  },
  children: [
    {
      path: 'check',
      component: () => import('@/views/students/Grade/Check/index'), // Parent router-view
      name: 'Check',
      meta: { title: 'Check' }
    }
  ]
}

export default gradesRouter
