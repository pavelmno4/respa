<template>
    <div>
        <v-app-bar
                color="cyan darken-4"
                app
                dark
        >
            <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
        </v-app-bar>

        <v-navigation-drawer
                v-model="drawer"
                app
                dark
                temporary
        >
            <v-list
                    nav
                    dense
            >
                <v-list-item @click="homePage" link>
                    <v-list-item-action>
                        <v-icon>mdi-home</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>Главная страница</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item @click="registration" link>
                    <v-list-item-action>
                        <v-icon>mdi-email</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>Регистрация</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item>
                    <v-list-item-action>
                        <v-icon>mdi-account</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>Списки участников</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
                <v-list-item>
                    <v-list-item-action>
                        <v-icon>mdi-arrow-left-circle</v-icon>
                    </v-list-item-action>
                    <v-list-item-content>
                        <v-list-item-title>Контакты</v-list-item-title>
                    </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-navigation-drawer>
    </div>
</template>

<script>
    export default {
        data: () => ({
            drawer: false
        }),
        methods: {
            homePage() {
                this.$router.push('/home')
            },
            registration() {
                this.$router.push('/registration')
            }
        }
    }
</script>