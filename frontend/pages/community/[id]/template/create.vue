<script setup lang="ts">
import {useAuthStore} from "~/stores/auth";
import type {PostField, Template} from "~/types/types";

const params = useRoute().params
const count = ref(0)
const templateFields = ref<PostField[]>([
  {
    name: '',
    type: ''
  }
])
const { data: community } = await useCustomFetch('/communities/' + params.id)

console.log(FIELDS)

/**
 * Template fields with example values
 * [
 *   {
 *     "name": "Konum",
 *     "type": "geolocation",
 *     "value": "41.2063632681756, 27.1295387971128"
 *   },
 *   {
 *     "name": "Görsel",
 *     "type": "file",
 *     "value": "41.2063632681756, 27.1295387971128"
 *   },
 *   {
 *     "name": "Açıklama",
 *     "type": "text",
 *     "value": "Bu bir açıklamadır."
 *   },
 *   {
 *     "name": "Video",
 *     "type": "file",
 *     "value": ""
 *   },
 *   {
 *     "name": "Audio",
 *     "type": "file",
 *     "value": ""
 *   }
 * ]
 */

const auth = useAuthStore()
const communityId = useRoute().params.id
const data = reactive<Template>({
  name: '',
  description: '',
  fields: [
    {
      id: Math.random().toString(36).substring(7),
      name: '',
      type: 'text'
    }
  ]
})
const submit = async () => {
  // validate fields
  validateFields(data.fields)
  // stringify fields array
  data.fields = JSON.stringify(data.fields)
  console.log(data)
try {
  const { data: communityRetrieved, error } = await useCustomFetch('/templates/' + communityId, {
    method: 'POST',
    body: JSON.stringify(data),
    watch: false,
  })
} catch(e) {
  handleError(e)
}
  data.fields = JSON.parse(data.fields)
}

const addField = () => {
  data.fields.push({
    id: Math.random().toString(36).substring(7),
    name: '',
    type: ''
  })
}


</script>
<template>
  <div class="">
    <PageHeader title="Şablon Oluştur" subtitle="Şablon oluşturmak için aşağıdaki formu doldurunuz." />
    <form @submit.prevent="submit">
      <input type="text" v-model="data.name" placeholder="Şablon Adı" />
      <input type="text" v-model="data.description" placeholder="Şablon Açıklaması" />
      <h2>Alanlar</h2>
      <!-- check if data.fields is an array -->
      <div class="grid grid-cols-2 gap-2 " v-if="Array.isArray(data.fields)" v-for="(field, i) in data.fields">
        <input :id="field.id + '-name'"
               type="text" :placeholder="field.name ? field.name : 'Alan adı'"
               class="input h-full"
               v-model="field.name"
               required
        />
        <select required v-model="field.type" :id="field.id + '-type'" class="p-1 rounded-md">
          <option v-for="fields in FIELDS" :value="fields.type">{{ fields.name }}</option>
        </select>
      </div>
      <button type="submit" class="btn bg-green-500">Oluştur</button>
      <button @click="addField" type="button" class="mt-4 btn bg-orange-500">Alan Ekle</button>
    </form>
  </div>
</template>