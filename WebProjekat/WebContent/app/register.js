Vue.component("register", {
	template: `
		<div>
			<h1> REGISTER COMPONENT </h1>
			<a href='#/login'>LOGIN</a>

			<form id="register-form">
				<p>Username : <input type="text" name="username"/></p>
				<p>Password : <input type="text" name="password"/></p>
				<p>Name     : <input type="text" name="name"/></p>
				<p>Surname  : <input type="text" name="surname"/></p>
				<p>Phone    : <input type="text" name="phone"/></p>
				<p>Email    : <input type="text" name="email"/></p>
				<p>Select a file : <input type="file" name="image"/></p>
			</form>
			<button v-on:click="submit">Register</button>

		</div>`,
	methods: {
		submit: function(e){
			var form = document.getElementById("register-form");
			var formData = new FormData(form);

			axios.post("/WebProjekat/api/auth/register", formData, {headers: {"Content-Type":"multipart/form-data"}})
				 .then(
				 	response => {
				 		alert(response.data.message);
				 		app.$router.push("/login");
				 	})
			     .catch(
				 	error => {
				 		alert(error.response.data.message)
				 	}
			);
		}
	},
	mounted: function() {
		var user = localStorage.getItem("user");
		if (user != null) {
			user = JSON.parse(user);
			if (user.role == "USER")
				app.$router.push("/user/home");
			else
				app.$router.push("/admin/home");
		}
	}
});
