<%@page import="model.Image"%>
<%@page import="model.Sous_categorie"%>
<%@page import="model.Produit"%>
<%@page import="java.util.ArrayList"%>
<%@page import="tools.Constantes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<% ArrayList<Sous_categorie> ss_cats = (ArrayList<Sous_categorie>) request.getAttribute("ss_cats"); %>
<% ArrayList<Produit> last5 = (ArrayList<Produit>) request.getAttribute("last5"); %>
<%-- <% ArrayList<Image> images = (ArrayList<Image>) request.getAttribute("images"); %> --%>


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
<title>Hytek - ajouter un produit</title>
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
							
							
					</div>
					<div class="breadcrumb-item active ps-3"></div>
				</div>
				<!--end breadcrumb-->
				
				<div class="row">
					<div class="col-xl-9 mx-auto">
						<h6 class="mb-0 text-uppercase">Ajout produit</h6>
						<hr/>
						<div class="card">
							<div class="card-body">
								<div class="p-4 border rounded">
									<form class="row g-3 needs-validation" method="post">
<!-- 										<div class="col-md-4"> -->
<!-- 											<label for="validationCustom01" class="form-label">Produit ID</label> -->
<%-- 											<input type="text" class="form-control" id="validationCustom01" name="idProd" value="<%= p.getId() %>" readonly="readonly" required>	 --%>
<!-- 										</div> -->
										<div class="col-md-12">
											<label for="validationCustom02" class="form-label">Titre</label>
											<input type="text" class="form-control" id="validationCustom02" name="<%= Constantes.prodTitre %>" placeholder="Saisir un titre" required>
											<div class="invalid-feedback">Saisir un titre</div>
										</div>
										<div class="col-md-12">
											<label for="validationCustomUsername" class="form-label">Description</label>
											<div class="input-group has-validation"> 
												<textarea type="text" class="form-control" id="validationCustomUsername" aria-describedby="inputGroupPrepend" name="<%= Constantes.descripProd %>" placeholder="Saisir une description" required></textarea>
												<div class="invalid-feedback">Saisir une description</div>
											</div>
										</div>
										<div class="col-md-12">
											<label for="validationCustomUsername" class="form-label">Image principale</label>
											<div class="input-group has-validation"> 
												<textarea type="text" class="form-control" id="validationCustomUsername" aria-describedby="inputGroupPrepend" name="<%= Constantes.mainImgProd %>" placeholder="copier le lien de l'image" required></textarea>
												<div class="invalid-feedback">sélectionner une image</div>
											</div>
										</div>
										<div class="col-md-12">
										<label for="validationCustom03" class="form-label">Détails du produit</label>
										</div>
										
										<div class="col-md-12">
											<label for="validationCustom04" class="form-label">Associer à une sous-catégorie</label>
											<select class="form-select" id="validationCustom04" name="<%= Constantes.ssCatProd %>" required>
												<option selected disabled value="">Choisir une sous-catégorie existante</option>
												
												<% for(Sous_categorie sc : ss_cats ) { %>
												<option value="<%= sc.getId() %>" > <%= sc.getTitre() %></option>
												<%}%>
											</select>
											<div class="invalid-feedback">Sélectionner une sous-catégorie</div>
										</div>

										<div class="col-md-6">
											<label for="validationCustom05" class="form-label">Stock</label>
											<input type="text" class="form-control" id="validationCustom05" name="<%= Constantes.stockProd %>" placeholder="Renseigner le stock" required>
										</div>
										<div class="col-md-6">
											<label for="validationCustom05" class="form-label">Fixer le stock minimal</label>
											<input type="text" class="form-control" id="validationCustom05" name="<%= Constantes.stockMinProd %>" placeholder="Renseigner le stock minimal" required>
										</div>
										<div class="col-md-6">
											<label for="validationCustom05" class="form-label">Prix Unitaire</label>
											<input type="text" class="form-control" id="validationCustom05"  name="<%= Constantes.PUProd %>" placeholder="Renseigner le prix unitaire" required>
										</div>
										<div class="col-md-6">
											<label for="validationCustom05" class="form-label">archivage</label>
											<input type="number" class="form-control" id="validationCustom05" min="0" max="1" name="<%= Constantes.archiveProd %>" placeholder="0" required>
										</div>
										
						<div class="dropdown">
                          <button class="btn btn-primary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">Actions</button>
                          <ul class="dropdown-menu">
                          
                            <li>
                            	<button class="dropdown-item" name="ajouter" type="submit" data-bs-toggle="tooltip" data-bs-original-title="Ajouter le produit" >Ajouter</button>
                            </li>
                            
                            <li>
                            	<button class="dropdown-item" name="annuleDernierAjout" type="submit" data-bs-toggle="tooltip" data-bs-original-title="Annuler le précédent ajout" >Annuler</button>
                            </li>
                          </ul>
                        </div>
						</form>
						
						<div class="col-md-12">
						</br>
						</div>
									
									<form class="row g-3 needs-validation" method="post">
										<div class="col-md-12">
										<label for="validationCustom03" class="form-label">Intégrer des images</label>
											<select onClick="opener.location='https://www.google.com'" class="form-select" id="validationCustom04" name="selectProd" required>
												<option selected disabled value="">Sélectionner le produit</option>
												<% for(Produit p : last5 ) { %>
												<option value="<%= p.getId() %>" > <%=p.getId() + ", " + p.getTitre() %></option>
												<%}%>
											</select>											
											<div class="invalid-feedback">Confirmer le produit</div>
										</div>
									
										<div class="col-md-12">
												<label for="validationCustom04" class="form-label">Image</label>
												<input type="text" class="form-control" id="validationCustom05" name="newImage" placeholder="Coller le lien image par image" required >
										</div>
										<div class="col-md-6">
												<label for="validationCustom04" class="form-label"></label>
												<input name="recImg" class="btn btn-primary" type="submit" value="Enregistrer l'image">											
										</div>
										</form>
											
								</div>
							</div>
						</div>	
						
							</div>
						</div>		
				<!--end row-->
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