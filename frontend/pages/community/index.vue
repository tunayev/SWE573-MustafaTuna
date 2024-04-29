<script setup>
const { data: communities } = useCustomFetch('/communities')
console.log(communities)
</script>
<template>
<!-- List communities from the api -->
<div class="container mx-auto">
  <div class="flex justify-between items-center">
    <h1 class="text-2xl font-semibold">Communities</h1>
    <NuxtLink to="/community/create" class="btn-primary">Create Community</NuxtLink>
  </div>
  <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 mt-4">
    <div v-for="community in communities" :key="community.id" class="bg-white shadow-md rounded-lg p-4">
      <NuxtLink :to="'/community/' + community.id">
        <h2 class="text-xl font-semibold">{{ community.name }}</h2>
      </NuxtLink>
      <p class="text-gray-500">{{ community.description }}</p>
    </div>
  </div>
</div>
</template>