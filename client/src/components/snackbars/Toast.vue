<script lang="ts" >
  import { defineComponent } from 'vue'

  export default defineComponent({
    props: {
      snackbar: Boolean,
      text: String
    },
    data: () => ({
      proxy : {
        open: false
      },
      timeout: 2000,
    }),
    mounted() {
      this.proxy.open = this.snackbar 
    }
  })
</script>

<template>
  <div class="text-center">
    <v-snackbar v-model="snackbar" :timeout="timeout">
      {{ text }}
    </v-snackbar>
  </div>
</template>