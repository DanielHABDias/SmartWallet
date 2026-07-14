<script setup lang="ts">
import { Triangle } from 'lucide-vue-next'

const props = defineProps<{
  params?: any
  value?: number | string
}>()

// Suporta tanto params (AG Grid) quanto value direto
const rawValue = props.params?.value ?? props.value ?? 0

let numValue =
  typeof rawValue === 'string'
    ? parseFloat(rawValue)
    : Number(rawValue)

if (isNaN(numValue)) {
  numValue = 0
}

const isPositive = numValue > 0
const isNegative = numValue < 0

numValue = Math.abs(numValue)
</script>

<template>
  <div class="numeric-cell">
    <span v-if="isPositive" class="icon positive">
      <Triangle
        :size="14"
        :stroke-width="2"
        color="#16a34a"
        fill="#16a34a"
        style="margin-right: 4px;"
      />
    </span>

    <span v-else-if="isNegative" class="icon negative">
      <Triangle
        :size="14"
        :stroke-width="2"
        color="#dc2626"
        fill="#dc2626"
        style="transform: rotate(180deg); margin-right: 4px;"  
      />
    </span>

    <span
      class="value"
      :class="{
        positive: isPositive,
        negative: isNegative
      }"
    >
      {{ numValue.toFixed(2) }}
    </span>
  </div>
</template>

<style scoped>
.numeric-cell {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  width: 100%;
}

.icon {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 6px;
  line-height: 0;
}

.icon.negative {
  transform: rotate(180deg);
}

.icon svg {
  display: block;
}

.value {
  font-size: 14px;
  font-weight: 600; 
}

.value.positive {
  color: var(--color-income, #16a34a);
}

.value.negative {
  color: var(--color-expense, #dc2626);
}
</style>