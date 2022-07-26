<%@page import="model.Commentaire"%>
<%@page import="tools.Constantes"%>
<%@page import="model.Image"%>
<%@page import="model.Sous_categorie"%>
<%@page import="model.Produit"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<% Produit p = (Produit)request.getAttribute("prod"); %>
<% ArrayList<Commentaire> comms = (ArrayList<Commentaire>) request.getAttribute("commentaires"); %>


<head>


  <!-- Required meta tags -->
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" href="assets/images/favicon-32x32.png" type="image/png" />
  <!--plugins-->
  <link href="assets/plugins/simplebar/css/simplebar.css" rel="stylesheet" />
  <link href="assets/plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet" />
  <link href="assets/plugins/metismenu/css/metisMenu.min.css" rel="stylesheet" />
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
<title>Produit numéro <%= p.getId() %></title>
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
				
				<div class="page-breadcrumb d-none d-sm-flex align-items-center mb-3">
					<div class="breadcrumb-title pe-3">
							Numéro de produit: <%= p.getId() %>
							
					</div>
					<div class="breadcrumb-item active ps-3"> Titre: <%= p.getTitre() %></div>
				</div>
				<!--end breadcrumb-->
				
				<div class="row">
				
					<div class="col-xl-9 mx-auto">
						<h6 class="mb-0 text-uppercase">Fiche du produit</h6>
						<hr/>
						<div class="card">
							<div class="card-body">
								<div class="p-4 border rounded">
									<div class="row g-3" >
										<div class="col-md-4">
											<label class="form-label">Produit ID</label>
											<p type="text"><%= p.getId() %></p>
										</div>
										<div class="col-md-8">
											<label class="form-label">Titre</label>
											<p type="text"><%= p.getTitre() %></p>
										</div>
									</div>
									<div class="row g-3" >
											
											<div class="col-md-12">
											<label for="validationCustom03" class="form-label">Détails du produit</label>
											</div>
											<div class="col-md-4">
												<label for="validationCustom05" class="form-label">Stock</label>
												<p><%= p.getStock() %></p>
											</div>
											<div class="col-md-4">
												<label for="validationCustom05" class="form-label">Stock minimal</label>
												<p><%= p.getStock_min() %></p>
											</div>
											<div class="col-md-4">
												<label for="validationCustom05" class="form-label">Prix Unitaire</label>
												<p><%= p.getPrix() %>€</p>
											</div>
											
										</div>
										
										<div class="col-md-12">
											<label class="form-label">Image Principale</label>
											<img src="<%= p.getImage() %>" class="d-block w-100" alt="...">
										</div>
						</div>	
						</div>
						</div>
					</div>	
				</div><!--end row-->	
				
				
				<div class="row">
				 <a href="DetailProduit?id=<%= p.getId() %>" type="submit" class="btn btn-primary" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Modifier ce produit ?"><i class="bi bi-pencil-fill"></i></a>
<!-- 				<button class="btn btn-primary" type="submit" name="newMainImg" >Enregistrer</button> -->
				</div>
				
				<div class="row">
				<div class="card">
                 <div class="card-body">
                   <div class="d-flex align-items-center">
                   
                      <h5 class="mb-0">Commentaires</h5>
                       <form class="ms-auto position-relative">
                         <div class="position-absolute top-50 translate-middle-y search-icon px-3"><i class="bi bi-search"></i></div>
                         <input class="form-control ps-5" type="text" placeholder="search">
                       </form>
                   </div>
                   <div class="table-responsive mt-3">
                     <table id="example" class="table align-middle">
                     <%if(comms.size() > 0){ %>
                       <thead class="table-secondary">
                         <tr>
                          <th>ID</th>
                          <th>Commentaire</th>
                          <th>Nom, prénom</th>
                          <th>Note</th>
                          <th>Date</th>
                          <th>Archiver</th>
                          <th>Actions</th>
                         </tr>
                       </thead>
                       <tbody>
                       <% for (Commentaire cm : comms){ %>
                       	<form method="post">
                         <tr>
                         
                          <td><%=cm.getId() %></td>
                          
                           <td>
                             <div class="d-flex align-items-center gap-3 cursor-pointer">
                                <textarea name="areaComm" class="rounded-circle"  ><%= cm.getCommentaire() %></textarea>
                             </div>
                           </td>
                           
                           <td><%= cm.getUtilisateur().getNom() + " " + cm.getUtilisateur().getPrenom() %></td>
                           
                           <td><%= cm.getNote() %></td>
                           
                           <td><%= cm.getDate() %></td>
                         
                           <td><%= cm.getArchiver() %></td>
                           
                           <td>
                           
                           <input type="hidden" name="<%= Constantes.commentaireId %>" value="<%= cm.getId() %>" />
                             <div class="table-actions d-flex align-items-center gap-3 fs-6">
<!--                                <a href="Toto" class="text-primary" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Voir informations & statistiques"><i class="bi bi-eye-fill"></i></a> -->
                               <button type="submit" name="<%= Constantes.modifier %>" class="text-warning" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Editer/Corriger"><i class="bi bi-pencil-fill"></i></button>
                               <button type="submit" name="<%= Constantes.archiver %>" class="text-danger" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Archiver"><i class="lni lni-archive"></i></button>
                             </div>
                            
                           </td>
                         </tr>
                          </form>
                         <% } %>
                         
                       </tbody>
                       <%}else{ %>
                       	<tr><h3>${message }</h3></tr>
                       <%} %>
                     </table>
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
  <script src="assets/js/pace.min.js"></script>
    <script src="assets/plugins/datatable/js/jquery.dataTables.min.js"></script>
  <script src="assets/plugins/datatable/js/dataTables.bootstrap5.min.js"></script>
  <script src="assets/js/table-datatable.js"></script>
  <!--app-->
  <script src="assets/js/app.js"></script>
  

</body>


</html>