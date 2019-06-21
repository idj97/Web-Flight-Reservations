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
		<a href='#/register'>Register</a>
	</div>`,

	methods: {
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
		}
	},
	mounted: function() {
		var user = localStorage.getItem("user");
		if (user != null) {
			user = JSON.parse(user);
			this.loginSuccess(user);
		}
	}
});
