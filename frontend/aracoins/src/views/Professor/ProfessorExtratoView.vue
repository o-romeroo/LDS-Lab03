


<template>
  <div>
    <div class="container">
      <div class="containerBuscarExtrato">
        <InputText
          v-model="searchQuery"
          placeholder="Procurar Extrato"
          class="search-input"
        />
        <button class="search-icon-button" @click="performSearch">
          <span class="pi pi-search"></span>
        </button>
      </div>

      <div></div>
      <div></div>
    </div>

    <DataTable
      class="dataTableHowView"
      :value="extratos"
      stripedRows
      :paginator="true"
      :rows="6"
    >
      <Column style="width: 4%" field="montante">
        <template #body="slotProps">
          <div
            style="
              display: flex;
              justify-content: flex-start;
              align-items: center;
              gap: 1rem;
            "
          >
            <i
              v-if="slotProps.data.tipoTransacao === 'saida'"
              class="pi pi-arrow-up"
              style="font-size: 1rem; color: red"
            ></i>
            <i
              v-if="slotProps.data.tipoTransacao === 'entrada'"
              class="pi pi-arrow-down"
              style="font-size: 1rem; color: green"
            ></i>

            <img :src="aracoinPNG" style="width: 25px; height: auto" />
            {{ slotProps.data.montante }}
          </div>
        </template>
      </Column>
      <Column style="width: 32%" field="destinatario" header="Aluno">
        <template #body="slotProps">
          <div style="display: flex; align-items: center; gap: 1rem">
            <img
              class="fotoPerfil"
              :src="slotProps.data.fotoRemetente"
              alt="Foto do Perfil"
            />
            {{ slotProps.data.destinatario }}
          </div>
        </template>
      </Column>
      <Column style="width: 32%" field="motivo" header="Motivo" />
      <Column style="width: 32%" field="data" header="Data" />
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

import InputText from "primevue/inputtext";
import Select from "primevue/select";
import DataTable from "primevue/datatable";
import professorService from "../../services/professorService.js";
import Column from "primevue/column";

import aracoinPNG from "../../assets/aracoin.png";
import douglas01 from "../../assets/douglas01.jpg";
import douglas02 from "../../assets/douglas02.jpg";
import douglas03 from "../../assets/douglas03.jpg";


const extratos = ref([]);
function performSearch() {
  
}

onMounted(() => {
  professorService.getExtrato().then(response => extratos.value = response.data.map(e=>({...e, tipoTransacao: 'saida'})));
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

.containerBuscarExtrato {
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

.containerBuscarExtrato {
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
</style>