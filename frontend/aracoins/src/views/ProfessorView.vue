


<template>
  <div>
    <ara-toolbar></ara-toolbar>

    <div class="container">
      <div class="containerBuscarAluno">
        <InputText
          v-model="searchQuery"
          placeholder="Procurar Aluno por Nome"
          class="search-input"
        />
        <button class="search-icon-button" @click="performSearch">
          <span class="pi pi-search"></span>
        </button>
      </div>

      <div class="containerSelectCurso">
        <Select
          v-model="selectedCourse"
          :options="courses"
          optionLabel="name"
          placeholder="Procurar por Curso"
          class="w-full"
        />
      </div>

      <!-- <DataTable :value="alunos" :paginator="true" :rows="10">
        <Column field="name" header="Nome"></Column>
        <Column field="email" header="Email"></Column>
        <Column field="course" header="Curso"></Column>
        <Column field="registration" header="Matrícula"></Column>
        <Column field="actions" header="Ações"></Column>
      </DataTable> -->
    </div>
  </div>
</template>

<script setup>
import {
  onBeforeMount,
  onMounted,
  onBeforeUpdate,
  onUpdated,
  onBeforeUnmount,
  onUnmounted,
} from "vue";
import { ref } from "vue";
import AraToolbar from "../components/AraToolbar.vue";
import InputText from "primevue/inputtext";
import Select from "primevue/select";
import DataTable from "primevue/datatable";
import alunoService from "../services/alunoService.js";

const searchQuery = ref("");
const selectedCourse = ref("");
const alunos = ref([]);

const courses = ref([
  { name: "Computer Science", code: "CS" },
  { name: "Information Systems", code: "IS" },
  { name: "Software Engineering", code: "SE" },
]);

function performSearch() {
  console.log("Searching for:", searchQuery.value);
}

onMounted(() => {
  alunoService.getAlunos().then((response) => {
    alunos.value = response.data;

    console.log(response);
  });
});
</script>









<style scoped>
.container {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin: 1rem;
  gap: 2rem;
}

.containerBuscarAluno {
  display: flex;
}

.containerBuscarAluno {
  display: flex;
  align-items: center;
  background: white;
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 5px 10px;
}

.search-input {
  border: none;
  flex-grow: 1;
  margin-right: 5px;
  outline: none;
}

.search-icon-button {
  background: none;
  border: none;
  cursor: pointer;
  display: flex;
  align-items: center;
  padding: 5px;
}

.search-icon-button .pi {
  color: #333;
}
</style>