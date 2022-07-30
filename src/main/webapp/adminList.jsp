<%@page import="tools.DateManipulator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ page import="model.Admin"%>
<%@ page import="java.util.ArrayList"%>
<% 
ArrayList<Admin> staff = (ArrayList<Admin>) request.getAttribute("staff");
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

  <title>Staff</title>
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
			<!--breadcrumb-->
			<!-- <div class="page-breadcrumb d-none d-sm-flex align-items-center mb-3">
				<div class="breadcrumb-title pe-3">Tables</div>
				<div class="ps-3">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb mb-0 p-0">
							<li class="breadcrumb-item"><a href="javascript:;"><i class="bx bx-home-alt"></i></a>
							</li>
							<li class="breadcrumb-item active" aria-current="page">Data Table</li>
						</ol>
					</nav>
				</div>
				<div class="ms-auto">
					<div class="btn-group">
						<button type="button" class="btn btn-primary">Settings</button>
						<button type="button" class="btn btn-primary split-bg-primary dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown">	<span class="visually-hidden">Toggle Dropdown</span>
						</button>
						<div class="dropdown-menu dropdown-menu-right dropdown-menu-lg-end">	<a class="dropdown-item" href="javascript:;">Action</a>
							<a class="dropdown-item" href="javascript:;">Another action</a>
							<a class="dropdown-item" href="javascript:;">Something else here</a>
							<div class="dropdown-divider"></div>	<a class="dropdown-item" href="javascript:;">Separated link</a>
						</div>
					</div>
				</div>
			</div> -->
			<!--end breadcrumb-->
			<div>
				<div class="alert alert-danger text-center" role="alert">
					Je souhaite utiliser <a href="https://editor.datatables.net/examples/simple/simple.html">EDITOR</a> mais je n’arrive pas à le faire fonctionner.<br>
					Avec DATATABLE, seuls les td qui n’ont pas d’Input peuvent être triés !<br> 
				</div>
			</div>
			<hr/>
			<h6 class="mb-0 text-uppercase">Ajouter un(e) employé(e)</h6>
			<br>
			<%
			if (request.getAttribute("invalidAdd") != null) {
			%>
			<div>
				<div class="alert alert-danger text-center" role="alert">
					<%=request.getAttribute("invalidAdd")%>
				</div>
			</div>
			<%
			}
			%>
			<%
			if (request.getAttribute("employeeAdded") != null) {
			%>
			<div>
				<div class="alert alert-success text-center" role="alert">
					<%=request.getAttribute("employeeAdded")%>
				</div>
			</div>
			<%
			}
			%>
			<div class="card">
				<div class="card-body">
					<form class="row g-4" method="post" action="AdminList" >
						<div class="col-md-4">
							<label class="form-label">Nom de famille</label>
							<input type="text" class="form-control" name="name" >
						</div>
						<div class="col-md-4">
							<label class="form-label">Email</label>
							<div class="input-group">
								<div class="input-group-text">@</div>
						    	<input type="email" class="form-control" name="mail" >
						    </div>
						</div>
					    <div class="col-md-4">
					    	<label class="form-label">Mot de passe</label>
							<input type="text" class="form-control" name="password" >
						</div>
						<div class="col-md-3">
							<label class="form-label">Type d’employé</label>
							<input type="text" class="form-control" name="employeeType" placeholder="Boss, directeur, responsable, agent..." >
						</div>
						<div class="col-md-9"></div>
							
						<div class="col-md-12">
							<label class="form-label">Gérer les droits d’affichage depuis le menu jusqu’aux éléments d’une page : boutons, tableaux, graphiques...</label>
							<ul style="list-style-type: none;">
								<li>
									<div class="form-check">
										<label class="form-check-label" for="stat">STATISTIQUES</label>
										<input class="form-check-input" type="checkbox" 
											name="stat" 
											value="stat" 
											id="stat" 
											onclick="statFunction()"
										>
									</div>
								
									<div id="divStat" style="display:none">
										<ul style="list-style-type: none;">
											<li>
												<div class="form-check">
													<label class="form-check-label" for="boStat">Back office</label>
													<input class="form-check-input" type="checkbox" 
														name="boStat" 
														value="boStat" 
														id="boStat" 
														onclick="statBoFunction()"
													>
												</div>
								
												<div id="divBoStat" style="display:none">
													<ul style="list-style-type: none;">
														<li>
															<div class="form-check">
																<label class="form-check-label" for="boStatEmployees">Employés</label>
																<input class="form-check-input" type="checkbox" 
																	name="boStatEmployees" 
																	value="boStatEmployees" 
																	id="boStatEmployees"								
																>
															</div>
														</li>
													</ul>
												</div>
											</li>
											<li>
												<div class="form-check">
													<label class="form-check-label" for="foStat">Front office</label>
													<input class="form-check-input" type="checkbox" 
														name="foStat" 
														value="foStat" 
														id="foStat" 
														onclick="statFoFunction()"	
													>
												</div>
						
												<div id="divFoStat" style="display:none">
													<ul style="list-style-type: none;">
														<li>
															<div class="form-check">
																<label class="form-check-label" for="foStatClients">Clients</label>
																<input class="form-check-input" type="checkbox" 
																	name="foStatClients" 
																	value="foStatClients" 
																	id="foStatClients"
																>
															</div>
														</li>
														<li>
															<div class="form-check">
																<label class="form-check-label" for="foStatProspects">Prospects</label>
																<input class="form-check-input" type="checkbox" 
																	name="foStatProspects" 
																	value="foStatProspects" 
																	id="foStatProspects"									
																	>
															</div>
														</li>
														<li>
															<div class="form-check">
																<label class="form-check-label" for="foStatVisiteurs">Visiteurs</label>
																<input class="form-check-input" type="checkbox" 
																	name="foStatVisiteurs" 
																	value="foStatVisiteurs" 
																	id="foStatVisiteurs"
																	>
															</div>
														</li>
													</ul>
												</div>
											</li>
										</ul>
									</div>
								</li>
							</ul>
							<ul style="list-style-type: none;">
								<li>
									<div class="form-check">
										<label class="form-check-label" for="webSite">GESTION DU SITE</label>
										<input class="form-check-input" type="checkbox" 
											name="webSite" 
											value="webSite" 
											id="webSite" 
											<%-- onclick="" --%>
										>
									</div>
								</li>
							</ul>
							<ul style="list-style-type: none;">
								<li>
									<div class="form-check">
										<label class="form-check-label" for="commandes">GESTION DES COMMANDES</label>
										<input class="form-check-input" type="checkbox" 
											name="commandes" 
											value="commandes" 
											id="commandes" 
											<%-- onclick="" --%>
										>
									</div>
								</li>
							</ul>
							<ul style="list-style-type: none;">
								<li>
									<div class="form-check">
										<label class="form-check-label" for="utilisateurs">GESTION DES UTILISATEURS</label>
										<input class="form-check-input" type="checkbox" 
											name="utilisateurs" 
											value="utilisateurs" 
											id="utilisateurs" 
											<%-- onclick="" --%>
										>
									</div>
								</li>
							</ul>
						</div>
						<div class="col-auto">
							<button class="btn btn-primary" type="submit" name="addEmployeeForm">Ajouter</button>
						</div>
					</form>
				</div>
			</div>
<script>
  /**************************
   *
   *	   PRIVILÈGES
   *
   **************************/
   
   /* function myFunction() {
	   var checkBox = document.getElementById("myCheck");
	   var text = document.getElementById("text");
	   if (checkBox.checked == true){
	     text.style.display = "block";
	   } else {
	      text.style.display = "none";
	   }
	 } */
   
  /* STATISTIQUES */
  	function statFunction() {
  		var checkBox = document.getElementById("stat");
  		var text = document.getElementById("divStat");
  		if (checkBox.checked == true){
  			text.style.display = "block";
  		} else {
  			text.style.display = "none";
  		}
	}
  	
  	function statBoFunction() {
  		var checkBox = document.getElementById("boStat");
  		var text = document.getElementById("divBoStat");
  		if (checkBox.checked == true){
  			text.style.display = "block";
  		} else {
  			text.style.display = "none";
  		}
	}
  	
  	function statFoFunction() {
  		var checkBox = document.getElementById("foStat");
  		var text = document.getElementById("divFoStat");
  		if (checkBox.checked == true){
  			text.style.display = "block";
  		} else {
  			text.style.display = "none";
  		}
	}
  	/* GESTION GÉNÉRAL */
  	/* function statFunction() {
  		var checkBox = document.getElementById("stat");
  		var text = document.getElementById("divStat");
  		if (checkBox.checked == true){
  			text.style.display = "block";
  		} else {
  			text.style.display = "none";
  		}
	}
  	
  	function statBoFunction() {
  		var checkBox = document.getElementById("boStat");
  		var text = document.getElementById("divBoStat");
  		if (checkBox.checked == true){
  			text.style.display = "block";
  		} else {
  			text.style.display = "none";
  		}
	}
  	
  	function statFoFunction() {
  		var checkBox = document.getElementById("foStat");
  		var text = document.getElementById("divFoStat");
  		if (checkBox.checked == true){
  			text.style.display = "block";
  		} else {
  			text.style.display = "none";
  		}
	} */
  </script>
			<h6 class="mb-0 text-uppercase">Liste des employé(e)s</h6>
			<hr/>
			<div class="card">
				<div class="card-body">
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
					<!-- <div class="table-responsive"> -->
						<form id="form-id" method="post" action="AdminList"> 
							<button type="submit" name="update" class="btn btn-primary btn-block">Mettre à jour</button>
							<p></p>
							<table id="userTable" class="display" style="width:100%">
							<!-- <table id="example" class="table table-striped table-bordered" style="width:100%"> -->
								<thead>
								<tr>
									<th>Id</th>
									<th>Nom</th>
									
									<th>Catégorie</th>
									<th>Nom</th>
									
									<th>Mail</th>
									<th class="text-center">Archiver</th>
									<th class="text-center">Fiche</th>
								</tr>
							</thead>
								<tbody>
								<% 
								int i = 0;
								for( Admin ab : staff ) {
									i++;
								%>
									<tr>
										<td>
											<input size="7" type="text" id="row-<%= i %>-id" name="row-<%= i %>-id" value="<%= ab.getId() %>" <%-- name="id"--%> class='form-control-plaintext' readonly>
										</td>
										<td><%= ab.getNom() %></td>
										<%
										String[] typeEmploye = ab.getPrivileges().split(","); 
										%>
										<td><%= typeEmploye[0] %></td>
										<td>
											<input size="15" type="text" id="row-<%= i %>-nom" name="row-<%= i %>-nom" value="<%= ab.getNom() %>" <%-- name="<%= client.getNom() %>" --%>>
										</td>
										<td>
											<input size="15" type="text" id="row-<%= i %>-prenom" name="row-<%= i %>-mail" value="<%= ab.getEmail() %>" <%-- name="<%= client.getPrenom() %>" --%>>
										</td>
									<%
									if ( ab.getArchiver() == 0 ) {
									%>
										<td class="text-center">
											<a href="AdminList?id=<%= ab.getId() %>&archived=isNotArchived" class="btn btn-sm btn-success px-5"><i class="fa-solid fa-file-circle-plus"></i></a>
										</td>
									<%
									} else {
									%>
										<td class="text-center">
											<a href="AdminList?id=<%= ab.getId() %>&archived=isArchived" class="btn btn-sm btn-danger px-5"><i class="fa-solid fa-file-circle-minus"></i></a>
										</td>
									<%
									}
									%>
										<td class="text-center">
		                                    <%-- <a href="UserCard?id=<%=client.getId() %>" class="btn btn-warning btn-block"><i class="bi bi-pencil-fill"></i></a> --%>
		                                    <a href="AdminCard?id=<%= ab.getId() %>" class="btn btn-sm btn-primary px-5"><i class="fa-solid fa-eye"></i></a>
	                                   </td>
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
					<!-- </div> --> <!-- table responsive -->
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
  <script src="assets/js/pace.min.js"></script>
  <script src="assets/plugins/datatable/js/jquery.dataTables.min.js"></script>
  <script src="assets/plugins/datatable/js/dataTables.bootstrap5.min.js"></script>
  <script src="assets/js/table-datatable.js"></script>
	
  <!--app-->
  <script src="assets/js/app.js"></script>
  <script type="text/javascript">
  $(document).ready(function () {
	    /* var table = $('#example').DataTable({
	        columnDefs: [
	            {
	                orderable: false,
	                targets: [1, 2, 3, 4],
	            },
	        ],
	    });
	 
	  	var form = document.getElementById("form-id");
	    $('button').click(function () {
	        var data = table.$('input, select').serialize();
	        alert('The following data would have been submitted to the server: \n\n' + data*//* .substr(0, 120) + '...' *//*);
			form.submit();
	        return false;
	    }); */
	    
	    /* POUR ALIMENTER LE ALERT CI-DESSOUS 	    
	*/
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
			/* alert('The following data would have been submitted to the server: \n\n' + data.substr(0, 120) + '...'); */ 
			return true;
		}); 
	});
  </script>
</body>

</html>