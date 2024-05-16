<script setup>
</script>
<template>
  <div class="flex flex-col min-h-screen">
    <Header hide-hero />
    <div class="flex-1 container mx-auto z-0 px-4 py-6 lg:px-20">
      <slot />
    </div>
    <Footer />
  </div>
</template>