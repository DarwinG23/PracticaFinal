var osmUrl = 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',
                    osmAttrib = '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
                    osm = L.tileLayer(osmUrl, {maxZoom: 15, attribution: osmAttrib});

            var map = L.map('map').setView([-4.036, -79.201], 15);

            L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(map);
L.marker([-3.993902334840428, -79.22118626204346]).addTo(map)
.bindPopup("TRANSELECTRIC")
.openPopup();
L.marker([-3.9920185709083276, -79.21380491206483]).addTo(map)
.bindPopup("OBRAPIA EERSSA")
.openPopup();
L.marker([-3.990779532919959, -79.19663825199216]).addTo(map)
.bindPopup("SAN CAYETANO EERSSA")
.openPopup();
L.marker([-3.9901533491877177, -79.19728718273937]).addTo(map)
.bindPopup("Empresa Electrica")
.openPopup();
L.marker([-3.996133703859583, -79.25064822718181]).addTo(map)
.bindPopup("GENSUR Villonaco")
.openPopup();
L.marker([-3.945090699898572, -79.23456529078376]).addTo(map)
.bindPopup("NORTE EERSSA")
.openPopup();
L.marker([-3.9461993324587796, -79.19915970463086]).addTo(map)
.bindPopup("YANACOCHA CELEC EP")
.openPopup();
L.marker([-3.9860556721108766, -79.35470514183324]).addTo(map)
.bindPopup("EERSSA")
.openPopup();
L.marker([-3.9673059119257377, -78.86112920780748]).addTo(map)
.bindPopup(" Cumbaratza")
.openPopup();
L.marker([-4.02480052193157, -79.21942157619178]).addTo(map)
.bindPopup("SUR EERSSA")
.openPopup();
L.marker([-3.6417448267267334, -79.24540469904628]).addTo(map)
.bindPopup("Eléctrica - Yarimala")
.openPopup();
