<script setup>
import {useAuthStore} from "~/stores/auth";

const params = useRoute().params
const { data: community } = await useCustomFetch('/communities/' + params.id)
console.log(community)

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

const fields = ref([
  {
    name: 'Konum',
    type: 'geolocation',
    value: '41.2063632681756, 27.1295387971128'
  },
  {
    name: 'Görsel',
    type: 'file',
    value: '41.2063632681756, 27.1295387971128'
  },
  {
    name: 'Metin',
    type: 'text',
    value: 'Bu bir açıklamadır.'
  },
  {
    name: 'Video',
    type: 'file',
    value: ''
  },
  {
    name: 'Audio',
    type: 'file',
    value: ''
  }
])
const auth = useAuthStore()
const communityId = useRoute().params.id
const data = reactive({
  name: '',
  communityId: communityId,
  fields: []
})
const submit = () => {
  const { data: community } = useCustomFetch('/templates', {
    method: 'POST',
    body: JSON.stringify(data)
  })
}


</script>
<template>
  <div>
    <h1>Yeni Şablon Oluştur</h1>
    <form @click.prevent="submit">
      <input type="text" placeholder="Şablon Adı" />
      <input type="text" placeholder="Şablon Açıklaması" />
      <input type="text" placeholder="Alan Adı" />
      <select>
        <option value="text">Kısa metin</option>
        <option value="text">Gövde</option>
        <option value="number">Resim</option>
        <option value="date">Tarih</option>
      </select>
      <button type="button">Alan Ekle</button>
      <ul>
        <li v-for="field in data.fields">
          {{field.name}} - {{field.type}}
        </li>
      </ul>
      <button type="submit">Oluştur</button>
    </form>
  </div>
</template>