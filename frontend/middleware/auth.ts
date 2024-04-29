import { useAuthStore } from '~/stores/auth'

export default defineNuxtRouteMiddleware((to, from) => {
    const auth = useAuthStore()
    const router = useRouter()

    if (!auth.loggedIn && to.path !== '/auth/login') {
        return navigateTo('/auth/login')
    }
})