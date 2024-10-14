


<style scoped>
.toolbar{
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

.saldo{
    color: white;
}

.buttonToolbarspan{
    color: white;

}

.buttonToolbarspan:hover{
    color: yellow;
}

.contentUser {
    display: flex;
    align-items: center;
}



</style>

<script setup>
import Toolbar from 'primevue/toolbar';
import Button from 'primevue/button';
import Avatar from 'primevue/avatar'; 

let saldo = 0;


</script>
