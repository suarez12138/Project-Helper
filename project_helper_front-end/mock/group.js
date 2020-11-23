const Mock = require('mockjs')

const GroupList = []
const MyGroup = []
const count = 100


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
    
    name: '@increment',
    pre_time: '@first',
    information: +Mock.Random.date('T'),
    'status/1': ['完成组队', '未完成组队'] 
  }))
}
for (let i = 0; i < 4; i++) {
MyGroup.push(Mock.mock({  //tableData33
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
    
      name: '@increment',
      gender: '@first',
      skill: +Mock.Random.date('T')
  }))
}

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
    response: _ => {
      return {
        code: 20000,
        data: {
          items: MyGroup
        }
      }
    }
  }
]

