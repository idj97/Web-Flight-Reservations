const LoginComponent = { template: "<login></login>" }
const RegisterComponent = { template: "<register></register>"}
const UserHomeComponent = { template: "<user-home></user-home>"}
const AdminHomeComponent = { template: "<admin-home></admin-home>"}

const router = new VueRouter({
	mode: "hash",
	routes: [
		{ path: "/login", component: LoginComponent },
		{ path: "/register", component: RegisterComponent },
		{ path: "/user", component: UserHomeComponent },
		{ path: "/admin", component: AdminHomeComponent}
	]
});

router.beforeEach((to, from, next) => {
	if (!(to.path == "/login" || to.path == "/register")) {
		var user = localStorage.getItem("user");
		if (user == null)
			next("/login");
		else
			next();
	} else
		next();
});

axios.interceptors.request.use(function (config) {
    var user = localStorage.getItem("user");
	if (user != null) {
		user = JSON.parse(user);
		config.headers["Authorization"] = "Bearer " + user.token;
	}
	return config;
});

var app = new Vue({
	router,
	el: "#app"
});
