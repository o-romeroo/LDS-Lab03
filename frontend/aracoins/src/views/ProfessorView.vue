


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

      <div></div><div></div>
    </div>
    <DataTable :value="alunos" :paginator="true" :rows="6">
      <Column style="width: 33%;" field="nome" header="Nome">
        <template #body="slotProps">
          <div
            style="
              display: flex;
              justify-content: flex-start;
              align-items: center;
              gap: 1rem;
            "
          >
            <img class="fotoPerfil"
              :src="slotProps.data.fotoPerfil"
              alt="Foto do Perfil"
            />


            {{ slotProps.data.nome }}
          </div>
        </template>
      </Column>
      <Column style="width: 33%;" field="curso" header="Curso" />
      <Column style="width: 33%;" field="acaoImg" header="Ações">
        <template #body>
          <button class="buttonEnviarAracoin">
            <img :src="aracoinPNG" style="width: 50px; height: auto" />
          </button>
        </template>
      </Column>
    </DataTable>
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
import Column from "primevue/column";

import aracoinPNG from "../assets/aracoin.png";
import douglas01 from "../assets/WIN_20241016_00_38_29_Pro.jpg";
import douglas02 from "../assets/WIN_20241016_00_39_02_Pro.jpg";
import douglas03 from "../assets/WIN_20241016_00_39_09_Pro.jpg";

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
    alunos.value = [
      {
        id: 1,
        fotoPerfil: douglas01,
        nome: "Douglas Machado",
        curso: "Engenharia",
        acaoImg: aracoinPNG,
      },
      {
        id: 2,
        fotoPerfil: douglas02,
        nome: "Douglas Machado 02",
        curso: "Computação",
        acaoImg: aracoinPNG,
      },
      {
        id: 3,
        fotoPerfil: douglas03,
        nome: "Douglas Machado 03",
        curso: "Fisica",
        acaoImg: aracoinPNG,
      },
      {
        id: 1,
        fotoPerfil: douglas01,
        nome: "Douglas Machado",
        curso: "Engenharia",
        acaoImg: aracoinPNG,
      },
      {
        id: 2,
        fotoPerfil: douglas02,
        nome: "Douglas Machado 02",
        curso: "Computação",
        acaoImg: aracoinPNG,
      },
      {
        id: 3,
        fotoPerfil: douglas03,
        nome: "Douglas Machado 03",
        curso: "Fisica",
        acaoImg: aracoinPNG,
      },
      {
        id: 1,
        fotoPerfil: douglas01,
        nome: "Douglas Machado",
        curso: "Engenharia",
        acaoImg: aracoinPNG,
      },
      {
        id: 2,
        fotoPerfil: douglas02,
        nome: "Douglas Machado 02",
        curso: "Computação",
        acaoImg: aracoinPNG,
      },
      {
        id: 3,
        fotoPerfil: douglas03,
        nome: "Douglas Machado 03",
        curso: "Fisica",
        acaoImg: aracoinPNG,
      },
      {
        id: 1,
        fotoPerfil: douglas01,
        nome: "Douglas Machado",
        curso: "Engenharia",
        acaoImg: aracoinPNG,
      },
      {
        id: 2,
        fotoPerfil: douglas02,
        nome: "Douglas Machado 02",
        curso: "Computação",
        acaoImg: aracoinPNG,
      },
      {
        id: 3,
        fotoPerfil: douglas03,
        nome: "Douglas Machado 03",
        curso: "Fisica",
        acaoImg: aracoinPNG,
      },
      {
        id: 1,
        fotoPerfil: douglas01,
        nome: "Douglas Machado",
        curso: "Engenharia",
        acaoImg: aracoinPNG,
      },
      {
        id: 2,
        fotoPerfil: douglas02,
        nome: "Douglas Machado 02",
        curso: "Computação",
        acaoImg: aracoinPNG,
      },
      {
        id: 3,
        fotoPerfil: douglas03,
        nome: "Douglas Machado 03",
        curso: "Fisica",
        acaoImg: aracoinPNG,
      },
    ];
  });
});
</script>









<style scoped>
.container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 1rem;
  /* gap: 5rem; */
}

.containerBuscarAluno {
  display: flex;
}

#columAlunos{
  width: 33%;

}

.fotoPerfil{
  border-radius: 100%;
  width:48px;
  height: 48px;
  object-fit: cover;

}

.containerSelectCurso{
  align-self: center;
}

.containerBuscarAluno {
  display: flex;
  align-items: center;
  background: white;
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 3px 8px;
}

.search-input {
  border: none;
  flex-grow: 1;
  margin-right: 5px;
  outline: none;
}
.buttonEnviarAracoin{
  background: none;
  border: none;
  cursor: pointer;
}

.buttonEnviarAracoin:hover{
  opacity: 0.8;
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