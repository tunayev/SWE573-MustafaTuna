<script setup lang="ts">
import type {Template} from "~/types/types";

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

const selectedTemplate = ref<Template>({
  id: 0,
  name: '',
  fields: [
    {
      name: '',
      type: '',
    }
  ]
})
</script>

<template>
  <div class="">
    <PageHeader :title="community.name + ' Yönetim Paneli'" subtitle="Topluluk ayarlarını buradan yönet"/>
    <div class="container mx-auto">
      <div class="w-full">
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
      <NuxtLink :to="'/community/' + community.id + '/template/create'" class="mt-4 btn bg-red-500 mt-4">Şablon Oluştur
      </NuxtLink>
      <!-- templates of the community -->
      <h2>Şablonlar </h2>
      <div class="grid grid-cols-1 gap-4 mt-4">
        <template v-for="template in community.templates">
          <div @click="selectedTemplate = template; JSON.parse(selectedTemplate.fields)" class="bg-white shadow-md rounded-lg p-4">
            <h3 class="font-semibold">{{ template.name }}</h3>
            <div v-if="selectedTemplate.id === template.id" class="mt-4">
              <h4 class="font-semibold">Fields</h4>
              <div class="flex  max-md:justify-between" v-for="(field, i) in JSON.parse(template.fields)" :key="i">
                <input type="text" v-model="selectedTemplate.fields[i].name" class="h-full font-semibold m-2">
                <select v-model="selectedTemplate.fields[i].type" class="p-0 m-2">
                  <option v-for="baseField in FIELDS" :value="baseField.type">{{ baseField.name }}</option>
                </select>
              </div>
              <button @click="updateTemplate(template)">
                Güncelle
              </button>

            </div>
          </div>
        </template>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>