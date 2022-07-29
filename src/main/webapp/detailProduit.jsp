<%@page import="tools.Constantes"%>
<%@page import="model.Image"%>
<%@page import="model.Sous_categorie"%>
<%@page import="model.Produit"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <jsp:useBean id="handler" class="tools.ValideForm" />
<!DOCTYPE html>
<html>

<% Produit p = (Produit)request.getAttribute("prod"); %>
<% ArrayList<Sous_categorie> ss_cats = (ArrayList<Sous_categorie>) request.getAttribute("ss_cats"); %>
<% ArrayList<Image> images = (ArrayList<Image>) request.getAttribute("images"); %>
<% Sous_categorie ss_cat_prod = (Sous_categorie)request.getAttribute("ss_cat_produit"); %>

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
						
						
						  <% if(request.getAttribute("message") != null){ %>
		                    <div class="alert border-0 bg-light-danger alert-dismissible fade show py-2">
		                    <div class="d-flex align-items-center">
		                      <div class="fs-3 text-danger"><i class="bi bi-x-circle-fill"></i>
		                      </div>
		                      <div class="ms-3">
		                        <div class="text-danger">${message}</div>
		                      </div>
		                    </div>
		                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		                  </div>
                        
                        <%} %>
                        
                        
						<div class="card">
							<div class="card-body">
								<div class="p-4 border rounded">
									<form class="row g-3 needs-validation" method="post">
										<div class="col-md-4">
											<label for="validationCustom01" class="form-label">Produit ID</label>
											<input type="text" class="form-control" id="validationCustom01" name="<%= Constantes.idProd %>" value="<%= p.getId() %>" readonly="readonly" required>	
										</div>
										<div class="col-md-8">
											<label for="validationCustom02" class="form-label">Titre</label>
											<input type="text" class="form-control" id="validationCustom02" name="<%= Constantes.prodTitre %>" value="<%= p.getTitre() %> €" required>
											<div class="invalid-feedback">Saisir un titre</div>
										</div>
										<div class="col-md-12">
											<label for="validationCustomUsername" class="form-label">Description</label>
											<div class="input-group has-validation"> <span class="input-group-text" id="inputGroupPrepend"></span>
												<textarea type="text" class="form-control" id="validationCustomUsername" aria-describedby="inputGroupPrepend" value="" name="<%= Constantes.descripProd %>" required><%= p.getDescription()%></textarea>
												<div class="invalid-feedback">Saisir une description</div>
											</div>
										</div>
										<div class="col-md-12">
											<label for="validationCustom05" class="form-label">Image Principale</label>
											<input type="hidden" name="<%= Constantes.mainImgProd %>" value="<%= p.getImage()%>"/>
											<img src="<%= p.getImage() %>" class="d-block w-100" alt="...">
										<div class="col-12">
											<a  href="#newImage" class="btn btn-primary" type="submit">Nouvelle image principale ?</a>
										</div>
<%-- 											<input type="text" class="form-control" id="validationCustom05" id="<%= Constantes.mainImgProd %>" name="<%= Constantes.mainImgProd %>" placeholder="Nouveau lien pour l'image principale" > --%>
<%-- 											<a href="DetailProduit?id=<%= p.getId()%>&del " class="btn btn-primary" >change</a> --%>
<!-- 											<input name="change2" class="btn btn-primary" type="submit" value="Change2"> -->
<!-- 										</div> -->
										</div>
										
										<div class="col-md-12">
										<label for="validationCustom03" class="form-label">Détails du produit</label>
										</div>
										<div class="col-md-12">
											<label for="validationCustom04" class="form-label">Associer à une sous-catégorie:</label>
											<select class="form-select" id="validationCustom04" name="<%= Constantes.ssCatProd %>" required>
												<option value="<%= ss_cat_prod.getId() %>">Choisir une sous-catégorie existante, actuellement: <%= ss_cat_prod.getTitre()%> </option>
												<% for(Sous_categorie sc : ss_cats ) { %>
												<option value=<%= sc.getId() %>> <%= sc.getTitre() %></option>
												<%}%>
											</select>
											<div class="invalid-feedback">Sélectionner une sous catégorie</div>
										</div>

										<div class="col-md-6">
											<label for="validationCustom05" class="form-label">Stock</label>
											<input type="text" class="form-control" id="validationCustom05"name="<%= Constantes.stockProd %>" value="<%= p.getStock() %>" required>
										</div>
										<div class="col-md-6">
											<label for="validationCustom05" class="form-label">Fixer le stock minimal</label>
											<input type="text" class="form-control" id="validationCustom05" name="<%= Constantes.stockMinProd %>" value="<%= p.getStock_min() %>" required>
										</div>
										<div class="col-md-6">
											<label for="validationCustom05" class="form-label">Prix Unitaire</label>
											<input type="text" class="form-control" id="validationCustom05"  name="<%= Constantes.PUProd %>" value="<%= p.getPrix() %>" required>
										</div>
							<% String archive= " "; %>
                            <% if(p.getArchiver()==0){ archive= "non"; }else{ archive="oui";} %>
										<div class="col-md-6">
											<label for="validationCustom05" class="form-label">archivé ?</label>
											<input type="text" class="form-control" id="validationCustom05"  name="<%= Constantes.archiveProd %>" readonly="readonly" value="<%= archive %>" title="Non éditable, voir le bouton 'actions' pour archiver le produit" required>
<%-- 											<select  class="form-select" name="<%= Constantes.archiveProd %>" required> --%>
<!-- 												<option>Non</option> -->
<!-- 												<option>Oui</option> -->
<!-- 											</select> -->
										</div>
										
						<div class="dropdown">
                          <button class="btn btn-primary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">Actions</button>
                          <ul class="dropdown-menu">
                            <li><button class="dropdown-item" name="<%= Constantes.modifier %>" type="submit" data-bs-toggle="tooltip" data-bs-original-title="Modifier les changements apportés" >Modifier</button>
                            </li>

                            <li><button class="dropdown-item" name="<%= Constantes.archiver %>" data-bs-toggle="tooltip" data-bs-original-title="Voulez-vous archiver ce produit ? Statut actuel <%= archive %>">Archiver</a>
                          </ul>
                        </div>
						</form>
						
										
						<div class="card">
						<label for="validationCustom03" class="form-label">Images associées</label>
			                <div class="card-body">
			                  <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">

			
			                    <div class="carousel-inner">
			                    <%int i = 0; %>
			                    <%for( i = 0; i < p.getImages().size(); i++ ){ %>  
			                    
			                 	<% Image im = p.getImages().get(i); %> 
			                 	
			                      <div class="carousel-item <% if(i == 1){%>active<%}%>" id="slide <%=i%>">
			                        <img src="<%= im.getUrl() %>" class="d-block w-100" alt="...">
				                        <form method="post">
					                        <input type="hidden" name="imgToDelete" value="<%= im.getId() %>" />
					                      	<div class="carousel-caption d-none d-md-block">
					                             <button name="deleteImg"  type="submit" class="text-danger" data-bs-toggle="tooltip" data-bs-placement="bottom" title="" data-bs-original-title="Supp" aria-label="Delete"><i class="lni lni-trash"></i></button>
					                        </div>
				                         </form>
			                      </div>
			                      
			                       <%} %>
			                    </div>
			                    
			                    <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-bs-slide="prev">	
			                      <span class="visually-hidden">Previous</span>
			                    </a>
			                    <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-bs-slide="next">	<span class="carousel-control-next-icon" aria-hidden="true"></span>
			                      <span class="visually-hidden">Next</span>
			                    </a>
			                  </div>
			                </div>
			              </div>

							<label for="validationCustom03" class="form-label">Intégrer de nouvelles images</label>
									<form method="post">
										<input type="hidden" name="idProd4AddImg" value="<%= p.getId() %>" />
										<div class="col-md-12">
											<input type="text" class="form-control" id="validationCustom05" name="newImage" placeholder="Coller le lien ici" required >
											<label for="validationCustom05" class="form-label"></label>
										</div>
										<div class="col-md-6"><input name="recImg" class="btn btn-primary" type="submit" value="Enregistrer"></div>
									</form>
									
									
						<div id="newImage"></div>
						<h6 class="mb-0 text-uppercase">Nouvelle image principale</h6>
						<hr/>
						<div class="card">
							<div class="card-body">
								<div class="p-4 border rounded">
								
									<form class="row g-3" method="post">
									<input type="hidden" name="<%= Constantes.idProd %>" value="<%= p.getId()%>">
										<div class="col-md-12">
											<label for="validationDefault01" class="form-label">Collez le lien ici</label>
											<input name="<%= Constantes.mainImgProd %>" type="text" class="form-control" id="validationDefault01" placeholder="Exemple: http://../../monImage.jpg..." required>
										</div>
										<div class="col-12">
											<button class="btn btn-primary" type="submit" name="newMainImg" >Enregistrer</button>
											<p>${message }</p>
										</div>
									</form>
								</div>
							</div>
						</div>
									
						<h6 class="mb-0 text-uppercase">Supported elements</h6>
						<hr/>
						<div class="card">
							<div class="card-body">
								<div class="p-4 border rounded">
									<form class="was-validated">

										<div class="mb-3">
											<input type="file" class="form-control" aria-label="file example" required>
											<div class="invalid-feedback">Example invalid form file feedback</div>
										</div>
										<div class="mb-3">
											<button class="btn btn-primary" type="submit" disabled>Submit form</button>
										</div>
									</form>
								</div>
							</div>
						</div>
							
								</div>
							</div>
						</div>	
						
					</div>	
				</div><!--end row-->	
				
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
  <!--app-->
  <script src="assets/js/app.js"></script>
  

</body>


</html>