<script lang="ts" >
  import { defineComponent } from 'vue'
  import { getCredentials } from '@/stores/credentials'
  import { storeToRefs } from 'pinia'

  export default defineComponent({
    setup() {
      const { id } = storeToRefs(getCredentials());
      return { id };
    },
    data() {
      return {
        books: []
      }
    },
    methods: {
      returnBook(item) {
        this.$http.get("http://localhost:8081/api/tx/return?txId="+item.transactionDetails.transactionId)
          .then((result) => {
            if(result.data.success) {
              alert("Sucessfully return the " + item.bookDetails.title)
            }
            this.books = this.books.filter(tx => item.transactionDetails.transactionId != tx.transactionDetails.transactionId)
          })
      }
    },
    mounted() {
      this.$http
        .get("http://localhost:8081/api/tx/mine?id="+this.id)
        .then((result) => {
          this.books = result.data;
          console.log(result.data)
        })
    }
  })
</script>

<template>
  <v-container fluid class="bg-surface-variant  fill-height">
    <v-row no-gutters v-if="books.length == 0">
      <v-col  xs12 align-self="center">
        <v-alert color="deep-orange" icon="mdi-fire" title="You are clean !" variant="outlined">
            You don't have any borrowed books.. <br>
            You can now borrow another books by clicking Search Library on the left side menu...
        </v-alert>
      </v-col >
    </v-row>
    <v-row no-gutters v-else>
      <v-col lg="2" md="4" sm="12" v-for="(item, index) in books" ref="index" class="pa-2">
        <v-card class="mx-auto elevation-12" max-width="344" variant="outlined">
          <v-card-item>
            <div>
              <div class="text-overline mb-1">
              {{ item.bookDetails.subject }}
            </div>
            <div class="text-h6 mb-1">
              {{ item.bookDetails.title }}
            </div>
            <v-divider></v-divider>
            <div class="text-caption">
              <b>ID:</b> {{ item.transactionDetails.transactionId }} <br>
              <b>Date Barrowed</b> : {{ new Date(item.transactionDetails.barrowDate).toDateString() }} <br>
              <b>Author</b>: {{ item.bookDetails.author }} <br>
              <b>publisher</b>: {{ item.bookDetails.publisher }} <br>
              <b>Library</b>: {{ item.nameOfLibrary }} <br>
              <b>Pages</b>: {{ item.bookDetails.pages }} <br>
              <b>Shelf No</b>: {{ item.bookDetails.selfNo }} <br>
              <b>Edition</b>: {{ item.bookDetails.editionNumber }} <br>
            </div>
            </div>
          </v-card-item>

          <v-card-actions>
            <v-btn variant="outlined" @click="returnBook(item)" :disabled="item.numberOfCopies <= 0"> Return </v-btn>
          </v-card-actions>
        </v-card>
      </v-col >
    </v-row>
  </v-container>
</template>

<style scoped>
</style>