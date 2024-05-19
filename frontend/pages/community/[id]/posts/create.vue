<script setup lang="ts">
/**
 * Posts are created upon predefined templates in the community. So that
 * 1. We retrieve templates of the community and display them in a dropdown
 * 2. Create a post with the selected template
 * 3. Redirect to the created post
 */
import type {Post, Template} from "~/types/types";
const params = useRoute().params
const post = ref<Post>({
  title: '',
  body: '',
  communityId: params.id,
  templateId: '',
})

const { data: community } = await useCustomFetch('/communities/' + params.id)
const selectedTemplate = ref<Template>({
  id: '',
  name: '',
  title: '',
  description: '',
  communityId: '',
  fields: [
    {
      id: 0,
      name: "Gövde",
      type: "textarea",
      value: ""
    }
  ]
})

watch(selectedTemplate, value => {
  value.fields = value.fields ?
      JSON.parse(value.fields) :
      []
})
const createPost = async () => {
  post.value.templateId = selectedTemplate.value.id
  post.value.body = JSON.stringify(selectedTemplate.value.fields);

  console.log(selectedTemplate)
  try {
    const { data: createdPost, error } = await useCustomFetch('/posts', {
      method: "POST",
      body: post,
      watch: false
    })
    handleSuccess('Gönderi başarıyla oluşturuldu!')
  } catch(e) {
    handleError(e)
  }
}
console.log(community)

const isGeolocationExists =  ref(false)
const geoLocationField = ref({
  id: 0,
  name: "Konum",
  type: "geolocation",
  value: ""
});
watch(selectedTemplate, value => {
  value.fields.forEach(field => {
    if (field.type === 'geolocation') {
      geoLocationField.value = field
    }
  })
})

</script>
<template>
  <div class="space-y-4">
    <PageHeader :title="community.name + ' Topluluğunda Gönderi Oluştur'" :subtitle="community.description"/>
    <!-- Select template -->
    <div class="w-full flex items-center space-x-4 max-md:justify:between">
      <label for="select-template">Şablon seçin</label>
      <select id="select-template" v-model="selectedTemplate">
        <option v-for="template in community.templates" :value="template"> {{template.name }} </option>
      </select>
    </div>
    <!-- Create post form of template -->
    <form @submit.prevent="createPost">
      <div>
        <input v-model="post.title" class="" type="text" placeholder="Başlık girin">
      </div>
      <div class="" v-for="(field, i) in selectedTemplate.fields" :key="i">
        <div v-if="field.type === 'textarea'">
          <label :for="field.type+'-'+i">{{ field.name }}</label>
          <textarea v-model="field.value" class="" :name="field.type+'-'+i" :id="field.type+'-'+i" cols="30" rows="10"
                    placeholder="Metin girin...">
          </textarea>
        </div>
        <div v-if="field.type == 'text'">
          <label :for="field.type+'-'+i">{{ field.name }}</label>
          <input v-model="field.value" type="text" :name="field.type+'-'+i"/>
        </div>
        <div class="flex space-x-4" v-if="field.type == 'image'">
          <label :for="field.type+'-'+i">{{ field.name }}</label>
          <input type="file" :name="field.type+'-'+i"/>
        </div>
        <div v-if="field.type === 'date'" class="flex space-x-4">
          <label :for="field.type+'-'+i">{{ field.name }}</label>
          <input v-model="field.value" type="date" :name="field.type+'-'+i"/>
        </div>
      </div>
      <GMap v-if="geoLocationField.id != 0" :lat="41" :lng="27" @update:coordinates="(coordinates) => geoLocationField.value = coordinates"  title="hallo"/>

      <button type="submit" class="btn bg-orange-500">
        Gönderi Oluştur!
      </button>
    </form>
  </div>
</template>