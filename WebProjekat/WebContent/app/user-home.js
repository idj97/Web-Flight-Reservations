Vue.component(
  "user-home",
  {
    data: function() {
      return {currentComponent: "flights-user"}
    },
    template: `
      <div>

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <a class="navbar-brand" href="#">User</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
              
              <li class="nav-item">
                <a class="nav-link" href="#" v-on:click="setCurrentComponent('flights-user')">Flights</a>
              </li>
              
              <li class="nav-item">
                <a class="nav-link" href="#" v-on:click="setCurrentComponent('my-profile')">My profile</a>
              </li>
              
              <li class="nav-item">
                <a class="nav-link" href="#" v-on:click="setCurrentComponent('my-reservation')">My reservations</a>
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
