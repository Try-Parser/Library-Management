<template>
    <v-app>
      <v-layout>
      <!--   <v-system-bar color="deep-purple darken-3">
          <v-icon icon="mdi-wifi-strength-3" class="ml-2"></v-icon>

          <v-icon icon="mdi-signal-cellular-outline" class="ml-2"></v-icon>

          <v-icon icon="mdi-battery" class="ml-2"></v-icon>

          <span class="ml-2">{{ clock }}</span>
        </v-system-bar> -->
        
        <!-- <v-system-bar color="deep-purple darken-3"></v-system-bar> -->

        <v-app-bar color="primary" prominent>
          <v-app-bar-nav-icon variant="text" @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
          <v-toolbar-title>My Library</v-toolbar-title>
          <v-spacer></v-spacer>

          <v-btn variant="text" icon="mdi-magnify"></v-btn>
          <v-btn variant="text" icon="mdi-filter"></v-btn>
          <v-btn variant="text" icon="mdi-dots-vertical"></v-btn>
        </v-app-bar>

        <v-navigation-drawer image="https://cdn.vuetifyjs.com/images/backgrounds/bg-2.jpg" permanent theme="dark">
        <v-list nav>
          <v-list-item prepend-icon="mdi-book-search" title="Search Library" value="inbox" to="home"></v-list-item>
          <v-list-item prepend-icon="mdi-book-lock-outline" title="My Books" value="supervisors" to="mybooks"></v-list-item>
          <v-list-item prepend-icon="mdi-book-lock-outline" title="Publish New Book" value="supervisors" to="publish"></v-list-item>
          <v-list-item prepend-icon="mdi-clock-start" title="Add User" value="clockin" to="user"></v-list-item>
        </v-list>

        <template v-slot:append v-if="!hideLogout">
          <div class="pa-2" >
            <v-btn  block @click="signout()" color="info">Sign out</v-btn>
          </div>
        </template>
      </v-navigation-drawer>

        <v-main>
          <router-view/>
        </v-main>
      </v-layout>
  </v-app>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { getCredentials } from '@/stores/credentials'

export default defineComponent({
  name: 'App',
  setup() {
    const { logout } = getCredentials();
    return { logout };
  },
  data () {
    return {
      clock: "00:00",
      hideLogout: false,
    }
  },
  methods: {
    signout() {
      this.logout();
      this.$router.push("/")
    }
  },
  mounted() {
    // if(this.$route.name == "login") {
    //   this.hideLogout = true;
    // } else {
    // this.hideLogout = false
    // }
  }
})
</script>
