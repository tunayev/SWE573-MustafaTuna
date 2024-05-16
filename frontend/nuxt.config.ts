// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
    ssr: false,
    modules: ['@pinia/nuxt', '@vee-validate/nuxt', '@nuxt/ui', '@cssninja/nuxt-toaster'],
    devtools: {enabled: true},
    runtimeConfig: {
        public: {
            gaId: '',
            apiBase: process.env.STRAPI_URL,
            S3_URL: "https://turkdil.s3.amazonaws.com/",
            environment: process.env.ENVIRONMENT,
        },
    },
    css: ['~/assets/css/app.css']
})