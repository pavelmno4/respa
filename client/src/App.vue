<template>
  <div id="app">

    <v-app>
      <Header />
      <MainPanel />
      <Footer />
    </v-app>
  </div>
</template>

<script>
import Header from '@/components/root/Header';
import MainPanel from '@/components/root/MainPanel';
import Footer from '@/components/root/Footer';


export default {
  name: 'App',

  components: {
    Header,
    MainPanel,
    Footer
  },
  data() {
    return {
    }
  }
}
</script>
