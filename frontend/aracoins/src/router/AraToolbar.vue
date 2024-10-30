<template>
  <div class="card">


    <Toolbar class="toolbar" v-if="usuarioType === 'Professor'">
      <template #start>
        <div class="contentToolbarItens">
          <router-link to="/professor/home" active-class="button-active">
            <Button style="background-color: black" label="Home" text plain><span
                class="buttonToolbarspan">Home</span></Button>
          </router-link>

          <router-link to="/professor/extrato" active-class="button-active">
            <Button style="background-color: black" label="Extrato" text plain><span
                class="buttonToolbarspan">Extrato</span></Button>
          </router-link>

          <router-link to="/professor/melhores-alunos" active-class="button-active">
            <Button style="background-color: black" label="Melhores_Alunos" text plain><span
                class="buttonToolbarspan">Melhores_Alunos</span></Button>
          </router-link>

        </div>
      </template>

      <template #end>
        <span class="saldo">{{ saldo }}</span>

        <img src="@/assets/aracoin.png" alt="AraCoin" style="width: 40px; height: 32px; margin-right: 1rem" />
        <Avatar image="https://primefaces.org/cdn/primevue/images/avatar/amyelsner.png"
          style="width: 32px; height: 32px" />
        <div class="contentUser">
          <Button icon="pi pi-sign-out" class="p-button-rounded p-button-danger" @click="logout" />
        </div>


      </template>
    </Toolbar>

    <Toolbar class="toolbar" v-if="usuarioType === 'Aluno'">
      <template #start>
        <div class="contentToolbarItens">
          <router-link to="/professor/home" active-class="button-active">
            <Button style="background-color: black" label="Home" text plain><span
                class="buttonToolbarspan">Home</span></Button>
          </router-link>

          <router-link to="/professor/extrato" active-class="button-active">
            <Button style="background-color: black" label="Extrato" text plain><span
                class="buttonToolbarspan">Extrato</span></Button>
          </router-link>

          <router-link to="/professor/melhores-alunos" active-class="button-active">
            <Button style="background-color: black" label="Melhores_Alunos" text plain><span
                class="buttonToolbarspan">Melhores_Alunos</span></Button>
          </router-link>

        </div>
      </template>

      <template #end>
        <span class="saldo">{{ saldo }}</span>

        <img src="@/assets/aracoin.png" alt="AraCoin" style="width: 40px; height: 32px;" />
        <Avatar image="https://primefaces.org/cdn/primevue/images/avatar/amyelsner.png"
          style="width: 32px; height: 32px; margin-right:1rem ;" />

        <div class="contentUser">
          <Button icon="pi pi-sign-out" class="p-button-rounded p-button-danger" @click="logout" />
        </div>

      </template>
    </Toolbar>

    <Toolbar class="toolbar" v-if="usuarioType === 'Empresa' || usuarioType === 'InstituicaoEnsino'">
      <template #start>
        <div class="contentToolbarItens">
          <router-link to="/professor/home" active-class="button-active">
            <Button style="background-color: black" label="Home" text plain><span
                class="buttonToolbarspan">Home</span></Button>
          </router-link>
        </div>
      </template>

      <template #end>
        <span class="saldo">{{ saldo }}</span>

        <img src="@/assets/aracoin.png" alt="AraCoin" style="width: 40px; height: 32px; margin-right: 1rem" />

        <div class="contentUser">
          <Button icon="pi pi-sign-out" class="p-button-rounded p-button-danger" @click="logout" />
        </div>

      </template>
    </Toolbar>

  </div>
</template>

<script setup>
import Toolbar from "primevue/toolbar";
import Button from "primevue/button";
import Avatar from "primevue/avatar";
import professorService from '../services/professorService.js';
import { ref } from "vue";

import { onMounted, onBeforeUnmount } from 'vue';

const usuarioType = ref(localStorage.getItem("userType"));
const userId = ref(localStorage.getItem("userId"));

const saldo = ref(0);


function consultaSaldo(){
  professorService.consultaHeader(userId.value).then((response) => {
    saldo.value = response.data.saldoMoedas;
  });
}


function logout() {
  localStorage.removeItem("userType");
  localStorage.removeItem("userId");
  window.location.href = "/";
}


onMounted(() => {
  consultaSaldo();
});

</script>

<style scoped>
.toolbar {
  background-color: black;
  color: white;
  padding: 1rem 1rem 1rem 1.5rem;
  /* border-radius: 3rem; */
}

.contentToolbarItens {
  display: flex;
  align-items: center;
  gap: 3rem;
  justify-content: flex-start;
  background-color: black;
}

.saldo {
  color: white;
}

.button-active .buttonToolbarspan {
  color: yellow;
  opacity: 0.9;
}

.buttonToolbarspan {
  color: white;
  opacity: 0.7;
}

.buttonToolbarspan:hover {
  color: yellow;
  opacity: 0.9;
}

.contentUser {
  display: flex;
  align-items: center;
}
</style>
