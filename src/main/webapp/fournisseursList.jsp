<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Fournisseur"%>
<%@ page import="model.Entree_stock"%>
<%@page import="tools.DateManipulator"%>
<%
ArrayList<Fournisseur> fbCol = (ArrayList) request.getAttribute("fbCol");
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

  <title>Fournisseurs</title>
</head>

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
		
			<h6 class="mb-0 text-uppercase">Liste des fournisseurs</h6>
			<hr/>
			<div class="row">
				<div class="col-xl-6 mx-auto">
					<div class="card">
						<div class="card-body">
							<%
							if (request.getAttribute("invalidAdd") != null) {
							%>
							<div>
								<div class="alert alert-danger" role="alert">
									<%=request.getAttribute("invalidAdd")%>
								</div>
							</div>
							<%
							}
							%>
							<%
							if (request.getAttribute("supplierAdded") != null) {
							%>
							<div>
								<div class="alert alert-success text-center" role="alert">
									<%=request.getAttribute("supplierAdded")%>
								</div>
							</div>
							<%
							}
							%>
							<form class="row g-2" method="post">
								<div class="col-md-9">
									<input type="text" name="nom" class="form-control" placeholder="Nom du fournisseur" aria-label="First name">
								</div>
								<div class="col-md-3">
									<div class="d-grid">
										<button type="submit" class="btn btn-primary" name="addFournisseurForm">Ajouter</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					
					<!-- boucle -->
				<%
				if (request.getAttribute("invalidUpdate") != null) {
				%>
				<div>
					<div class="alert alert-danger" role="alert">
						<%=request.getAttribute("invalidUpdate")%>
					</div>
				</div>
				<%
				}
				%>
				<%
				if (request.getAttribute("supplierUpdated") != null) {
				%>
				<div>
					<div class="alert alert-success text-center" role="alert">
						<%=request.getAttribute("supplierUpdated")%>
					</div>
				</div>
				<%
				}
				%>
				<%
				for ( Fournisseur fb : fbCol ) {
				%>
					<div class="card">
						<div class="card-body">
							<form class="row g-2" method="post">
								<input type="hidden" name="idFournisseur" value="<%= fb.getId() %>" >
								<div class="col-md-9">
									<input type="text" name="nom" class="form-control" value="<%= fb.getNom() %>">
								</div>
								<div class="col-md-3">
									<div class="d-grid">
										<button type="submit" class="btn btn-warning" name="updateFournisseurForm">Mettre à jour</button>
									</div>
								</div>
								<%-- <div class="col-md-3">
									<div class="d-grid">
										<a href="FournisseursList?id=<%= fb.getId() %>&idFournisseur=<%= fb.getId() %>&delete=ok" class="btn btn-danger" name="addAddressForm">Supprimer</a>
									</div>
								</div> --%>
					<%
					if ( fb.getArchiver() == 0 ) {
					%>
								<div class="col-md-9">
									<p>Le compte de ce client est actif. Voulez-vous l’archiver ?</p>
								</div>
								<div class="col-md-3">
									<div class="d-grid">
										<a href="FournisseursList?id=<%=fb.getId() %>&archived=isNotArchived" class="btn btn-success px-5"><i class="fa-solid fa-file-circle-plus"></i></a>
									</div>
								</div>
					<%
					} else {
					%>
								<div class="col-md-9">
									<p>Le compte de ce client est archivé. Voulez-vous le réactiver ?</p>
								</div>
								<div class="col-md-3">
									<div class="d-grid">
										<a href="FournisseursList?id=<%=fb.getId() %>&archived=isArchived" class="btn btn-danger px-5"><i class="fa-solid fa-file-circle-minus"></i></a>
									</div>
								</div>
					<%
					}
					%>
							</form>
						</div>
					</div>
				<%
				}
				%>
				</div>
			</div>
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
  
</body>

</html>		