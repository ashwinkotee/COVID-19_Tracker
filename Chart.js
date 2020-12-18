/* chart.js chart examples */

// chart colors

function show_chart(chart_values_confirmed,chart_values_death,chart_values_recovered){

var colors = ['#007bff','#28a745','#333333','#c3e6cb','#dc3545','#6c757d'];

/* large line chart */
var chLine = document.getElementById("chLine");
var chartData = {
  labels: ["8 Dec","9 Dec", "10 Dec", "11 Dec", "12 Dec", "13 Dec", "14 Dec", "15 Dec", "16 Dec", "17 Dec"],
  datasets: [{
    data:chart_values_confirmed,
    backgroundColor: 'transparent',
    borderColor: colors[0],
    borderWidth: 4,
    pointBackgroundColor: colors[0]
  },
  {
    data: chart_values_death,
    backgroundColor: 'transparent',
    borderColor: colors[1],
    borderWidth: 4,
    pointBackgroundColor: colors[1]
  },
  {
      data: chart_values_recovered,
      backgroundColor: 'transparent',
      borderColor: colors[2],
      borderWidth: 4,
      pointBackgroundColor: colors[2]
  }
  ]
};
if (chLine) {
  new Chart(chLine, {
  type: 'line',
  data: chartData,
  options: {
    scales: {
      xAxes: [{
        ticks: {
          beginAtZero: false
        }
      }]
    },
    legend: {
      display: false
    },
    responsive: true
  }
  });
}

}
