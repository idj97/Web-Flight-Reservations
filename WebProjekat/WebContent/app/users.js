Vue.component("users", {
  data: function() {
    return {
    	users: []
    }
  },
  template:`
    <div>
      <table class="table table-cover">
            <tr>
                <th> Name </th>
                <th> Surname </th>
                <th> Phone </th>
                <th> Email </th>
                <th> Blocked </th>
            </tr>
            <tr v-for="u in users">
                <td> {{ u.name }} </td>
                <td> {{ u.surname }} </td>
                <td> {{ u.phone }} </td>
                <td> {{ u.email }} </td>
                <td> <input v-model:value="u.blocked" class="form-control" v-on:click="blockUser(u)" type="checkbox" name="blocked"/> </td>
            </tr>
        </table>
        
        <button class="btn btn-primary" data-toggle="modal" data-target="#addUser">Add user</button>
	  	<!--modal dodavanja-->
        <div class="modal fade" id="addUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Add new user</h5>
              </div>
              <div class="modal-body">
                <form id="addUserForm">
                    	  <label for="name"><b>Firstname :</b></label>
						  <input type="text" class="form-control" name="name">
						  
						 <label for="surname"><b>Surname :</b></label>
						  <input type="text" class="form-control" name="surname">
						  
						 <label for="phone"><b>Phone :</b></label>
						  <input type="text" class="form-control" name="phone">
						  
						 <label for="email"><b>Email address :</b></label>
						  <input type="text" class="form-control" name="email">
						  
						 <label for="username"><b>Username :</b></label>
						  <input type="text" class="form-control" name="username">
						  
						 <label for="password"><b>Password :</b></label>
						  <input type="password" class="form-control" name="password">
						 
						 <label for="image"><b>Image :</b></label>
                          <input class="form-control" type="file" name="image"/>
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
  `,
  methods: {
	  
	  getUsers: function() {
		  axios.get("/WebProjekat/api/users")
		       .then(response => {
		    	   this.users = response.data;
		       })
		       .catch(response => {
		    	   toastr.error("Something is wrong with with your request. (get users)");
		       });
	  },
	  
	  
	  blockUser: function(user) {
		  axios.put(`/WebProjekat/api/users/block/${user.username}/${user.blocked}`)
		       .then(response => {
		    	   if (user.blocked)
		    		   toastr.success("User is now blocked.");
		    	   else 
		    		   toastr.success("User is now unblocked.");
		       })
		       .catch(response => {
		    	  user.blocked = !blocked;
		    	  toastr.error("Blocking user failed."); 
		       });
	  }
	  
  },
  mounted: function() {
	  this.getUsers();
  }
});
