<script setup>
const id = useRoute().params.id
const { data: community } = useCustomFetch('/communities/' + id)
console.log(community)
</script>
<template>
  <!-- Community details -->
  <div class="container mx-auto">
    <div class="flex justify-between items-center">
      <h1 class="text-2xl font-semibold">{{ community.name }}</h1>
      <NuxtLink to="/community" class="btn-primary">Back</NuxtLink>
    </div>
    <div class="bg-white shadow-md rounded-lg p-4 mt-4">
      <h2 class="text-xl font-semibold">{{ community.name }}</h2>
      <p class="text-gray-500">{{ community.description }}</p>
      <div class="mt-4">
        <h3 class="text-lg font-semibold">Members</h3>
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 mt-4">
          <div v-for="user in community.users" :key="user.id" class="bg-white shadow-md rounded-lg p-4">
            <h4 class="text-lg font-semibold">{{ user.nickname }}</h4>
            <p class="text-gray-500">{{ user.avatar }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>