import {useAuthStore} from "~/stores/auth";
import type {Community} from "~/types/types";

export const postDate = (date: string | Date) => {
    return new Date(date).toLocaleDateString('tr-TR', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
    })
}

export const handleErrors = (error: any) => {
    if(error.status === 401) {
        const auth = useAuthStore()
        auth.logout()
        const router = useRouter()
        router.push('/auth/login')
    }
}

/** Toaster error handler function */
export function handleError(e: any) {
    const { $toaster } = useNuxtApp();
    const error = {
        title: 'Hata',
        message: 'Bir hata oluştu',
    }
    if(e.message)
        error.message = e.message;
    if(e.status == 422) {
        const errors = Object.values(e.data?.errors).flat();
        if(errors !== undefined) {
            error.message = errors[0];
        }
    }
    if(e.status === 429) {
        error.message = 'Çok fazla istek gönderdiniz. Lütfen biraz bekleyin.';
    }
    if(e.status === 500)
        error.message = "An error occured. Please try again in a few seconds."
    console.error(error.message);
    $toaster.error(error);
}

/** Toaster success handler function */
export function handleSuccess(message: string) {
    const { $toaster } = useNuxtApp();
    $toaster.success({
        title: 'Başarılı',
        message,
    });
}

export function logout() {
    const auth = useAuthStore()
    auth.logout()
    const router = useRouter()
    return router.push('/auth/login')
}

export const joinCommunity = async (community: Community) => {
    const auth = useAuthStore()
    if(!auth.loggedIn)
        return navigateTo('/auth/login')
    const { data, error } = await useCustomFetch('/communities/' + community.id + '/join', {
        method: 'POST',
    })
    if(error.value) {
        handleError(error)
        return
    }
    community.users.push(auth?.user)
    const { user } = useAuthStorage()
    if(!user.value?.communities)
        user.value.communities = []
    user.value.communities.push(community)
    user.value = JSON.stringify(user.value)

    handleSuccess('Community joined successfully')
}

export const checkIfJoined = (community: Community) => {
    const auth = useAuthStore()
    if(auth.user) {
        const joined = community.users.some(member => member.id === auth?.user?.id)
        console.log(joined)
        return joined
    }
    return false
}

export const leaveCommunity = async (community: Community) => {
    const { data, error } = await useCustomFetch('/communities/' + community.id + '/leave', {
        method: 'POST',
    })
    if(error.value) {
        handleError(error)
        return
    }
    const auth = useAuthStore()
    const { user } = useAuthStorage()
    const index = community.users.findIndex(member => member.id === auth?.user?.id)
    if(index === -1) return
    if(!user.value.communities)
        user.value.communities = []
    user.value.communities = user.value.communities.filter(c => c.id !== community.id)
    community.users.splice(index, 1)
    handleSuccess('Community left successfully')
}