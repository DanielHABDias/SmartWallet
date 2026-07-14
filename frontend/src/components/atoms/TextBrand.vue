<script setup lang="ts">
import { computed } from 'vue'

const props = defineProps<{
    fontSize?: string
}>()

const finalFontSize = computed(() => props.fontSize || 'var(--font-size-xxl)')
</script>

<template>
    <h1>
        SMART WALLET
    </h1>
</template>

<style scoped>

h1 {
  margin-left: 12px;
  font-size: v-bind('finalFontSize');
  white-space: nowrap;
  letter-spacing: -0.5px;
  font-weight: 800;

  background: var(--gradient-title);
  background-clip: text;
  -webkit-background-clip: text;
  color: transparent;
}

</style>