<script lang="ts" >
  import { defineComponent } from 'vue'
  import { getCredentials } from '@/stores/credentials'
  import { storeToRefs } from 'pinia'
  import Toast  from '@/components/snackbars/Toast.vue'


  export default defineComponent({
    components: {
      Toast
    },
    setup() {
      const { id } = storeToRefs(getCredentials());
      return { id };
    },
    data: () => ({
      books: [],
      toaster: {
        message: "",
        enable: false
      }
    }),
    methods: {
      borrow(item) {
        this.$http.post(
          "http://localhost:8081/api/tx/borrow", {
            "bookId" : item.bookId,
            "memberId" : this.id
          }
        ).then((result) => {
          if(result.data.response == null) {
            this.toaster.message = "Only (2) books allowed to be borrowed at any point of  time."
          } else { 
            item.numberOfCopies -= 1
            this.toaster.message = "Successfully borrowed book of " + item.title
          }

          if(item.numberOfCopies == 0) {
            this.books = this.books.filter(tx => tx.bookId != item.bookId)
          }

          alert(this.toaster.message)
          // this.toaster.enable = true
        })
      }
    },
    mounted() {
      this.$http.get("http://localhost:8081/api/book/all").then((result) => {
        this.books = result.data.filter(b => b.numberOfCopies != 0);
      })
    }
  })
</script>

<template>
  <v-container fluid  class="bg-surface-variant  fill-height">
    <v-row no-gutters v-if="books.length == 0">
      <v-col xs12 align-self="center">
        <v-alert color="deep-orange" icon="mdi-fire" title="Oopss Sorry we don't have books right now !" variant="outlined">
          We might don't have any books for now! <br>
          But today is not the end please com back later on to check some available cool stuff!
        </v-alert>
      </v-col>
    </v-row>
    <v-row no-gutters v-else>
      <v-col lg="2" md="4" sm="12" v-for="(item, index) in books" ref="index" class="pa-2">
        <v-card class="mx-auto elevation-12" max-width="344" variant="outlined" >
          <v-card-item>
            <div>
              <div class="text-overline mb-1">
              {{ item.subject }}
            </div>
            <div class="text-h6 mb-1">
              {{ item.title }}
            </div>
            <v-divider></v-divider>
            <div class="text-caption">
              <b>ID</b> : {{ item.bookId }} <br>
              <b>Author</b> : {{ item.author }} <br>
              <b>publisher</b> : {{ item.publisher }} <br>
              <b>Library</b> : {{ item.nameOfLibrary }} <br>
              <b>Pages</b> : {{ item.pages }} <br>
              <b>Shelf No</b> : {{ item.selfNo }} <br>
              <b>Edition</b> : {{ item.editionNumber }} <br>
              <b>Remaining Copies</b> : {{ item.numberOfCopies }}
            </div>
            </div>
          </v-card-item>

          <v-card-actions>
            <v-btn variant="outlined" @click="borrow(item)" :disabled="item.numberOfCopies <= 0"> Borrow </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style scoped>
</style>