Vue.component("flights", {
  data: function() {
    return {
    	flights: []
    }
  },
  template:`
  <div>
    <div>
	  <table class="table table-cover">
            <tr>
                <th> Id </th>
                <th> Start </th>
                <th> End </th>
                <th> Date </th>
                <th> Price </th>
                <th> Type </th>
                <th> Airplane </th>
                <th> Business </th>
                <th> First </th>
                <th> Economy </th>
            </tr>
            <tr v-for="f in flights">
                <td> {{ f.id }} </td>
                <td> {{ f.start.name }} </td>
                <td> {{ f.end.name }} </td>
                <td> {{ f.date }} </td>
                <td> {{ f.price }} </td>
                <td> {{ f.type }} </td>
                <td> {{ f.airplane }} </td>
                <td> {{ f.business }} </td>
                <td> {{ f.first }} </td>
                <td> {{ f.economy }} </td>
                <td> <a href="#" v-on:click="editFlight(f)"> Edit </a> </td>
                <td> <a href="#" v-on:click="deleteFlight(f)"> Delete </a> </td>
            </tr>
        </table>
        
        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#addFlightModal">Add flight</button>
        
        <!--modal dodavanja-->
        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#addFlightModal">Add flight</button>
        <div class="modal fade" id="addFlightModal" role="dialog">
	  		<div class="modal-dialog modal-sm">
      			<div class="modal-content">
        			<div class="modal-header">        			
          				<h4 class="modal-title">Add flight</h4>
          				<button type="button" class="close" data-dismiss="modal">&times;</button>
        			</div>
        			<div class="modal-body">
        				  <label for="startDestinationAdd"><b>Start destination :</b></label>
        				  <select class="form-control" id="startDestinationAdd"><option>1</option></select>
        				  
        				  <label for="endDestinationAdd"><b>End destination :</b></label>
        				  <select class="form-control" id="endDestinationAdd"><option>1</option></select>
        				  
          				  <label for="dateAdd"><b>Date :</b></label>
						  <input type="date" class="form-control" id="dateAdd">
								
						  <label for="priceAdd"><b>Price :</b></label>
						  <input type="number" class="form-control" id="priceAdd">
						  
						  <label for="typeAdd"><b>Flight type :</b></label>
        				  <select class="form-control" id="typeAdd"></select>
								
						  <label for="airplaneAdd"><b>Airplane :</b></label>
						  <input type="text" class="form-control" id="airplaneAdd">
						  
						  <label for="businessAdd"><b>Business class seats :</b></label>
						  <input type="number" class="form-control" id="businessAdd">
						  
						  <label for="firstAdd"><b>First class seats :</b></label>
						  <input type="number" class="form-control" id="firstAdd">
						  
						  <label for="economyAdd"><b>Economy class seats :</b></label>
						  <input type="number" class="form-control" id="economyAdd">
        			</div>
        			<div class="modal-footer">
          				<button type="button" class="btn btn-success" onclick="">Add</button>
          				<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
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
        				  <label for="startDestinationEdit"><b>Start destination :</b></label>
        				  <select class="form-control" id="startDestinationEdit"></select>
        				  
        				  <label for="endDestinationEdit"><b>End destination :</b></label>
        				  <select class="form-control" id="endDestinationEdit"></select>
        				  
          				  <label for="dateEdit"><b>Date :</b></label>
						  <input type="date" class="form-control" id="dateEdit">
								
						  <label for="priceEdit"><b>Price :</b></label>
						  <input type="number" class="form-control" id="priceEdit">
						  
						  <label for="typeEdit"><b>Flight type :</b></label>
        				  <select class="form-control" id="typeEdit"></select>
								
						  <label for="airplaneEdit"><b>Airplane :</b></label>
						  <input type="text" class="form-control" id="airplaneEdit">
						  
						  <label for="businessEdit"><b>Business class seats :</b></label>
						  <input type="number" class="form-control" id="businessEdit">
						  
						  <label for="firstEdit"><b>First class seats :</b></label>
						  <input type="number" class="form-control" id="firstEdit">
						  
						  <label for="economyEdit"><b>Economy class seats :</b></label>
						  <input type="number" class="form-control" id="economyEdit">
        			</div>
        			<div class="modal-footer">
          				<button type="button" class="btn btn-success" onclick="">Edit</button>
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
	  createFlight: function() {
		  // json copy
	  },
	  
	  editFlight: function(flight) {
		  // json copy
	  },
	  
	  
	  deleteFlight: function(flight) {
		  // json copy
	  }
  },
  	  showEditModal: function() {
  		$("#editFlightModal").modal();  
  	  },
  mounted: function() {

  }
});
