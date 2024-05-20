<script setup>
/**
 * Search component is used to search for posts, users, communities, etc.
 */


const params = reactive({
  query: '',
  advanced: false,
  within: []

})

const { data: searchResults, refresh } = await useCustomFetch('/search', {
  params,
  immediate: false
})

watch(params, () => {
  refresh()
})

console.log(searchResults)
</script>
<template>
  <div class="relative px-4 flex">
    <input v-model="params.query" placeholder="Ara..." class="input"/>
    <button @click="params.advanced = !params.advanced">Gelişmiş</button>
<!--    <div class=" flex flex-col" v-if="params.advanced">
      &lt;!&ndash; checkboxes &ndash;&gt;
      <input type="checkbox" v-model="params.within['community']" value="communities" id="communities">
      <label for="communities">Topluluklar</label>
      <input type="checkbox" v-model="params.within['post']" value="posts" id="posts">
      <label for="posts">Gönderiler</label>
      <input type="checkbox" v-model="params.within['user']" value="users" id="users">
      <label for="users">Kullanıcılar</label>
    </div>-->
    <div v-if="searchResults" class="absolute top-20  p-10 bg-white shadow-md top-12 left-4 h-[200px] w-[220px] z-40 overflow-y-scroll">
      <div class="z-50">
        <div v-if="searchResults?.communities?.length > 0">
          <h3>Topluluklar</h3>
          <div class="py-1 mt-1 relative community" v-for="(community, i) in searchResults.communities">
            <NuxtLink :to="'/community/' + community.id">{{ community.name }}</NuxtLink>
          </div>
        </div>
        <div v-if="searchResults?.posts?.length > 0">
          <h3 class="">Gönderiler</h3>
          <div class="post block" v-for="(post, i) in searchResults.posts">
            <NuxtLink :to="'/community/' + post.community.id + '/posts/' + post.id">{{ post.title }}</NuxtLink>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>