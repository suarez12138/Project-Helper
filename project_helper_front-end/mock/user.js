
const tokens = {
  admin: {
    stu_id: 'admin'
  },
  editor: {
    stu_id: 'editor'
  },
  teacher: {
    stu_id: 'teacher'
  },
  student: {
    stu_id: 'student'
  },
  11813009:{
    stu_id: '11813009'
  }
}

const users = {
  'admin': {
    roles: ['admin'],
    introduction: 'I am a super administrator',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Super Admin'
  },
  'editor': {
    roles: ['editor'],
    introduction: 'I am an editor',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Normal Editor'
  },
  'teacher': {
    roles: ['teacher'],
    introduction: 'I am a teacher',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Normal Teacher'
  },
  'student': {
    roles: ['student'],
    introduction: 'I am a student',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Super student'
  },
  '11813009':{
    roles: ['student'],
    introduction: 'I am a ssuper studen     t',
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
  //     const token2 = users[token]

  //     // mock error
  //     if (!token2) {
      
  //       return {
  //         code: 20000,
  //         token: {
  //           roles: ['student'],
  //           introduction: 'I am a ssuper studen     t',
  //           avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
  //           name: 'Longcan Chen'
  //         },
  //         message: 'Login failed, unable to get user details.'
  //       }
  //     }

  //     return {
  //       code: 20000,
  //       data: token2
  //     }
  //   }
  // },

  // user logout
  {
    url: '/vue-element-admin/user/logout\.*',
    type: 'post',
    response: config => {
      const { token } = config.query
      console.log('aaaaaa')
      return {
        code: 20000,
        data: 'success'
      }
    }
  }
]
