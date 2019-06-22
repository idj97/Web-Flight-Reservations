Vue.component("my-profile", {
  data: function() {
    return {
    	user: {
        	name: "",
        	surname: "",
        	phone: "",
        	email: ""
        },
        
        passwords: {
        	old: "",
        	fresh: ""
        }
    }
  },
  template:`
    <div class="myProfileDiv">
      <label for="firstname"><b>Firstname :</b></label>
	  <input v-model:value="user.name" type="text" class="form-control" id="name">
			
	  <label for="surname"><b>Surname :</b></label>
	  <input v-model:value="user.surname" type="text" class="form-control" id="surname">
	  
	  <label for="phone"><b>Phone :</b></label>
	  <input v-model:value="user.phone" type="text" class="form-control" id="phone">
			
	  <label for="email"><b>Email :</b></label>
	  <input v-model:value="user.email" type="text" class="form-control" id="email">
	  <br>
	  <button class="btn btn-primary"  onclick="editMe()">Save</button>  <button class="btn btn-dark"  data-toggle="modal" data-target="#changePassModal">Change password</button>
	  
	  
	  <div class="modal fade" id="changePassModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Add new user</h5>
              </div>
              <div class="modal-body">
                <form id="changePassword">
                
					<label for="old_password"><b>Old assword :</b></label>
			    	<input v-model:value="passwords.old" type="password" class="form-control" name="old_password">
						  
					<label for="new_password"><b>New assword :</b></label>
					<input v-model:value="passwords.fresh" type="password" class="form-control" name="new_password">
						 
                </form>
              </div>
              <div class="modal-footer">
                <button v-on:click="changePassword()" type="button" class="btn btn-primary">Add</button>
                <button id="close-create-modal" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
    </div>
	  
	  
	  
	  
    </div>
  `,
  methods: {
	  
	  getLoggedUser: function() {
		  axios.get("/WebProjekat/api/auth/detail", {headers: {"responseType":"json"}})
          .then(response => {
              this.user = response.data;
          })
          .catch(response => {
              toastr.error("Something went wrong.");
          });
	  },
	  
	  
	  changePassword: function() {
		  if (!this.validateObject(this.passwords)) {
			  toastr.warning("Please enter all fields!");
			  return;
		  }
		  
		  axios.put(`/WebProjekat/api/auth/change-password/${this.passwords.old}/${this.passwords.fresh}`) 
		       .then(response => {
		    	   toastr.info("Password changed.");
		    	   $("#changePassModal").modal("toggle");
		       })
		       .catch(response => {
		    	   toastr.error("Try again.");
		       });
	  },
	  
	  validateObject: function(object) {
    	  for (var key of Object.keys(object)) {
    		  if (!object[key])
    			  return false;
    	  }
    	  return true;
      },
      
      editMe: function(){
    	  axios.put("/WebProjekat/api/auth/editUser")
    	  .then(response => {
	    	   toastr.info("Data updated.");
	       })
	       .catch(response => {
	    	   toastr.error("Data is not updated.");
	       });
 },

  },
  mounted: function() {
	  lUser = JSON.parse(localStorage.getItem("user"));
	  $("#name").val(lUser.name);
	  $("#surname").val(lUser.surname);
	  $("#phone").val(lUser.phone);
	  $("#email").val(lUser.email);

  }
});
