<script setup lang="ts">
import {useField, useForm} from 'vee-validate'
import {useCustomFetch} from "~/apps/auth/composables/customFetch";
import { useAuthStore } from '~~/stores/auth'
import { object, string } from 'yup'
import {handleError, handleSuccess} from "~/apps/blog/utils/helpers";

const props = defineProps<{
  tempUserId?: string | null
}>()
const auth = useAuthStore()
const router = useRouter()
const route = useRoute()
const { store } = useAuthStorage()

const { handleSubmit } = useForm({
  validationSchema: object({
    name: string().required().label('Name'),
    email: string().required().email().label('Email'),
    password: string()?.required().label('Password'),
  }),
})

const { value: email } = useField('email', undefined, {
  initialValue: '',
})
const { value: password } = useField('password', undefined, {
  initialValue: '',
})

const { value: password_confirmation } = useField('password_confirmation', undefined, {
  initialValue: '',
})

const { value: name } = useField('name', undefined, {
  initialValue: '',
})

const onSubmit = handleSubmit(async (values) => {
  if(props.tempUserId) {
    values.temporary_user_id = props.tempUserId
  }
  try {
    const config = useRuntimeConfig()
    const { data } = await $fetch('/auth/register', {
      method: 'POST',
      body: JSON.stringify(values),
      headers: {
        'Content-Type': 'application/json',
      },
      baseURL: config.public.apiBase + '/api/v1'
    })
    console.log(data.value)
    // login user
    const token = data.access_token
    const refresh_token = data.refresh_token
    const user = data.user

    store(token, refresh_token, user)

    auth.user = user
    auth.loggedIn = true

    handleSuccess('Logged in successfully')
    return router.push((route.query as any).next || '/')
  } catch (e) {
    console.log(e)
    handleError(e)
  }
})
console.log("tempUserId", props.tempUserId)
</script>

<template>
  <div class="flex bg-content items-center justify-center h-full">
    <form class="rounded-lg p-8 w-full max-w-md mx-auto text-gray-600" @submit.prevent="onSubmit">

      <div class="mb-5 flex gap-2 justify-between items-center">
        <label class="flex flex-wrap gap-2 items-center text-sm">
          <input
              type="checkbox"
              class="w-4 h-4 rounded text-primary-500 focus:ring-primary-500 transition duration-300"
          >

          <span> Okudum </span>
          <nuxt-link
              to="/toc"
              class="text-primary-500 hover:underline font-semibold text-sm inline"
          >
            Şartlar ve Koşullar
          </nuxt-link>
        </label>
      </div>

      <VButton type="submit" color="primary" block class="mb-5">
        Kayıt
      </VButton>

      <div class="text-gray-600 text-sm">
        Hesabınız var mı?
        <nuxt-link
            to="'/auth/login'"
            class="text-primary-500 hover:underline font-semibold text-sm"
        >
          Giriş
        </nuxt-link>
      </div>
    </form>
  </div>
</template>