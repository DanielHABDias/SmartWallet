<script setup lang="ts">
import TextBrand from '../atoms/TextBrand.vue'
import { ref, computed } from 'vue'
import {
  LayoutDashboard,
  ArrowLeftRight,
  ChartColumn,
  Tags,
  Settings,
  PanelLeftClose,
  PanelLeftOpen,
} from 'lucide-vue-next'

const isOpen = ref(false)
const activeLabel = ref('Overview')

const navItems = [
  { label: 'Overview', icon: LayoutDashboard },
  { label: 'Transactions', icon: ArrowLeftRight },
  { label: 'Analytics', icon: ChartColumn },
  { label: 'Categories', icon: Tags },
  { label: 'Configurations', icon: Settings },
]

import iconBrand from '@/assets/images/icone_smartwallet.png';

const emit = defineEmits<{
  (e: 'update:activeLabel', label: string): void
}>()
</script>

<template>
  <aside :class="['sidebar', { open: isOpen }]">
    <div class="sidebar__top">
      <button
        class="toggle"
        type="button"
        :aria-label="isOpen ? 'Recolher menu' : 'Expandir menu'"
        @click="isOpen = !isOpen"
      >
        <img :src="iconBrand" alt="Logo" class="toggle_icon"/>
      </button>
      <TextBrand class="brand"/>
    </div>

    <nav class="menu">
      <a
        v-for="item in navItems"
        :key="item.label"
        href="#"
        :class="{ active: activeLabel === item.label }"
        @click.prevent="activeLabel = item.label; emit('update:activeLabel', item.label)"
      >
        <span class="icon-slot">
          <component :is="item.icon" :size="20" />
        </span>
        <span class="label">{{ item.label }}</span>
      </a>
    </nav>
  </aside>
</template>

<style scoped>
.sidebar {
  position: relative;
  width: 100%;
  margin: 0;
  margin-bottom: 10px;
  background: var(--color-surface, var(--sb-bg));
  
  border: 1px solid var(--sb-border);
  
  overflow: hidden;
  display: flex;
  flex-direction: column;
}

.sidebar__top {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  gap: 20%;
  padding: var(--space-sm, 16px);
  width: 100%;
}

.toggle_icon {
  width: 48px;
  height: 48px;
  border-radius: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  color: #fff;
  background: linear-gradient(135deg, #3b82f6, #06b6d4);
  box-shadow: 0 3px 9px rgba(59, 130, 246, 0.35);
}

.toggle {
  flex-shrink: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 48px;
  height: 48px;
  border: 1px solid var(--sb-border);
  border-radius: 12px;
  color: var(--sb-text);
  background: transparent;
  cursor: pointer;
  transition: background 0.2s ease, color 0.2s ease, border-color 0.2s ease;
}

.toggle:hover {
  color: var(--sb-text-active);
  background: rgba(255, 255, 255, 0.06);
  border-color: rgba(255, 255, 255, 0.14);
}

.toggle:focus-visible,
nav a:focus-visible {
  outline: 2px solid var(--sb-accent);
  outline-offset: 2px;
}

nav {
  display: flex;
  flex-direction: column; 
  padding: 0 8px;
}

.sidebar:not(.open) nav {
  display: none;
}

nav > * {
  overflow: hidden;
}

.sidebar.open nav {
  padding: 4px 8px 12px;
  display: flex;
  flex-direction: column;
}

nav a {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 10px 12px;
  border-radius: 10px;
  color: var(--color-text-secondary);
  text-decoration: none;
  font-size: 14px;
  font-weight: 500;
  position: relative;
  transition:
    background 0.2s ease,
    color 0.2s ease;
}

nav a:hover {
  color: var(--color-text-primary);
  background: var(--color-surface-secondary);
}

nav a::before {
  content: '';
  position: absolute;
  left: -8px;
  top: 50%;
  transform: translateY(-50%);
  height: 60%;
  width: 3px;
  border-radius: 2px;
  background: var(--color-primary);
  opacity: 0;
  transition: opacity 0.2s ease;
}

nav a.active {
  color: var(--color-primary);
  background: color-mix(
    in srgb,
    var(--color-primary) 12%,
    transparent
  );
}

nav a.active::before {
  opacity: 1;
}

.icon-slot {
  flex-shrink: 0;
  display: flex;
}

.label {
  white-space: nowrap;
  overflow: hidden;
}

@media (prefers-reduced-motion: reduce) {
  .sidebar,
  nav,
  nav a,
  .toggle {
    transition: none !important;
  }
}

@media screen and (min-width: 1024px) {
  .sidebar {
    width: 76px;
    height: 100vh;
    margin: 0;
    border-radius: 0px 20px 20px 0px;
    transition: width 0.28s cubic-bezier(0.4, 0, 0.2, 1);
    gap: 20%
  }

  .sidebar.open {
    width: 232px;
  }

  .sidebar__top {
    padding: 16px 14px;
  }

  .toggle_icon {
    box-shadow: 0 6px 18px rgba(59, 130, 246, 0.35);
  }

  nav {
    display: flex !important;
    grid-template-rows: unset;
    opacity: 1 !important;
    padding: 4px 10px 16px;
    flex: 1;
    justify-content: flex-start;
    gap: 40px;  
  }

  nav a {
    justify-content: flex-start;
  }

  .sidebar:not(.open) nav a {
    justify-content: center;
    padding: 10px;
  }

  .sidebar:not(.open) .label {
    display: none;
  }

  .sidebar:not(.open) nav a::before {
    left: 4px;
  }

  .sidebar__top {
    justify-content: flex-start;
    gap: 0;
  }

  .sidebar:not(.open) .brand {
    display: none;
  }

  .sidebar.open .brand {
    display: block;
  }
}
</style>