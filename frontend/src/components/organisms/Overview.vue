<script setup lang="ts">
import Table from '@/components/molecules/Table.vue';
import { ref } from 'vue';

const rowData = ref([
    { id: 1, description: "Supermercado", amount: -150.50, date: "2026-07-14", category: "Alimentação" },
    { id: 2, description: "Salário", amount: 3000, date: "2026-07-10", category: "Renda" },
    { id: 3, description: "Conta de luz", amount: -220, date: "2026-07-08", category: "Utilities" },
]);

const colDefs = ref([
    { field: "id", headerName: "ID" },
    { field: "description", headerName: "Descrição" },
    { field: "amount", headerName: "Valor" },
    { field: "date", headerName: "Data" },
    { field: "category", headerName: "Categoria" }
]);
</script>

<template>
    <div class="overview">
        <Table :rows="rowData" :columns="colDefs" />
    </div>
</template>

<style scoped>
.overview {
    display: flex;
    width: 100%;
    height: 100%;
}
</style>