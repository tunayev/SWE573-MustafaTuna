export default defineEventHandler(async (event) => {
    const body = await readBody(event)

    const { data: user } = useCustomFetch('/auth/login', {
        method: 'POST',
        body: JSON.stringify(body),
    })
    return user;
})
