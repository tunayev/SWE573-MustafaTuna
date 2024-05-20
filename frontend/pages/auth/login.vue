<script lang="ts" setup>
import { object, string } from 'yup'
import { useAuthStore } from '~~/stores/auth'

useHead({
  title: 'Login',
})
definePageMeta({
  layout: 'auth',
  middleware: 'guest',
})

const { handleSubmit } = useForm({
  validationSchema: object({
    email: string().required().email().label('Email'),
    password: string().required().label('Password'),
  }),
})

const auth = useAuthStore()
const router = useRouter()
const error = ref()
const route = useRoute()

const { store } = useAuthStorage()

const { value: email } = useField('email', undefined, {
  initialValue: '',
})
const { value: password } = useField('password', undefined, {
  initialValue: '',
})

const onSubmit = handleSubmit(async (values) => {
  error.value = ''
  try {
    const { data } = await useCustomFetch('/auth/authenticate', {
      method: 'POST',
      body: JSON.stringify(values),
      watch: false,
    })

    const token = data.value.token
    const refresh_token = data.value.refresh_token
    const user = data.value.user

    store(token, refresh_token, user)

    auth.user = user
    auth.loggedIn = true

    handleSuccess('Logged in successfully')

    return router.push((route.query as any).next || '/')
  }
  catch (e: any) {
    handleError(e)
  }
})
</script>

<template>
  <div class="flex items-center justify-center h-full text-gray-600 lg:max-w-[400]">
    <div class="relative">
      <div
          class="absolute inset-0 bg-gradient-primary shadow-lg transform -skew-y-6 sm:skew-y-0 sm:-rotate-6 sm:rounded-3xl">
      </div>
      <form
          class="bg-white relative rounded-lg px-10 py-8 w-full max-w-md mx-auto"
          @submit="onSubmit"
      >
        <AuthHeader title="Giriş Yapın" subtitle="Bilgilerinizi girin"/>

        <div v-if="error" class="bg-error-600 text-white text-sm px-4 py-3 rounded-lg mb-4">
          {{ error }}
        </div>

        <input
            v-model="email"
            wrapper-class="mb-4"
            name="email"
            label="Email"
            class="input"
            placeholder="Email"
        />
        <input
            v-model="password"
            name="password"
            label="Password"
            class="input"
            placeholder="Şifre"
            type="password"
        />

        <div class="mb-5 flex gap-2 justify-between items-center">
          <label class="flex gap-2 items-center text-sm">
            <input
                type="checkbox"
                class="w-4 h-4 rounded text-primary-500 focus:ring-primary-500 transition duration-300"
            >
            Beni Hatırla
          </label>
          <nuxt-link to="/auth/forgot-password" class="text-primary-500 hover:underline font-semibold text-sm">
            Şifremi Unuttum
          </nuxt-link>
        </div>

        <button type="submit" class="btn mb-5">
          Giriş
        </button>

        <!-- <button
          class="px-3 py-2.5 rounded-md mb-4 font-semibold inline-flex w-full items-center justify-center bg-primary-500 text-white border-primary-500 focus:ring focus:ring-primary-500 focus:ring-offset-1 transition duration-300 hover:bg-primary-600 hover:border-primary-600"
        >
          Login
        </button> -->

        <div class="text-gray-600 text-sm">
          Hesabınız yok mu?
          <nuxt-link to="/auth/register" class="text-primary-500 hover:underline font-semibold text-sm">
            Kayıt ol
          </nuxt-link>
        </div>
      </form>
    </div>
  </div>
</template>