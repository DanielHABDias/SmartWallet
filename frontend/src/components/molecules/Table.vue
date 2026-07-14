<script setup lang="ts">
import { ref } from "vue";
import { AgGridVue } from "ag-grid-vue3";

interface ColumnDef {
    field: string;
    headerName: string;
}

const props = defineProps<{
    rows: Array<Record<string, any>>;
    columns?: Array<ColumnDef>;
}>();

const rowData = ref(props.rows);
let colDefs = ref(props.columns);
if (!colDefs.value || colDefs.value.length === 0) {
    colDefs.value = Object.keys(rowData.value[0] || {}).map((key) => ({
        field: key,
        headerName: key.charAt(0).toUpperCase() + key.slice(1),
    }));
}
</script>

<template>
    <div class="table">
        <div v-if="!rowData || rowData.length === 0 || !colDefs|| colDefs.length === 0" class="empty-state">
            <p>Não existem transações</p>
        </div>
        <ag-grid-vue
            v-else
            :rowData="rowData"
            :columnDefs="colDefs"
            style="height: 100%; width: 100%"
        >
        </ag-grid-vue>
    </div>
</template>

<style scoped> 
.table {
    width: 100%;
    height: 100%;
}

.empty-state {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    border: 2px dashed #ccc;    
    border-radius: var(--table-radius);
}

.empty-state p {
    color: #999;
    font-size: 2rem;
    font-weight: 500;
    margin: 0;
}
</style>