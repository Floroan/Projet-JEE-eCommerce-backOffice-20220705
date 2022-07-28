<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ page import="model.Coordonnee"%>
<%@ page import="model.Slide"%>
<%@ page import="java.util.ArrayList"%>
<%
Coordonnee cb = (Coordonnee) request.getAttribute("cb");
ArrayList<Slide> sbCol = (ArrayList) request.getAttribute("sbCol");
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

  <title>Front office</title>
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
		
			<h6 class="mb-0 text-uppercase">Modifier un élément du front</h6>
			<hr/>
			<div class="alert alert-danger text-center" role="alert">
				Il faut rafraîchir le serveur (F5 à la racine du projet) pour que le nouveau logo apparaisse !!?<br> 
				La modification du slider ne peut être vue car ce sont 2 projets différents.<br>
				Du coup, on fait quoi...<br>
			</div>
			<div class="row">
				<div class="col-xl-12"> <!-- mx-auto -->
					<div class="card">
						<div class="card-body">
							<h6 class="mb-0 text-uppercase">Modifier les informations de l’entreprise</h6>
                			<hr/>
								<div class="col-md-12">
									<img src="<%= cb.getLogo() %>" alt="image"  width="13%">
								</div>
							<form class="row g-5" method="post" action="SiteFrontOffice" enctype="multipart/form-data">
								<div class="col-md-2">
									<label for="logoFile" class="form-label">Choisir un nouveau logo</label>
									<input class="form-control" type="file" name="logoFile" id="logoFile"/>
								</div>
								<div class="col-md-2">
									<label class="form-label">Nom de l’entreprise</label>
									<input type="text" name="name" class="form-control" value="<%= cb.getNom() %>"/>
								</div>
								<div class="col-md-3">
									<label class="form-label">Adresse</label>
									<input type="text" name="address" class="form-control" value="<%= cb.getAdresse() %>"/>
								</div>
								<div class="col-md-2">
									<label class="form-label">Téléphone</label>
									<input type="text" name="phone" class="form-control" value="<%= cb.getTelephone() %>"/>
								</div>
								<div class="col-md-3">
									<label class="form-label">Email</label>
									<input type="text" name="mail" class="form-control" value="<%= cb.getEmail() %>"/>
								</div>
								<div class="col-md-3">
									<div class="d-grid">
										<!-- <button type="submit" class="btn btn-warning" name="updateCompany">Mettre à jour les coordonnées</button> -->
										<input type="submit" class="btn btn-warning" name="updateCompany" value="Mettre à jour les coordonnées" />
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				
				<div class="col-xl-12"> <!-- mx-auto -->
					<div class="card">
						<div class="card-body">
							<h6 class="mb-0 text-uppercase">Ajouter un slide au slider de la page d’accueil</h6>
                			<hr/>
							<form id="addSlide" class="row g-1" method="post" action="SiteFrontOffice" enctype="multipart/form-data">
								<div class="col-md-3">
									<input class="form-control" type="file" name="newSlideFile" id="newSlideFile"/>
									<label for="newSlideFile" class="form-label">Choisir une image en 1760 x 703</label>
									<br>
								</div>
								<div class="col-md-3">
									<input type="text" name="title" class="form-control" placeholder="Titre sur l’image"/>
									<br>
								</div>
								<div class="col-md-3">
									<input type="text" name="titleButton" class="form-control" placeholder="Titre du bouton"/>
									<br>
								</div>
								<div class="col-md-3">
									<input type="text" name="urlButton" class="form-control" placeholder="Destination du bouton (URL)"/>
									<br>
								</div>
								<div class="col-md-12">
									<textarea name="text" form="addSlide" class="form-control" rows="5">Écrivez ici vos phrases d’accroches, explications... qui apparaîtront sur l’image.</textarea>
									<br>
								</div>
								<div class="col-md-2">
									<div class="d-grid">
										<input type="submit" class="btn btn-primary" name="addNewSlide" value="Ajouter un slide" />
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				
				<div class="col-xl-12"> <!-- mx-auto -->
					<div class="card">
						<div class="card-body">
							<h6 class="mb-0 text-uppercase">Modifier un slide du slider de la page d’accueil</h6>
                			<hr/>
                			

							<%
							if (request.getAttribute("alertUpdateSlider") != null) {
							%>
							<div>
								<div class="alert alert-danger text-center" role="alert">
									<%=request.getAttribute("alertUpdateSlider")%>
								</div>
							</div>
							<%
							}
							%>
							<form id="updateSlide" method="post" action="SiteFrontOffice" enctype="multipart/form-data"> 
								<input type="submit" class="btn btn-warning" name="updateSlide" value="Mettre à jour le slider" />
								<p></p>
								<table id="example" class="table table-striped table-bordered dataTable" style="width:100%"
									role="grid" aria-describedby="example_info">
									<thead>
									<tr>
										<th>Id</th>
										<th>Image actuelle</th>
										<th>Nouvelle Image</th>
										<th>Titre</th>
										<th>Titre bouton</th>
										<th>Url bouton</th>
										<th>Texte</th>
										<th>Actions</th>
									</tr>
								</thead>
									<tbody>
									<% 
									int i = 0;
									for( Slide sb : sbCol ) {
										i++;
									%>
										<tr>
											<td>
												<input type="text" id="row-<%= i %>-id" name="row-<%= i %>-id" value="<%= sb.getId() %>" <%-- name="id"--%> class='form-control-plaintext' readonly>
											</td>
											<td>
												<img src="<%= sb.getImage() %>" alt="image" height="30px" width="100%">
											</td>
											<td>
												<input class="form-control form-control-sm" type="file" id="row-<%= i %>-urlImg" name="row-<%= i %>-urlImg"/>
												<label for="row-<%= i %>-urlImg" class="form-label">Choisir une image en 1760 x 703</label>
											</td>
											<td>
												<input type="text" id="row-<%= i %>-title" name="row-<%= i %>-title" value="<%= sb.getTitre() %>"/>
											</td>
											<td>
												<input type="text" id="row-<%= i %>-titleButton" name="row-<%= i %>-titleButton" value="<%= sb.getTitreBouton() %>"/>
											</td>
											<td>
												<input type="text" id="row-<%= i %>-urlButton" name="row-<%= i %>-urlButton" value="<%= sb.getUrl() %>"/>
											</td>
											<td>
												<textarea id="row-<%= i %>-text" name="row-<%= i %>-text" form="updateSlide" class="form-control" rows="5"><%= sb.getDescription() %></textarea>
											</td>
										<%
										if ( sb.getArchiver() == 0 ) {
										%>
											<td class="text-center">
												<a href="SiteFrontOffice?id=<%= sb.getId() %>&archived=isNotArchived" class="btn btn-sm btn-success px-5"><i class="fa-solid fa-file-circle-plus"></i></a>
												<hr>
												<a href="SiteFrontOffice?id=<%= sb.getId() %>&deleted=ok" class="btn btn-sm btn-danger px-5"><i class="fa-solid fa-trash"></i></a>
											</td>
										<%
										} else {
										%>
											<td class="text-center">
												<a href="SiteFrontOffice?id=<%= sb.getId() %>&archived=isArchived" class="btn btn-sm btn-danger px-5"><i class="fa-solid fa-file-circle-minus"></i></a>
												<hr>
												<a href="SiteFrontOffice?id=<%= sb.getId() %>&deleted=ok" class="btn btn-sm btn-danger px-5"><i class="fa-solid fa-trash"></i></a>
											</td>
										<%
										}
										%>
											</tr>
										<%
										} 
										%>
									
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