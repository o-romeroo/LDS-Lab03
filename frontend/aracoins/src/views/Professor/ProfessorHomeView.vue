<template>
  <div>
    <div class="container">
      <div class="containerBuscarAluno">
        <InputText
          v-model="searchQuery"
          placeholder="Procurar Aluno por Nome"
          class="search-input"
        />
        <button class="search-icon-button" @click="buscarAluno">
          <span class="pi pi-search"></span>
        </button>
      </div>

      <div class="containerBuscarAluno">
        <InputText
          v-model="searchQueryByCurso"
          placeholder="Procurar Aluno por Curso"
          class="search-input"
        />
        <button class="search-icon-button" @click="buscarAlunoByCurso">
          <span class="pi pi-search"></span>
        </button>
      </div>

      <div></div>
      <div></div>
    </div>
    <DataTable
      class="dataTableHowView"
      :value="alunos"
      :paginator="true"
      stripedRows 
      :rows="6"
    >
      <Column style="width: 33%" field="nome" header="Nome">
        <template #body="slotProps">
          <div
            style="
              display: flex;
              justify-content: flex-start;
              align-items: center;
              gap: 1rem;
            "
          >
            <img
              class="fotoPerfil"
              :src="slotProps.data.fotoPerfil"
              alt="Foto do Perfil"
            />

            {{ slotProps.data.nome }}
          </div>
        </template>
      </Column>
      <Column style="width: 33%" field="curso" header="Curso" />
      <Column style="width: 33%" field="acaoImg" header="Ações">
        <template #body>
          <button class="buttonEnviarAracoin" @click="openDialogEnviarMoedas">
            <img :src="aracoinPNG" style="width: 50px; height: auto" />
          </button>
        </template>
      </Column>
    </DataTable>

    <Dialog
      v-model:visible="showDialog"
      style="width: 40%; text-align: center"
    >
      <template #header>
        <div class="headerDialog">
          <p>Enviar Moedas</p>
        </div>
      </template>

      <ModalTransferirMoedas />
    </Dialog>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import { ref } from "vue";

import InputText from "primevue/inputtext";
import DataTable from "primevue/datatable";
import Column from "primevue/column";

import aracoinPNG from "../../assets/aracoin.png";
import douglas01 from "../../assets/douglas01.jpg";
import douglas02 from "../../assets/douglas02.jpg";
import douglas03 from "../../assets/douglas03.jpg";
import Dialog from "primevue/dialog";
import ModalTransferirMoedas from "@/components/ModalTransferirMoedas.vue";

const searchQuery = ref("");
const searchQueryByCurso = ref("");
const alunos = ref([]);
const filteredAlunos = ref([]);

const showDialog = ref(false);

const courses = ref([
  { name: "Computer Science", code: "CS" },
  { name: "Information Systems", code: "IS" },
  { name: "Software Engineering", code: "SE" },
]);

function buscarAluno() {
  console.log("Searching for:", searchQuery.value);
}

function buscarAlunoByCurso() {
  console.log("Searching for:", searchQueryByCurso.value);
}

function openDialogEnviarMoedas() {
  showDialog.value = true;
}



onMounted(() => {
  // alunoService.getAlunos().then((response) => {
  //   alunos.value = response.data;
  // });

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

#columAlunos {
  width: 33%;
}

.dataTableHowView {
  padding: 1rem;
}

.fotoPerfil {
  border-radius: 100%;
  width: 48px;
  height: 48px;
  object-fit: cover;
}

.containerSelectCurso {
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
.buttonEnviarAracoin {
  background: none;
  border: none;
  cursor: pointer;
}

.buttonEnviarAracoin:hover {
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

.headerDialog{
  font-size: 1.5rem;
  font-weight: bold;
  padding: 1rem;
  margin-left: 2rem;
  width: 100%;
  justify-content: center;
  display: flex;
}
</style>