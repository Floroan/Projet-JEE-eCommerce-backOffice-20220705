<%@page import="model.Details_commande"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Adresse_livraison"%>
<%@page import="model.Commande"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
					<div class="breadcrumb-item active ps-3"> Client: Mme/M <%= cmd.getU().getNom() + " " + cmd.getU().getPrenom() %></div>
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
										<div class="col-md-4">
											<label for="validationCustom01" class="form-label">Commande ID</label>
											<input type="text" class="form-control" id="validationCustom01" value="<%= cmd.getId() %>" readonly="readonly" required>
										</div>
										<div class="col-md-4">
											<label for="validationCustom02" class="form-label">Total</label>
											<input type="text" class="form-control" id="validationCustom02" value="<%= cmd.getTotal() %> €"  readonly="readonly" required>
										</div>
										<div class="col-md-4">
											<label for="validationCustomUsername" class="form-label">Client, prénom et nom</label>
											<div class="input-group has-validation"> <span class="input-group-text" id="inputGroupPrepend"></span>
												<input type="text" class="form-control" id="validationCustomUsername" aria-describedby="inputGroupPrepend" value="<%= cmd.getU().getPrenom() + " " + cmd.getU().getNom() %>" required>
												<div class="invalid-feedback">Please choose a username.</div>
											</div>
										</div>
										<div class="col-md-12">
										<label for="validationCustom03" class="form-label">Adresse de livraison</label>
										</div>
										<div class="col-md-6">
											<label for="validationCustom03" class="form-label">ligne adresse</label>
											<input type="text" class="form-control" id="validationCustom03" value="<%= cmd.getAdresse().getAdresse() %>" required>
											<div class="invalid-feedback">Please provide a valid city.</div>
										</div>
										<div class="col-md-6">
											<label for="validationCustom03" class="form-label">Ville</label>
											<input type="text" class="form-control" id="validationCustom03" value="<%= cmd.getAdresse().getVille() %>" required>
											<div class="invalid-feedback">Please provide a valid city.</div>
										</div>
										<div class="col-md-3">
											<label for="validationCustom04" class="form-label">Pays</label>
											<input type="text" class="form-control" id="validationCustom03" value="<%= cmd.getAdresse().getPays() %>" required>					
											<div class="invalid-feedback">Please select a valid state.</div>
										</div>
										<div class="col-md-3">
											<label for="validationCustom05" class="form-label">code postal</label>
											<input type="text" class="form-control" id="validationCustom05" value="<%= cmd.getAdresse().getCp() %>" required>
											<div class="invalid-feedback">Please provide a valid zip.</div>
										</div>
										
										<div class="col-md-12">
										<label for="validationCustom03" class="form-label">Détails de la commande</label>
										</div>
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
								</div>
							</div>
						</div>
						
						
						<div id="newAddress"></div>
						<h6 class="mb-0 text-uppercase">Nouvelle adresse pour ce client</h6>
						<hr/>
						<div class="card">
							<div class="card-body">
								<div class="p-4 border rounded">
								
									<form class="row g-3" method="post">
										<div class="col-md-12">
											<label for="validationDefault01" class="form-label">Ligne adresse</label>
											<input name="newAdresse" type="text" class="form-control" id="validationDefault01" required>
										</div>
										<div class="col-md-4">
											<label for="validationDefault02" class="form-label">Ville</label>
											<input name="newVille" type="text" class="form-control" id="validationDefault02" required>
										</div>
										<div class="col-md-4">
											<label for="validationDefaultUsername" class="form-label">code postale</label>
											<div class="input-group"> <span class="input-group-text" id="inputGroupPrepend2">CP</span>
												<input name="newCP" type="text" class="form-control" id="validationDefaultUsername" aria-describedby="inputGroupPrepend2" required>
											</div>
										</div>
										<div class="col-md-6">
											<label for="validationDefault03" class="form-label">Pays</label>
											<input name="newPays" type="text" class="form-control" id="validationDefault03" required>
										</div>
							
										<div class="col-12">
											<button class="btn btn-primary" type="submit" name="nouvelleAdresse">Enregistrer</button>
										</div>
									</form>
								</div>
							</div>
						</div>
						
						
						<jsp:include page="/formNouvelleAdresse.jsp"></jsp:include>
						
						<h6 class="mb-0 text-uppercase">Server side</h6>
						<hr/>
						<div class="card">
							<div class="card-body">
								<div class="p-4 border rounded">
									<form class="row g-3">
										<div class="col-md-4">
											<label for="validationServer01" class="form-label">First name</label>
											<input type="text" class="form-control is-valid" id="validationServer01" value="Mark" required>
											<div class="valid-feedback">Looks good!</div>
										</div>
										<div class="col-md-4">
											<label for="validationServer02" class="form-label">Last name</label>
											<input type="text" class="form-control is-valid" id="validationServer02" value="Otto" required>
											<div class="valid-feedback">Looks good!</div>
										</div>
										<div class="col-md-4">
											<label for="validationServerUsername" class="form-label">Username</label>
											<div class="input-group has-validation"> <span class="input-group-text" id="inputGroupPrepend3">@</span>
												<input type="text" class="form-control is-invalid" id="validationServerUsername" aria-describedby="inputGroupPrepend3 validationServerUsernameFeedback" required>
												<div id="validationServerUsernameFeedback" class="invalid-feedback">Please choose a username.</div>
											</div>
										</div>
										<div class="col-md-6">
											<label for="validationServer03" class="form-label">City</label>
											<input type="text" class="form-control is-invalid" id="validationServer03" aria-describedby="validationServer03Feedback" required>
											<div id="validationServer03Feedback" class="invalid-feedback">Please provide a valid city.</div>
										</div>
										<div class="col-md-3">
											<label for="validationServer04" class="form-label">State</label>
											<select class="form-select is-invalid" id="validationServer04" aria-describedby="validationServer04Feedback" required>
												<option selected disabled value="">Choose...</option>
												<option>...</option>
											</select>
											<div id="validationServer04Feedback" class="invalid-feedback">Please select a valid state.</div>
										</div>
										<div class="col-md-3">
											<label for="validationServer05" class="form-label">Zip</label>
											<input type="text" class="form-control is-invalid" id="validationServer05" aria-describedby="validationServer05Feedback" required>
											<div id="validationServer05Feedback" class="invalid-feedback">Please provide a valid zip.</div>
										</div>
										<div class="col-12">
											<div class="form-check">
												<input class="form-check-input is-invalid" type="checkbox" value="" id="invalidCheck3" aria-describedby="invalidCheck3Feedback" required>
												<label class="form-check-label" for="invalidCheck3">Agree to terms and conditions</label>
												<div id="invalidCheck3Feedback" class="invalid-feedback">You must agree before submitting.</div>
											</div>
										</div>
										<div class="col-12">
											<button class="btn btn-primary" type="submit">Submit form</button>
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
											<label for="validationTextarea" class="form-label">Textarea</label>
											<textarea class="form-control is-invalid" id="validationTextarea" placeholder="Required example textarea" required></textarea>
											<div class="invalid-feedback">Please enter a message in the textarea.</div>
										</div>
										<div class="form-check mb-3">
											<input type="checkbox" class="form-check-input" id="validationFormCheck1" required>
											<label class="form-check-label" for="validationFormCheck1">Check this checkbox</label>
											<div class="invalid-feedback">Example invalid feedback text</div>
										</div>
										<div class="form-check">
											<input type="radio" class="form-check-input" id="validationFormCheck2" name="radio-stacked" required>
											<label class="form-check-label" for="validationFormCheck2">Toggle this radio</label>
										</div>
										<div class="form-check mb-3">
											<input type="radio" class="form-check-input" id="validationFormCheck3" name="radio-stacked" required>
											<label class="form-check-label" for="validationFormCheck3">Or toggle this other radio</label>
											<div class="invalid-feedback">More example invalid feedback text</div>
										</div>
										<div class="mb-3">
											<select class="form-select" required aria-label="select example">
												<option value="">Open this select menu</option>
												<option value="1">One</option>
												<option value="2">Two</option>
												<option value="3">Three</option>
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
						<h6 class="mb-0 text-uppercase">Tooltips</h6>
						<hr/>
						<div class="card">
							<div class="card-body">
								<div class="p-4 border rounded">
									<form class="row g-3 needs-validation" novalidate>
										<div class="col-md-4 position-relative">
											<label for="validationTooltip01" class="form-label">First name</label>
											<input type="text" class="form-control" id="validationTooltip01" value="Mark" required>
											<div class="valid-tooltip">Looks good!</div>
										</div>
										<div class="col-md-4 position-relative">
											<label for="validationTooltip02" class="form-label">Last name</label>
											<input type="text" class="form-control" id="validationTooltip02" value="Otto" required>
											<div class="valid-tooltip">Looks good!</div>
										</div>
										<div class="col-md-4 position-relative">
											<label for="validationTooltipUsername" class="form-label">Username</label>
											<div class="input-group has-validation"> <span class="input-group-text" id="validationTooltipUsernamePrepend">@</span>
												<input type="text" class="form-control" id="validationTooltipUsername" aria-describedby="validationTooltipUsernamePrepend" required>
												<div class="invalid-tooltip">Please choose a unique and valid username.</div>
											</div>
										</div>
										<div class="col-md-6 position-relative">
											<label for="validationTooltip03" class="form-label">City</label>
											<input type="text" class="form-control" id="validationTooltip03" required>
											<div class="invalid-tooltip">Please provide a valid city.</div>
										</div>
										<div class="col-md-3 position-relative">
											<label for="validationTooltip04" class="form-label">State</label>
											<select class="form-select" id="validationTooltip04" required>
												<option selected disabled value="">Choose...</option>
												<option>...</option>
											</select>
											<div class="invalid-tooltip">Please select a valid state.</div>
										</div>
										<div class="col-md-3 position-relative">
											<label for="validationTooltip05" class="form-label">Zip</label>
											<input type="text" class="form-control" id="validationTooltip05" required>
											<div class="invalid-tooltip">Please provide a valid zip.</div>
										</div>
										<div class="col-12">
											<button class="btn btn-primary" type="submit">Submit form</button>
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