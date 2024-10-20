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
      component: () => import('../views/Professor/ProfessorHomeView.vue'),
      // beforeEnter: validaRota('professor')
    },
    {
      path: '/professor/extrato',
      name: 'professor-extrato',
      component: () => import('../views/Professor/ProfessorExtratoView.vue'),
      // beforeEnter: validaRota('professor')
    },
    {
      path: '/professor/melhores-alunos',
      name: 'professor-melhores-alunos',
      component: () => import('../views/Professor/MelhoresAlunos.vue'),
      // beforeEnter: validaRota('professor')
    },
    {
      path: '/aluno/home',
      name: 'aluno-home',
      component: () => import('../views/Aluno/AlunoHomeView.vue'),
      // beforeEnter: validaRota('aluno')
    },
    {
      path: '/aluno/extrato',
      name: 'aluno-extrato',
      component: () => import('../views/Aluno/AlunoExtratoView.vue'),
      // beforeEnter: validaRota('aluno')
    },
    {
      path: '/aluno/melhores-alunos',
      name: 'aluno-melhores-alunos',
      component: () => import('../views/Aluno/MelhoresAlunos.vue'),
      // beforeEnter: validaRota('aluno')
    },
    {
      path: '/empresa/home',
      name: 'empresa-home',
      component: () => import('../views/Empresa/EmpresaHomeView.vue'),
      beforeEnter: validaRota('empresa')
    }



  ]
})

function validaRota(tipoPermissao) {
  return (to, from, next) => {
    const user = JSON.parse(localStorage.getItem('user'));

    if (user && user.type === tipoPermissao) {
      next();
    } else {
      next('/login');
    }
  }
}

export default router
