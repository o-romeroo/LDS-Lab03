 <template>
  <div class="card">
    <Toolbar class="toolbar">
      <template #start>
        <div class="contentToolbarItens">
          <router-link to="/professor/home" active-class="button-active">
            <Button style="background-color: black" label="Home" text plain
              ><span class="buttonToolbarspan">Home</span></Button
            >
          </router-link>

          <router-link to="/professor/extrato" active-class="button-active">
            <Button style="background-color: black" label="Extrato" text plain
              ><span class="buttonToolbarspan">Extrato</span></Button
            >
          </router-link>
          
          <router-link to="/professor/melhores-alunos" active-class="button-active">
            <Button
              style="background-color: black"
              label="Melhores_Alunos"
              text
              plain
              ><span class="buttonToolbarspan">Melhores_Alunos</span></Button
            >
          </router-link>

        </div>
      </template>

      <template #end>
        <span class="saldo">{{ saldo }}</span>

        <img
          src="@/assets/aracoin.png"
          alt="AraCoin"
          style="width: 40px; height: 32px; margin-right: 1rem"
        />

        <div class="contentUser">
          <Avatar
            image="https://primefaces.org/cdn/primevue/images/avatar/amyelsner.png"
            style="width: 32px; height: 32px"
          />
        </div>
      </template>
    </Toolbar>
  </div>
</template>

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

<script setup>
import Toolbar from "primevue/toolbar";
import Button from "primevue/button";
import Avatar from "primevue/avatar";

let saldo = 1240;
</script>

