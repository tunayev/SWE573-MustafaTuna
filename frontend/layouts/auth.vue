<script lang="ts" setup>
//
</script>

<template>
  <div class="h-screen flex items-center justify-center w-full bg-content-accent bg-slate-100">
    <div class="lg:w-[400px]">
      <Logo class="flex justify-center mb-5" />
      <div class="max-lg:bg-white rounded-xl max-lg:shadow-md">
        <slot />
      </div>
    </div>
  </div>
</template>
