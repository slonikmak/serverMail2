function initLeafMap() {

    var recordsCount = 0;

    var jsonData;


    var map = L.map('map').setView([51.505, -0.09], 13);

    // Tiles/{z}/{x}/{y}.png  http://{s}.tile.osm.org/{z}/{x}/{y}.png
    L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
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

    jQuery.getJSON(window.location.origin + "/" + "sessions", null, function (data) {
        console.log("Loading data");
        console.log(data);
        setSessionList(data);


    });


    function setSessionList(data) {
        for (var i = 0; i < data.length; i++) {
            var a = $("<div class='sessionItem'></div>")
                .html("<h3>" + data[i].name + "</h3><span>type: </span>" + data[i].type +
                    "<span> date: </span>" + data[i].date + "<span> time:</span> " + millisToTime(data[i].time))
                .attr("data-link", window.location.origin + "/" +
                    "records?sessionid=" + data[i].id + "&type=GPS");

            $("#sessions").append(a);
            console.log(data[i]);
        }
    }

    $("#sessions").click(function (e) {

        var link = $(e.target).attr("data-link");
        if (link == undefined) {
            link = $(e.target).parent().attr("data-link");
        }
        jQuery.getJSON(link + "&gps_type=full", function (data) {
            var layer;
            var count = 0;
            //L.geoJson(data).addTo(map);

            recordsCount = data.features[0].geometry.coordinates.length;
            jsonData = data.features[0].geometry.coordinates;

            var slider = $('#slider').slider({
                max: recordsCount,
                min: 0,
                value: 0,
                create: function (e, ui) {
                    handle.text($(this).slider("value"));

                },
                slide: function (event, ui) {
                    count = ui.value;
                    handle.text(ui.value);
                    console.log("ssdcds");
                    changeGsonLayer();



                }
            });

            function changeGsonLayer() {
                data.features[0].geometry.coordinates = jsonData.slice(0, count);
                map.removeLayer(layer);
                layer = L.geoJSON(data, {
                    onEachFeature: function (feature, layer) {
                        layer.on({
                            click: onClick
                        });
                    }
                }).addTo(map)
            }

            $("#forward").click(function () {
                count = 1+slider.slider("value");

                slider.slider("value", count);
                handle.text(slider.slider("value"));
                changeGsonLayer();

            });
            $("#back").click(function () {
                if ((0+slider.slider("value")) == 0) return;
                count = -1+slider.slider("value");

                slider.slider("value", count);
                handle.text(slider.slider("value"));
                changeGsonLayer();
            });

            console.log(recordsCount);

            console.log(data);

            layer = L.geoJson(data, {
                onEachFeature: function (feature, layer) {
                    layer.on({
                        click: onClick
                    });
                }
            }).addTo(map)

            function onClick(e) {
                map.removeLayer(layer);
            }

        });
        jQuery.getJSON(link + "&gps_type=self", function (data) {
            //L.geoJson(data).addTo(map);


            console.log(data);

            var layer = L.geoJson(data, {
                onEachFeature: function (feature, layer) {
                    layer.on({
                        click: onClick
                    });
                },
                style: function (feature) {
                    return {color: "red"};
                }
            }).addTo(map)

            function onClick(e) {
                map.removeLayer(layer);
            }

        });
        console.log(link);

    });

    var handle = $("#custom-handle");


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


$(function () {

    //initMap();
    //init2();
    initLeafMap();
})