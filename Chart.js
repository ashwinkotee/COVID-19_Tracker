var ctxL = document.getElementById("lineChart").getContext('2d');
var myLineChart = new Chart(ctxL, {
type: 'line',
data: {
labels: ["February", "March", "April", "May", "June", "July", "August", "September", "October"],
datasets: [{
label: "Active",
data: [1012, 5590, 5000, 4810, 4360, 4131, 5030, 6300, 7326],
backgroundColor: [
'rgba(105, 0, 132, .2)',
],
borderColor: [
'rgba(200, 99, 132, .7)',
],
borderWidth: 2
},
{
label: "Confirmed",
data: [1012, 6602, 11602, 16412, 20543, 25573, 31,873, 39199],
backgroundColor: [
'rgba(0, 137, 132, .2)',
],
borderColor: [
'rgba(0, 10, 130, .7)',
],
borderWidth: 2
}
]
},
options: {
responsive: true
}
});

var ctxL = document.getElementById("lineChart2").getContext('2d');
var myLineChart = new Chart(ctxL, {
type: 'line',
data: {
labels: ["February", "March", "April", "May", "June", "July", "August", "September", "October"],
datasets: [{
label: "Recovered",
data: [165, 1059, 1280, 1381, 1226, 2255, 1240, 1230],
backgroundColor: [
'rgba(105, 0, 132, .2)',
],
borderColor: [
'rgba(200, 99, 132, .7)',
],
borderWidth: 2
},
{
label: "Deceased",
data: [128, 148, 140, 119, 186, 87, 73, 221, 300],
backgroundColor: [
'rgba(0, 137, 132, .2)',
],
borderColor: [
'rgba(0, 10, 130, .7)',
],
borderWidth: 2
}
]
},
options: {
responsive: true
}
});

