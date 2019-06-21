Vue.component("logout", {
  template:`
    <div>
      <li class="nav-item">
        <a class="nav-link" href="#" v-on:click="logout()">Logout</a>
      </li>
    </div>
  `,
  methods: {
    logout: function() {
    	axios.post("/WebProjekat/api/auth/logout")
             .then(response => {
     	         toastr.info("Logged out successfully.");
     	         localStorage.clear();
     			 app.$router.push("/login");
             })
             .catch(response => {
            	 toastr.error("Something went wrong.");
            	 localStorage.clear();
         		 app.$router.push("/login");
             });
    	}
    }
});
