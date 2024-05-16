<script setup lang="ts">
import type {Community} from "~/types/types";
import {checkIfAdmin} from "~/utils/helpers";

const props = defineProps<{
  community: Community
}>()
</script>
<template>
  <div class="bg-white rounded-lg shadow-sm space-y-2 p-4">
    <div class="flex justify-between">
      <div class="flex justify-between items-center">
        <NuxtLink :to="'/community/' + community.id">
          <h2 class="text-xl font-semibold">{{ community.name }}</h2>
        </NuxtLink>
        <Icon name="ri:git-repository-private-fill" v-if="community.isPrivate" class="w-5 h-5 text-gray-400"/>
      </div>
      <Icon v-if="checkIfAdmin(community)" name="ri:settings-2-line" class="w-5 h-5 text-gray-400"/>
      <Icon v-else-if="checkIfModerator(community)" name="ri:equalizer-line" class="w-5 h-5 text-gray-400"/>
      <button v-else-if="!checkIfJoined(community)" @click="joinCommunity(community)" class="btn">
        {{ community.isPrivate ? 'Talep gönder' : 'Katıl' }}
      </button>
      <button v-else @click="leaveCommunity(community)" class="btn bg-red-500">Ayrıl</button>
    </div>
    <p class="text-gray-500">{{ community.description }}</p></div>
</template>