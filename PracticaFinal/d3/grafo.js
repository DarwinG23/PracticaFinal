var nodes = new vis.DataSet([
{id: 1, label: "TRANSELECTRIC"},
{id: 2, label: "OBRAPIA EERSSA"},
{id: 3, label: "SAN CAYETANO EERSSA"},
{id: 4, label: "Empresa Electrica"},
{id: 5, label: "GENSUR Villonaco"},
{id: 6, label: "NORTE EERSSA"},
{id: 7, label: "YANACOCHA CELEC EP"},
{id: 8, label: "EERSSA"},
{id: 9, label: " Cumbaratza"},
{id: 10, label: "SUR EERSSA"},
{id: 11, label: "Eléctrica - Yarimala"},
{id: 12, label: "ESTACION 12"},
{id: 13, label: "ESTACION 13"},
{id: 14, label: "ESTACION 14"},
{id: 15, label: "ESTACION 15"},
{id: 16, label: "ESTACION 16"},
{id: 17, label: "ESTACION 17"},
{id: 18, label: "ESTACION 18"},
{id: 19, label: "ESTACION 19"},
{id: 20, label: "ESTACION 20"},
{id: 21, label: "ESTACION 21"},
{id: 22, label: "ESTACION 22"},
{id: 23, label: "ESTACION 23"},
{id: 24, label: "ESTACION 24"},
{id: 25, label: "ESTACION 25"},
{id: 26, label: "ESTACION 26"},
{id: 27, label: "ESTACION 27"},
{id: 28, label: "ESTACION 28"},
{id: 29, label: "ESTACION 29"},
{id: 30, label: "ESTACION 30"},
]);

var edges = new vis.DataSet([
{from: 1, to: 9, label: "40.09"},
{from: 1, to: 28, label: "4.98"},
{from: 1, to: 10, label: "3.45"},
{from: 1, to: 30, label: "6.95"},
{from: 1, to: 17, label: "2.6"},
{from: 2, to: 21, label: "4.15"},
{from: 2, to: 23, label: "4.05"},
{from: 2, to: 18, label: "5.31"},
{from: 2, to: 17, label: "3.34"},
{from: 3, to: 22, label: "5.77"},
{from: 3, to: 20, label: "6.27"},
{from: 3, to: 26, label: "4.51"},
{from: 4, to: 27, label: "8.08"},
{from: 4, to: 26, label: "4.61"},
{from: 4, to: 28, label: "4.74"},
{from: 5, to: 12, label: "3.37"},
{from: 5, to: 19, label: "1.53"},
{from: 6, to: 23, label: "1.75"},
{from: 6, to: 13, label: "6.17"},
{from: 6, to: 21, label: "8.9"},
{from: 7, to: 29, label: "1.07"},
{from: 7, to: 10, label: "9.03"},
{from: 7, to: 19, label: "6.79"},
{from: 8, to: 23, label: "14.79"},
{from: 8, to: 25, label: "19.24"},
{from: 9, to: 1, label: "40.09"},
{from: 9, to: 28, label: "38.99"},
{from: 9, to: 14, label: "37.05"},
{from: 10, to: 7, label: "9.03"},
{from: 10, to: 18, label: "4.02"},
{from: 10, to: 1, label: "3.45"},
{from: 11, to: 25, label: "38.14"},
{from: 11, to: 30, label: "37.16"},
{from: 12, to: 14, label: "3.81"},
{from: 12, to: 5, label: "3.37"},
{from: 12, to: 16, label: "4.39"},
{from: 13, to: 24, label: "6.78"},
{from: 13, to: 6, label: "6.17"},
{from: 13, to: 18, label: "6.04"},
{from: 14, to: 12, label: "3.81"},
{from: 14, to: 9, label: "37.05"},
{from: 15, to: 20, label: "5.24"},
{from: 15, to: 27, label: "4.65"},
{from: 15, to: 16, label: "4.88"},
{from: 16, to: 15, label: "4.88"},
{from: 16, to: 12, label: "4.39"},
{from: 17, to: 1, label: "2.6"},
{from: 17, to: 27, label: "6.54"},
{from: 17, to: 2, label: "3.34"},
{from: 18, to: 10, label: "4.02"},
{from: 18, to: 2, label: "5.31"},
{from: 18, to: 13, label: "6.04"},
{from: 19, to: 7, label: "6.79"},
{from: 19, to: 5, label: "1.53"},
{from: 20, to: 15, label: "5.24"},
{from: 20, to: 3, label: "6.27"},
{from: 21, to: 2, label: "4.15"},
{from: 21, to: 6, label: "8.9"},
{from: 22, to: 29, label: "6.69"},
{from: 22, to: 3, label: "5.77"},
{from: 23, to: 2, label: "4.05"},
{from: 23, to: 8, label: "14.79"},
{from: 23, to: 6, label: "1.75"},
{from: 24, to: 26, label: "12.25"},
{from: 24, to: 30, label: "5.18"},
{from: 24, to: 13, label: "6.78"},
{from: 25, to: 11, label: "38.14"},
{from: 25, to: 8, label: "19.24"},
{from: 26, to: 24, label: "12.25"},
{from: 26, to: 3, label: "4.51"},
{from: 26, to: 4, label: "4.61"},
{from: 27, to: 15, label: "4.65"},
{from: 27, to: 4, label: "8.08"},
{from: 27, to: 17, label: "6.54"},
{from: 28, to: 1, label: "4.98"},
{from: 28, to: 9, label: "38.99"},
{from: 28, to: 4, label: "4.74"},
{from: 29, to: 7, label: "1.07"},
{from: 29, to: 22, label: "6.69"},
{from: 30, to: 24, label: "5.18"},
{from: 30, to: 1, label: "6.95"},
{from: 30, to: 11, label: "37.16"},
]);

var container = document.getElementById("mynetwork");
      var data = {
        nodes: nodes,
        edges: edges,
      };
      var options = {};
      var network = new vis.Network(container, data, options);