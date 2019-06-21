Vue.component("destinations", {
  data: function() {
    return {
        destinations: [],
        currDest: {
        	name: "",
        	state: "",
        	airportName: "",
        	airportCode: "",
        	lat: "",
        	log: "",
        	archived: ""
        }
    }
  },
  template:`
    <div>
        <table class="table table-cover">
            <tr>
                <th> Name </th>
                <th> State </th>
                <th> Airport name </th>
                <th> Airport code </th>
                <th> Archived </th>
                <th> Map </th>
                <th> Picture </th>
                <th> Edit </th>
            </tr>
            <tr v-for="d in destinations">
                <td> {{ d.name }} </td>
                <td> {{ d.state }} </td>
                <td> {{ d.airportName }} </td>
                <td> {{ d.airportCode }} </td>
                <td> {{ d.archived }} </td>
                <td> <a class="form-control" href="#" v-on:click="showMap(d.lat, d.log)"> Show </a> </td>
                <td> <a class="form-control" href="#" v-on:click="showImg(d.picturePath)"> Show </a> </td>
                <td> <a class="form-control" href="#" v-on:click="showEdit(d)"> Edit </a> </td>
            </tr>
        </table>

        <google-map ref="googleMap"></google-map>
        <image-view ref="imageView"></image-view>

        <button class="btn btn-primary" data-toggle="modal" data-target="#create-dest">Create destination</button>
	  	<!--modal dodavanja-->
        <div class="modal fade" id="create-dest" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Create destination</h5>
              </div>
              <div class="modal-body">
                <form id="create-destination-form">
                    	  <label for="name"><b>Name :</b></label>
						  <input type="text" class="form-control" name="name">
						  
						 <label for="state"><b>State :</b></label>
						  <input type="text" class="form-control" name="state">
						  
						 <label for="airportName"><b>Airport name :</b></label>
						  <input type="text" class="form-control" name="airportName">
						  
						 <label for="airportCode"><b>Airport state :</b></label>
						  <input type="text" class="form-control" name="airportCode">
						  
						 <label for="lat"><b>Latitude :</b></label>
						  <input type="text" class="form-control" name="lat">
						  
						 <label for="log"><b>Longitude :</b></label>
						  <input type="text" class="form-control" name="log">
						 
						 <label for="image"><b>Image :</b></label>
                          <input class="form-control" type="file" name="image"/>
                </form>
              </div>
              <div class="modal-footer">
                <button v-on:click="createDestination" type="button" class="btn btn-primary">Create</button>
                <button id="close-create-modal" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
        <!--modal editovanja-->
        <div class="modal fade" id="edit-dest" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Edit destination</h5>
              </div>
              <div class="modal-body">
                <form id="edit-destination-form">
                	
                		<label for="name"><b>Name :</b></label>
						  <input v-model:value="currDest.name" type="text" class="form-control" name="name" readonly>
						  
						 <label for="state"><b>State :</b></label>
						  <input v-model:value="currDest.state" type="text" class="form-control" name="state">
						  
						 <label for="airportName"><b>Airport name :</b></label>
						  <input v-model:value="currDest.airportName" type="text" class="form-control" name="airportName">
						  
						 <label for="airportCode"><b>Airport state :</b></label>
						  <input v-model:value="currDest.airportCode" type="text" class="form-control" name="airportCode">
						  
						 <label for="lat"><b>Latitude :</b></label>
						  <input v-model:value="currDest.at" type="text" class="form-control" name="lat">
						  
						 <label for="log"><b>Longitude :</b></label>
						  <input v-model:value="currDest.log" type="text" class="form-control" name="log">
						 
						 <label for="image"><b>Archived :</b></label>
                          <input v-model:value="currDest.archived" class="form-control" type="checkbox" name="archived"/>
                	
                	
                </form>
              </div>
              <div class="modal-footer">
                <button v-on:click="editDestination" type="button" class="btn btn-primary">Save</button>
                <button id="close-edit-dest" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>

    </div>
        
        
    </div>
  `,
  methods: {
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
	  
	  
      createDestination: function() {
          var form = document.getElementById("create-destination-form");
          var formData = new FormData(form);
          if (this.validateForm(formData)) {
              axios.post("/WebProjekat/api/destinations", formData, {headers:{"Content-Type":"multipart/form-data"}})
                   .then(response => {
                       toastr.success("Destination added.")
                       this.destinations.push(response.data);
                       document.getElementById("close-create-modal").click();
                   })
                   .catch(response => {
                       toastr.error("Something is wrong with your request.");
                       console.log(response);
                   })
          } else {
              toastr.warning('Please enter all fields!');
          }
      },
      
      
      editDestination: function() {
    	  if (true) {
    		  axios.put("/WebProjekat/api/destinations", this.currDest)
    		       .then(response => {
    		    	   toastr.success("Edits saved.");
    		    	   this.getDestinations();
    		    	   $("#edit-dest").modal("toggle");
    		       })
    		       .catch(response => {
    		    	   coastr.error("Something is wrong with your request.");
                       console.log(response);
    		       });
    	  }
      },


      showEdit: function(dest) {
    	  this.currDest = JSON.parse(JSON.stringify(dest));
          $("#edit-dest").modal("toggle");

      },


      validateForm: function(form) {
          for (var pair of form.entries())
              if (!pair[1])
                  return false;
          return true;
      },


      showMap: function(lat, log) {
          this.$refs.googleMap.render(parseFloat(lat), parseFloat(log));
      },
      
      
      showImg: function(src) {
    	  this.$refs.imageView.render(src);
      }
      
  },
  mounted: function() {
	  this.getDestinations();
  }
});
