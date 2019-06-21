Vue.component("users", {
  data: function() {
    return {
    	users: []
    }
  },
  template:`
    <div>
      <table border="1">
            <tr>
                <th> Name </th>
                <th> Surname </th>
                <th> Phone </th>
                <th> Email </th>
                <th> Blocked </th>
            </tr>
            <tr v-for="u in users">
                <td> {{ u.name }} </td>
                <td> {{ u.surname }} </td>
                <td> {{ u.phone }} </td>
                <td> {{ u.email }} </td>
                <td> <input v-model:value="u.blocked" type="checkbox" name="blocked"/> </td>
            </tr>
        </table>
    </div>
  `,
  methods: {
	  
	  getUsers: function() {
		  toastr.error("start");
		  axios.get("/WebProjekat/api/users")
		       .then(response => {
		    	   this.users = response.data;
		       })
		       .catch(response => {
		    	   toastr.error("Something is wrong with with your request. (get users)");
		       });
	  }
	  
  },
  mounted: function() {
	  this.getUsers();
  }
});
