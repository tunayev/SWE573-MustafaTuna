<script setup lang="ts">
import { ShieldCheckIcon, XMarkIcon } from '@heroicons/vue/24/outline'
// components/MyToast.vue
const props = defineProps<{
  title?: string
  message?: string
  type: string
}>()

const {
  isHovered,
  isActive,
  timer,
  duration,
  click,
  close,
} = useNinjaToasterState()

const {
  percent,
  endAt,
  closeIn
} = useNinjaToasterProgress()
</script>

<template>
  <div id="toast" class="z-1000 fixed top-20 right-20 flex items-center w-full max-w-xs p-4 mb-4 text-gray-500 bg-white rounded-lg shadow dark:text-gray-400 dark:bg-gray-800" role="alert">
    <div class="inline-flex items-center justify-center flex-shrink-0 w-8 h-8 rounded-lg"
         :class="{
            'text-green-500 bg-green-100 dark:bg-green-800 dark:text-green-200': props.type === 'success',
            'text-red-500 bg-red-100 dark:bg-red-800 dark:text-red-200': props.type === 'error',
            'text-blue-500 bg-blue-100 dark:bg-blue-800 dark:text-blue-200': props.type === 'info',
          }">
      <ShieldCheckIcon v-if="props.type === 'success'" class="w-5 h-5" aria-hidden="true" />
      <XMarkIcon v-if="props.type === 'error'" class="w-5 h-5" aria-hidden="true" />
      <XMarkIcon v-if="props.type === 'info'" class="w-5 h-5" aria-hidden="true" />
      <span class="sr-only">icon</span>
    </div>
    <div class="ml-3 text-sm font-normal">{{ props.message }}</div>
    <button type="button" class="ml-auto -mx-1.5 -my-1.5 bg-white text-gray-400 hover:text-gray-900 rounded-lg focus:ring-2 focus:ring-gray-300 p-1.5 hover:bg-gray-100 inline-flex items-center justify-center h-8 w-8 dark:text-gray-500 dark:hover:text-white dark:bg-gray-800 dark:hover:bg-gray-700" data-dismiss-target="#toast-success" aria-label="Close">
      <span class="sr-only">Close</span>
      <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 14">
        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"/>
      </svg>
    </button>
  </div>
</template>