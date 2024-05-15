<script setup>
const community = reactive({
  name: '',
  description: '',
  isPrivate: false,
  image: '',
})
const createCommunity = async () => {
  try {
    const {data} = await useCustomFetch('/communities', {
      method: 'POST',
      body: JSON.stringify(community)
    })
    console.log(data)
    handleSuccess('Community created successfully')
    return navigateTo('/community')
    if(error.value)
      throw new Error(error.value)
  } catch (e) {
    handleError(e)
  }
}

definePageMeta({
  layout: 'default',
})
</script>
<template>
  <form class="max-w-md mx-auto" @submit.prevent="createCommunity">
    <AuthHeader title="Topluluk oluştur"
                subtitle="Topluluk oluşturmak için aşağıdaki formu doldurunuz."/>
    <div class="container mx-auto">
      <div class="mt-8">
        <div class="grid grid-cols-1 gap-3 sm:grid-cols-2">
          <div class="sm:col-span-2">
            <label for="title" class="block text-sm font-semibold text-gray-700">Başlık</label>
            <input type="text" id="title"
                   v-model="community.name"
                   required
                   class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50"/>
          </div>
          <div class="sm:col-span-2">
            <label for="description" class="block text-sm font-semibold text-gray-700">Açıklama</label>
            <textarea id="description"
                      v-model="community.description"
                      required
                      class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50">

            </textarea>
          </div>
          <!-- hidden or not -->
          <div class="sm:col-span-2 space-x-2">
            <label for="isPrivate" class="text-sm font-semibold text-gray-700">Gizli mi?</label>
            <input type="checkbox" id="isPrivate"
                   v-model="community.isPrivate"
                   />
          </div>
          <div class="sm:col-span-2">
            <label for="image" class="block text-sm font-semibold text-gray-700">Resim</label>
            <input type="file" id="image"
                   class="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:border-indigo-300 focus:ring focus:ring-indigo-200 focus:ring-opacity-50"/>
          </div>
          <div class="sm:col-span-2">
            <button class="btn">Oluştur</button>
          </div>
        </div>
      </div>
    </div>
  </form>
</template>