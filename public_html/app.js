function initLeafMap() {
    var map = L.map('map').setView([51.505, -0.09], 13);

    // Tiles/{z}/{x}/{y}.png  http://{s}.tile.osm.org/{z}/{x}/{y}.png
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
        setSessionList(data);


    });


    jQuery.getJSON( "newData.json", function( data ) {
        L.geoJson(data).addTo(map);
    });

    function setSessionList(data) {
        for (var i=0; i<data.length; i++){
            var a = $("<a href=''></a>").html(data[i].name+"&nbsp;&nbsp;&nbsp;&nbsp;"+data[i].type +
                " "+data[i].date+" "+millisToTime(data[i].time)).attr("href", window.location.origin+"/"+"records?sessionid="+data[i].id);
            var div = $("<div class='sessionItem'></div>").append(a);
            $("#sessions").append(div);
            console.log(data[i]);
        }
    }

    function millisToTime(s) {
        var ms = s % 1000;
        s = (s - ms) / 1000;
        var secs = s % 60;
        s = (s - secs) / 60;
        var mins = s % 60;
        var hrs = (s - mins) / 60;

        return hrs + ':' + mins + ':' + secs + '.' + ms;
    }





}


$(function(){

    //initMap();
    //init2();
    initLeafMap();
})