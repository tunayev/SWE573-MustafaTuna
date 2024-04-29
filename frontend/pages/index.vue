<script setup>
const auth = useAuthStore()
</script>
<template>
  Hoşgeldiniz {{auth.user?.username}}
</template>