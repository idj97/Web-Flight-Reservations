Vue.component("my-profile", {
  data: function() {
    return {
    	user: {
        	name: "",
        	surname: "",
        	phone: "",
        	email: ""
        }
    }
  },
  template:`
    <div class="myProfileDiv">
      <label for="firstname"><b>Firstname :</b></label>
	  <input type="text" class="form-control" id="name">
			
	  <label for="surname"><b>Surname :</b></label>
	  <input type="text" class="form-control" id="surname">
	  
	  <label for="phone"><b>Phone :</b></label>
	  <input type="text" class="form-control" id="phone">
			
	  <label for="email"><b>Email :</b></label>
	  <input type="text" class="form-control" id="email">
	  <br>
	  <button class="btn btn-primary"  onclick="">Save</button>  <button class="btn btn-dark"  data-toggle="modal" data-target="#changePassModal">Change password</button>
	  
	  
	  <div class="modal fade" id="changePassModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Add new user</h5>
              </div>
              <div class="modal-body">
                <form id="changePassword">
                
					<label for="old_password"><b>Old assword :</b></label>
			    	<input type="password" class="form-control" name="old_password">
						  
					<label for="new_password"><b>New assword :</b></label>
					<input type="password" class="form-control" name="new_password">
						 
                </form>
              </div>
              <div class="modal-footer">
                <button v-on:click="" type="button" class="btn btn-primary">Add</button>
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
	  }

  },
  mounted: function() {
	  lUser = JSON.parse(localStorage.getItem("user"));
	  $("#name").val(lUser.name);
	  $("#surname").val(lUser.surname);
	  $("#phone").val(lUser.phone);
	  $("#email").val(lUser.email);

  }
});
