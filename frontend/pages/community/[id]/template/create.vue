<script setup>
import {useAuthStore} from "~/stores/auth";

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