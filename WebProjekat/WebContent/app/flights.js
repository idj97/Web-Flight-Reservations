Vue.component("flights", {
  data: function() {
    return {
    	flights: []
    }
  },
  template:`
    <div>
	  <table border="1">
            <tr>
                <th> Id </th>
                <th> Start </th>
                <th> End </th>
                <th> Date </th>
                <th> Price </th>
                <th> Type </th>
                <th> Airplane </th>
                <th> Business </th>
                <th> First </th>
                <th> Economy </th>
            </tr>
            <tr v-for="f in flights">
                <td> {{ f.id }} </td>
                <td> {{ f.start.name }} </td>
                <td> {{ f.end.name }} </td>
                <td> {{ f.date }} </td>
                <td> {{ f.price }} </td>
                <td> {{ f.type }} </td>
                <td> {{ f.airplane }} </td>
                <td> {{ f.business }} </td>
                <td> {{ f.first }} </td>
                <td> {{ f.economy }} </td>
                <td> <a href="#" v-on:click="editFlight(f)"> Edit </a> </td>
                <td> <a href="#" v-on:click="deleteFlight(f)"> Delete </a> </td>
            </tr>
        </table>
    </div>
  `,
  methods: {
	  createFlight: function() {
		  // json copy
	  },
	  
	  editFlight: function(flight) {
		  // json copy
	  },
	  
	  
	  deleteFlight: function(flight) {
		  // json copy
	  }
  },
  mounted: function() {

  }
});
