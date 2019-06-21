Vue.component("flights", {
  data: function() {
    return {
    	search:{
    		basicSearch:{
    			date: "",
    			start: "",
    			end: ""
    		},
    		flightNumber: "",
    		flightType: ""
    		
    		
    	},
    	flightToEdit:{
    		number: "",
    		price: 0,
    		airplane: "",
    		firstSize: 0,
    		businessSize: 0,
    		economySize: 0,
    		date: "",
    		type: "",
    		start: "",
    		end: ""
    		
    		
    	},
    	flights: [],
    	destinations:[],
    	flightToAdd:{
    		number: "",
    		price: 0,
    		airplane: "",
    		firstSize: 0,
    		businessSize: 0,
    		economySize: 0,
    		date: "",
    		type: "",
    		start: "",
    		end: ""
    		
    		
    	}
    }
  },
  template:`
  <div>
	<div id="search">				
	  		<label for="numberS"><b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbspNumber :&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</b></label>
        	<input type="text" v-model:value="search.number" id="numberS"/>
	
	
	  		<label for="startDestinationS"><b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbspStart destination :&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</b></label>
        	<input type="text" v-model:value="search.basicSearch.start" id="startDestinationS"/>
        				  
        	<label for="endDestinationS"><b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbspEnd destination :&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</b></label>
        	<input type="text" v-model:value="search.basicSearch.end" id="endDestinationS"/>
        				  
        	<label for="typeS"><b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbspFlight type :&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</b></label>
        	<select v-model:value="search.type" id="typeS"><option></option><option>CHARTER</option><option>REGIONAL</option><option>OVERSEA</option></select>
        				  
        	<label for="dateS"><b>&nbsp&nbsp&nbsp&nbsp&nbsp&nbspDate :&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</b></label>
        	<input type="date" v-model:value="search.basicSearch.date" id="dateS"/>			  
        				  
        				  
        	<button class="btn btn-dark" v-on:click="searchFlights()">Search</button>			  
        				  
        				  
        				  
        				  
        				  
	</div>
    <div>
	  <table class="table table-cover">
            <tr>
                <th> Number </th>
                <th> Start </th>
                <th> End </th>
                <th> Date </th>
                <th> Price </th>
                <th> Type </th>
                <th> Airplane </th>
                <th> Business </th>
                <th> First </th>
                <th> Economy </th>
                <th> Edit </th>
                <th> Delete </th>
            </tr>
            <tr v-for="f in flights">
                <td> {{ f.number }} </td>
                <td> {{ f.start }} </td>
                <td> {{ f.end }} </td>
                <td> {{ f.date }} </td>
                <td> {{ f.price }} </td>
                <td> {{ f.type }} </td>
                <td> {{ f.airplane }} </td>
                <td> {{ f.businessSize }} </td>
                <td> {{ f.firstSize }} </td>
                <td> {{ f.economySize }} </td>
                <td> <a class="form-control" href="#" v-on:click="showEditModal(f)"> Edit </a> </td>
                <td> <a class="form-control" href="#" v-on:click="deleteFlight(f)"> Delete </a> </td>
            </tr>
            
            
        </table>
        
        <button id="addModal" v-on:click="addM()" type="button" class="btn btn-info">Add flight</button>
        
        <!--modal dodavanja-->
        <div class="modal fade" id="addFlightModal" role="dialog">
	  		<div class="modal-dialog modal-sm">
      			<div class="modal-content">
        			<div class="modal-header">        			
          				<h4 class="modal-title">Add flight</h4>
          				<button type="button" class="close" data-dismiss="modal">&times;</button>
        			</div>
        			<div class="modal-body">
        				<form id="addFlightForm">
        				  <label for="number"><b>Flight number :</b></label>
						  <input v-model:value="flightToAdd.number" type="number" class="form-control" id="number">
        				  
        				  <label for="startDestinationAdd"><b>Start destination :</b></label>
        				  <select v-model:value="flightToAdd.start" class="form-control" id="startDestinationAdd"><option v-for="d in destinations">{{ d.name }}</option></select>
        				  
        				  <label for="endDestinationAdd"><b>End destination :</b></label>
        				  <select v-model:value="flightToAdd.end" class="form-control" id="endDestinationAdd"><option v-for="d in destinations">{{ d.name }}</option></select>
        				  
          				  <label for="dateAdd"><b>Date :</b></label>
						  <input v-model:value="flightToAdd.date" type="date" class="form-control" id="dateAdd">
								
						  <label for="priceAdd"><b>Price :</b></label>
						  <input v-model:value="flightToAdd.price" type="number" class="form-control" id="priceAdd">
						  
						  <label for="typeAdd"><b>Flight type :</b></label>
        				  <select v-model:value="flightToAdd.type" class="form-control" id="typeAdd"><option>CHARTER</option><option>REGIONAL</option><option>OVERSEA</option></select>
								
						  <label for="airplaneAdd"><b>Airplane :</b></label>
						  <input v-model:value="flightToAdd.airplane" type="text" class="form-control" id="airplaneAdd">
						  
						  <label for="businessAdd"><b>Business class seats :</b></label>
						  <input v-model:value="flightToAdd.businessSize" type="number" class="form-control" id="businessAdd">
						  
						  <label for="firstAdd"><b>First class seats :</b></label>
						  <input v-model:value="flightToAdd.firstSize" type="number" class="form-control" id="firstAdd">
						  
						  <label for="economyAdd"><b>Economy class seats :</b></label>
						  <input v-model:value="flightToAdd.economySize" type="number" class="form-control" id="economyAdd">
						</form>
        			</div>
        			<div class="modal-footer">
          				<button type="button" class="btn btn-success" v-on:click="createFlight">Add</button>
          				<button type="button" id="closeAddFlightModal" class="btn btn-danger" data-dismiss="modal">Close</button>
        			</div>
      			</div>
	  		</div>
	  	  </div>
	  	</div>
	  	
	  	<!--modal editovanja-->
        <div class="modal fade" id="editFlightModal" role="dialog">
	  		<div class="modal-dialog modal-sm">
      			<div class="modal-content">
        			<div class="modal-header">        			
          				<h4 class="modal-title">Edit flight</h4>
          				<button type="button" class="close" data-dismiss="modal">&times;</button>
        			</div>
        			<div class="modal-body">
        				  <label for="number"><b>Flight number :</b></label>
						  <input v-model:value="flightToEdit.number" type="number" class="form-control" id="number" readonly>
						  
        				  <label for="startDestinationEdit"><b>Start destination :</b></label>
        				  <input type="text" v-model:value="flightToEdit.start" class="form-control" id="startDestinationEdit" readonly/>
        				  
        				  <label for="endDestinationEdit"><b>End destination :</b></label>
        				  <input type="text" v-model:value="flightToEdit.end" class="form-control" id="endDestinationEdit" readonly/>
        				  
          				  <label for="dateEdit"><b>Date :</b></label>
						  <input type="string" v-model:value="flightToEdit.date" class="form-control" id="dateEdit">
								
						  <label for="priceEdit"><b>Price :</b></label>
						  <input v-model:value="flightToEdit.price" type="number" class="form-control" id="priceEdit">
						  
						  <label for="typeEdit"><b>Flight type :</b></label>
        				  <select v-model:value="flightToEdit.type" class="form-control" id="typeEdit"><option>CHARTER</option><option>REGIONAL</option><option>OVERSEA</option></select>
								
						  <label for="airplaneEdit"><b>Airplane :</b></label>
						  <input v-model:value="flightToEdit.airplane" type="text" class="form-control" id="airplaneEdit">
						  
						  <label for="businessEdit"><b>Business class seats :</b></label>
						  <input v-model:value="flightToEdit.businessSize" type="number" class="form-control" id="businessEdit">
						  
						  <label for="firstEdit"><b>First class seats :</b></label>
						  <input v-model:value="flightToEdit.firstSize" type="number" class="form-control" id="firstEdit">
						  
						  <label for="economyEdit"><b>Economy class seats :</b></label>
						  <input v-model:value="flightToEdit.economySize" type="number" class="form-control" id="economyEdit">
        			</div>
        			<div class="modal-footer">
          				<button v-on:click="editFlight()"  class="btn btn-success" >Edit</button>
          				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        			</div>
      			</div>
	  		</div>
	  	  </div>
	  	</div>

    </div>
   </div>
  `,
  methods: {
  	  getFlights: function() {
	  axios.get("/WebProjekat/api/flights", {headers: {"responseType":"json"}})
      .then(response => {
    	  this.flights = [];
          this.flights = response.data;
      })
      .catch(response => {
          toastr.error("Something went wrong.");
      });
  },
	  createFlight: function() {
		  //var form = document.getElementById("addFlightForm");
          //var formData = new FormData(form);
          //if (this.validateForm(formData)) {
		  	  console.log(this.flightToAdd);
              axios.post("/WebProjekat/api/flights", this.flightToAdd)
                   .then(response => {
                       toastr.success("Flight added.")
                       this.destinations.push(response.data);
                       document.getElementById("closeAddFlightModal").click();
                       this.getFlights();
                   })
                   .catch(response => {
                	   
                       toastr.error("Something is wrong with your request.");
                       console.log(response);
                   })
          //} else {
             // toastr.warning('Please enter all fields!');
          //}
	  },
	  getDestinations: function() {
		  axios.get("/WebProjekat/api/destinations", {headers: {"responseType":"json"}})
          .then(response => {
        	  this.destinations = [];
              this.destinations = response.data;
          })
          .catch(response => {
              toastr.error("Something went wrong.");
          });
	  },
	  
	  editFlight: function() {
		  console.log(this.flightToEdit);
		  axios.put("/WebProjekat/api/flights", this.flightToEdit)
          .then(response => {
        	  toastr.success("Flight edited successfuly.");
        	  $("#editFlightModal").modal("toggle");
        	  this.getFlights();
          })
          .catch(response => {
              toastr.error("Flight can't be edited.");
          });
	  },
	  
	  
	  deleteFlight: function(flight) {
	  console.log(flight);
		  axios.delete(`/WebProjekat/api/flights/${flight.number}`)
          .then(response => {
        	  toastr.success("Flight deleted.")
        	  this.getFlights();
          })
          .catch(response => {
              toastr.error("Flight can't be deleted.");
          });
	  },
  
  	  showEditModal: function(flight) {
  		$("#editFlightModal").modal();
  		this.flightToEdit = JSON.parse(JSON.stringify(flight));
  		
  	  },
  	addM: function() {
  		$("#addFlightModal").modal();
  		this.getDestinations();
  	  },
  	searchFlights: function() {
  		console.log(this.search);
		  axios.get("/WebProjekat/api/flights/combinedSearch", this.search, {headers: {"responseType":"json"}})
        .then(response => {
      	  this.destinations = [];
          this.destinations = response.data;
        })
        .catch(response => {
            toastr.error("Something went wrong.");
        });
	  },
  	  },
  mounted: function() {
	  this.getFlights();
	  
  }
});
