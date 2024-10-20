<template>
  <div class="container">
    <Card v-for="vantagem in vantagens" :key="vantagem.nome" class="card">
      <img :src="vantagem.imagem" alt="Imagem" class="card-image" />
      <div class="card-content">
        <div class="card-title">{{ vantagem.nome }}</div>
        <div class="card-description">{{ vantagem.descricao }}</div>
        <div class="card-price">
          <img
            :src="aracoin"
            alt="Aracoin"
            style="width: 36px; height: 32px; margin-right: 5px"
          />
          <span class="priceText">{{ vantagem.preco }}</span>
        </div>
        <Button label="Comprar" class="buttonComprarVantagem"></Button>
      </div>
    </Card>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";

import Button from "primevue/button";

import trailer_puc_minas from "../../assets/trailer_puc_minas.png";
import matricula_pucminas from "../../assets/matricula_pucminas.png";
import boca_do_forno from "../../assets/boca_do_forno.png";
import aracoin from "../../assets/aracoin.png";

const vantagens = ref([]);

onMounted(() => {
  vantagens.value = [
    {
      nome: "Puc Coração Eurcaristico",
      imagem: matricula_pucminas,
      descricao: "Cupom de R$200 na próxima matrícula",
      preco: 200,
    },
    {
      nome: "Trailer PUC Coreu",
      imagem: trailer_puc_minas,
      descricao: "Cupom de R$10 no trailer da PUC Coreu",
      preco: 60,
    },
    {
      nome: "Boca do Forno",
      imagem: boca_do_forno,
      descricao: "Cupom de R$15 para salgados em qualquer unidade",
      preco: 80,
    },
  ];
});
</script>

<style scoped>
.container {
  display: flex;
  flex-wrap: wrap;
  gap: 2rem;
  justify-content: center;
  padding: 2rem;
}

.card {
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  transition: transform 0.2s, box-shadow 0.2s;
  max-width: 300px;
  width: 100%;
  display: flex;
  flex-direction: column;
}

.card:hover {
  transform: translateY(-5px);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.card-image {
  width: 100%;
  height: 180px;
  object-fit: cover;
}

.card-content {
  padding: 1rem;
  display: flex;
  flex-direction: column;
  background-color: #333;
  gap: 0.5rem;
}

.card-title {
  font-size: 1.25rem;
  font-weight: bold;
  color: white;
  margin: 0;
}

.card-price {
  display: flex;
  align-items: center;
}

.card-description {
  font-size: 1rem;
  color: white;
  line-height: 1.4;
}

.priceText {
  font-size: 1.25rem;
  font-weight: bold;
  color: white;
  margin: 0;
}
.buttonComprarVantagem {
  background: #555;
  color: white;
  border: none;
  cursor: pointer;
  padding: 0.5rem;
  border-radius: 8px;
  margin-top: 1rem;
}

.buttonComprarVantagem:hover {
  background: #ebeb00;
  opacity: 0.8;
}
</style>
