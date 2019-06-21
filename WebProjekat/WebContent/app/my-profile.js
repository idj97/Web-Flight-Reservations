Vue.component("my-profile", {
  data: function() {
    return {
    	user: {
        	name: "",
        	surname: "",
        	phone: "",
        	email: ""
        }
    }
  },
  template:`
    <div class="myProfileDiv">
      <label for="firstname"><b>Firstname :</b></label>
	  <input type="text" class="form-control" id="name">
			
	  <label for="surname"><b>Surname :</b></label>
	  <input type="text" class="form-control" id="surname">
	  
	  <label for="phone"><b>Phone :</b></label>
	  <input type="text" class="form-control" id="phone">
			
	  <label for="email"><b>Email :</b></label>
	  <input type="text" class="form-control" id="email">
	  <br>
	  <button class="btn btn-primary"  onclick="">Save</button>
    </div>
  `,
  methods: {
	  
	  getLoggedUser: function() {
		  axios.get("/WebProjekat/api/auth/detail", {headers: {"responseType":"json"}})
          .then(response => {
              this.user = response.data;
          })
          .catch(response => {
              toastr.error("Something went wrong.");
          });
	  }

  },
  mounted: function() {
	  lUser = JSON.parse(localStorage.getItem("user"));
	  $("#name").val(lUser.name);
	  $("#surname").val(lUser.surname);
	  $("#phone").val(lUser.phone);
	  $("#email").val(lUser.email);

  }
});
