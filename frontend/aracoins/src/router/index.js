import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/professor/home'
    },
    {
      path: '/professor/home',
      name: 'professor-home',
      component: () => import('../views/ProfessorView.vue')
    },
    {
      path: '/professor/extrato',
      name: 'professor-extrato',
      component: () => import('../views/ProfessorView.vue')
    }


  ]
})

export default router
