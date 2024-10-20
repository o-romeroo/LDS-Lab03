<template>
  <div class="container">
    <i class="pi pi-spin pi-spinner" style="font-size: 2rem"></i>
    
    <DataTable
      :value="extratoAluno"
      class="datableAlunoExtrato"
      :paginator="true"
      :striped-rows="true"
      :rows="6"
    >
      <Column field="parteEnvolvida" header="Parte Envolvida">
        <template #body="slotProps">
          <div style="display: flex; align-items: center; gap: 0.5rem">
            <div style="gap: 1rem; display: flex; align-items: center">
              <img :src="slotProps.data.fotoPerfil" class="fotoPerfil" />
              {{ slotProps.data.parteEnvolvida }}
            </div>
            <div
              style="
                display: flex;
                justify-content: center;
                gap: 0.3rem;
                align-items: center;
              "
            >
              <img :src="aracoin" alt="" class="fotoAracoin" />
              <span class="quantiadeAraCoinsRecebidas">
                {{ slotProps.data.montante }}
              </span>
              <i
                v-if="slotProps.data.tipoTransacao === 'SAIDA'"
                class="pi pi-arrow-up"
                style="font-size: 1rem; color: red"
              ></i>
              <i
                v-if="slotProps.data.tipoTransacao === 'ENTRADA'"
                class="pi pi-arrow-down"
                style="font-size: 1rem; color: green"
              ></i>
            </div>
          </div>
        </template>
      </Column>
      <Column field="detalhes" header="Detalhes"></Column>
      <Column field="data" header="Data"></Column>
      <Column field="montante" header="Montante">
        <template #body="slotProps">
          <span
            :class="{
              'text-green': slotProps.data.montante > 0,
              'text-red': slotProps.data.montante < 0,
            }"
          >
            {{ slotProps.data.montante }}
          </span>
        </template>
      </Column>
      <Column field="tipoTransacao" header="Tipo de Transação"></Column>
    </DataTable>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import { ref } from "vue";

import aracoin from "../../assets/aracoin.png";
import DataTable from "primevue/datatable";
import Column from "primevue/column";
import douglas01 from "../../assets/douglas01.jpg";
import douglas03 from "../../assets/douglas03.jpg";
import bocadoforno from "../../assets/boca_do_forno.png";
import aramuni from "../../assets/aramuni.png";

const extratoAluno = ref([]);

onMounted(() => {
  extratoAluno.value = [
    {
      montante: 150,
      parteEnvolvida: "Jõao Paulo Aramuni",
      detalhes: "Tirou a maior nota na primeira prova",
      data: "28/09/2024",
      fotoPerfil: aramuni,
      tipoTransacao: "ENTRADA",
    },
    {
      montante: -50,
      parteEnvolvida: "Boca do Forno",
      detalhes: "Cupom de R$15 para salgados em qualquer unidade",
      data: "15/10/2024",
      fotoPerfil: bocadoforno,
      tipoTransacao: "SAIDA",
    },
  ];
});
</script>









<style scoped>
.container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  /* gap: 5rem; */
}

.fotoPerfil {
  border-radius: 100%;
  width: 48px;
  height: 48px;
  object-fit: cover;
}

.fotoAracoin {
  border-radius: 100%;
  width: 32px;
  height: 32px;
  object-fit: cover;
}

.datableAlunoExtrato {
  width: 100%;
}

.quantiadeAraCoinsRecebidas {
  font-size: 1rem;
  font-weight: bold;
  color: #bca941;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
</style>