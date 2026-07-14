<script setup lang="ts">
import { ref } from "vue";
import { AgGridVue } from "ag-grid-vue3";

interface ColumnDef {
    field: string;
    headerName: string;
    cellRenderer?: any;
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
            class="ag-theme-quartz"
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
    border-radius: var(--table-radius);
    overflow: hidden;
    box-shadow: var(--table-shadow);
    border: 1px solid var(--table-border);
}

.empty-state {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    border: 2px dashed var(--table-border);    
    border-radius: var(--table-radius);
    background-color: var(--color-surface);
}

.empty-state p {
    color: var(--color-text-tertiary);
    font-size: 1rem;
    font-weight: 500;
    margin: 0;
}

/* AG Grid Theming */
:deep(.ag-theme-quartz) {
    --ag-row-border-color: var(--table-border);
    --ag-border-color: var(--table-border);
    --ag-background-color: var(--table-row-bg);
    --ag-foreground-color: var(--color-text-primary);
}

/* Header específico */
:deep(.ag-header) {
    background: var(--table-header-bg) !important;
    border-bottom: 2px solid var(--table-border);
}

:deep(.ag-header-cell) {
    background: var(--table-header-bg) !important;
    color: var(--table-header-text) !important;
    font-weight: 600;
}

:deep(.ag-header-cell:hover) {
    background: var(--table-header-bg) !important;
}

/* Linhas da tabela */
:deep(.ag-row) {
    background-color: var(--table-row-bg) !important;
}

:deep(.ag-row:hover) {
    background-color: var(--table-row-hover) !important;
}

/* Células */
:deep(.ag-cell) {
    color: var(--color-text-primary);
}
</style>