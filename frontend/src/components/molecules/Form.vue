<script setup lang="ts">
import { reactive } from 'vue'
import Input from '../atoms/Input.vue'

type Field = {
  name: string
  label: string
  type: string
  placeholder?: string
}

const props = defineProps<{
    title: string
    listFields: Field[]
}>()

const emit = defineEmits<{
  (e: 'submit', data: Record<string, string>): void
}>()

const form = reactive<Record<string, string>>({})

props.listFields.forEach((field) => {
  form[field.name] = ''
})

function submit() {
  emit('submit', { ...form })
}
</script>

<template>
  <form @submit.prevent="submit">
    <h2>
        {{ title }}
    </h2>

    <Input
      v-for="field in listFields"
      :id="field.name"
      v-model="form[field.name]"
      :label="field.label"
      :type="field.type"
      :placeholder="field.placeholder"
    />

    <button type="submit">
      Enviar
    </button>

  </form>
</template>

<style scoped>
form {
  width: calc(100% - 32px);
  
  background: var(--color-surface);

  border: 1px solid var(--color-border);
  border-radius: var(--card-radius);

  padding: var(--space-lg);

  margin: 16px;

  display: flex;
  flex-direction: column;
  gap: var(--space-lg);

  box-shadow: var(--shadow-medium);

  transition:
    transform 0.2s ease,
    box-shadow 0.2s ease;
}

form:hover {
  transform: translateY(-2px);
  box-shadow: var(--shadow-large);
}

/* Título */
h2 {
  margin: 0;
  
  font-size: 24px;
  font-weight: 800;
  letter-spacing: -0.5px;

  background: var(--gradient-title);

  background-clip: text;
  -webkit-background-clip: text;

  color: transparent;

  text-align: center;
}


/* Inputs */
:deep(.input_text) {
  width: 100%;
}


:deep(input) {
  width: 100%;
}



/* Botão */
button {
  width: 100%;
  height: 44px;

  border: none;

  border-radius: var(--button-radius);

  background:
    linear-gradient(
      135deg,
      var(--color-brand-start),
      var(--color-brand-end)
    );

  color: white;

  font-size: 15px;
  font-weight: 600;

  cursor: pointer;

  box-shadow:
    0 8px 20px rgba(37, 99, 235, 0.25);

  transition:
    transform 0.2s ease,
    box-shadow 0.2s ease,
    opacity 0.2s ease;
}


button:hover {
  transform: translateY(-1px);

  box-shadow:
    0 12px 28px rgba(37, 99, 235, 0.35);
}


button:active {
  transform: translateY(0);
}


button:focus-visible {
  outline: 3px solid var(--color-primary);
  outline-offset: 3px;
}

@media screen and (min-width: 1240px) {
  form {
    width: min(100%, 420px);
    padding: var(--space-xl);
  }
  h2 {
    font-size: 28px;
  }
}
</style>