<template>
  <div>
    <div class="container">
      <img class="fotoPerfilPrimeiro" v-if="alunos.length" :src="alunos[0]?.fotoPerfil || ''" alt="Foto do Perfil" />

      <img class="fotoPerfilSegundo" v-if="alunos.length" :src="alunos[1]?.fotoPerfil || ''" alt="Foto do Perfil" />

      <img class="fotoPerfilTerceiro" v-if="alunos.length" :src="alunos[2]?.fotoPerfil || ''" alt="Foto do Perfil" />

      <img :src="podium" class="fotoPodium" />
    </div>
    <DataTable class="dataTableHowView" :value="alunos" :paginator="true" stripedRows :rows="6">
      <Column style="width: 33%" field="nome" header="Nome">
        <template #body="slotProps">
          <div style="
              display: flex;
              justify-content: flex-start;
              align-items: center;
              gap: 1rem;
            ">
            <img v-if="slotProps.data.posicaoRanking === 1" :src="first_medal" style="width: 25px; height: auto" />
            <img v-if="slotProps.data.posicaoRanking === 2" :src="second_medal" style="width: 25px; height: auto" />
            <img v-if="slotProps.data.posicaoRanking === 3" :src="third_medal" style="width: 25px; height: auto" />
            <span v-if="slotProps.data.posicaoRanking > 3" class="positionLabel">{{ slotProps.data.posicaoRanking + "º"
              }}</span>

            <img class="fotoPerfil" :src="slotProps.data.fotoPerfil" alt="Foto do Perfil" />

            {{ slotProps.data.nome }}
          </div>
        </template>
      </Column>

      <Column style="width: 33%" field="curso" header="Curso" />
      <Column style="width: 33%" field="totalMoedasRecebidas" header="Aracoins Recebidas">
        <template #body="slotProps">
          <div class="contentAracoinsRecebidas">
            <button class="buttonEnviarAracoin">
              <img :src="aracoinPNG" style="width: 50px; height: auto" />
            </button>
            <span class="quantiadeAraCoinsRecebidas">
              {{ slotProps.data.totalMoedasRecebidas }}
            </span>
          </div>
        </template>
      </Column>


    </DataTable>
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import { ref } from "vue";

import DataTable from "primevue/datatable";
import Column from "primevue/column";

import aracoinPNG from "../../assets/aracoin.png";
import douglas01 from "../../assets/douglas01.jpg";
import douglas02 from "../../assets/douglas02.jpg";
import douglas03 from "../../assets/douglas03.jpg";
import podium from "../../assets/podium.png";
import first_medal from "../../assets/first_medal.png";
import second_medal from "../../assets/second_medal.png";
import third_medal from "../../assets/third_medal.png";
import professorService from "../../services/professorService.js";

const searchQuery = ref("");
const searchQueryByCurso = ref("");
const alunos = ref([

]);


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



onMounted(() => {
  professorService.getRankingAlunos().then((response) => {
    alunos.value = response.data.map(e => ({ ...e, acaoImg: aracoinPNG }));
  });

});
</script>


<style scoped>
.container {
  position: relative;
  width: 80vw;
  /* Ajusta a largura do contêiner ao tamanho da tela */
  /* height: 50vh;  */
  display: flex;
  justify-content: center;
  align-items: center;
  background: url("@/assets/podium.png") center/contain no-repeat;
}

.fotoPerfilPrimeiro,
.fotoPerfilSegundo,
.fotoPerfilTerceiro {
  border-radius: 50%;
  /* garante círculo perfeito */
  position: absolute;
  object-fit: cover;
  z-index: 2;
  /* Acima do pódio */
}

.fotoPerfilPrimeiro {
  top: 0;
  left: 53%;
  width: 5vw;
  height: 5vw;
  margin-left: -5vw;
}

.fotoPerfilSegundo {
  top: 12%;
  left: 30%;
  width: 4.5vw;
  height: 4.5vw;
  margin-left: 4vw;
  /* margin-bottom: 4vw; */
}

.fotoPodium {
  width: 55%;
  height: 100%;
}

.fotoPerfilTerceiro {
  top: 20%;
  left: 65%;
  width: 4vw;
  height: 4vw;
  margin-left: -4vw;
}

.dataTableHowView {
  padding: 1rem;
}

.profileContainer {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  gap: 1rem;
}

.fotoPerfil {
  border-radius: 100%;
  width: 48px;
  height: 48px;
  object-fit: cover;
}

.buttonEnviarAracoin {
  background: none;
  border: none;
}

.buttonEnviarAracoin:hover {
  opacity: 0.8;
}

.positionLabel {
  font-size: 1.5rem;
  font-weight: bold;
  color: #bca941;
}

.quantiadeAraCoinsRecebidas {
  font-size: 1rem;
  font-weight: bold;
  color: #bca941;
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.contentAracoinsRecebidas {
  display: flex;
  align-items: center;
  gap: 1rem;
}
</style>