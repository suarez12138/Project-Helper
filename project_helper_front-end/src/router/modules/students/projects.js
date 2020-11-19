/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const student_projectsRouter = {
  path: '/student_projects',
  component: Layout,
  redirect: '/students/Project/Grouping',
  name: 'Project',
  meta: {
    title: 'Project',
    icon: 'tree',
    roles: ['student'] 
  },
  children: [
    {
      path: 'Grouping',
      component: () => import('@/views/students/Project/Grouping/index'), // Parent router-view
      name: 'Grouping',
      meta: { title: 'Grouping' ,
              roles: ['student'] 
        }
    },
    {
      path: 'Progess',
      component: () => import('@/views/students/Project/Progess/index'),
      name: 'Progess',
      meta: { title: 'Progess',
              roles: ['student'] 
        }
    },
    {
      path: 'Upload',
      component: () => import('@/views/students/Project/Upload/index'),
      name: 'Upload',
      meta: { title: 'Upload' ,
              roles: ['student'] 
        }
    }
  ]
}

export default student_projectsRouter
