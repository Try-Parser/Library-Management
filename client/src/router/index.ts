import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Home from '../views/Home.vue'
import MyBooks from '../views/MyBooks.vue'
import Books from '../views/Books.vue'
import User from '../views/User.vue'

import { getCredentials } from '@/stores/credentials'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    }, {
      path: '/home',
      name: 'home',
      component: Home,
      meta: { requiresLogin: true }
    }, {
      path: '/mybooks',
      name: 'mybooks',
      component: MyBooks,
      meta: { requiresLogin: true }
    }, {
      path: '/publish',
      name: 'publish',
      component: Books,
      meta: { requiresLogin: true }
    }, {
      path: '/user',
      name: 'user',
      component: User,
      meta: { requiresLogin: true }
    }
  ]
})

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresLogin) && !getCredentials().authorized()) {
      next("/")
    } else {
      next()
    }
})

export default router
