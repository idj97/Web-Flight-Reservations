Vue.component(
  "admin-home",
  {
    data: function() {
      return {
        currentComponent: "destinations"
      }
    },
    template: `
      <div>

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <a class="navbar-brand" href="#">Admin</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
              <li class="nav-item">
                <a class="nav-link" href="#" v-on:click="setCurrentComponent('destinations')">Destinations</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#" v-on:click="setCurrentComponent('flights')">Flights</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#" v-on:click="setCurrentComponent('users')">Users</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="#" v-on:click="setCurrentComponent('my-profile')">My profile</a>
              </li>
              <logout></logout>
            </ul>
          </div>
          </nav>

          <component v-bind:is="currentComponent"></component>
      </div>
    `,
    methods: {
      setCurrentComponent: function(componentName) {
        this.currentComponent = componentName;
      }
    },
    mounted: function() {

    }
  }
);
