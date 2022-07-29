<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Utilisateur"%>
<%@ page import="model.Adresse_livraison"%>
<%@ page import="tools.DataTablesListeClients"%>
<%@ page import="tools.DateManipulator"%>
<%
Utilisateur client = (Utilisateur) request.getAttribute("ub");
DataTablesListeClients dc = (DataTablesListeClients) request.getAttribute("dc");
ArrayList<Adresse_livraison> abCol = (ArrayList) request.getAttribute("abCol");
%>
<!doctype html>
<html lang="fr">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" href="assets/images/favicon-32x32.png" type="image/png" />
  <!--plugins-->
  <link href="assets/plugins/simplebar/css/simplebar.css" rel="stylesheet" />
  <link href="assets/plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet" />
  <link href="assets/plugins/metismenu/css/metisMenu.min.css" rel="stylesheet" />
  <link href="assets/plugins/datatable/css/dataTables.bootstrap5.min.css" rel="stylesheet" />
  <!-- Bootstrap CSS -->
  <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
  <link href="assets/css/bootstrap-extended.css" rel="stylesheet" />
  <link href="assets/css/style.css" rel="stylesheet" />
  <link href="assets/css/icons.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

  <!-- loader-->
	<link href="assets/css/pace.min.css" rel="stylesheet" />


  <!--Theme Styles-->
  <link href="assets/css/dark-theme.css" rel="stylesheet" />
  <link href="assets/css/light-theme.css" rel="stylesheet" />
  <link href="assets/css/semi-dark.css" rel="stylesheet" />
  <link href="assets/css/header-colors.css" rel="stylesheet" />
  
  <!-- FONTAWESOME -->
  <script src="https://kit.fontawesome.com/bff2375f4b.js" crossorigin="anonymous"></script>

  <title><%= client.getNom() %> <%= client.getPrenom() %></title>
</head>

<body>


  <!--start wrapper-->
  <div class="wrapper">
    
		<!--start top header-->
		<jsp:include page="/Header"></jsp:include>
       	<!--end top header-->

       	<!--start sidebar -->
       	<jsp:include page="/SideBar"></jsp:include>
       	<!--end sidebar -->


       <!--start content-->
		<main class="page-content">
			<h1 class="mb-0 text-uppercase text-center"><%= client.getNom() %> <%= client.getPrenom() %> </h6>
			<%
			String d = DateManipulator.dateConvertToDDmmYYYY( client.getDate_inscription() );
			%>
			<p class="text-center">inscrit(e) depuis le <%= d %></p>
			<hr/>
			
			
			
			
			

				
				
							
			<h6 class="mb-0 text-uppercase">Statistiques commandes</h6>
			<hr/>
			<div class="card">
				<div class="card-body">
					<!-- <div class="table-responsive"> -->
					<div class="table-responsive mt-3">
						<!-- <table class="table table-striped table-bordered" style="width:100%" > -->
						<table class="table align-middle mb-0">
							<!-- <thead> -->
							<thead class="table-light">
							<tr>
								<th class="text-center">Première commande</th>
								<th class="text-center">Dernière commande</th>
								<th class="text-center">Panier moyen</th>
								<th class="text-center">Panier total</th>
							</tr>
						</thead>
							<tbody>
							
                                <tr>
                                    <td class="text-center"><%= dc. getDateFirstOrder() %></td>
                                    <td class="text-center"><%= dc.getDateLastOrder() %></td>
                                    <td class="text-center"><%= dc.getOrderAverage() %>€</td>
                                    <td class="text-center"><%= dc.getOrderSum() %>€</td>
                                </tr>
                           
							</tbody>
							<!-- <tfoot>
								<tr>
									<th>Name</th>
									<th>Position</th>
									<th>Office</th>
									<th>Age</th>
									<th>Start date</th>
									<th>Salary</th>
								</tr>
							</tfoot> -->
						</table>
					</div>
				</div>
			</div>
			
			
			
			
			
			
			
			
			
			
			<div class="row">
				<div class="col-12 col-lg-6">
					<h6 class="mb-0 text-uppercase">Nombre de fiches produit vues par mois</h6>
					<a href="#">Détails</a>
					<hr/>
	                <div class="card">
	                  <div class="card-body">
	                    <!-- <div id="chart9"></div> -->
	                    <div id="chart5"></div>
	                  </div>
	                </div>
					<!-- <div class="card radius-10 w-100">
						<div class="card-body">
							<div class="d-flex align-items-center">
								<h6 class="mb-0">Total des cliques arcicle par mois</h6>
								<div class="fs-5 ms-auto dropdown">
									<div
										class="dropdown-toggle dropdown-toggle-nocaret cursor-pointer"
										data-bs-toggle="dropdown">
										<i class="bi bi-three-dots"></i>
									</div>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#">Voir en détails</a></li>
										<li><a class="dropdown-item" href="#">Another action</a></li>
                             <li><hr class="dropdown-divider"></li>
                             <li><a class="dropdown-item" href="#">Something else here</a></li>
									</ul>
								</div>
							</div>
							<div id="chart5"></div>
						</div>
					</div> -->
				</div>
				<div class="col-12 col-lg-6">
					<h6 class="mb-0 text-uppercase">Nombre de Clique(s) par article</h6>
					<a href="#">Détails</a> 
	                <hr/>
	                <div class="card">
	                  <div class="card-body">
	                    <!-- <div id="chart9"></div> -->
	                    <div id="chart9"></div>
	                  </div>
	                </div>
				</div>
			</div>
			<!--end row-->
			<div class="row row-cols-1 row-cols-lg-2 row-cols-xl-2 row-cols-xxl-4">
				<div class="col">
					<div class="card overflow-hidden radius-10">
						<div class="card-body">
							<div
								class="d-flex align-items-stretch justify-content-between overflow-hidden">
								<div class="w-50">
									<p>
										<a href="">Recherches</a>
									</p>
									<h4 class="">${totalResearches}</h4>
								</div>
								<div class="w-50">
									<!-- <p class="mb-3 float-end text-success">
										+ 16% <i class="bi bi-arrow-up"></i>
									</p> -->
									<div id="chart1"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card overflow-hidden radius-10">
						<div class="card-body">
							<div
								class="d-flex align-items-stretch justify-content-between overflow-hidden">
								<div class="w-50">
									<p>
										<a href="">Messages Envoyés</a>
									</p>
									<h4 class="">${totalMessages}</h4>
								</div>
								<div class="w-50">
									<!-- <p class="mb-3 float-end text-danger">
										- 3.4% <i class="bi bi-arrow-down"></i>
									</p> -->
									<div id="chart2"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card overflow-hidden radius-10">
						<div class="card-body">
							<div
								class="d-flex align-items-stretch justify-content-between overflow-hidden">
								<div class="w-50">
									<p>
										<a href="">Commentaires</a>
									</p>
									<h4 class="">${totalComments}</h4>
								</div>
								<div class="w-50">
									<!-- <p class="mb-3 float-end text-success">
										+ 24% <i class="bi bi-arrow-up"></i>
									</p> -->
									<div id="chart3"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col">
					<div class="card overflow-hidden radius-10">
						<div class="card-body">
							<div
								class="d-flex align-items-stretch justify-content-between overflow-hidden">
								<div class="w-50">
									<p>
										<a href="">Commandes</a>
									</p>
									<h4 class="">${totalCommandes}</h4>
								</div>
								<div class="w-50">
									<!-- <p class="mb-3 float-end text-success">
										+ 8.2% <i class="bi bi-arrow-up"></i>
									</p> -->
									<div id="chart4"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--end row-->

			<h6 class="mb-0 text-uppercase">Fiche client</h6>
			<hr/>
			<%
			if (request.getAttribute("msg") != null) {
			%>
			<div>
				<div class="alert alert-danger text-center" role="alert">
					<%=request.getAttribute("msg")%>
				</div>
			</div>
			<%
			}
			%>
			<div class="card">
				<div class="card-body">
					<form class="row g-5" method="post" action="UserClientCard" >
						<input type="hidden" name="id" value="<%= client.getId() %>" >
						
						<div class="col-md-3">
							<!-- <input type="text" class="form-control" placeholder="First name" aria-label="First name"> -->
							<input type="text" class="form-control" name="row-1-nom" value="<%= client.getNom() %>" >
						</div>
						<div class="col-md-3">
							<%-- <input type="text" name="row-1-prenom" value="<%= client.getPrenom() %>" name="<%= client.getPrenom() %>"> --%>
							<input type="text" class="form-control" name="row-1-prenom" value="<%= client.getPrenom() %>">
						</div>
						<div class="col-md-4">
							<div class="input-group">
								<div class="input-group-text">@</div>
						    	<!-- <input type="email" class="form-control" placeholder="email" aria-label="Last name"> -->
						    	<input type="email" class="form-control" name="row-1-mail" value="<%= client.getEmail() %>">
						    </div>
						</div>
							<div class="col-auto">
								<button class="btn btn-warning" type="submit" name="updateProfileForm">Mettre à jour</button>
							</div>
					</form>
				</div>
			</div>

			<div class="card">
				<div class="card-body">
					<div class="row g-5">
					<%
					if ( client.getArchiver() == 0 ) {
					%>
						<div class="col-md-10">
							<p>Le compte de ce client est actif. Voulez-vous l’archiver ?</p>
						</div>
						<div class="col-auto">
							<a href="UserClientCard?id=<%=client.getId() %>&archived=isNotArchived" class="btn btn-success px-5"><i class="fa-solid fa-file-circle-plus"></i></a>
						</div>
					<%
					} else {
					%>
						<div class="col-md-10">
							<p>Le compte de ce client est archivé. Voulez-vous le réactiver ?</p>
						</div>
						<div class="col-auto">
							<a href="UserClientCard?id=<%=client.getId() %>&archived=isArchived" class="btn btn-danger px-5"><i class="fa-solid fa-file-circle-minus"></i></a>
						</div>
					<%
					}
					%>				
					</div>
				</div>
			</div>			
			
			
			<h6 class="mb-0 text-uppercase">Adresses de livraison</h6>
			<hr/>
			<%
			if (request.getAttribute("responseAddAddressForm") != null) {
			%>
			<div>
				<div class="alert alert-danger text-center" role="alert">
					<%=request.getAttribute("responseAddAddressForm")%>
				</div>
			</div>
			<%
			}
			%>
			<div class="card">
				<div class="card-body">
					<form class="row g-6" method="post">
						<div class="col-md-4">
							<input type="text" name="address" class="form-control" placeholder="adresse" aria-label="First name">
						</div>
						<div class="col-md-1">
							<input type="text" name="cp" class="form-control" placeholder="cp" aria-label="Last name">
						</div>
						<div class="col-md-2">
							<input type="text" name="city" class="form-control" placeholder="ville" aria-label="Last name">
						</div>
						<div class="col-md-2">
							<input type="text" name="country" class="form-control" placeholder="pays" aria-label="Last name">
						</div>
						<div class="col-auto">
							<button type="submit" class="btn btn-primary" name="addAddressForm">Ajouter</button>
						</div>
					</form>
				</div>
			</div>
			
			<!-- boucle -->
		<%
		if (request.getAttribute("responseUpdateAddressForm") != null) {
		%>
			<div>
				<div class="alert alert-danger text-center" role="alert">
					<%=request.getAttribute("responseUpdateAddressForm")%>
				</div>
			</div>
		<%
		}
		%>
		<%
		for ( Adresse_livraison ab : abCol ) {
		%>
			<div class="card">
				<div class="card-body">
					<form class="row g-6" method="post">
						<input type="hidden" name="idAddress" value="<%= ab.getId() %>" >
						<div class="col-md-4">
							<input type="text" name="address" class="form-control" value="<%= ab.getAdresse() %>">
						</div>
						<div class="col-md-1">
							<input type="text" name="cp" class="form-control" value="<%= ab.getCp() %>">
						</div>
						<div class="col-md-2">
							<input type="text" name="city" class="form-control" value="<%= ab.getVille() %>">
						</div>
						<div class="col-md-2">
							<input type="text" name="country" class="form-control" value="<%= ab.getPays() %>">
						</div>
						<div class="col-auto">
							<button type="submit" class="btn btn-warning" name="updateAddressForm">Mettre à jour</button>
							<a href="UserClientCard?id=<%= client.getId() %>&idAddress=<%= ab.getId() %>&delete=ok" class="btn btn-danger" name="addAddressForm">Supprimer</a>
						</div>
					</form>
				</div>
			</div>
		<%
		}
		%>
			
			
			
			
			
					
		</main>
		<!--end page main-->


       <!--start overlay-->
        <div class="overlay nav-toggle-icon"></div>
       <!--end overlay-->

        <!--Start Back To Top Button-->
        <a href="javaScript:;" class="back-to-top"><i class='bx bxs-up-arrow-alt'></i></a>
        <!--End Back To Top Button-->
        
        <!--start switcher-->
       <div class="switcher-body">
        <button class="btn btn-primary btn-switcher shadow-sm" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasScrolling" aria-controls="offcanvasScrolling"><i class="bi bi-paint-bucket me-0"></i></button>
        <div class="offcanvas offcanvas-end shadow border-start-0 p-2" data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1" id="offcanvasScrolling">
          <div class="offcanvas-header border-bottom">
            <h5 class="offcanvas-title" id="offcanvasScrollingLabel">Theme Customizer</h5>
            <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"></button>
          </div>
          <div class="offcanvas-body">
            <h6 class="mb-0">Theme Variation</h6>
            <hr>
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="inlineRadioOptions" id="LightTheme" value="option1" checked>
              <label class="form-check-label" for="LightTheme">Light</label>
            </div>
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="inlineRadioOptions" id="DarkTheme" value="option2">
              <label class="form-check-label" for="DarkTheme">Dark</label>
            </div>
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="inlineRadioOptions" id="SemiDarkTheme" value="option3">
              <label class="form-check-label" for="SemiDarkTheme">Semi Dark</label>
            </div>
            <hr>
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="inlineRadioOptions" id="MinimalTheme" value="option3">
              <label class="form-check-label" for="MinimalTheme">Minimal Theme</label>
            </div>
            <hr/>
            <h6 class="mb-0">Header Colors</h6>
            <hr/>
            <div class="header-colors-indigators">
              <div class="row row-cols-auto g-3">
                <div class="col">
                  <div class="indigator headercolor1" id="headercolor1"></div>
                </div>
                <div class="col">
                  <div class="indigator headercolor2" id="headercolor2"></div>
                </div>
                <div class="col">
                  <div class="indigator headercolor3" id="headercolor3"></div>
                </div>
                <div class="col">
                  <div class="indigator headercolor4" id="headercolor4"></div>
                </div>
                <div class="col">
                  <div class="indigator headercolor5" id="headercolor5"></div>
                </div>
                <div class="col">
                  <div class="indigator headercolor6" id="headercolor6"></div>
                </div>
                <div class="col">
                  <div class="indigator headercolor7" id="headercolor7"></div>
                </div>
                <div class="col">
                  <div class="indigator headercolor8" id="headercolor8"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
       </div>
       <!--end switcher-->

  </div>
  <!--end wrapper-->


  <!-- Bootstrap bundle JS -->
  <script src="assets/js/bootstrap.bundle.min.js"></script>
  <!--plugins-->
  <script src="assets/js/jquery.min.js"></script>
  <script src="assets/plugins/simplebar/js/simplebar.min.js"></script>
  <script src="assets/plugins/metismenu/js/metisMenu.min.js"></script>
  <script src="assets/plugins/perfect-scrollbar/js/perfect-scrollbar.js"></script>
  
  <!-- CARTE GRAPHIQUE -->
  <!-- <script src="assets/plugins/vectormap/jquery-jvectormap-2.0.2.min.js"></script>
  <script src="assets/plugins/vectormap/jquery-jvectormap-world-mill-en.js"></script> -->
  
  <script src="assets/js/pace.min.js"></script>
  
  <!-- GRAPHIQUE -->
  	<!--
  	ChartJS : https://www.chartjs.org/
  	-->
  <script src="assets/plugins/chartjs/js/Chart.min.js"></script>
  <script src="assets/plugins/chartjs/js/Chart.extension.js"></script>
  	<!-- 
  		Apex Charts : https://apexcharts.com/docs/options/plotoptions/pie/#labels 
  	-->
  <script src="assets/plugins/apexcharts-bundle/js/apexcharts.min.js"></script>
  <!-- AFFICHER LES GRAPHIQUES AVEC APEXCHARTS -->
  <!-- <script src="assets/plugins/apexcharts-bundle/js/apex-custom.js"></script> -->
  
  <script src="assets/plugins/datatable/js/jquery.dataTables.min.js"></script>
  <script src="assets/plugins/datatable/js/dataTables.bootstrap5.min.js"></script>
  <script src="assets/js/table-datatable.js"></script>
	
  <!--app-->
  <script src="assets/js/app.js"></script>
  
  <!-- AFFICHER LES GRAPHIQUES AVEC CHARTJS -->
  <!-- <script src="assets/js/index2.js"></script> -->
  <script type="text/javascript">
  /* $(document).ready(function () {
	
	DATATABLE
	Alimente le alert ci-dessous
	var table = $('#userTable').DataTable({
		columnDefs: [
			{
				orderable: true,
				targets: [1, 2, 3],
			},
		],
	}); 
	 
	$('button').click(function () {
		var data = table.$('input, select').serialize();
		alert('The following data would have been submitted to the server: \n\n' + data.substr(0, 120) + '...');
		return true;
	});
	
  }); */
  
  $(function() {
		"use strict";


	// chart 1
	var options = {
		series: [{
			name: "Total recherches",
			/* data: [240, 160, 671, 414, 555, 257] */
			data: [${cliksPerResearch}]
		}],
		chart: {
			type: "line",
			//width: 100%,
			height: 40,
			toolbar: {
				show: !1
			},
			zoom: {
				enabled: !1
			},
			dropShadow: {
				enabled: 0,
				top: 3,
				left: 14,
				blur: 4,
				opacity: .12,
				color: "#e72e7a"
			},
			sparkline: {
				enabled: !0
			}
		},
		markers: {
			size: 0,
			colors: ["#e72e7a"],
			strokeColors: "#fff",
			strokeWidth: 2,
			hover: {
				size: 7
			}
		},
		plotOptions: {
			bar: {
				horizontal: !1,
				columnWidth: "35%",
				endingShape: "rounded"
			}
		},
		dataLabels: {
			enabled: !1
		},
		stroke: {
			show: !0,
			width: 2.5,
			curve: "smooth"
		},
		colors: ["#e72e7a"],
		xaxis: {
			categories: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
		},
		fill: {
			opacity: 1
		},
		tooltip: {
			theme: "dark",
			fixed: {
				enabled: !1
			},
			x: {
				show: !1
			},
			y: {
				title: {
					formatter: function(e) {
						return ""
					}
				}
			},
			marker: {
				show: !1
			}
		}
	  };

	  var chart = new ApexCharts(document.querySelector("#chart1"), options);
	  chart.render();



 
 	// chart 2
	var options = {
		series: [{
			name: "Total Views",
			/* data: [400, 555, 257, 640, 460, 671, 350] */
			data: [${countMessagesPerMonth}]
		}],
		chart: {
			type: "bar",
			//width: 100%,
			height: 40,
			toolbar: {
				show: !1
			},
			zoom: {
				enabled: !1
			},
			dropShadow: {
				enabled: 0,
				top: 3,
				left: 14,
				blur: 4,
				opacity: .12,
				color: "#3461ff"
			},
			sparkline: {
				enabled: !0
			}
		},
		markers: {
			size: 0,
			colors: ["#3461ff"],
			strokeColors: "#fff",
			strokeWidth: 2,
			hover: {
				size: 7
			}
		},
		plotOptions: {
			bar: {
				horizontal: !1,
				columnWidth: "35%",
				endingShape: "rounded"
			}
		},
		dataLabels: {
			enabled: !1
		},
		stroke: {
			show: !0,
			width: 2.5,
			curve: "smooth"
		},
		colors: ["#3461ff"],
		xaxis: {
			categories: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
		},
		fill: {
			opacity: 1
		},
		tooltip: {
			theme: "dark",
			fixed: {
				enabled: !1
			},
			x: {
				show: !1
			},
			y: {
				title: {
					formatter: function(e) {
						return ""
					}
				}
			},
			marker: {
				show: !1
			}
		}
	  };

	  var chart = new ApexCharts(document.querySelector("#chart2"), options);
	  chart.render();



	// chart 3
	var options = {
		series: [{
			name: "Revenue",
			/* data: [240, 160, 555, 257, 671, 414] */
			data: [${countCommentsPerMonth}]
		}],
		chart: {
			type: "line",
			//width: 100%,
			height: 40,
			toolbar: {
				show: !1
			},
			zoom: {
				enabled: !1
			},
			dropShadow: {
				enabled: 0,
				top: 3,
				left: 14,
				blur: 4,
				opacity: .12,
				color: "#12bf24"
			},
			sparkline: {
				enabled: !0
			}
		},
		markers: {
			size: 0,
			colors: ["#12bf24"],
			strokeColors: "#fff",
			strokeWidth: 2,
			hover: {
				size: 7
			}
		},
		plotOptions: {
			bar: {
				horizontal: !1,
				columnWidth: "35%",
				endingShape: "rounded"
			}
		},
		dataLabels: {
			enabled: !1
		},
		stroke: {
			show: !0,
			width: 2.5,
			curve: "smooth"
		},
		colors: ["#12bf24"],
		xaxis: {
			categories: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
		},
		fill: {
			opacity: 1
		},
		tooltip: {
			theme: "dark",
			fixed: {
				enabled: !1
			},
			x: {
				show: !1
			},
			y: {
				title: {
					formatter: function(e) {
						return ""
					}
				}
			},
			marker: {
				show: !1
			}
		}
	  };

	  var chart = new ApexCharts(document.querySelector("#chart3"), options);
	  chart.render();




	// chart 4
	var options = {
		series: [{
			name: "Customers",
			/* data: [400, 555, 257, 640, 460, 671, 350] */
			data: [${countOrdersPerMonth}]
		}],
		chart: {
			type: "bar",
			//width: 100%,
			height: 40,
			toolbar: {
				show: !1
			},
			zoom: {
				enabled: !1
			},
			dropShadow: {
				enabled: 0,
				top: 3,
				left: 14,
				blur: 4,
				opacity: .12,
				color: "#ff6632"
			},
			sparkline: {
				enabled: !0
			}
		},
		markers: {
			size: 0,
			colors: ["#ff6632"],
			strokeColors: "#fff",
			strokeWidth: 2,
			hover: {
				size: 7
			}
		},
		plotOptions: {
			bar: {
				horizontal: !1,
				columnWidth: "35%",
				endingShape: "rounded"
			}
		},
		dataLabels: {
			enabled: !1
		},
		stroke: {
			show: !0,
			width: 2.5,
			curve: "smooth"
		},
		colors: ["#ff6632"],
		xaxis: {
			categories: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
		},
		fill: {
			opacity: 1
		},
		tooltip: {
			theme: "dark",
			fixed: {
				enabled: !1
			},
			x: {
				show: !1
			},
			y: {
				title: {
					formatter: function(e) {
						return ""
					}
				}
			},
			marker: {
				show: !1
			}
		}
	  };

	  var chart = new ApexCharts(document.querySelector("#chart4"), options);
	  chart.render();
	  

	// GRAPHIQUE : id="chart5"

	var options = {
	    series: [{
	        name: "Clicks",
			/* data: [0, 0, 171, 657, 160, 471, 340] */
	    data: [${cliksPerProduct}]
	    }],
	    chart: {
	         type: "area",
	       // width: 130,
		    stacked: true,
	        height: 340,
	        toolbar: {
	            show: !1
	        },
	        zoom: {
	            enabled: !1
	        },
	        dropShadow: {
	            enabled: 0,
	            top: 3,
	            left: 14,
	            blur: 4,
	            opacity: .12,
	            color: "#3461ff"
	        },
	        sparkline: {
	            enabled: !1
	        }
	    },
	    markers: {
	        size: 0,
	        colors: ["#3461ff"],
	        strokeColors: "#fff",
	        strokeWidth: 2,
	        hover: {
	            size: 7
	        }
	    },
	    plotOptions: {
	        bar: {
	            horizontal: !1,
	            columnWidth: "25%",
	            //endingShape: "rounded"
	        }
	    },
	    dataLabels: {
	        enabled: !1
	    },
	    stroke: {
	        show: !0,
	        width: [2.5],
			//colors: ["#3461ff"],
	        curve: "smooth"
	    },
		fill: {
			type: 'gradient',
			gradient: {
			  shade: 'light',
			  type: 'vertical',
			  shadeIntensity: 0.5,
			  gradientToColors: ['#3361ff'],
			  inverseColors: false,
			  opacityFrom: 0.7,
			  opacityTo: 0.1,
			 // stops: [0, 100]
			}
		},
		colors: ["#3361ff"],
	    xaxis: {
	        categories: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
	    },
		responsive: [
			{
			  breakpoint: 1000,
			  options: {
				chart: {
					type: "area",
				   // width: 130,
					stacked: true,
				}
			  }
			}
		  ],
		legend: {
			show: false
		  },
	    tooltip: {
	        theme: "dark"        
	    }
	  };

	  var chart = new ApexCharts(document.querySelector("#chart5"), options);
	  chart.render();



/*
	// chart6

	  var chart = new Chart(document.getElementById('chart6'), {
		type: 'doughnut',
		data: {
			labels: [${char_cats_titre}],
			datasets: [{
				label: "Device Users",
				backgroundColor: ["#12bf24", "#3461ff", "#ff6632"],
				data: [${char_cats_nbr}]
			}]
		  },
		options: {
			maintainAspectRatio: false,
			cutoutPercentage: 85,
			responsive: true,
		  legend: {
			display: false
		  }
		}
	  });
*/	  
	  
	// chart 9
	/* var options = {
		series: [44, 55, 41, 17, 15],
		chart: {
			foreColor: '#9ba7b2',
			height: 380,
			type: 'donut',
		},
		colors: ["#0d6efd", "#212529", "#17a00e", "#f41127", "#ffc107"],
		responsive: [{
			breakpoint: 480,
			options: {
				chart: {
					height: 320
				},
				legend: {
					position: 'bottom'
				}
			}
		}]
	};
	var chart = new ApexCharts(document.querySelector("#chart9"), options);
	chart.render(); */
	
	/* CAMEMBERT : id="chart9" */
    /* pour le total à l’intérieur cf */
    /* https://apexcharts.com/docs/options/plotoptions/pie/#labels */
    var donutChart = {
   	    chart: {
   	        height: 350,
   	        type: 'donut', /* pie */
   	        toolbar: {
   	          show: true,
   	        }
   	    },
   	    series: [${char_cats_nbr}],
   	    labels: [${char_cats_titre}],
   	    responsive: [{
   	        breakpoint: 480,
   	        options: {
   	            chart: {
   	                width: 200
   	            }
   	        }
   	    }],
   	    legend: {
   	    	show: true,
   	    	showForSingleSeries: true,
   	      	showForNullSeries: true,
   	     	showForZeroSeries: true,
   	    	position: 'right',
   	    	horizontalAlign: 'center',
   	    	onItemClick: {
   	    		toggleDataSeries: true
   	    	},
   	    	onItemHover: {
   	    		highlightDataSeries: true
   	    	}
        },
        plotOptions: {
        	pie: {
        		donut: {
        			labels: {
            			show: true,
            			total: {
            				show: true,
            				label: "Total clicks"
            			}
        			}
        		}
        	}
        }
   	}

   	var donut = new ApexCharts(
   	    document.querySelector("#chart9"),
   	    donutChart
   	);

   	donut.render();
	
	}); 
  </script>
</body>

</html>