const Mock = require('mockjs')

const MyGroup = {
  '11812917': [{

    skill: 'I am a super student',
    gender: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Longcan Chen'

  }],
  'editor-token': {
    skill: 'I am a super student',
    gender: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Longcan Chen'
  },
  'teacher-token': {
    skill: 'I am a super student',
    gender: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Longcan Chen'
  },
  'student-token': {
    skill: 'I am a super student',
    gender: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Longcan Chen'
  },
  '11813009':[{
    skill: 'I am a ssssuper student, I can do everything， espeically touching fish!',
    gender: 'Boy',
    name: 'Longcan Chen'
  },
  {
    skill: 'water skiing',
    gender: 'unknown',
    name: 'Zonghan He'
  },
  {
    skill: 'I am a super student',
    gender: 'Boy',
    name: 'Kaiyu Wu'
  },
  {
    skill: 'I am a super student',
    gender: 'Boy',
    name: 'Siyu Chen'
  },
  {
    skill: 'I am a super student',
    gender: 'Boy',
    name: 'Yueqian Hu'
  }]

}
const count = 20

const GroupList = []
for (let i = 0; i < count; i++) {
  GroupList.push(Mock.mock({
    // id: '@increment',
    // timestamp: +Mock.Random.date('T'),
    // author: '@first',
    // reviewer: '@first',
    // title: '@title(5, 10)',
    // content_short: 'mock data',
    // content: baseContent,
    // forecast: '@float(0, 100, 2, 2)',
    // importance: '@integer(1, 3)',
    // 'type|1': ['CN', 'US', 'JP', 'EU'],
    // 'status|1': ['published', 'draft'],
    // display_time: '@datetime',
    // comment_disabled: true,
    // pageviews: '@integer(300, 5000)',
    // image_uri,
    // platforms: ['a-platform']

    name: '@first',
    pre_time: '@increment',
    information: +Mock.Random.date('T'),
    status: '未完成组队'
  }))
}
// for (let i = 0; i < 4; i++) {
// MyGroup.push(Mock.mock({  //tableData33
//     // id: '@increment',
//     // timestamp: +Mock.Random.date('T'),
//     // author: '@first',
//     // reviewer: '@first',
//     // title: '@title(5, 10)',
//     // content_short: 'mock data',
//     // content: baseContent,
//     // forecast: '@float(0, 100, 2, 2)',
//     // importance: '@integer(1, 3)',
//     // 'type|1': ['CN', 'US', 'JP', 'EU'],
//     // 'status|1': ['published', 'draft'],
//     // display_time: '@datetime',
//     // comment_disabled: true,
//     // pageviews: '@integer(300, 5000)',
//     // image_uri,
//     // platforms: ['a-platform']

//       name: '@increment',
//       gender: '@first',
//       skill: +Mock.Random.date('T')
//   }))
// }

module.exports = [
  {
    url: '/vue-element-admin/group/group_list',
    type: 'get',
    response: _ => {

      return {
        code: 20000,
        data: {
          items: GroupList
        }
      }
    }
  },


  {
    url: '/vue-element-admin/group/my_group',
    type: 'get',
    response: config => {
      const{token} = config.query
      return {
        code: 20000,
        data: {
          items: MyGroup[token]
        }
      }
    }
  }
]

