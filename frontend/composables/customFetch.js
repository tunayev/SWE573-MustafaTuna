import {useAuthStore} from "~/stores/auth";

export function useCustomFetch(url, opts) {
    const config = useRuntimeConfig()
    const { token, refresh_token, store } = useAuthStorage()

    const xsrfToken = useCookie('XSRF-TOKEN').value
    let headers = {
        accept: 'application/json',
/*        ...opts?.headers,*/
    }
    if(token.value)
        headers['authorization'] = `Bearer ${token.value}`
    if (xsrfToken && xsrfToken.value !== null)
        headers['X-XSRF-TOKEN'] = xsrfToken

    headers = {
        ...headers,
        ...useRequestHeaders(['cookie']),
        referer: config.public.baseURL,
    }
    return useFetch(url, {
        baseURL: config.public.apiBase + '/api/v1',
        ...opts,
        retry: 1,
        retryStatusCodes: [401],
        headers,
        credentials: 'include',
        // TODO: If refresh token is broken, then it doesnt do error handling
    })
}

export async function csrfToken() {
    await useCustomFetch('/sanctum/csrf-cookie')
}
