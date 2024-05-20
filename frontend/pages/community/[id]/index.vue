<script setup>
import PageHeader from "~/components/PageHeader.vue";
const id = useRoute().params.id
const { data: community } = await useCustomFetch('/communities/' + id)
const auth = useAuthStore()
console.log(community)
</script>
<template>
  <div><!-- Community details -->
    <div class="flex justify-end space-x-4">
      <NuxtLink v-if="checkIfAdmin(community)" :to="'/community/' + community.id + '/update'" class="btn bg-green-300">
        Yönetim Paneli
      </NuxtLink>
      <NuxtLink v-if="checkIfModerator(community)" :to="'/community/' + community.id + '/moderation'"
                class="btn bg-green-300">Moderasyon
      </NuxtLink>
      <NuxtLink v-if="checkIfMember(community)" :to="'/community/' + community.id + '/posts/create'"
                class="btn bg-orange-300">Gönderi Oluştur
      </NuxtLink>
    </div>
    <PageHeader :title="community.name" :subtitle="community.description"/>
    <div class="container w-full mx-auto sm:grid grid-cols-4 gap-8 ">
      <div class="col-span-3">
        <!-- Posts List -->
        <div class="grid grid-cols-1 gap-4">
          <PostCard v-for="post in community.posts" :key="post.id" :post="post"/>
        </div>
      </div>
      <div class="col-span-1 bg-white shadow-md rounded-lg p-4 mt-4">
        <div class="mt-4">
          <div class="mt-4 text-sm ">
            <h3 class="font-semibold inline">Members: </h3>
            <NuxtLink :to="`/user/${user.id}`" v-for="user in community.users" :key="user.id"
                      class="bg-orange-400 rounded-lg p-1 mx-1">
              <span class="font-semibold">{{ user.nickname }}</span>
            </NuxtLink>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>