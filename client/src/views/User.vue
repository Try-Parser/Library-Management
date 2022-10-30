<script lang="ts">
  import { defineComponent } from 'vue'
  export default defineComponent({
  	data() {
      return { 
      	form: {
					name: "",
					email: "",
					password: ""
				}
      }
    },
    methods: {
    	add() {
    		this.$http.post("http://localhost:8081/api/member/add", this.form)
	    		.then((result) => {
	    			if(result.data.success) {
		    			alert("User " + this.form.name + " created successfully")
	    			} else {
	    				alert(result.data.response)
	    			}
	    		}).catch((e) =>alert("We experience some issue no please try later."))
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
				      New User
				    </template>
				    <v-card-text>
		    			<v-form ref="form">
		  					<v-container fluid>
		  						<v-row>
		  							<v-col sm="12">
		  								<v-text-field v-model="form.name" prepend-inner-icon="mdi-map-marker" 
		  									label="Name" placeholder="Placeholder" variant="outlined"></v-text-field>
		  							</v-col>
		  							<v-col sm="12">
		  								<v-text-field v-model="form.email" prepend-inner-icon="mdi-map-marker" 
		  									label="Email" placeholder="Placeholder" variant="outlined" type="email"></v-text-field>
		  							</v-col>
		  						</v-row>
		  						<v-row>
		  							<v-col >
		  								<v-text-field v-model="form.password" prepend-inner-icon="mdi-map-marker" 
		  									label="Password" placeholder="Password" variant="outlined" type="password"></v-text-field>
		  							</v-col>
		  						</v-row>
		  						<v-row>
		  							<v-col class="text-right">
									    <v-btn color="info" class="mr-4" @click="add()">
									      	Create
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