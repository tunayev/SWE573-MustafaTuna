<script setup>
import PageHeader from "~/components/PageHeader.vue";

const id = useRoute().params.id
const { data: community } = await useCustomFetch('/communities/' + id)
console.log(community)
</script>
<template>
  <!-- Community details -->
  <div class="container mx-auto">
    <PageHeader :title="community.name" :subtitle="community.description"/>
    <div class="bg-white shadow-md rounded-lg p-4 mt-4">
      <div class="mt-4">
        <div class="mt-4 text-sm ">
          <h3 class="font-semibold inline">Members: </h3>
          <NuxtLink :to="`/user/${user.id}`" v-for="user in community.users" :key="user.id" class="bg-orange-400 rounded-lg p-1 mx-1">
            <span class="font-semibold">{{ user.nickname }}</span>
          </NuxtLink>
        </div>
      </div>
    </div>
  </div>
</template>