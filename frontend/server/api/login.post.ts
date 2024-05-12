export default defineEventHandler(async (event) => {
    const body = await readBody(event)

    const { data: user } = useFetch('/auth/login', {
        method: 'POST',
        body: JSON.stringify(body),
    })
    return user;
})
