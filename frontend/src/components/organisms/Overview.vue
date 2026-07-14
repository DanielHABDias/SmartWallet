<script setup lang="ts">
import LineChart from '@/components/molecules/LineChart.vue';
import PieChart from '@/components/molecules/PieChart.vue';
import Table from '@/components/molecules/Table.vue';
import NumericCell from '@/components/atoms/NumericCell.vue';
import { ref } from 'vue';

const rowData = ref([
    { id: 1, description: "Supermercado", amount: -150.50, date: "2026-07-14", category: "Alimentação" },
    { id: 2, description: "Salário", amount: 3000, date: "2026-07-10", category: "Renda" },
    { id: 3, description: "Conta de luz", amount: -220, date: "2026-07-08", category: "Utilities" },
]);

const colDefs = ref([
    { field: "id", headerName: "ID" },
    { field: "description", headerName: "Descrição" },
    { field: "amount", headerName: "Valor", cellRenderer: NumericCell },
    { field: "date", headerName: "Data" },
    { field: "category", headerName: "Categoria" }
]);
</script>

<template>
    <div class="overview">
        <div class="table-panel">
            <Table :rows="rowData" :columns="colDefs" />
        </div>
    
        <div class="charts-panel">
          <LineChart :rows="rowData" dateField="date" amountField="amount" />
          <PieChart :rows="rowData" categoryField="category" amountField="amount" />
        </div>
    </div>
</template>

<style scoped>
.overview {
    display: grid;
    grid-template-columns: 2fr 1fr;
    gap: 24px;
    width: 100%;
    height: 100%;
    padding: 0.2rem;
}

.table-panel {
    min-width: 0;
}

.charts-panel {
    display: flex;
    flex-direction: column;
    gap: 20px;
}
</style>