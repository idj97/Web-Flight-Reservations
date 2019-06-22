Vue.component("my-reservation", {
  data: function() {
    return {
    	reservations:[],
    	
    	
    }
  },
  template:`
  <div>
	
    
	  <table class="table table-cover">
            <tr>
            	<th> Number of reservation </th>
                <th> Flight number </th>
                <th> Date </th>
                <th> Number of seats </th>
                <th> Seat type </th>
                <th> Cancel </th>
                
            </tr>
            <tr v-for="r in reservations">
                <td> {{ r.number }} </td>
                <td> {{ r.flightNum }} </td>
                <td> {{ r.dateCreated }} </td>
                <td> {{ r.passengerNum }} </td>
                <td> {{ r.type }} </td>
                <td> <a class="form-control" href="#" v-on:click="cancelReservation(r)"> Cancel </a> </td>
                
            </tr>
            
            
        </table>
        
        

    
    
    
   </div>
  `,
  methods: {
	  
  	  getReservations: function() {
	  axios.get("/WebProjekat/api/reservations", {headers: {"responseType":"json"}})
      .then(response => {
    	  this.reservations = [];
          this.reservations = response.data;
          console.log(JSON.stringify(response.data));
      })
      .catch(response => {
          toastr.error("Something went wrong.");
      });
  },

  	
  	  },
  mounted: function() {
	  
	  this.getReservations();
	  
  }
});
