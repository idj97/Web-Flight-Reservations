Vue.component("login", {
	data: function() {
		return {
			username: "",
			password: ""
		}
	},
	template: `
	<div id="loginDiv">
		<h1> LOGIN </h1>
		<label for="usernameInput"><b>Username:</b></label>
		<input class="form-control" placeholder="Enter your username..." v-model="username" type="text" name="username"></input><br>

		<label for="passwordInput"><b>Password:</b></label>
		<input class="form-control" placeholder="Enter your password..." v-model="password" type="password" name="password"></input> <br>
		<button type="button" id="loginButton" class="btn btn-primary" v-on:click="login">Login</button>
		<br>
		<label id="regLabel"for="register"></b>Dont have an account?</b></label>
		<button class="btn btn-link" v-on:click="registerModal()">Register</button>
		<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Register</h5>
              </div>
              <div class="modal-body">
                <form id="register-form">
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
                <button v-on:click="register()" type="button" class="btn btn-primary">Add</button>
                <button id="close-create-modal" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>
	</div>`,

	methods: {
		register: function(e){
			var form = document.getElementById("register-form");
			var formData = new FormData(form);

			axios.post("/WebProjekat/api/auth/register", formData, {headers: {"Content-Type":"multipart/form-data"}})
				 .then(
				 	response => {
				 		toastr.success("Registration successful.")
				 		$("#close-create-modal").click();
				 	})
			     .catch(
				 	error => {
				 		toastr.error("Registration unsuccessful.");

				 	}
			);
		},
		login: function() {
			if (this.username != "" && this.password != "") {
				axios.post(
					`/WebProjekat/api/auth/login/${this.username}/${this.password}`,
					{
						headers: {"responseType":"json"}
					})
				.then(
					response=> {
						localStorage.setItem("user", JSON.stringify(response.data.data));
						this.loginSuccess(response.data.data);
					})
				.catch(
					error => {
						var message = error.response.data.message;
						alert(message);
					}
				);
			}
		},
	  loginSuccess: function(user) {
			if (user.role == "USER")
				this.$router.push("/user");
			else
				this.$router.push("/admin");
		},
	
	  registerModal: function(){
		  $("#registerModal").modal();
	  }},
	mounted: function() {
		var user = localStorage.getItem("user");
		if (user != null) {
			user = JSON.parse(user);
			this.loginSuccess(user);
		}
	}
});
