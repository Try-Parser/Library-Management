<script lang="ts" >
  import { defineComponent } from 'vue'
  import { getCredentials } from '@/stores/credentials'

  export default defineComponent({
    setup() {
      const { setId, authorized } = getCredentials();
      return { setId, authorized }
    },
    data() {
      return {
        loginForm: {
          email: '',
          password: ''
        }
      }
    },
    methods: {
      login() {
        this.$http.post(
          "http://localhost:8081/api/member/get", 
          this.loginForm
        ).then((result) => { 
          console.log(result.data)
          if(result.data.response != null) {
            this.setId(result.data.response)
            this.$router.push("home")
          } else 
            alert("Error credentials!")
        })
      }
    },
    mounted() {
      if(this.authorized()) {
        this.$router.push("home")
      }
    }
  })
</script>

<template>
    <div class="bg-surface-variant d-flex justify-center align-center fill-height xs12 sm8 md4"> 
      <v-card class="elevation-12" width="400"> 
         <v-toolbar dark color="primary">
            <v-toolbar-title>Welcome to Library</v-toolbar-title>
         </v-toolbar>
         <v-card-text>
            <v-form >
               <v-text-field prepend-icon="mdi-account-circle" name="login" label="Email" type="email"  v-model="loginForm.email"></v-text-field>
               <v-text-field id="password" prepend-icon="mdi-lock" name="password" label="Password" type="password" v-model="loginForm.password"></v-text-field>
            </v-form>
         </v-card-text>
         <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn class="ma-2" color="primary" @click="login()">Sign in</v-btn>
         </v-card-actions>
      </v-card>
    </div>
</template>

<style scoped>
</style>