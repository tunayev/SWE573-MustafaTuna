<script setup>
const props = defineProps({
  title: String,
  subtitle: String
})
</script>
<template>
  <div class="py-2 flex justify-between items-center">
    <div>
      <h1 class="text-2xl font-semibold">{{ title }}</h1>
      <p class="text-gray-500">{{ subtitle }}</p>
    </div>
    <button @click="useRouter().go(-1)" class="btn-primary">
      <Icon name="ri:arrow-left-s-fill" class="w-6 h-6"/>
      Geri
    </button>
  </div>
</template>