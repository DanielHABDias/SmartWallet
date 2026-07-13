<script setup lang="ts">
    import Aside from '@/components/organisms/Aside.vue';
</script>

<template>
    <div class="dashboard">
        <Aside/>
        <div class="content">
            <h1>DASHBOARD</h1>
        </div>
    </div>
</template>

<style scoped>
    .dashboard {
        display: flex;
        flex-direction: column;
        flex-wrap: wrap;
        height: 100vh;
    }    

    @media screen and (min-width: 1024px) {
        .dashboard {
            display: flex;
            flex-direction: row;
        }

        .content {
            padding: 1rem;
        }
    }
</style>