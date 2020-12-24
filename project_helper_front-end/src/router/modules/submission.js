/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout/index'

const submissionRouter = {
  path: '/submission',
  component: Layout,
  name: 'submission',
  meta: { title: 'Submission', icon: 'clipboard', roles: ['teacher', 'student'] },

  children: [
    {
      path: 'submission1',
      component: () => import('@/views/teachers/Submission/submission/index'), // Parent router-view
      name: 'submission1',
      meta: { title: 'Submissions', icon: 'clipboard', roles: ['teacher', 'student'] }
    }, {
      path: 'submission4',
      component: () => import('@/views/teachers/Submission/s4/index'), // Parent router-view
      name: 'submission4',
      meta: { title: 'Content', icon: 'clipboard', roles: ['teacher', 'student'] }
    }, {
      path: 'submission2',
      component: () => import('@/views/teachers/Submission/s2/index'), // Parent router-view
      name: 'submission2',
      meta: { title: 'Grades', icon: 'clipboard', roles: ['teacher'] }
    }, {
      path: 'submission3',
      component: () => import('@/views/teachers/Submission/s3/index'), // Parent router-view
      name: 'submission3',
      meta: { title: 'Create', icon: 'clipboard', roles: ['teacher'] }
    }
    // , {
    //   path: 'submission5',
    //   component: () => import('@/views/teachers/Submission/s5/index'), // Parent router-view
    //   name: 'submission5',
    //   meta: { title: 'Create', icon: 'clipboard', roles: ['teacher'] }
    // }
  ]
}

export default submissionRouter
