<template>
    <footer class="footer">
        <p>
            © {{ year }} SmartWallet. Todos os direitos reservados.
        </p>
    </footer>
</template>

<script setup lang="ts">
    const year = new Date().getFullYear();
</script>


<style scoped>
    .footer {
        padding: 24px;
        text-align: center;

        background: var(--color-surface);
        color: var(--color-text-secondary);

        border-top: 1px solid var(--color-border);

        bottom: 0;
    }
</style>