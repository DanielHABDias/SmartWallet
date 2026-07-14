<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import * as echarts from 'echarts'

interface ChartData {
    category: string
    amount: number
}

const props = defineProps<{
    rows: Array<Record<string, any>>
    categoryField?: string
    amountField?: string
}>()

const chartContainer = ref<HTMLDivElement>()
const myChart = ref<echarts.ECharts>()

// Configurações padrão
const categoryField = props.categoryField ?? 'category'
const amountField = props.amountField ?? 'amount'

// Agrupa dados por categoria
const chartData = computed(() => {
    const grouped: Record<string, number> = {}
    
    props.rows.forEach(row => {
        const category = row[categoryField]
        const amount = Math.abs(row[amountField]) // Usar valor absoluto para gastos
        
        if (category && !isNaN(amount)) {
            grouped[category] = (grouped[category] ?? 0) + amount
        }
    })
    
    return Object.entries(grouped).map(([name, value]) => ({
        value,
        name
    }))
})

const pieColors = ['#2563eb', '#06b6d4', '#f59e0b', '#ec4899', '#8b5cf6', '#06b6d4', '#14b8a6', '#f43f5e']

const initChart = () => {
    if (!chartContainer.value) return
    
    myChart.value = echarts.init(chartContainer.value)
    
    const option = {
        tooltip: {
            trigger: 'item',
            formatter: '{b}: R$ {c:,.2f}'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            textStyle: {
                color: getComputedStyle(document.documentElement).getPropertyValue('--chart-text-color').trim()
            }
        },
        series: [
            {
                name: 'Gasto por Categoria',
                type: 'pie',
                radius: '50%',
                data: chartData.value,
                color: pieColors,
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    }
    
    myChart.value.setOption(option)
}

onMounted(() => {
    setTimeout(() => {
        initChart()
    }, 100)
})

// Redimensionar quando o container mudar
const handleResize = () => {
    myChart.value?.resize()
}

window.addEventListener('resize', handleResize)
</script>

<template>
    <div ref="chartContainer" class="chart-container"></div>
</template>

<style scoped>
.chart-container {
    width: 100%;
    height: 100%;
    min-height: 400px;
}
</style>
