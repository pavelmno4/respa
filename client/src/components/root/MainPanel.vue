<template>
    <v-main>
        <v-container>
            <router-view>
                <Greeting/>
            </router-view>
        </v-container>
    </v-main>
</template>

<script>
    import Greeting from '@/components/Greeting.vue'

    export default {
        components: {
            Greeting
        }
    }
</script>