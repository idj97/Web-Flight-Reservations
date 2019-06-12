Vue.component("register-component", {
	template: `
		<div>
			<h1> REGISTER COMPONENT </h1> 
			<a href='#/'>LOGIN</a>

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
				 		app.$router.push("/");
				 	})
			     .catch(
				 	error => {
				 		alert(error.response.data.message) 
				 	}
			);
		}
	}
});