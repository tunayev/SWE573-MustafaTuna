<script setup lang="ts">
import { Bars3Icon } from '@heroicons/vue/24/outline'
import { useAuthStore } from '~~/stores/auth'

defineProps<{
  navigation: NavigationItem[]
}>()

const mobileMenu = useMobileMenu()

const auth = useAuthStore()
</script>

<template>
  <nav class="container mx-auto flex items-center justify-between" aria-label="Global">
    <div class="flex lg:flex-1">
      <Logo />
    </div>
    <Search />
    <div class="flex lg:hidden">
      <button type="button" class="-m-2.5 inline-flex items-center justify-center rounded-md p-2.5 text-gray-700" @click="mobileMenu = true">
        <span class="sr-only">Open main menu</span>
        <Bars3Icon class="h-6 w-6" aria-hidden="true" />
      </button>
    </div>
    <div class="hidden lg:flex lg:gap-x-12">
      <template v-for="item in navigation" :key="item.title">
        <div v-if="item.children" :items="item.children" :title="item.title" :to="item.to" />
        <NuxtLink v-else :to="item.to" class="text-sm font-semibold leading-6">
          {{ item.title }}
        </NuxtLink>
      </template>
    </div>
    <div class="hidden lg:flex lg:flex-1 lg:justify-end">
      <div @click="logout" v-if="auth.loggedIn" to="/logout" class="cursor-pointer text-sm font-semibold leading-6 text-gray-900">
        Çıkış Yap <span aria-hidden="true">&rarr;</span>
      </div>
      <NuxtLink v-if="!auth.loggedIn" to="/auth/login" class="text-sm font-semibold leading-6 text-gray-900">
        Giriş <span aria-hidden="true">&rarr;</span>
      </NuxtLink>
    </div>
  </nav>
</template>
