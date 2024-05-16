<script setup>
const auth = useAuthStore()
</script>
<template>
  Hoşgeldiniz {{auth.user?.nickname}}
</template>