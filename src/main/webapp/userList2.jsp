<%@page import="tools.DateManipulator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Utilisateur"%>
<% 
Utilisateur visiteurs = (Utilisateur) request.getAttribute("visiteurs");
ArrayList<Utilisateur> clients = (ArrayList<Utilisateur>) request.getAttribute("clients");
ArrayList<Utilisateur> prospects = (ArrayList<Utilisateur>) request.getAttribute("prospects");
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

  <title>Utilisateurs</title>
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
			<h6 class="mb-0 text-uppercase">Ajouter un utilisateur</h6>
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
			if (request.getAttribute("userAdded") != null) {
			%>
			<div>
				<div class="alert alert-success text-center" role="alert">
					<%=request.getAttribute("userAdded")%>
				</div>
			</div>
			<%
			}
			%>
			<div class="card">
				<div class="card-body">
					<form class="row g-4" method="post" action="UserList" >
						<div class="col-md-3">
							<label class="form-label">Nom de famille</label>
							<input type="text" class="form-control" name="firstName" >
						</div>
						<div class="col-md-3">
							<label class="form-label">Prénom</label>
							<input type="text" class="form-control" name="lastName" >
						</div>
						<div class="col-md-3">
							<label class="form-label">Email</label>
							<div class="input-group">
								<div class="input-group-text">@</div>
						    	<input type="email" class="form-control" name="mail" >
						    </div>
						</div>
					    <div class="col-md-3">
					    	<label class="form-label">Mot de passe</label>
							<input type="text" class="form-control" name="password" >
						</div>
						<div class="col-auto">
							<button class="btn btn-primary" type="submit" name="addUserForm">Ajouter</button>
						</div>
					</form>
				</div>
			</div>
			<h6 class="mb-0 text-uppercase">Liste des utilisateurs</h6>
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
						<form id="form-id" method="post" action="UserList"> 
							<button type="submit" name="update" class="btn btn-primary btn-block">Mettre à jour</button>
							<p></p>
							<table id="userTable" class="display" style="width:100%">
							<!-- <table id="example" class="table table-striped table-bordered" style="width:100%"> -->
								<thead>
								<tr>
									<th>Id</th>
									<th>Nom</th>
									<th>Inscrit(e) le</th>
									<th>Catégorie</th>
									<th>Nom</th>
									<th>Prénom</th>
									<th>Mail</th>
									<th class="text-center">Archiver</th>
									<th class="text-center">Fiche</th>
								</tr>
							</thead>
								<tbody>
									<tr>
										<td>
											<input size="7" type="text" id="row-1-id" name="row-1-id" value="1" <%-- name="id"--%> class='form-control-plaintext' readonly>
										</td>
										<td><%= visiteurs.getNom() %></td>
										<td>
										<%
										String d = DateManipulator.dateConvertToDDmmYYYY( visiteurs.getDate_inscription() );
										
										%>
											<%= d %>
										</td>
										<td>Visiteurs</td>
										<td>
											<input size="15" type="text" id="row-1-nom" name="row-1-nom" value="<%= visiteurs.getNom() %>" <%-- name="<%= client.getNom() %>" --%>>
										</td>
										<td>
											<input size="15" type="text" id="row-1-prenom" name="row-1-prenom" value="<%= visiteurs.getPrenom() %>" <%-- name="<%= client.getPrenom() %>" --%>>
										</td>
										<td>
											<input size="30" type="text" id="row-1-mail" name="row-1-mail" value="<%= visiteurs.getEmail() %>" <%-- name='<%= client.getEmail() %>' --%>>
										</td>
									
										<td class="text-center">
											<b style="color: RED;">impossible</b>
										</td>
									
										<td class="text-center">
		                                    <%-- <a href="UserCard?id=<%=client.getId() %>" class="btn btn-warning btn-block"><i class="bi bi-pencil-fill"></i></a> --%>
		                                    <a href="UserVisitorList" class="btn btn-sm btn-primary px-5"><i class="fa-solid fa-eye"></i></a>
	                                    </td>
									</tr>
								<% 
								int i = 1;
								for( Utilisateur client : clients ) {
									i++;
								%>
									<tr>
										<td>
											<input size="7" type="text" id="row-<%= i %>-id" name="row-<%= i %>-id" value="<%= client.getId() %>" <%-- name="id"--%> class='form-control-plaintext' readonly>
										</td>
										<td><%= client.getNom() %></td>
										<td>
										<%
										String dc = DateManipulator.dateConvertToDDmmYYYY( client.getDate_inscription() );
										
										%>
											<%= dc %>
										</td>
										<td>Client</td>
										<td>
											<input size="15" type="text" id="row-<%= i %>-nom" name="row-<%= i %>-nom" value="<%= client.getNom() %>" <%-- name="<%= client.getNom() %>" --%>>
										</td>
										<td>
											<input size="15" type="text" id="row-<%= i %>-prenom" name="row-<%= i %>-prenom" value="<%= client.getPrenom() %>" <%-- name="<%= client.getPrenom() %>" --%>>
										</td>
										<td>
											<input size="30" type="text" id="row-<%= i %>-mail" name="row-<%= i %>-mail" value="<%= client.getEmail() %>" <%-- name='<%= client.getEmail() %>' --%>>
										</td>
									<%
									if ( client.getArchiver() == 0 ) {
									%>
										<td class="text-center">
											<a href="UserList?id=<%=client.getId() %>&archived=isNotArchived" class="btn btn-sm btn-success px-5"><i class="fa-solid fa-file-circle-plus"></i></a>
										</td>
									<%
									} else {
									%>
										<td class="text-center">
											<a href="UserList?id=<%=client.getId() %>&archived=isArchived" class="btn btn-sm btn-danger px-5"><i class="fa-solid fa-file-circle-minus"></i></a>
										</td>
									<%
									}
									%>
										<td class="text-center">
		                                    <%-- <a href="UserCard?id=<%=client.getId() %>" class="btn btn-warning btn-block"><i class="bi bi-pencil-fill"></i></a> --%>
		                                    <a href="UserClientCard?id=<%= client.getId() %>" class="btn btn-sm btn-primary px-5"><i class="fa-solid fa-eye"></i></a>
	                                   </td>
									</tr>
								<%
								} 
								%>
								<% 
								int j = clients.size() + 1;
								for( Utilisateur prospect : prospects ) {
									j++;
								%>
									<tr>
										<td>
											<input size="7" type="text" id="row-<%= j %>-id" name="row-<%= j %>-id" value="<%= prospect.getId() %>" <%-- name="id"--%> class='form-control-plaintext' readonly>
										</td>
										<td><%= prospect.getNom() %></td>
										<td>
										<%
										String dp = DateManipulator.dateConvertToDDmmYYYY( prospect.getDate_inscription() );
										
										%>
											<%= dp %>
										</td>
										<td>Prospect</td>
										<td>
											<input size="15" type="text" id="row-<%= j %>-nom" name="row-<%= j %>-nom" value="<%= prospect.getNom() %>" <%-- name="<%= prospect.getNom() %>" --%>>
										</td>
										<td>
											<input size="15" type="text" id="row-<%= j %>-prenom" name="row-<%= j %>-prenom" value="<%= prospect.getPrenom() %>" <%-- name="<%= prospect.getPrenom() %>" --%>>
										</td>
										<td>
											<input size="30" type="text" id="row-<%= j %>-mail" name="row-<%= j %>-mail" value="<%= prospect.getEmail() %>" <%-- name='<%= prospect.getEmail() %>' --%>>
										</td>
									<%
									if ( prospect.getArchiver() == 0 ) {
									%>
										<td class="text-center">
											<a href="UserList?id=<%=prospect.getId() %>&archived=isNotArchived" class="btn btn-sm btn-success px-5"><i class="fa-solid fa-file-circle-plus"></i></a>
										</td>
									<%
									} else {
									%>
										<td class="text-center">
											<a href="UserList?id=<%=prospect.getId() %>&archived=isArchived" class="btn btn-sm btn-danger px-5"><i class="fa-solid fa-file-circle-minus"></i></a>
										</td>
									<%
									}
									%>
										<td class="text-center">
		                                    <%-- <a href="UserCard?id=<%=prospect.getId() %>" class="btn btn-warning btn-block"><i class="bi bi-pencil-fill"></i></a> --%>
		                                    <%
		                                    if (prospect.getArchiver() == 0) {
		                                    %>
		                                    <a href="UserProspectCard?id=<%= prospect.getId() %>" class="btn btn-sm btn-primary px-5"><i class="fa-solid fa-eye"></i></a>
		                                    <%
		                                    } else {
		                                    %>
		                                    
		                                    <%
		                                    }
		                                    %>
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