import Vue from 'vue'
import Router from 'vue-router'
import Greeting from '@/components/Greeting.vue'
import Registration from "./components/Registration";

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [
        {
            path: '/home',
            component: Greeting
        },
        {
            path: "/registration",
            component: Registration
        }
    ]
})