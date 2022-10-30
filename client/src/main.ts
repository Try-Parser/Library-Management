import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import vuetify from './plugins/vuetify'
import { loadFonts } from './plugins/webfontloader'
import { createPinia } from 'pinia'
import VueAxios from 'vue-axios'
import axios from 'axios'

loadFonts()

createApp(App)
  .use(VueAxios, axios)
  .use(createPinia())
  .use(router)
  .use(vuetify)
  .mount('#app')
