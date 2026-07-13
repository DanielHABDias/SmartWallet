<script setup lang="ts">
import Form from '@/components/molecules/Form.vue';
</script>

<template>
    <Form
        title="Cadastro"
        :list-fields="[
            {
                name: 'email',
                label: 'E-mail',
                type: 'email',
                placeholder: 'Digite seu e-mail'
            },
            {
                name: 'password',
                label: 'Senha',
                type: 'password'
            }
        ]"
    />
</template>