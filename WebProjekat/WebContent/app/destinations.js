Vue.component("destinations", {
  data: function() {
    return {
        destinations: []
    }
  },
  template:`
    <div>
        <table border="1">
            <tr>
                <th> Name </th>
                <th> State </th>
                <th> Airport name </th>
                <th> Airport code </th>
                <th> Archived </th>
                <th> Map </th>
                <th> Picture </th>
                <th> Edit </th>
            </tr>
            <tr v-for="d in destinations">
                <td> {{ d.name }} </td>
                <td> {{ d.state }} </td>
                <td> {{ d.airportName }} </td>
                <td> {{ d.airportCode }} </td>
                <td> {{ d.archived }} </td>
                <td> <a href="#" v-on:click="showMap(d.lat, d.log)"> Show </a> </td>
                <td> <a href="#"> Show </a> </td>
                <td> <a href="#"> Edit </a> </td>
            </tr>
        </table>

        <google-map ref="googleMap"></google-map>

        <a href="#create-dest" data-toggle="modal" data-target="#create-dest">Create destination</a>

        <div class="modal fade" id="create-dest" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Create destination</h5>
              </div>
              <div class="modal-body">
                <form id="create-destination-form">
                    <table>
                        <tr> <td> Name: </td> <td> <input type="text" name="name"/> </td> </tr>
                        <tr> <td> State: </td> <td> <input type="text" name="state"/> </td> </tr>
                        <tr> <td> Airport name: </td> <td> <input type="text" name="airportName"/> </td> </tr>
                        <tr> <td> Airport state: </td> <td> <input type="text" name="airportCode"/> </td> </tr>
                        <tr> <td> Lat: </td> <td> <input type="text" name="lat"/> </td> </tr>
                        <tr> <td> Log: </td> <td> <input type="text" name="log"/> </td> </tr>
                        <tr> <td> Image: </td> <td> <input type="file" name="image"/> </td> </tr>
                    </table>
                </form>
              </div>
              <div class="modal-footer">
                <button v-on:click="createDestination" type="button" class="btn btn-primary">Create</button>
                <button id="close-create-modal" type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              </div>
            </div>
          </div>
        </div>

    </div>
  `,
  methods: {
      createDestination: function() {
          var form = document.getElementById("create-destination-form");
          var formData = new FormData(form);
          if (this.validateForm(formData)) {
              axios.post("/WebProjekat/api/destinations", formData, {headers:{"Content-Type":"multipart/form-data"}})
                   .then(response => {
                       toastr.success("Destination added.")
                       this.destinations.push(response.data);
                       document.getElementById("close-create-modal").click();
                   })
                   .catch(response => {
                       toastr.error("Something is wrong with your request.");
                       console.log(response);
                   })
          } else {
              toastr.warning('Please enter all fields!');
          }
      },


      editDestination: function() {
      },


      validateForm: function(form) {
          for (var pair of form.entries())
              if (!pair[1])
                  return false;
          return true;
      },


      showMap: function(lat, log) {
          this.$refs.googleMap.render(parseFloat(lat), parseFloat(log));
      }
  },
  mounted: function() {
      axios.get("/WebProjekat/api/destinations", {headers: {"responseType":"json"}})
           .then(response => {
               this.destinations = response.data;
           })
           .catch(response => {
               toastr.error("Something went wrong.");
           })
  }
});
