<script setup lang="ts">
    import Aside from '@/components/organisms/Aside.vue';
    import Analytics from '@/components/organisms/Analytics.vue';
    import Transactions from '@/components/organisms/Transactions.vue';
    import Categories from '@/components/organisms/Categories.vue';
    import Configurations from '@/components/organisms/Configurations.vue';
    import Overview from '@/components/organisms/Overview.vue';
    import { ref } from 'vue';

    const activeComponent = ref('Overview');
</script>

<template>
    <div class="dashboard">
        <Aside @update:activeLabel="activeComponent = $event"/>
        <div class="content">
            <Analytics v-if="activeComponent === 'Analytics'" />
            <Transactions v-else-if="activeComponent === 'Transactions'" />
            <Categories v-else-if="activeComponent === 'Categories'" />
            <Configurations v-else-if="activeComponent === 'Configurations'" />
            <Overview v-else />
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