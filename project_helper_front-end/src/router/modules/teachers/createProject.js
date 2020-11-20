/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const createProjectRouter = {
  path: '/createProject',
  component: Layout,
  children: [
    {
      path: 'createProject',
      component: () => import('@/views/teachers/CreateProject/createProject/index'), // Parent router-view
      name: 'createProject',
      meta: { title: 'Creating Project', icon: 'edit', roles: ['teacher'] }
    }
  ]
}

export default createProjectRouter
