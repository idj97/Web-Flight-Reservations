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
      axios.post("/WebProjekat/api/auth/logout", {
        headers: {
          "Authorization" : "Bearer " + JSON.parse(localStorage.getItem("user")).token 
        }
      }).then(response => {
        localStorage.clear();
        app.$router.push("/login");
      });
    }
  }
});
