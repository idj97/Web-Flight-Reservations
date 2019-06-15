Vue.component("login", {
	data: function() {
		return {
			username: "",
			password: ""
		}
	},
	template: `
	<div>
		<h1> LOGIN COMPONENT </h1>
		<a href='#/register'>register</a>

		<label for="usernameInput">Username:</label>
		<input v-model="username" type="text" name="username"></input><br>

		<label for="passwordInput">Password:</label>
		<input v-model="password" type="password" name="password"></input> <br>
		<button v-on:click="login">Login</button>
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
				app.$router.push("/user");
			else
				app.$router.push("/admin");
		}
	},
	mounted: function() {
		var user = localStorage.getItem("user");
		if (user != null) {
			user = JSON.parse(user);
			loginSuccess(user);
		}
	}
});
