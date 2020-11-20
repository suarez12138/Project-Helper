/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const emailRouter = {
  path: '/email',
  component: Layout,
  children: [
    {
      path: 'email',
      component: () => import('@/views/teachers/Email/email/index'), // Parent router-view
      name: 'email',
      meta: { title: 'Email', icon: 'guide', roles: ['teacher'] }
    }
  ]
}

export default emailRouter
