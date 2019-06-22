Vue.component("flights-user", {
  data: function() {
    return {
    	reservation:{
    		number: 0,
    		dateCreated: "",
    		type: "",
    		passengerNum: 0,
    		flightNum: ""
    	},
    	search:{
    		
    			date: "",
    			start: "",
    			end: ""	
    	},
    	
    	filter:{
    		
    		flightNumber: "",
    		flightType: "",
    		sort: false
    		
    		
    	},
    	
    	flights: []
    	
    }
  },
  template:`
  <div>
	<div id="search">				
	  		
	
	  		<div width="60%">
	  		<label for="startDestinationS"><b>Start destination :</b></label>
        	<input type="text" v-model:value="search.start" id="startDestinationS"/>
        				  
        	<label for="endDestinationS"><b>End destination :</b></label>
        	<input type="text" v-model:value="search.end" id="endDestinationS"/>
        	
        	<label for="dateS"><b>Date :</b></label>
        	<input type="date" v-model:value="search.date" id="dateS"/>
        	
        	<button class="btn btn-dark" v-on:click="searchFlightsAsUser()">Search</button>
        	</div>
        	<div width="40%">
        	<label for="numberS"><b>Number :</b></label>
        	<input type="text" v-model:value="filter.flightNumber" id="numberS"/>
        	
        	<label for="typeS"><b>Flight type :</b></label>
        	<select v-model:value="filter.flightType" id="typeS"><option></option><option>CHARTER</option><option>REGIONAL</option><option>OVERSEA</option></select>
        	
        	<label for="numberS"><b>Sort by date :</b></label>
        	<input type="checkbox" v-model:value="filter.sort" id="numberS"/>
        	
        	<button class="btn btn-dark" v-on:click="filterFlights()">Filter</button>
        	</div>			  
        				  
        				  
        				  
        	
        	
        	
        				  
        				  
        				  
        				  
        				  
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
                <th> Reserve </th>
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
                <td> <a class="form-control" href="#" v-on:click="reserveModal(f)"> Reserve </a> </td>
                
            </tr>
            
            
        </table>
        
        

    </div>
    
    <!--modal rezervacija-->
        <div class="modal fade" id="resModal" role="dialog">
	  		<div class="modal-dialog modal-sm">
      			<div class="modal-content">
        			<div class="modal-header">        			
          				<h4 class="modal-title">Create reservation</h4>
          				<button type="button" class="close" data-dismiss="modal">&times;</button>
        			</div>
        			<div class="modal-body">
        				<form id="addFlightForm">
        				  
        				  
        				  <label for="flightNum"><b>Flight number :</b></label>
        				  <input type="text" v-model:value="reservation.flightNum" class="form-control" id="flightNum" readonly/>
        				  	
						  <label for="passengerNum"><b>Seats number :</b></label>
						  <input v-model:value="reservation.passengerNum" type="number" class="form-control" id="passengerNum">
						  
						  <label for="type"><b>Seat type :</b></label>
        				  <select v-model:value="reservation.type" class="form-control" id="typeAdd"><option>FIRST</option><option>ECONOMY</option><option>BUSINESS</option></select>
								
						  
						</form>
        			</div>
        			<div class="modal-footer">
          				<button type="button" class="btn btn-success" v-on:click="reserve()">Reserve</button>
          				<button type="button" id="closeAddFlightModal" class="btn btn-danger" data-dismiss="modal">Close</button>
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

  	filterFlights: function() {
  		console.log(this.search);
		  axios.post("/WebProjekat/api/flights/filter", this.filter, {headers: {"responseType":"json"}})
        .then(response => {
      	  this.flights = [];
          this.flights = response.data;
        })
        .catch(response => {
            toastr.error("Something went wrong.");
        });
	  },
	  searchFlightsAsUser: function() {
	  		console.log(this.search);
			  axios.post("/WebProjekat/api/flights/search", this.search, {headers: {"responseType":"json"}})
	        .then(response => {
	      	  this.flights = [];
	          this.flights = response.data;
	        })
	        .catch(response => {
	            toastr.error("Something went wrong.");
	        });
		  },
		  
		  reserve: function() {
			  
		  		console.log(this.reservation);
				  axios.post("/WebProjekat/api/reservations", this.reservation, {headers: {"responseType":"json"}})
		        .then(response => {
		        	toastr.success("Seat/Seats reserved.");
		        })
		        .catch(response => {
		            toastr.error("Something went wrong.");
		        });
			  },
			  
			  reserveModal: function(flight) {
			  		$("#resModal").modal();
			  		this.reservation.flightNum = flight.number;
				  },
  	  },
  mounted: function() {
	  
	  this.getFlights();
	  
  }
});
