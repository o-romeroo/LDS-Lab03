<template>
  <div class="login-container">
    <h2 class="header">Login</h2>
    <form class="login-form" @submit.prevent="login">
      <div class="form-group">
        <InputText v-model="username" placeholder="Usuário" class="input-field-login" />
      </div>
      <div class="form-group">
        <Password v-model="password" placeholder="Senha" toggleMask class="input-field" />
      </div>
      <Button label="Entrar" class="login-button" @click="login" />
    </form>
  </div>
</template>

<script setup>
import { ref } from "vue";
import InputText from "primevue/inputtext";
import Password from "primevue/password";
import Button from "primevue/button";
import loginService from "../../services/loginService.js";



const username = ref("");
const password = ref("");

function login() {
  loginService.login({ login: username.value, senha: password.value }).then((response) => {
    if (response.data) {
      localStorage.setItem("userType", response.data.userType);
      localStorage.setItem("userId", response.data.id);
      switch (response.data.userType) {
        case "Aluno":
          window.location.href = "/aluno/home";
          break;
        case "Professor":
          window.location.href = "/professor/home";
          break;
        case "Empresa":
          window.location.href = "/empresa/home";
          break;
        default:
          break;
      }

    }
  });
}
</script>

<style scoped>
.login-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background-color: #000;
  color: #ffd700;
}

.header {
  font-size: 2rem;
  font-weight: bold;
  margin-bottom: 2rem;
  color: #ffd700;
}

.login-form {
  background-color: #222;
  border-radius: 10px;
  padding: 2rem;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.5);
  width: 100%;
  max-width: 400px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.form-group {
  margin-bottom: 1.5rem;
}

.input-field {
  display: flex;
  justify-content: center;
  width: max-content;
}

.input-field-login {
  display: flex;
  justify-content: center;
  width: 100%;

}

.login-button {
  width: max-content;
  background-color: #ffd700;
  color: #000;
  padding: 0.5rem 5.5rem;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  font-weight: bold;
  transition: background-color 0.3s, opacity 0.3s;
}

.login-button:hover {
  background-color: #ffc300;
  opacity: 0.9;
}
</style>
