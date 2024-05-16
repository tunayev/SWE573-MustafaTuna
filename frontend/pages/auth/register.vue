<script lang="ts" setup>
import {useAuthStore} from '~~/stores/auth'
import {object, string} from 'yup'

useHead({
  title: 'Register',
})

definePageMeta({
  layout: 'auth'
})
const auth = useAuthStore()
const router = useRouter()
const route = useRoute()
const {store} = useAuthStorage()

const {handleSubmit} = useForm({
  validationSchema: object({
    nickname: string().required().label('Nickname'),
    email: string().required().email().label('Email'),
    password: string()?.required().label('Password'),
    nickname: string().required().label('Nickname'),
  }),
})

const {value: email} = useField('email', undefined, {
  initialValue: '',
})
const {value: password} = useField('password', undefined, {
  initialValue: '',
})

const {value: password_confirmation} = useField('password_confirmation', undefined, {
  initialValue: '',
})

const {value: nickname} = useField('nickname', undefined, {
  initialValue: '',
})

const onSubmit = handleSubmit(async (values) => {
  console.log(values)
  try {
    const config = useRuntimeConfig()
    const {data, error} = await useCustomFetch('/auth/register', {
      method: 'POST',
      body: JSON.stringify(values),
      watch:false
    })
/*    const data = {
      token: "random",
      user: values,
      refresh_token: null
    }*/
    if(error.value)
      throw "Lütfen bilgileri kontrol ediniz"
    console.log(data)
    // login user
    const token = data.value.token
    const user = data.value.user
    const refresh_token = data.value.refresh_token
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
</script>

<template>
  <div class="flex items-center justify-center h-full">
    <form class="rounded-lg p-8 w-full max-w-md mx-auto text-gray-600" @submit.prevent="onSubmit">
      <AuthHeader title="Kayıt Ol" subtitle="Bilgilerinizi girin"/>
      <input v-model="nickname" name="nickname" placeholder="Kullanıcı adı" class="input"
      />
      <input v-model="email" name="email" placeholder="Email" class="input"
      />
      <input v-model="password"
             name="password"
             class="input"
             placeholder="Şifre"
             type="password"
      />
      <input v-model="password_confirmation"
             name="password_confirmation"
             placeholder="Şifre onayı"
             class="input"
             type="password"
      />

      <div class="mb-5 flex gap-2 justify-between items-center">
        <label class="flex flex-wrap gap-2 items-center text-sm">
          <input
              type="checkbox"
              class="w-4 h-4 rounded text-primary-500 focus:ring-primary-500 transition duration-300"
          >

          <span> Kabul ediyorum </span>
          <nuxt-link
              to="/toc"
              class="text-primary-500 hover:underline font-semibold text-sm inline"
          >
            Şartlar ve koşullar
          </nuxt-link>
        </label>
      </div>

      <button type="submit" class="btn mb-5">
        Kayıt ol
      </button>

      <div class="text-gray-600 text-sm">
        Zaten hesabın var mı?
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
