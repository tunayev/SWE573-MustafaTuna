<script setup>
const { data: community } = await useCustomFetch('/communities/' + params.id)
const { data: templates } = useCustomFetch('/templates/')
</script>
<template>
  <div>
    <h1>Şablonlar</h1>
    <ul>
      <li v-for="template in templates">
        {{template.name}}
      </li>
    </ul>
  </div>
</template>