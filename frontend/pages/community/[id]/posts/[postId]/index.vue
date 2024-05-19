<script setup lang="ts">
import type {Post} from "~/types/types";
import GMap from "~/components/GMap.vue";

const postId = useRoute().params.postId
const communityId = useRoute().params.id
const { data: post } = await useCustomFetch('/posts/' + postId, {
  transform: (res: Post) => {
    res.body = JSON.parse(res.body)
    return res
  },
  watch: false,
  retry: 0
})
console.log(post.value)
const { data: community } = await useCustomFetch('communities/' + communityId)

const updatePost = async () => {
  try {
    // TODO update post
    handleSuccess("Başarıyla güncellendi")
  } catch(e) {
    handleError(e)
  }
}

const isUpdating = ref(false)
</script>
<template>
  <div class="bg-white p-4 rounded-lg shadow-md">
    <div v-if="!isUpdating">
      <div class="flex justify-between">
        <h2 class="font-semibold text-lg mt-4">{{ post.title }}</h2>
        <Icon @click="isUpdating = true" v-if="checkIfPostOwner(post) || checkIfModerator(community)" name="ri:settings-2-line" class="w-6 h-6 cursor-pointer text-gray-500"/>
      </div>
      <div v-for="field in post.body" class="mt-4">
        <p v-if="field.type === 'text' || field.type === 'textarea'">
          {{ field.name }}:
          {{ field.value }}
        </p>
        <img v-if="field.type = 'image'" :src="field.value" alt="">
        <p v-if="field.type === 'date'">
          {{ field.name }}:
          {{ new Date(field.value).toLocaleDateString() }}
        </p>
        <GMap v-if="field.type === 'geolocation'" :lat="field.value.split(',')[0]" :lng="field.value.split(',')[1]" :title="post.title"/>
      </div>
    </div>
    <div v-else>
      <label for="">Başlık</label>
      <input type="text" v-model="post.title">
      <div v-for="field in post.body">
        <label for="">{{field.name}}</label>
        <input v-if="field.type === 'text'" v-model="field.value" type="text">
        <textarea v-if="field.type=== 'textarea'" v-model="field.value" name="" id="" cols="30" rows="10"></textarea>
      </div>
    </div>
  </div>
</template>