/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const teacher_projectsRouter = {
  path: '/teacher_projects',
  component: Layout,
  redirect: '/teachers/Projects/AddProject',
  name: 'Projects',
  meta: {
    title: 'Projects',
    icon: 'tree',
    roles: ['teacher'] 
  },
  children: [
    {
      path: 'AddProject',
      component: () => import('@/views/teachers/Projects/AddProject/index'), // Parent router-view
      name: 'AddProject',
      meta: { title: 'Add Projects' }
    },
    {
      path: 'ManageProject',
      component: () => import('@/views/teachers/Projects/ManageProject/index'),
      name: 'Manage Project',
      meta: { title: 'Manage Projects' }
    },
    {
      path: 'Notice',
      component: () => import('@/views/teachers/Projects/Notice/index'),
      name: 'Notice',
      meta: { title: 'Notice' }
    },
    {
      path: 'SubmissionInformation',
      component: () => import('@/views/teachers/Projects/SubmissionInformation/index'),
      name: 'Submission Information',
      meta: { title: 'Submission Information' }
    }
  ]
}

export default teacher_projectsRouter
