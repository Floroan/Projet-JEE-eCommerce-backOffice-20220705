<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ page import="model.Commentaire"%>
<%@ page import="model.Utilisateur"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="tools.DateManipulator"%>
<%@page import="tools.RegexValidator"%>
<%
ArrayList<Commentaire> cbCol = (ArrayList) request.getAttribute("cbCol");
ArrayList<Utilisateur> ubColClients = (ArrayList) request.getAttribute("ubColClients");
ArrayList<Utilisateur> ubColProspects = (ArrayList) request.getAttribute("ubColProspects");
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

  <title>Commentaires</title>
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
			<h1 class="mb-0 text-uppercase text-center">Commentaires produits</h1>
			<hr/>
			<div class="row">
				<div class="col-xl-12"> <!-- mx-auto -->
					<div class="card">
						<div class="card-body">
							<!-- <h6 class="mb-0 text-uppercase"></h6>
                			<hr/> -->
							<%
							if (request.getAttribute("alertUpdateComments") != null) {
							%>
							<div>
								<div class="alert alert-danger text-center" role="alert">
									<%=request.getAttribute("alertUpdateComments")%>
								</div>
							</div>
							<%
							}
							%>
							<form id="updateComments" method="post"> 
								<input type="submit" class="btn btn-warning" name="updateComments" value="Mettre à jour le(s) commentaire(s)" />
								<p></p>
								<table id="example" class="table table-striped table-bordered dataTable" style="width:100%"
									role="grid" aria-describedby="example_info">
									<thead>
									<tr>
										<th class="text-center">Id</th>
										<th style="width:50px">Produit</th>
										<th style="width:150px">Utilisateur</th>
										<th>Date</th>
										<th>Note</th>
										<th style="width:300px">Commentaire</th>
										<th style="width:300px">Commentaire</th>
										<th style="width:90px">Gros mots ?</th>
										<th>Actions</th>
									</tr>
								</thead>
									<tbody>
									<% 
									int i = 0;
									for( Commentaire cb : cbCol ) {
										i++;
									%>
										<tr>
											<td class="align-middle">
												<input size="1" type="text" id="row-<%= i %>-id" name="row-<%= i %>-id" value="<%= cb.getId() %>" <%-- name="id"--%> class='form-control-plaintext text-center' readonly>
											</td>
											<td class="align-middle">
												<a href="#" ><img src="<%= cb.getProd().getImage() %>" alt="image" height="100%" width="100%"></a>
											</td>
											<td class="align-middle text-center">
											<%
											for (Utilisateur client : ubColClients) {
												if ( client.getId() == cb.getFk_user()) {
											%>
												<a href="CommentairesUser?idUser=<%= cb.getFk_user() %>" class="btn btn-sm btn-primary px-5"><%= cb.getUtilisateur().getNom() %></a>
											<%
												}
											}
											for ( Utilisateur prospect : ubColProspects ) {
												if ( prospect.getId() == cb.getFk_user() ) {
											%>
												<a href="CommentairesUser?idUser=<%= cb.getFk_user() %>" class="btn btn-sm btn-primary px-5"><%= cb.getUtilisateur().getNom() %></a>
											<%
												}
											}
											%>
											
											</td>
											<%
											String d = DateManipulator.dateConvertToDDmmYYYY( cb.getDate() );
											%>
											<td class="align-middle"><%= d %></td>
											<td class="align-middle text-center"><%= cb.getNote() %></td>
											<td class="align-middle"><%= cb.getCommentaire() %></td>
											<td class="align-middle">
												<textarea id="row-<%= i %>-text" name="row-<%= i %>-text" form="updateComments" class="form-control" <%-- rows="5" --%>><%= cb.getCommentaire() %></textarea>
											</td>
										<%
										if ( RegexValidator.RudeWordsValidator(cb.getCommentaire()) ) {
										%>
											<td class="align-middle text-center">
												<span class="alert alert-danger" role="alert">Peut-être</span>
											</td>
										<%
										} else {
										%>
											<td class="align-middle text-center">Non</td>
										<%
										}
										if ( cb.getArchiver() == 0 ) {
										%>
											<td class="text-center align-middle">
												<div class="mb-1">
													<a href="CommentairesList?id=<%= cb.getId() %>&archived=isNotArchived" class="btn btn-sm btn-success px-5"><i class="fa-solid fa-file-circle-plus"></i></a>
												</div>
												<!-- <hr> -->
												<!-- <br> -->
												<div>
													<a href="CommentairesList?id=<%= cb.getId() %>&deleted=ok" class="btn btn-sm btn-danger px-5"><i class="fa-solid fa-trash"></i></a>
												</div>
											</td>
										<%
										} else {
										%>
											<td class="text-center align-middle">
												<div class="mb-1">
													<a href="CommentairesList?id=<%= cb.getId() %>&archived=isArchived" class="btn btn-sm btn-danger px-5"><i class="fa-solid fa-file-circle-minus"></i></a>
												</div>
												<!-- <hr> -->
												<div>
													<a href="CommentairesList?id=<%= cb.getId() %>&deleted=ok" class="btn btn-sm btn-danger px-5"><i class="fa-solid fa-trash"></i></a>
												</div>
											</td>
									<%
										}

									} 
									%>
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
		
		