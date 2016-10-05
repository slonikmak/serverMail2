function initLeafMap() {
    var map = L.map('map').setView([51.505, -0.09], 13);

    // Tiles/{z}/{x}/{y}.png  http://{s}.tile.osm.org/{z}/{x}/{y}.png
    L.tileLayer('Tiles/{z}/{x}/{y}.png ', {
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

    jQuery.getJSON(window.location.origin+"/"+"sessions", null,function (data) {
        console.log("Loading data");
        console.log(data);
        setSessionList(data);


    });




    function setSessionList(data) {
        for (var i=0; i<data.length; i++){
            var a = $("<div class='sessionItem'></div>")
                .html("<h3>"+data[i].name+"</h3><span>type: </span>"+data[i].type +
                "<span> date: </span>"+data[i].date+"<span> time:</span> "+millisToTime(data[i].time))
        .attr("data-link", window.location.origin+"/"+
                "records?sessionid="+data[i].id + "&type=GPS");

            $("#sessions").append(a);
            console.log(data[i]);
        }
    }

    $("#sessions").click(function (e) {
        console.log(e);

        var link = $(e.target).attr("data-link");
        if (link == undefined){
            link = $(e.target).parent().attr("data-link");
        }
        jQuery.getJSON( link, function( data ) {
            //L.geoJson(data).addTo(map);

            var layer = L.geoJson(data, {
                onEachFeature: function(feature, layer) {
                    layer.on({
                        click: onClick
                    });
                }
            }).addTo(map)

            function onClick(e) {
                map.removeLayer(layer);
            }

        });
        console.log(link);

    });


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