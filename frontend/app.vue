<template>
  <Html lang="en" />
  <NuxtLoadingIndicator />
  <NuxtLayout>
    <NuxtPage />
  </NuxtLayout>
</template>
<script setup lang="ts">
if (process.server) {
  const config = useRuntimeConfig()
  useHead({
    script: [
      {
        src: 'https://maps.googleapis.com/maps/api/js?key=' + config.mapsApi + '&v=weekly',
        async: true,
        defer: true
      }
    ]
  })
}
</script>
