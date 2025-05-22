import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import RubyView from '../views/RubyView.vue'
import KanaView from '../views/KanaView.vue'
import MemchoView from '../views/MemchoView.vue'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import MyView from '@/views/MyView.vue'
import ProfileView from '@/views/ProfileView.vue'
import FanTags from '@/views/FanTags.vue'
import Schedule from '@/views/Schedule.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },
    {
      path:'/ruby',
      component:RubyView
    },
    {
      path:'/kana',
      component:KanaView
    },
    {
      path:'/memcho',
      component:MemchoView
    },
    {
      path:'/login',
      component:LoginView
    },
    {
      path:'/register',
      component:RegisterView
    },
    {
      path:'/my',
      component:MyView
    },
    {
      path:'/pro',
      component:ProfileView
    },
    {
      path:'/fan',
      component:FanTags
    },
    {
      path:'/sch',
      component:Schedule
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue'),
    },
  ],
})

export default router
