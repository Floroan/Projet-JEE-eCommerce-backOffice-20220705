<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Utilisateur"%>
<% 
ArrayList<Utilisateur> u = (ArrayList<Utilisateur>) request.getAttribute("utilisateur");
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
			<h6 class="mb-0 text-uppercase">Liste des utilisateurs</h6>
			<hr/>
			<div class="card">
				<div class="card-body">
				<%
				if (request.getAttribute("msg") != null) {
				%>
				<div class="pt-20">
					<div class="alert alert-danger text-center" role="alert">
						<%=request.getAttribute("msg")%>
					</div>
				</div>	
				<%
				}
				%>
					<div class="table-responsive">
							<table id="example" class="table table-striped table-bordered" style="width:100%">
								<thead>
								<tr>
									<th>ID</th>
									<th>Nom</th>
									<th>Prénom</th>
									<th>Mail</th>
									<th>Date</th>
									<th class="text-center">Statut Archiver</th>
									<!-- <th class="text-center">Actions</th> -->
								</tr>
							</thead>
								<tbody>
								<% 
								for( Utilisateur cmd : u ) { 
								%>
								
						<!-- 
							ON A PAS LE DROIT DE METTRE UN FORM ICI
							- un form englobe le <table>
							- ou est à l’intérieur d’un <td> 
						-->		
						<form method="post" action="UserList">
						
						
										<tr>
											<td>
												<input type="text" class='form-control-plaintext' readonly  name="id" value="<%= cmd.getId()%>" >
											</td>
											<%-- <td><%= cmd.getId() %></td> --%>
											<td>
												<input type='text' class='form-control' value='<%= cmd.getNom() %>' name='<%= cmd.getNom() %>'>
											</td>
											<td>
												<input type='text' value='<%= cmd.getPrenom() %>' name='<%= cmd.getPrenom() %>'>
											</td>
											<td>
												<input type='text' class='form-control' value='<%= cmd.getEmail() %>' name='<%= cmd.getEmail() %>'>
											</td>
											<td><%= cmd.getDate_inscription() %></td>
									<%
									if ( cmd.getArchiver() == 0 ) {
									%>
											<td class="text-center">
												<a href="UserList?id=<%=cmd.getId() %>&archived=isNotArchived" class="btn btn-success btn-block"><i class="fa-solid fa-file-circle-plus"></i></a>
											</td>
									<%
									} else {
									%>
											<td class="text-center">
												<a href="UserList?id=<%=cmd.getId() %>&archived=isArchived" class="btn btn-danger btn-block"><i class="fa-solid fa-file-circle-minus"></i></a>
											</td>
									<%
									}
									%>
											<td class="text-center">
			                                    <%-- <a href="UserCard?id=<%=cmd.getId() %>" class="btn btn-warning btn-block"><i class="bi bi-pencil-fill"></i></a> --%>
			                                    <button type="submit" name="update" class="btn btn-primary btn-block">Mettre à jour</button>                                 
			                                    <a href='UserCard?id=<%= cmd.getId() %>';" class="btn btn-primary btn-block"><i class="fa-solid fa-eye"></i></a>
		                                   </td>
		                                   
										</tr>
						</form>
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
  <script src="assets/js/pace.min.js"></script>
  <script src="assets/plugins/datatable/js/jquery.dataTables.min.js"></script>
  <script src="assets/plugins/datatable/js/dataTables.bootstrap5.min.js"></script>
  <script src="assets/js/table-datatable.js"></script>
	
  <!--app-->
  <script src="assets/js/app.js"></script>
  
</body>

</html>