Vue.component("google-map",{
    template:
    `
    <div>
        <div id="map-modal" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
          <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title">Destination location</h5>
                </div>
                <div class="modal-body">
                  <div id="google-map"> </div>
                </div>
            </div>
          </div>
        </div>
    </div>
    `,
    methods: {
        render: function(lat, log) {
            var myLatLng = {lat: lat, lng: log};
            var map = new google.maps.Map(document.getElementById('google-map'), {
                zoom: 4,
                center: myLatLng
            });

            var marker = new google.maps.Marker({
                position: myLatLng,
                map: map,
                title: 'Destination location.'
            });
            $("#map-modal").modal("toggle");
        }
    }
});
