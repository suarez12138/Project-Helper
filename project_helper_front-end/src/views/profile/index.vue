<template>
  <div class="app-container">
    <div v-if="user">
      <el-row :gutter="20">

        <el-col :span="6" :xs="24">
          <user-card :user="user" />
        </el-col>

        <el-col :span="18" :xs="24">
          <el-card>
            <el-tabs v-model="activeTab">
              <!--              <el-tab-pane label="Activity" name="activity">-->
              <!--                <activity />-->
              <!--              </el-tab-pane>-->
              <!--              <el-tab-pane label="Timeline" name="timeline">-->
              <!--                <timeline />-->
              <!--              </el-tab-pane>-->
              <el-tab-pane label="Account" name="account">
                <account :user="user" />
              </el-tab-pane>
            </el-tabs>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import UserCard from './components/UserCard'
// import Activity from './components/Activity'
// import Timeline from './components/Timeline'
import Account from './components/Account'
import { getToken } from '@/utils/auth'
import { get_profile } from '@/api/student/profile'

export default {
  name: 'Profile',
  components: { UserCard, Account },
  data() {
    return {
      user: {},
      activeTab: 'account'
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'avatar',
      'roles'
    ])
  },
  created() {
    this.getUser()
  },
  methods: {
    getUser() {
      this.user = {
        id: getToken(),
        role: this.roles.join(' | '),
        name: 'miao',
        email: getToken()+"@mail.sustech.edu.cn",
        avatar: this.avatar,
        gender: '',
        location: '',
        introduction: '我bu就是我'
      }
      get_profile(getToken()).then(response => {
        this.user = {
          id: getToken(),
          role: this.roles.join(' | '),
          name: response.data.name,
          email: getToken()+"@mail.sustech.edu.cn",
          avatar: this.avatar,
          gender: response.data.gender,
          location: response.data.location,
          introduction: response.data.introduction
        }
      })
      // this.user = {
      //   id: getToken(),
      //   role: this.roles.join(' | '),
      //   name: 'miao',
      //   email: 'admin@test.com',
      //   avatar: this.avatar,
      //   gender: '',
      //   location: '',
      //   introduction: '我bu就是我'
      //   // introduction: this.introduction
      // }
    }
  }
}
</script>

<style>

.el-card, .el-input__inner, .el-textarea__inner,.el-button,.el-select-dropdown,.el-select-dropdown__list,.el-select-dropdown__item.hover  {
  border-radius: 30px;
}
</style>
