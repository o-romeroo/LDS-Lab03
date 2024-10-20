<script setup>
import AraToolbar from "./router/AraToolbar.vue";
</script>

<template>
  <div>
    <ara-toolbar :saldo="saldo" class="toolbar-fixed" />
    <div style="margin-top: 5rem">
      <RouterView />
    </div>
  </div>
</template>

<style scoped>
.toolbar-fixed {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 5;
  background: white; 
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); 
}
</style>
  
