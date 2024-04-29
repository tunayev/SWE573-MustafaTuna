<script setup>
const { data: community } = useCustomFetch('/communities/1')
</script>
<template>
{{community}}
</template>