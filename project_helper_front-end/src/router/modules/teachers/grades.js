/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const gradesRouter = {
  path: '/grades',
  component: Layout,
  redirect: '/teachers/Grades/Grading',
  name: 'Grades',
  meta: {
    title: 'Grades',
    icon: 'form',
    roles: ['teacher'] 
  },
  children: [
    {
      path: 'datas',
      component: () => import('@/views/teachers/Grades/Datas/index'), // Parent router-view
      name: 'Data',
      meta: { title: 'Data'}
    },
    {
      path: 'greading',
      component: () => import('@/views/teachers/Grades/Grading/index'),
      name: 'Greading',
      meta: { title: 'Greading' }
    }
  ]
}

export default gradesRouter
