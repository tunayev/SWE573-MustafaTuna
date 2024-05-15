import { useAuthStore } from '~/stores/auth'

export default defineNuxtRouteMiddleware((to, from) => {
    const auth = useAuthStore()
    const router = useRouter()
    // check if user's role is moderator of the community or not
    if (!auth.loggedIn && to.path !== '/auth/login') {
        return navigateTo('/auth/login')
    }

})