import {useAuthStore} from "~/stores/auth";
import type {Community, Post, PostField, Template} from "~/types/types";

export const postDate = (date: string | Date) => {
    return new Date(date).toLocaleDateString('tr-TR', {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
    })
}

export const handleErrors = (error: any) => {
    if (error.status === 401) {
        const auth = useAuthStore()
        auth.logout()
        const router = useRouter()
        router.push('/auth/login')
    }
}

/** Toaster error handler function */
export function handleError(e: any) {
    const {$toaster} = useNuxtApp();
    const error = {
        title: 'Hata',
        message: 'Bir hata oluştu',
    }
    if ('string' == typeof e)
        error.message = e;
    if (e.message)
        error.message = e.message;
    if (e.status == 422) {
        const errors = Object.values(e.data?.errors).flat();
        if (errors !== undefined) {
            error.message = errors[0];
        }
    }
    if (e.status === 429) {
        error.message = 'Çok fazla istek gönderdiniz. Lütfen biraz bekleyin.';
    }
    if (e.status === 500)
        error.message = "An error occured. Please try again in a few seconds."
    console.error(error.message);
    $toaster.error(error);
}

/** Toaster success handler function */
export function handleSuccess(message: string) {
    const {$toaster} = useNuxtApp();
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
    if (!auth.loggedIn)
        return navigateTo('/auth/login')
    const {data, error} = await useCustomFetch('/communities/' + community.id + '/join', {
        method: 'POST',
    })
    if (error.value) {
        handleError(error)
        return
    }
    community.users.push(auth?.user)
    const {user} = useAuthStorage()
    if (!user.value?.communities)
        user.value.communities = []
    user.value.communities.push(community)
    user.value = JSON.stringify(user.value)

    handleSuccess('Community joined successfully')
}

export const checkIfJoined = (community: Community) => {
    const auth = useAuthStore()
    if (auth.user) {
        const joined = community.users.some(member => member.id === auth?.user?.id)
        return joined
    }
    return false
}

export const leaveCommunity = async (community: Community) => {
    const {data, error} = await useCustomFetch('/communities/' + community.id + '/leave', {
        method: 'POST',
    })
    if (error.value) {
        handleError(error)
        return
    }
    const auth = useAuthStore()
    const {user} = useAuthStorage()
    const index = community.users.findIndex(member => member.id === auth?.user?.id)
    if (index === -1) return
    if (!user.value.communities)
        user.value.communities = []
    user.value.communities = user.value.communities.filter(c => c.id !== community.id)
    community.users.splice(index, 1)
    handleSuccess('Community left successfully')
}

export const checkIfModerator = (community: Community) => {
    const auth = useAuthStore()
    if (auth.user) {
        const moderator = community.moderators.some(moderator => moderator.id === auth?.user?.id)
        return moderator
    }
    return false
}

export const checkIfAdmin = (community: Community) => {
    const auth = useAuthStore()
    if (auth.user) {
        const admin = community.admin?.id === auth?.user?.id
        return admin
    }
    return false
}

export const checkIfMember = (community: Community) => {
    const auth = useAuthStore()
    if (auth.user) {
        const member = community.users.some(member => member.id === auth?.user?.id)
        return member
    }
    return false

}

export const checkIfPostOwner = (post: Post) => {
    const auth = useAuthStore()
    if (auth.user) {
        const owner = post.user.id === auth.user.id
        return owner
    }
    return false

}

export const validateFields = (fields: PostField[]) => {
    // check the field types are included in the allowed types
    const allowedTypes = FIELDS.map(field => field.type)
    const valid = fields.every(field => allowedTypes.includes(field.type))
    return valid
}

export const updateTemplate = async (template: Template) => {
    if (typeof "string" !== typeof template.fields)
        template.fields = JSON.stringify(template.fields)
    try {
        const {data: updatedTemplate} = await useCustomFetch('/templates/t/' + template.id, {
            method: "PUT",
            body: template
        })
        return updatedTemplate
    } catch (e) {
        handleError(e)
    }
}

export const prepareTemplateToPost = (template: Template) => {
    if (Array.isArray(template.fields))
        template.fields = JSON.stringify(template.fields)
    return template
}

export const prepareTemplateToGet = (template: Template) => {
    if ("string" === typeof template.fields)
        template.fields = JSON.parse(template.fields)
    return template
}
