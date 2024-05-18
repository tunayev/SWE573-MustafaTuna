<script setup lang="ts">
const params = useRoute().params
const {data: community} = await useCustomFetch('/communities/' + params.id)

const save = async () => {
  const {data, error} = await useCustomFetch('/communities/' + params.id, {
    method: 'PUT',
    body: JSON.stringify(community.value),
    watch: false
  })
  if (data.value) {
    handleSuccess('Community updated successfully')
  }
  if (error.value) {
    handleError(error.value)
  }
}
console.log(community)
</script>

<template>
  <PageHeader :title="community.name + ' Yönetim Paneli'" subtitle="Topluluk ayarlarını buradan yönet"/>
  <div class="container mx-auto">
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 mt-4">
      <form @submit.prevent="save()" class="bg-white shadow-md rounded-lg p-4">
        <h3 class="font-semibold">Topluluk Ayarları</h3>
        <div class="mt-4">
          <h4 class="font-semibold">Topluluk Adı</h4>
          <input type="text" class="input" v-model="community.name"/>
        </div>
        <div class="mt-4">
          <h4 class="font-semibold">Topluluk Açıklaması</h4>
          <textarea class="input" v-model="community.description"></textarea>
        </div>
        <div class="mt-4">
          <button class="btn bg-green-300">Kaydet</button>
        </div>
      </form>
    </div>
    <!-- templates of the community -->
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 mt-4">
      <template v-for="template in community.templates">
        <div class="bg-white shadow-md rounded-lg p-4">
          <h3 class="font-semibold">{{ template.name }}</h3>
          <div class="mt-4">
            <h4 class="font-semibold">Fields</h4>
            <div v-for="field in template.fields" :key="field.id">
              <h5 class="font-semibold">{{ field.name }}</h5>
              <input type="text" class="input" v-model="field.value"/>
            </div>
          </div>
        </div>
      </template>
    </div>
  </div>
</template>

<style scoped>

</style>