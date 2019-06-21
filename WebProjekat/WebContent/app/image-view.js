Vue.component("image-view", {
    data: function() {
    	return {
    		src: ""
    	};
    },
	template:
    `
    <div>
        <div id="img-modal" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title">Destination location</h5>
                </div>
                <div class="modal-body">
                  <img id="mapa" v-bind:src="src"/>
                </div>
            </div>
          </div>
        </div>
    </div>
    `,
    methods: {
        render: function(path) {
        	this.src = path;
            $("#img-modal").modal("toggle");
        }
    }
});
