<%@page import="model.Details_commande"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Adresse_livraison"%>
<%@page import="model.Commande"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/functions" %> --%>
<!DOCTYPE html>
<html>
<% Commande cmd = (Commande)request.getAttribute("commande"); %>
<% ArrayList<Adresse_livraison> adresses = (ArrayList<Adresse_livraison>) request.getAttribute("adresses"); %>
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
<title>Commande numéro ${commande.id }</title>
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
							Numéro de commande: ${commande.id }	
					</div>
					<div class="breadcrumb-item active ps-3"> Client: Mme/M ${ commande.u.nom }  ${ commande.u.prenom }</div>
				</div>
				<!--end breadcrumb-->
				
				<div class="row">
					<div class="col-xl-9 mx-auto">
						<h6 class="mb-0 text-uppercase">Fiche de la commande</h6>
						<hr/>
						<div class="card">
							<div class="card-body">
								<div class="p-4 border rounded">
									<form class="row g-3 needs-validation" novalidate>
										<div class="col-md-8">
											<label for="validationCustom01" class="form-label">Rechercher</label>
											<input type="text" class="form-control" id="validationCustom01" placeholder="rechercher" name="rechercher" required>
										</div>
										<div class="col-md-4">
											<label for="validationCustom01" class="form-label">Commande ID</label>
											<input type="text" class="form-control" id="validationCustom01" value="${commande.id }" readonly="readonly" required>
										</div>
										<div class="col-md-4">
											<label for="validationCustom02" class="form-label">Total</label>
											<input type="text" class="form-control" id="validationCustom02" value="${ commande.total } €"  readonly="readonly" required>
										</div>
										<div class="col-md-4">
											<label for="validationCustomUsername" class="form-label">Client, prénom et nom</label>
											<div class="input-group has-validation"> <span class="input-group-text" id="inputGroupPrepend"></span>
												<input type="text" class="form-control" id="validationCustomUsername" aria-describedby="inputGroupPrepend" value="${ commande.u.prenom }  ${ commande.u.nom }" required>
												<div class="invalid-feedback">Please choose a username.</div>
											</div>
										</div>
										<div class="col-md-12">
										<label for="validationCustom03" class="form-label">Adresse de livraison</label>
										</div>
										<div class="col-md-6">
											<label for="validationCustom03" class="form-label">ligne adresse</label>
											<input type="text" class="form-control" id="validationCustom03" value="${ commande.adresse.adresse }" required>
											<div class="invalid-feedback">Please provide a valid adress line.</div>
										</div>
										<div class="col-md-6">
											<label for="validationCustom05" class="form-label">code postal</label>
											<input type="text" class="form-control" id="validationCustom05" value="${ commande.adresse.cp }" required>
											<div class="invalid-feedback">Please provide a valid zip.</div>
										</div>
										<div class="col-md-6">
											<label for="validationCustom03" class="form-label">Ville</label>
											<input type="text" class="form-control" id="validationCustom03" value="${ commande.adresse.ville }" required>
											<div class="invalid-feedback">Please provide a valid city.</div>
										</div>
										<div class="col-md-6">
											<label for="validationCustom04" class="form-label">Pays</label>
											<input type="text" class="form-control" id="validationCustom03" value="${ commande.adresse.pays }" required>					
											<div class="invalid-feedback">Please select a valid state.</div>
										</div>

										
										<% if(cmd != null){ %>
										
										<%for(Details_commande dc : cmd.getDetails()){ %>
										 
										<div class="col-md-6">
											<label for="validationCustom05" class="form-label">Produit</label>
											<input type="text" class="form-control" id="validationCustom05" value="<%= dc.getP().getTitre() %>"  readonly="readonly" required>
										</div>
										<div class="col-md-6">
											<label for="validationCustom05" class="form-label">Quantité</label>
											<input type="text" class="form-control" id="validationCustom05" value="<%= dc.getQte() %>" required>
										</div>
										<%} %>


										<% if (adresses != null){ %>
										<div class="col-md-12">
											<label for="validationCustom04" class="form-label">Changer l'adresse de livraison</label>
											<select class="form-select" id="validationCustom04" required>
												<option selected disabled value="">Choisir une adresse existante</option>
												<% for(Adresse_livraison ad : adresses){ %>
												<option value=<%= ad.getId() %>> <%= ad.getAdresse() + ", " + ad.getVille() + " " + ad.getCp() + ", " + ad.getPays() %></option>
												<%}%>
											</select>
											<div class="invalid-feedback">Please select a valid state.</div>
										</div>
										<div class="col-12">
											<a  href="#newAddress" class="btn btn-primary" type="submit">Nouvelle adresse</a>
										</div>
										<%} %>
										
						<div class="dropdown">
                          <button class="btn btn-primary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">Actions</button>
                          <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#" data-bs-toggle="tooltip" data-bs-original-title="Modifier les changements apportés" >Modifier</a>
                            </li>
                            <% String archive= " "; %>
                            <%if(cmd.getArchiver()==0){ archive= "non"; }else{ archive="oui";} %>
                            <li><a class="dropdown-item" href="#" data-bs-toggle="tooltip" data-bs-original-title="Voulez-vous archiver cette commande ? Statut actuel <%= archive %>">Archiver</a>
                          </ul>
                        </div>
									</form>
									
										<%}else{ %>
										 <div class="col-md-8">
											<label for="validationCustom01" class="form-label">Rechercher</label>
											<input type="text" class="form-control" id="validationCustom01" placeholder="rechercher" name="rechercher" required>
										</div>
										<%} %>
								</div>
							</div>
						</div>
						
						
<!-- 						<div id="newAddress"></div> -->
<!-- 						<h6 class="mb-0 text-uppercase">Nouvelle adresse pour ce client</h6> -->
<!-- 						<hr/> -->
<!-- 						<div class="card"> -->
<!-- 							<div class="card-body"> -->
<!-- 								<div class="p-4 border rounded"> -->
								
<!-- 									<form class="row g-3" method="post"> -->
<!-- 										<div class="col-md-12"> -->
<!-- 											<label for="validationDefault01" class="form-label">Ligne adresse</label> -->
<!-- 											<input name="newAdresse" type="text" class="form-control" id="validationDefault01" required> -->
<!-- 										</div> -->
<!-- 										<div class="col-md-4"> -->
<!-- 											<label for="validationDefault02" class="form-label">Ville</label> -->
<!-- 											<input name="newVille" type="text" class="form-control" id="validationDefault02" required> -->
<!-- 										</div> -->
<!-- 										<div class="col-md-4"> -->
<!-- 											<label for="validationDefaultUsername" class="form-label">code postale</label> -->
<!-- 											<div class="input-group"> <span class="input-group-text" id="inputGroupPrepend2">CP</span> -->
<!-- 												<input name="newCP" type="text" class="form-control" id="validationDefaultUsername" aria-describedby="inputGroupPrepend2" required> -->
<!-- 											</div> -->
<!-- 										</div> -->
<!-- 										<div class="col-md-6"> -->
<!-- 											<label for="validationDefault03" class="form-label">Pays</label> -->
<!-- 											<input name="newPays" type="text" class="form-control" id="validationDefault03" required> -->
<!-- 										</div> -->
							
<!-- 										<div class="col-12"> -->
<!-- 											<button class="btn btn-primary" type="submit" name="nouvelleAdresse">Enregistrer</button> -->
<!-- 										</div> -->
<!-- 									</form> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
						
						
						<jsp:include page="/formNouvelleAdresse.jsp"></jsp:include>
						

						<h6 class="mb-0 text-uppercase">Supported elements</h6>
						<hr/>
						<div class="card">
							<div class="card-body">
								<div class="p-4 border rounded">
									<form class="was-validated">

										<div class="mb-3">
											<select class="form-select" required aria-label="select example">
												<option selected disabled ="">filtres de recherche</option>
												<option value="1">Client, nom & prénom</option>
												<option value="2">ID Commande</option>
											</select>
											<div class="invalid-feedback">Example invalid select feedback</div>
										</div>
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