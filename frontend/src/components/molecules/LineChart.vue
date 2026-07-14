<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import * as echarts from 'echarts'

const props = defineProps<{
    rows: Array<Record<string, any>>
    dateField?: string
    amountField?: string
}>()

const chartContainer = ref<HTMLDivElement>()
const myChart = ref<echarts.ECharts>()

// Configurações padrão
const dateField = props.dateField ?? 'date'
const amountField = props.amountField ?? 'amount'

// Agrupa dados por data e calcula o valor acumulado
const chartData = computed(() => {
    const sortedRows = [...props.rows].sort((a, b) => {
        const dateA = new Date(a[dateField]).getTime()
        const dateB = new Date(b[dateField]).getTime()
        return dateA - dateB
    })
    
    let accumulated = 0
    const data = sortedRows.map(row => {
        const amount = row[amountField] ?? 0
        accumulated += amount
        return {
            date: row[dateField],
            value: accumulated
        }
    })
    
    return data
})

const xAxisData = computed(() => chartData.value.map(d => d.date))
const seriesData = computed(() => chartData.value.map(d => d.value))

const initChart = () => {
    if (!chartContainer.value) return
    
    myChart.value = echarts.init(chartContainer.value)
    
    const textColor = getComputedStyle(document.documentElement).getPropertyValue('--chart-text-color').trim()
    const gridColor = getComputedStyle(document.documentElement).getPropertyValue('--chart-grid-color').trim()
    const lineColor = getComputedStyle(document.documentElement).getPropertyValue('--chart-line-color').trim()
    const areaColor = getComputedStyle(document.documentElement).getPropertyValue('--chart-area-color').trim()
    
    const option = {
        tooltip: {
            trigger: 'axis',
            formatter: (params: any) => {
                if (params.length > 0) {
                    return `${params[0].axisValue}<br/>Valor: R$ ${params[0].value.toLocaleString('pt-BR', { minimumFractionDigits: 2 })}`
                }
                return ''
            }
        },
        xAxis: {
            type: 'category',
            data: xAxisData.value,
            axisLine: {
                lineStyle: {
                    color: gridColor
                }
            },
            axisLabel: {
                color: textColor
            }
        },
        yAxis: {
            type: 'value',
            axisLine: {
                lineStyle: {
                    color: gridColor
                }
            },
            splitLine: {
                lineStyle: {
                    color: gridColor
                }
            },
            axisLabel: {
                color: textColor
            }
        },
        series: [
            {
                name: 'Gasto ao Longo do Tempo',
                type: 'line',
                data: seriesData.value,
                smooth: true,
                lineStyle: {
                    color: lineColor,
                    width: 3
                },
                areaStyle: {
                    color: areaColor
                },
                itemStyle: {
                    color: lineColor,
                    borderWidth: 2,
                    borderColor: '#fff'
                }
            }
        ],
        grid: {
            left: '10%',
            right: '10%',
            top: '10%',
            bottom: '10%',
            containLabel: true
        }
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
