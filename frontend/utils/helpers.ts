import {useAuthStore} from "~/stores/auth";

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