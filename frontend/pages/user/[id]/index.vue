<script setup>
const id = useRoute().params.id
const {data: user} = await useCustomFetch('/users/' + id)
if(!user) {
  useRouter().push('/404')
}

const randomBg = () => {
  const colors = ['bg-red-400', 'bg-blue-400', 'bg-green-400', 'bg-yellow-400', 'bg-indigo-400', 'bg-pink-400', 'bg-purple-400']
  return colors[Math.floor(Math.random() * colors.length)]
}
</script>
<template>
<div class="container mx-auto">
  <PageHeader :title="user.nickname" :subtitle="user.bio"/>
  <div class="bg-white shadow-md rounded-lg p-4 mt-4">
    <div class="mt-4">
      <div class="mt-4 text-sm ">
        <h3 class="text-sm font-semibold inline">Communities: </h3>
        <NuxtLink :to="`/community/${community.id}`" v-for="community in user.communities" :key="community.id" class="rounded-lg p-1 px-2 mr-2 bg-orange-300" :class="randomBg">
          <span class="font-semibold">{{ community.name }}</span>
        </NuxtLink>
      </div>
    </div>
  </div>
</div>

</template>