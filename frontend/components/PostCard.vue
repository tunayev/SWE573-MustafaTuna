<script setup lang="ts">
import type {Post} from "~/types/types";
const params = useRoute().params

const props = defineProps<{
  post: Post
}>()


</script>

<template>
  <div class="bg-white shadow-md rounded-lg p-4">
    <div class="flex justify-between">
      <h1 class="text-xl font-semibold"><a :href="'/community/' + params.id + '/posts/' + post.id">{{ post.title }}</a></h1>
      <span> <img :src="post.user.avatar ?? ''" alt=""> {{post.user.nickname }}</span>
    </div>
    <div v-for="field in JSON.parse(post.body)" class="mt-4">
      <p v-if="field.type === 'text' || field.type === 'textarea'">
        {{ field.name }}:
        {{ field.value }}
      </p>
      <img v-if="field.type = 'image'" :src="field.value" alt="">
      <video v-if="field.type == 'video'" :src="field.value"></video>
    </div>
  </div>
</template>

<style scoped>

</style>