<script setup lang="ts">
const params = useRoute().params
const { data: community } = await useCustomFetch('/communities/' + params.id)
console.log(community)
const jsons = "[\n" +
    "  {\n" +
    "    \"name\": \"Konum\",\n" +
    "    \"type\": \"geolocation\",\n" +
    "    \"value\": \"41.2063632681756, 27.1295387971128\"\n" +
    "  },\n" +
    "  {\n" +
    "    \"name\": \"Görsel\",\n" +
    "    \"type\": \"file\",\n" +
    "    \"value\": \"41.2063632681756, 27.1295387971128\"\n" +
    "  },\n" +
    "  {\n" +
    "    \"name\": \"Açıklama\",\n" +
    "    \"type\": \"text\",\n" +
    "    \"value\": \"Bu bir açıklamadır.\"\n" +
    "  },\n" +
    "  {\n" +
    "    \"name\": \"Video\",\n" +
    "    \"type\": \"file\",\n" +
    "    \"value\": \"\"\n" +
    "  },\n" +
    "  {\n" +
    "    \"name\": \"Audio\",\n" +
    "    \"type\": \"file\",\n" +
    "    \"value\": \"\"\n" +
    "  }\n" +
    "]"
</script>

<template>

</template>

<style scoped>

</style>