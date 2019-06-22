Vue.component("flights-user", {
  data: function() {
    return {
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
                <td> <a class="form-control" href="#" v-on:click="reserve(f)"> Reserve </a> </td>
                
            </tr>
            
            
        </table>
        
        

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
  	  },
  mounted: function() {
	  this.getFlights();
	  
  }
});
