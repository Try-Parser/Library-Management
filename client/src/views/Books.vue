<script lang="ts">
  import { defineComponent } from 'vue'
  export default defineComponent({
  	data() {
      return { 
      	form: {
				   subject: "High fantasy", 
				   title: "The Hobbit",
				   author: "J. R. R. Tolkien",
				   publisher: "George Allen & Unwin (UK)",
				   copyRight: "J. R. R. Tolkien",
				   editionNumber: 1,
				   pages: 310,
				   numberOfCopies: 10,
				   nameOfLibrary: "United Kingdom",
				   selfNo: 2
				}
      }
    },
    methods: {
    	publish() {
    		this.$http.post("http://localhost:8081/api/book/add", this.form)
	    		.then((result) => alert("Book of " + this.form.title + " sucessfully publish"))
	  			.catch((e) =>alert("We experience some issue no please try later."))
    	}
    }
  })
</script>

<template>
	<v-container fluid class="bg-surface-variant  fill-height">
    	<v-row no-gutters> 
			<v-col align-self="center">
				<v-alert color="deep-orange" icon="mdi-fire" title="Knowledge is Power" variant="outlined">
					Keeping up with the world !<br>
					We are very happy and greatful because are now adding a new world in our mind by Using paper and colors, publishing it will make new great things.
				</v-alert>
			</v-col >
    	</v-row>
    	<br>
    	<v-row no-gutters>
    		<v-col >
    			<v-card class="mx-auto ma-1 pa-2" >
    				<template v-slot:title>
				      Publish new book to the library!
				    </template>
				    <v-card-text>
		    			<v-form ref="form">
		  					<v-container fluid>
		  						<v-row>
		  							<v-col sm="12">
		  								<v-text-field v-model="form.subject" prepend-inner-icon="mdi-map-marker" 
		  									label="Subject" placeholder="Placeholder" variant="outlined"></v-text-field>
		  							</v-col>
		  							<v-col sm="12">
		  								<v-text-field v-model="form.title" prepend-inner-icon="mdi-map-marker" 
		  									label="Title" placeholder="Placeholder" variant="outlined"></v-text-field>
		  							</v-col>
		  						</v-row>
		  						<v-row>
		  							<v-col >
		  								<v-text-field v-model="form.author" prepend-inner-icon="mdi-map-marker" 
		  									label="Author" placeholder="Placeholder" variant="outlined"></v-text-field>
		  							</v-col>
		  							<v-col >
		  								<v-text-field v-model="form.publisher" prepend-inner-icon="mdi-map-marker" 
		  									label="Publisher" placeholder="Placeholder" variant="outlined"></v-text-field>
		  							</v-col>
		  						</v-row>
		  						<v-row>
		  							<v-col>
		  								<v-text-field v-model="form.copyRight" prepend-inner-icon="mdi-map-marker" 
		  									label="Copy Right" placeholder="Placeholder" variant="outlined"></v-text-field>
		  							</v-col>
		  							<v-col>	
		  								<v-text-field v-model="form.editionNumber" prepend-inner-icon="mdi-map-marker" 
		  									label="Edition Number" placeholder="Placeholder" variant="outlined" type="number"></v-text-field>
		  							</v-col>
		  						</v-row>
		  						<v-row>
		  							<v-col>
		  								<v-text-field v-model="form.pages" prepend-inner-icon="mdi-map-marker" 
		  									label="Pages" placeholder="Placeholder" variant="outlined" type="number"></v-text-field>
		  							</v-col>
		  							<v-col>
		  								<v-text-field v-model="form.numberOfCopies" prepend-inner-icon="mdi-map-marker" 
		  									label="Number Of Copies" placeholder="Placeholder" variant="outlined" type="number"></v-text-field>
		  							</v-col>
		  						</v-row>
		  						<v-row>
		  							<v-col>
		  								<v-text-field v-model="form.nameOfLibrary" prepend-inner-icon="mdi-map-marker" 
		  									label="Name of Library" placeholder="Placeholder" variant="outlined"></v-text-field>
		  							</v-col>
		  							<v-col>
		  								<v-text-field v-model="form.selfNo" prepend-inner-icon="mdi-map-marker" 
		  									label="Shelf Number" placeholder="Placeholder" variant="outlined" type="number"></v-text-field>
		  							</v-col>
		  						</v-row>
		  						<v-row>
		  							<v-col class="text-right">
	  									<!-- <v-btn :disabled="!valid" color="error" class="mr-4" @click="validate">
									      	clear
									    </v-btn> -->

									    <v-btn color="info" class="mr-4" @click="publish()">
									      	Publish
									    </v-btn>
									</v-col>
		  						</v-row>
		  					</v-container>
					  	</v-form>		  	
				    </v-card-text>
				</v-card>
    		</v-col>
    	</v-row>
    </v-container>
</template>

<style></style>