function initLeafMap() {
    var map = L.map('map').setView([51.505, -0.09], 13);

    // http://localhost/Tiles/{z}/{x}/{y}.png   http://{s}.tile.osm.org/{z}/{x}/{y}.png
    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    map.locate({setView: true, maxZoom: 15});

    map.on('locationfound', onLocationFound);

    function onLocationFound(e) {
        // create a marker at the users "latlng" and add it to the map
        L.marker(e.latlng).addTo(map);
    }

    L.marker([51.5, -0.09]).addTo(map)
        .bindPopup('A pretty CSS3 popup.<br> Easily customizable.')
        .openPopup();

    jQuery.getJSON("http://localhost:8080/sessions", null,function (data) {
        console.log("Loading data");
        console.log(data);
    });
    $.ajax({
        dataType:"text",
        url: "http://localhost:8080/sessions",
        success: function(data){
            console.log( jQuery.parseJSON(data));
        }
    });


    jQuery.getJSON( "newData.json", function( data ) {
        L.geoJson(data).addTo(map);
    });





}


$(function(){

    //initMap();
    //init2();
    initLeafMap();
});