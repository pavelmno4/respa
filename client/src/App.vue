<template>
    <div id="app">

        <v-app>
            <Navigation/>
            <MainPanel/>
            <Footer/>
        </v-app>
    </div>
</template>

<script>
    import Navigation from '@/components/root/Navigation';
    import MainPanel from '@/components/root/MainPanel';
    import Footer from '@/components/root/Footer';


    export default {
        name: 'App',

        components: {
            Navigation,
            MainPanel,
            Footer
        },
        data() {
            return {}
        }
    }
</script>
