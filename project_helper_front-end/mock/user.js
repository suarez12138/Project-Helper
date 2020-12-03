
const tokens = {
  admin: {
    stu_id: 'admin-token'
  },
  editor: {
    stu_id: 'editor-token'
  },
  teacher: {
    stu_id: 'teacher-token'
  },
  student: {
    stu_id: 'student-token'
  },
  11813009:{
    stu_id: '11813009'
  }
}

const users = {
  'admin-token': {
    roles: ['admin'],
    introduction: 'I am a super administrator',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Super Admin'
  },
  'editor-token': {
    roles: ['editor'],
    introduction: 'I am an editor',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Normal Editor'
  },
  'teacher-token': {
    roles: ['teacher'],
    introduction: 'I am a teacher',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Normal Teacher'
  },
  'student-token': {
    roles: ['student'],
    introduction: 'I am a student',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Super student'
  },
  '11813009':{
    roles: ['student'],
    introduction: 'I am a ssuper student',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Longcan Chen'
  }

}

module.exports = [
  // // user login
  // {
  //   url: '/vue-element-admin/user/login',
  //   type: 'post',
  //   response: config => {
  //     const { username } = config.body
  //     const stu_id = tokens[username]

  //     // mock error
  //     if (!stu_id) {
  //       return {
  //         code: 60204,
  //         message: 'Account and password are incorrect.'
  //       }
  //     }

  //     return {
  //       code: 20000,
  //       data: stu_id
  //     }
  //   }
  // },

  // // get user info
  // {
  //   url: '/vue-element-admin/user/info\.*',
  //   type: 'get',
  //   response: config => {
  //     const { token } = config.query
  //     const info = users[token]

  //     // mock error
  //     if (!info) {
  //       return {
  //         code: 50008,
  //         message: 'Login failed, unable to get user details.'
  //       }
  //     }

  //     return {
  //       code: 20000,
  //       data: info
  //     }
  //   }
  // },

  // user logout
  {
    url: '/vue-element-admin/user/logout',
    type: 'post',
    response: _ => {
      return {
        code: 20000,
        data: 'success'
      }
    }
  }
]
