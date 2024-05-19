<template>
  <Html lang="en" />
  <NuxtLoadingIndicator />
  <NuxtLayout>
    <NuxtPage />
  </NuxtLayout>
</template>
<script setup lang="ts">
if (process.server) {
  useHead({
    script: [
      {
        src: 'https://maps.googleapis.com/maps/api/js?key=AIzaSyAVd41C5rLq3Ice-jY7nJ2S4_S62842qcU&v=weekly',
        async: true,
        defer: true
      }
    ]
  })
}
</script>
