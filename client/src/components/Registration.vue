<template>
    <v-container>
        <v-row
                align="center"
                justify="center"
        >
            <v-col
                    cols="12"
                    md="6"
                    sm="8"
            >
                <v-form
                        ref="form"
                        v-model="valid"
                >
                    <v-text-field
                            label="Фамилия"
                            v-model="participant.lastName"
                            maxlength="50"
                            required
                            :rules="lastNameRules"
                            :counter="50"
                            color="teal accent-4"
                    ></v-text-field>
                    <v-text-field
                            label="Имя"
                            v-model="participant.firstName"
                            maxlength="50"
                            required
                            :rules="firstNameRules"
                            :counter="50"
                            color="teal accent-4"
                    ></v-text-field>
                    <v-text-field
                            label="Год рождения"
                            v-model="participant.birthYear"
                            maxlength="4"
                            required
                            :rules="birthYearRules"
                            :counter="4"
                            color="teal accent-4"
                    ></v-text-field>
                    <v-select
                            label="Пол"
                            v-model="participant.gender"
                            required
                            :rules="sexRules"
                            :items="items"
                            color="teal accent-4"
                            item-color="teal darken-3"
                    ></v-select>
                    <v-text-field
                            label="Вес"
                            v-model="participant.weight"
                            maxlength="5"
                            required
                            :rules="weightRules"
                            color="teal accent-4"
                    ></v-text-field>
                    <v-text-field
                            label="Название команды"
                            v-model="participant.team.teamName"
                            maxlength="50"
                            :rules="teamRules"
                            :counter="50"
                            required
                            color="teal accent-4"
                    ></v-text-field>
                    <v-text-field
                            label="Город"
                            v-model="participant.team.city"
                            maxlength="50"
                            :rules="cityRules"
                            :counter="50"
                            required
                            color="teal accent-4"
                    ></v-text-field>
                    <v-checkbox
                            label="Вы уверены, что хотите продолжить?"
                            v-model="checkbox"
                            required
                            :rules="checkboxRules"
                            color="orange darken-1"
                    ></v-checkbox>
                    <v-btn
                            class="mr-4"
                            color="teal lighten-4"
                            :disabled="!valid"
                            @click="submit"
                    >
                        Отправить
                    </v-btn>
                </v-form>
                <v-alert
                        class="mt-6"
                        icon="mdi-alert-octagram"
                        id="errorAlert"
                        prominent
                        text
                        type="error"
                        v-model="this.alert.show"
                >
                    {{ this.alert.errorMessage }}
                </v-alert>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    export default {
        data: () => ({
            valid: false,
            participant: {
                lastName: null,
                firstName: null,
                gender: null,
                birthYear: null,
                weight: null,
                team: {
                    teamName: null,
                    city: null
                }
            },
            items: [
                'М',
                'Ж'
            ],
            checkbox: false,
            alert: {
                show: false,
                errorMessage: null
            },
            firstNameRules: [
                v => !!v || 'Имя обязательно для заполнения',
                v => /^[a-zA-Zа-яА-ЯёЁ]+$/.test(v) || 'Имя можнет содержать только русские или латинские буквы'
            ],
            lastNameRules: [
                v => !!v || 'Фамилия обязательна для заполнения',
                v => /^[a-zA-Zа-яА-ЯёЁ]+$/.test(v) || 'Фамилия можнет содержать только русские или латинские буквы'
            ],
            birthYearRules: [
                v => !!v || 'Год рождения обязателен для заполнения',
                v => /^\d{4}$/.test(v) || 'Может содержать только цифры'
            ],
            sexRules: [
                v => !!v || 'Пол обязателен для заполнения'
            ],
            weightRules: [
                v => !!v || 'Вес обязателен для заполнения',
                v => /^\d?\d{2}\.\d$/.test(v) || 'Придерживайтесь шаблона: 55.5 (через точку)'
            ],
            teamRules: [
                v => !!v || 'Название команды обязателено для заполнения',
                v => /^[a-zA-Zа-яА-ЯёЁ0-9-_!'№\s]+$/.test(v) || "Может содержать только буквы, цифры и символы '-', '_', ', '№', '!'"
            ],
            cityRules: [
                v => !!v || 'Город обязателен для заполнения',
                v => /^[a-zA-Zа-яА-ЯёЁ]+$/.test(v) || 'можнет содержать только русские или латинские буквы'
            ],
            checkboxRules: [
                v => !!v || 'Необходимо подтверждение!'
            ]
        }),
        methods: {
            submit() {
                this.$http
                    .post('/participant/save/', this.participant)
                    .then(() => this.$router.push('/home'))
                    .catch((err) => {
                            this.alert.show = true
                            if (err.response.status === 500) {
                                this.alert.errorMessage = 'Ошибка на сервере'
                            } else {
                                this.alert.errorMessage = err.response.statusText
                            }
                        }
                    )
            }
        }
    }
</script>

