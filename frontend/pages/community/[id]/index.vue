<script setup>
const id = useRoute().params.id
const { data: community } = useCustomFetch('/communities/' + id)
</script>
<template>
  {{community}}
</template>