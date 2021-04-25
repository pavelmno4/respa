<template>
    <v-main>
        <v-container>
            <Greeting/>
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