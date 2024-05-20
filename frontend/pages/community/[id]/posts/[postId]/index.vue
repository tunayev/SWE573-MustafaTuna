<script setup lang="ts">
import type {Post} from "~/types/types";
import GMap from "~/components/GMap.vue";

const postId = useRoute().params.postId
const communityId = useRoute().params.id
const { data: postInitial } = await useCustomFetch('/posts/' + postId, {
  watch: false,
  retry: 0
})
const post = ref<Post>({
  id: 0,
  title: '',
  body: [],
  communityId: '',
  templateId: '',
})

if(postInitial.value?.body && typeof postInitial.value.body === 'string') {
  post.value = { ...postInitial.value }
  post.value.body = JSON.parse(postInitial.value.body)

}
console.log(post.value, postInitial.value)
const { data: community } = await useCustomFetch('communities/' + communityId, {
  watch: false,
  retry: 0
})

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
    <div v-if="post.id != 0 && !isUpdating">
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
    <div v-if="isUpdating">
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