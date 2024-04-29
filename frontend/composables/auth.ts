import type { AuthUser } from '~~/stores/auth'

export const useAuthStorage = (
    { authTokenKey, authRefreshTokenKey, authUserKey, authLocaleKey  } = {
        authTokenKey: 'auth.token',
        authRefreshTokenKey: 'auth.refresh_token',
        authUserKey: 'auth.user',
        authLocaleKey: 'auth.locale'
    },
) => {
    const user = useCookie<AuthUser>(authUserKey)
    const refresh_token = useCookie<string>(authRefreshTokenKey)
    const token = useCookie<string>(authTokenKey)
    const locale = useCookie<string>('i18n_redirected')

    const store = (newToken: string, newRefreshToken: string, newUser: Record<string, any>) => {
        token.value = newToken
        refresh_token.value = newRefreshToken
        user.value = newUser
    }

    const storeUser = (newUser: Record<string, any>) => {
        user.value = JSON.stringify(newUser)
    }

    const clear = () => {
        user.value = null
        refresh_token.value = ''
        token.value = ''
    }

    return { store, clear, storeUser, user, token, refresh_token, locale }
}
