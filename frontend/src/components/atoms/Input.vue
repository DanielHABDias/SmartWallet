<script setup lang="ts">
defineProps<{
  label: string
  id: string
  type?: string
  placeholder?: string
  modelValue?: string
  disabled?: boolean
  required?: boolean
  error?: string
}>()

const emit = defineEmits<{
  (e: 'update:modelValue', value: string): void
}>()
</script>

<template>
    <div class="input">
        <label :for="id">
            {{ label }}
        </label>

        <input
            :id="id"
            :type="type ?? 'text'"
            :placeholder="placeholder"
            :value="modelValue"
            :disabled="disabled"
            :required="required"
            @input="emit('update:modelValue', ($event.target as HTMLInputElement).value)"
        />

        <span v-if="error" class="error">
            {{ error }}
        </span>
    </div>
</template>

<style scoped>
.input {
    display: flex;
    flex-direction: column;
    gap: var(--space-sm);
}

input {
    width: 15rem;
    height: 2rem;
    border: 1px solid var(--color-border);
    border-radius: var(--input-radius);
    background: var(--color-surface);
    color: var(--color-text-primary);
    padding: 2px;
}

input:hover {
    border-color: var(--color-border-hover);
}

input:focus {
    outline: none;
    border-color: var(--color-primary);
}
</style>