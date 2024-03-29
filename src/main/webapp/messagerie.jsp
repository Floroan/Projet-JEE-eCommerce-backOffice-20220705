<%@page import="tools.Constantes"%>
<%@page import="model.Contact"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
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

  <title>Hytek</title>
</head>

<body>

<% ArrayList<Contact> allmess = (ArrayList) request.getAttribute("allMessages"); %>

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
				<!--start email wrapper-->
				<div class="email-wrapper">
					<div class="email-sidebar">
						<div class="email-sidebar-header d-grid"> <a href="javascript:;" class="btn btn-primary compose-mail-btn"><i class="bi bi-plus-lg me-2"></i>Compose</a>
						</div>
						<div class="email-sidebar-content">
							<div class="email-navigation">
								<div class="list-group list-group-flush"> <a href="GestionMessagerie?" class="list-group-item active d-flex align-items-center"><i class='bx bxs-inbox me-3 font-20'></i><span>Tous</span><span class="badge bg-primary rounded-pill ms-auto">${count }</span></a>
									<a href="GestionMessagerie?etat=0" class="list-group-item d-flex align-items-center"><i class='bx bxs-star me-3 font-20'></i><span>Non Lus</span></a>
									<a href="GestionMessagerie?etat=1" class="list-group-item d-flex align-items-center"><i class='bx bxs-alarm-snooze me-3 font-20'></i><span>En cours</span></a>
									<a href="GestionMessagerie?etat=2" class="list-group-item d-flex align-items-center"><i class='bx bxs-send me-3 font-20'></i><span>Résolus</span></a>
									<a href="GestionMessagerie?etat=3" class="list-group-item d-flex align-items-center"><i class='bx bxs-file-blank me-3 font-20'></i><span>Non résolus</span><span class="badge bg-primary rounded-pill ms-auto"></span></a>
<!-- 									<a href="javascript:;" class="list-group-item d-flex align-items-center"><i class='bx bxs-bookmark me-3 font-20'></i><span>Important</span></a> -->
<!-- 									<a href="javascript:;" class="list-group-item d-flex align-items-center"><i class='bx bxs-message-rounded-error me-3 font-20'></i><span>Chats</span></a> -->
<!-- 									<a href="javascript:;" class="list-group-item d-flex align-items-center"><i class='bx bx-mail-send me-3 font-20'></i><span>Scheduled</span></a> -->
<!-- 									<a href="javascript:;" class="list-group-item d-flex align-items-center"><i class='bx bxs-envelope-open me-3 font-20'></i><span>All Mail</span></a> -->
<!-- 									<a href="javascript:;" class="list-group-item d-flex align-items-center"><i class='bx bxs-info-circle me-3 font-20'></i><span>Spam</span></a> -->
									<a href="javascript:;" class="list-group-item d-flex align-items-center"><i class='bx bxs-trash-alt me-3 font-20'></i><span>Trash</span></a>
								</div>
							</div>
							
							<div class="email-meeting">
								
									<div class="list-group-item"><span>Qui ?</span>
									</div> 
									<div class="list-group-item email-hangout cursor-pointer border-top">
										<div class="d-flex align-items-center">
											<div class="chat-user-online">
												<img src="assets/images/avatars/avatar-1.png" width="42" height="42" class="rounded-circle" alt="" />
											</div>
											<div class="flex-grow-1 ms-3">
												<p class="mb-0">Vous êtes connecté en tant que: ${admin.nom }</p>
											</div>
										</div>
									</div>
								
							</div>
						</div>
					</div>
					
<!-- 					header de la messagerie -->
								<jsp:include page="headerMessagerie.jsp"></jsp:include>
<!-- 					header de la messagerie -->
					
					
					<div class="email-content">
						<div class="">
							<div class="email-list">
							<% for(Contact c : allmess){ %>
								<a href="DetailMessage?id=<%= c.getId()%>">
									<div class="d-md-flex align-items-center email-message px-3 py-1">
										<div class="d-flex align-items-center email-actions">
											<input class="form-check-input" type="checkbox" value="" /> <i class='bx bx-star font-20 mx-2 email-star'></i>
											<p class="mb-0"><b><%= c.getSujet()  %></b>
											</p>
										</div>
										<div class="">
										
										<% if(c.getMessage().length() > 40){  %>
											<p class="mb-0"><%= c.getMessage().substring(0, 40) %></p>
										<%}else { %>
											<p class="mb-0"><%= c.getMessage() %></p>
										<%} %>
											
										</div>
										<div class="ms-4">
											<p class="mb-0 ms-4"><%= c.getU().getPrenom() + " " + c.getU().getNom() %></p>
										</div>
										<div class="ms-auto">
											<p class="mb-0 email-time"><%= c.getDate() %></p>
										</div>
									</div>
								</a>
							<%} %>
							</div>
						</div>
					</div>
					
					
					<!--start compose mail-->
					<div class="compose-mail-popup">
						<div class="card">
							<div class="card-header bg-dark text-white py-2 cursor-pointer">
								<div class="d-flex align-items-center">
									<div class="compose-mail-title">New Message</div>
									<div class="compose-mail-close ms-auto">x</div>
								</div>
							</div>
							<div class="card-body">
								<div class="email-form">
									<div class="mb-3">
										<input type="text" class="form-control" placeholder="To" />
									</div>
									<div class="mb-3">
										<input type="text" class="form-control" placeholder="Subject" />
									</div>
									<div class="mb-3">
										<textarea class="form-control" placeholder="Message" rows="10" cols="10"></textarea>
									</div>
									<div class="mb-0">
										<div class="d-flex align-items-center">
											<div class="">
												<div class="btn-group">
													<button type="button" class="btn btn-primary">Action</button>
													<button type="button" class="btn btn-primary split-bg-primary dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown">	<span class="visually-hidden">Toggle Dropdown</span>
													</button>
													<div class="dropdown-menu">	<a class="dropdown-item" href="javascript:;">Action</a>
														<a class="dropdown-item" href="javascript:;">Another action</a>
														<a class="dropdown-item" href="javascript:;">Something else here</a>
														<div class="dropdown-divider"></div>	<a class="dropdown-item" href="javascript:;">Separated link</a>
													</div>
												</div>
											</div>
											<div class="ms-2">
												<button type="button" class="btn border-0 btn-sm btn-white"><i class="lni lni-text-format"></i>
												</button>
												<button type="button" class="btn border-0 btn-sm btn-white"><i class='bx bx-link-alt'></i>
												</button>
												<button type="button" class="btn border-0 btn-sm btn-white"><i class="lni lni-emoji-tounge"></i>
												</button>
												<button type="button" class="btn border-0 btn-sm btn-white"><i class="lni lni-google-drive"></i>
												</button>
											</div>
											<div class="ms-auto">
												<button type="button" class="btn border-0 btn-sm btn-white"><i class="lni lni-trash"></i>
												</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--end compose mail-->
					
					
					
					<!--start email overlay-->
					<div class="overlay email-toggle-btn-mobile"></div>
					<!--end email overlay-->
				</div>
				<!--end email wrapper-->
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
  <script src="assets/js/app-emailbox.js"></script>
  

</body>

</html>