<script setup>
const auth = useAuthStore()
definePageMeta({
  middleware: 'auth',
})
</script>
<template>
  Hoşgeldiniz {{auth.user?.nickname}}
</template>