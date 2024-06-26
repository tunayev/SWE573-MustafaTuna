import { useAuthStore } from '~/stores/auth'

export default defineNuxtRouteMiddleware((to, from) => {
    const auth = useAuthStore()
    const router = useRouter()

    // refetch the user and store it in the store
    if (auth.loggedIn && !auth.user) {
        auth.fetchUser()
    }

    if (!auth.loggedIn && to.path !== '/auth/login') {
        return navigateTo('/auth/login')
    }
})