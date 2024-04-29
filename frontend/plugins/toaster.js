import Toaster from "~/components/Toaster.vue";

export default defineNuxtPlugin((nuxtApp) => {
    // define or import a theme
    const theme = {
        containerId: 'nt-container-bottom-right',
        containerClass: [
            'absolute',
            'inset-0',
            'pointer-events-none',
            'p-[5%]',
            'flex',
            'flex-col',
            'items-end',
            'gap-2'
        ],
        wrapperClass: [
            'pointer-events-auto',
            'cursor-pointer',
        ],
    }

    // set default show options here
    const nt = createNinjaToaster({
        theme,
        maxToasts: 5,
        transition: {
            enterActiveClass: 'transition duration-300 ease-out',
            enterFromClass: 'transform translate-x-full opacity-0',
            enterToClass: 'transform translate-x-0 opacity-100',
            leaveActiveClass: 'transition duration-300 ease-in',
            leaveFromClass: 'transform translate-x-0 opacity-100',
            leaveToClass: 'transform translate-x-full opacity-0'
        }
    })

    const toaster = {
        info (options) {
            nt.show(() => h(Toaster, {
                ...options,
                type: 'info'
            }))
        },
        success (options) {
            nt.show(() => h(Toaster, {
                ...options,
                type: 'success'
            }))
        },
        async error (options) {
            // wait for the toast to be mounted
            const { el, close } = await nt.show(() => h(Toaster, {
                ...options,
                type: 'error'
            }))

            // focus the toast once it's mounted
            //el.focus()
        },
        close() {
            // close all toasts
            nt.closeAll()

            // or close toasts in a specific containerId
            nt.close('nt-container-bottom-right')

            // or close toasts using a theme
            nt.close(theme)
        },
    }

    return {
        provide: {
            toaster
        }
    }
})