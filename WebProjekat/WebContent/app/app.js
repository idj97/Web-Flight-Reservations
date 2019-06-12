const LoginComponent = { template: "<login-component></login-component>" }
const RegisterComponent = { template: "<register-component></register-component>"}

const router = new VueRouter({
	mode: "hash",
	routes: [
		{ path: "/", component: LoginComponent },
		{ path: "/register", component: RegisterComponent }
	]
});

var app = new Vue({
	router,
	el: "#app"
});