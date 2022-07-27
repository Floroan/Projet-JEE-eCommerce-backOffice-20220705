<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ page import="model.Produit"%>
<%@ page import="model.Fournisseur"%>
<%@ page import="model.Entree_stock"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="tools.DateManipulator"%>
<%
Produit p = (Produit) request.getAttribute("pb");
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
		
			<h6 class="mb-0 text-uppercase">Commande fournisseur</h6>
			<hr/>
			<div class="row">
				<div class="col-xl-6 mx-auto">
					<div class="card">
						<div class="card-body">
						<%
						if (request.getAttribute("invalidProvider") != null) {
						%>
							<div>
								<div class="alert alert-danger text-center" role="alert">
									<%=request.getAttribute("invalidProvider")%>
								</div>
							</div>
						<%
						}
						%>
							<h6 class="mb-0 text-uppercase"><img alt="produit" src="<%= p.getImage() %>" style="height: 50px; width: 50px">   <%= p.getTitre() %></h6>
							<!-- <div class="table-responsive"> -->
							<p></p>
							<div class="table-responsive mt-3">
								<!-- <table id="example"> class="table table-striped table-bordered" style="width:100%"> -->
			                    <table class="table align-middle mb-0">
									<!-- <thead> -->
									<thead class="table-light">
										<tr>
											<!-- <th>Id</th>
											<th>Produit</th>
											<th>Titre</th> -->
											<th class="text-center">Stock</th>
											<th class="text-center">Stock Min</th>
											<th class="text-center">Qté à commander</th>
										</tr>
									</thead>
									<tbody>
										<tr>
		                                	<%-- <td><%= p.getId() %></td>
		                                    <td><img alt="produit" src="<%= p.getImage() %>" style="height: 50px; width: 50px"></td>
		                                    <td><%= p.getTitre().substring(0, 15) %>...</td> --%>
		                                    
		                                    <td class="text-center"><%= p.getStock() %></td>
		                                    <td class="text-center"><%= p.getStock_min() %></td>
		                                    <%
		                                    int s = p.getStock_min() - p.getStock();
		                                    %>
		                                    <td class="text-center"><%= s %></td>
										</tr>
									</tbody>
								</table>
							</div>
							<!-- <form class="row g-2" method="post">
								<div class="col-md-6">
									<input type="text" name="nom" class="form-control" placeholder="Nom du fournisseur" aria-label="First name">
								</div>
								<div class="col-md-6">
									<div class="d-grid">
										<button type="submit" class="btn btn-primary" name="addFournisseurForm">Ajouter</button>
									</div>
								</div>
							</form> -->
							<p><br><br></p>
							<form class="row g-3" method="get">
			                	<div class="col-12">
			                		<input type="hidden" name="fk_produit" value=<%= p.getId() %>>
			                		<label for="exampleDataList" class="form-label">Fournisseurs</label>
									<!-- <input  class="form-control" list="datalistOptions" id="exampleDataList" placeholder="Type to search..."> -->
									<select class="form-select mb-3" aria-label="Default select example" name="fk_fournisseur">
										<option selected>Choisir un fournisseur</option>
									<%
									for ( Fournisseur f : fbCol ) {
									%>
										<option value="<%= f.getId() %>"><%= f.getNom() %></option>
									<%
									}
									%>
									</select>
								</div>
			                  	<div class="col-12">
			                    	<label class="form-label">Quantité</label>
			                    	<input type="text" class="form-control" name="quantity">
			                  	</div>
			                  	<p></p>
			                  	<div class="col-12">
			                    	<div class="d-grid">
			                      		<button type="submit" class="btn btn-warning" name="orderToValidate">Commander</button>
			                    	</div>
			                  	</div>
			                </form>
						</div>
					</div>
					
					
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