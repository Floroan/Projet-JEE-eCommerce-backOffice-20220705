package tools;

public class ChartsGenerator {

	private String render;
	
	public String pie(String titres, String count, String id) {
	
		render =

				 "<script src=\"https://cdn.jsdelivr.net/npm/apexcharts\"></script>  \r\n"
				
				+ "              <div id=\"chart9\">\r\n"
				+ "          \r\n"
				+ "				</div>\r\n"
				+ "<script>\r\n"
				+ "\r\n"
				+ "var options = {\r\n"
				+ "        series: [ " + count + "],\r\n"
				+ "        chart: {\r\n"
				+ "        width: 380,\r\n"
				+ "        type: 'pie',\r\n"
				+ "      },\r\n"
				+ "      labels: [ " + titres + "],\r\n"
				+ "      responsive: [{\r\n"
				+ "        breakpoint: 480,\r\n"
				+ "        options: {\r\n"
				+ "          chart: {\r\n"
				+ "            width: 200\r\n"
				+ "          },\r\n"
				+ "          legend: {\r\n"
				+ "            position: 'bottom'\r\n"
				+ "          }\r\n"
				+ "        }\r\n"
				+ "      }]\r\n"
				+ "      };\r\n"
				+ "\r\n"
				+ "      var chart = new ApexCharts(document.querySelector(\"#chart9\"), options);\r\n"
				+ "      chart.render()\r\n"
				+ "        \r\n"
				+ "        </script>";
		return render;
	}
	
//	public String bubbleChart(String titres, String count, String id) {
//		
//		render = "  <script src=\"https://cdn.jsdelivr.net/npm/apexcharts\"></script>  \r\n"
//				
//				+ "         var options = {\r\n"
//				+ "          series: [{\r\n"
//				+ "          name: 'Bubble1',\r\n"
//				+ "          data: generateData(new Date('11 Feb 2017 GMT').getTime(), 20, {\r\n"
//				+ "            min: 10,\r\n"
//				+ "            max: 60\r\n"
//				+ "          })\r\n"
//				+ "        },\r\n"
//				+ "        {\r\n"
//				+ "          name: 'Bubble2',\r\n"
//				+ "          data: generateData(new Date('11 Feb 2017 GMT').getTime(), 20, {\r\n"
//				+ "            min: 10,\r\n"
//				+ "            max: 60\r\n"
//				+ "          })\r\n"
//				+ "        },\r\n"
//				+ "        {\r\n"
//				+ "          name: 'Bubble3',\r\n"
//				+ "          data: generateData(new Date('11 Feb 2017 GMT').getTime(), 20, {\r\n"
//				+ "            min: 10,\r\n"
//				+ "            max: 60\r\n"
//				+ "          })\r\n"
//				+ "        },\r\n"
//				+ "        {\r\n"
//				+ "          name: 'Bubble4',\r\n"
//				+ "          data: generateData(new Date('11 Feb 2017 GMT').getTime(), 20, {\r\n"
//				+ "            min: 10,\r\n"
//				+ "            max: 60\r\n"
//				+ "          })\r\n"
//				+ "        }],\r\n"
//				+ "          chart: {\r\n"
//				+ "            height: 350,\r\n"
//				+ "            type: 'bubble',\r\n"
//				+ "        },\r\n"
//				+ "        dataLabels: {\r\n"
//				+ "            enabled: false\r\n"
//				+ "        },\r\n"
//				+ "        fill: {\r\n"
//				+ "            opacity: 0.8\r\n"
//				+ "        },\r\n"
//				+ "        title: {\r\n"
//				+ "            text: 'Simple Bubble Chart'\r\n"
//				+ "        },\r\n"
//				+ "        xaxis: {\r\n"
//				+ "            tickAmount: 12,\r\n"
//				+ "            type: 'category',\r\n"
//				+ "        },\r\n"
//				+ "        yaxis: {\r\n"
//				+ "            max: 70\r\n"
//				+ "        }\r\n"
//				+ "        };\r\n"
//				+ "\r\n"
//				+ "        var chart = new ApexCharts(document.querySelector(\"#chart\"), options);\r\n"
//				+ "        chart.render();";
//
//		return render;
//	}
	
}



//<style type="text/css">
//body {
//font-family: Roboto, sans-serif;
//	}
//
//#chart {
//max-width: 800px;
//margin: 20px auto;
//}
//
//#chart9 {
//max-width: 800px;
//margin: 20px auto;
//}
//</style>




//<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>  
//<div id="chart">
//
//	</div>
//<script>
//var options = {
//  series: [${char_sscats_nbr}],
//  chart: {
//  width: 380,
//  type: 'pie',
//},
//labels: [${char_sscats_titre}],
//responsive: [{
//  breakpoint: 480,
//  options: {
//    chart: {
//      width: 200
//    },
//    legend: {
//      position: 'bottom'
//    }
//  }
//}]
//};
//
//var chart = new ApexCharts(document.querySelector("#chart"), options);
//chart.render()
//</script>



// ce script est le donut original à tester car malfunction
//<script>
//var chart = new Chart(document.getElementById('chart61'), {
//type: 'doughnut',
//data: {
//labels: [${topVisites_produits}],
//datasets: [{
//label: "Device Users",
//backgroundColor: ["#12bf24", "#3461ff", "#ff6632"],
//data: [${topVisites_values}]
//}]
//},
//options: {
//maintainAspectRatio: false,
//cutoutPercentage: 85,
//responsive: true,
//legend: {
//display: false
//}
//}
//});
//</script>
