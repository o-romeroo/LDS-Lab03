import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('../views/Login/Login.vue'),
      beforeEnter: (to, from, next) => {
        let userType = localStorage.getItem('userType');
        if (userType === 'Professor') {
          next('/professor/home');
        }

        if (userType === 'Aluno') {
          next('/aluno/home');
        }

        if (userType === 'Empresa') {
          next('/empresa/home');
        }
        next();

      }
    }
    ,
    {
      path: '/professor/home',
      name: 'professor-home',
      component: () => import('../views/Professor/ProfessorHomeView.vue'),
      // beforeEnter: validaRota('Professor')
    },
    {
      path: '/professor/extrato',
      name: 'professor-extrato',
      component: () => import('../views/Professor/ProfessorExtratoView.vue'),
      // beforeEnter: validaRota('Professor')
    },
    {
      path: '/professor/melhores-alunos',
      name: 'professor-melhores-alunos',
      component: () => import('../views/Professor/MelhoresAlunos.vue'),
      // beforeEnter: validaRota('Professor')
    },
    {
      path: '/aluno/home',
      name: 'aluno-home',
      component: () => import('../views/Aluno/AlunoHomeView.vue'),
      // beforeEnter: validaRota('Aluno')
    },
    {
      path: '/aluno/extrato',
      name: 'aluno-extrato',
      component: () => import('../views/Aluno/AlunoExtratoView.vue'),
      // beforeEnter: validaRota('Aluno')
    },
    {
      path: '/aluno/melhores-alunos',
      name: 'aluno-melhores-alunos',
      component: () => import('../views/Aluno/MelhoresAlunos.vue'),
      // beforeEnter: validaRota('Aluno')
    },
    {
      path: '/empresa/home',
      name: 'empresa-home',
      component: () => import('../views/Empresa/EmpresaHomeView.vue'),
      // beforeEnter: validaRota('Empresa')
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
