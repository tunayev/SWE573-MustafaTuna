<script setup>
const { data: communities } = await useCustomFetch('/communities')
console.log(communities)
</script>
<template>
<!-- List communities from the api -->
<div class="container mx-auto">
  <div class="flex justify-between items-center">
    <h1 class="text-2xl font-semibold">Topluluklar</h1>
    <NuxtLink to="/community/create" class="btn bg-green-300">Topluluk oluştur</NuxtLink>
  </div>
  <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 mt-4">
    <CommunityCard v-for="community in communities" :key="community.id" :community="community"/>
  </div>
</div>
</template>