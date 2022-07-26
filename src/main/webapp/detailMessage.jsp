<%@page import="tools.Constantes"%>
<%@page import="model.Contact"%>
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

<% Contact ct = (Contact)request.getAttribute("contact"); %>

  <!--start wrapper-->
  <div class="wrapper">
    <!--start top header-->
    
     <!--end top header-->
		<jsp:include page="/Header"></jsp:include>
       <!--start sidebar -->
       	<jsp:include page="/SideBar"></jsp:include>
       <!--end sidebar -->

       <!--start content-->
          <main class="page-content">
            <!--start email wrapper-->
            <div class="email-wrapper">
              <div class="email-sidebar">
              
                <div class="email-sidebar-header d-grid"> 
                <a href="javascript:;" class="btn btn-primary compose-mail-btn"><i class="bi bi-plus-lg me-2"></i>Répondre</a>
                </div>
                
                <div class="email-sidebar-content">
                  <div class="email-navigation">
                  <form method="post">
                  <input type="hidden" name="<%= Constantes.idContact %>" value="<%= ct.getId()%>">
                  <p>Marquer comme</p>
                  <div class="mb-2">
                  <select class="form-select" name="etatMessage">
                  		<option value="<%= Constantes.enCours%>">en cours</option>
                  		<option value="<%= Constantes.resolu%>"><p>résolu</p></option>
                  		<option value="<%= Constantes.nonResolu%>">non résolu</option>
                  </select>
                  </div>
                  <div class="mb-3">
                  	<button name="editEtat" class="btn btn-primary">Envoyer</button>
                  </div>
                  </form>
                  
                    <div class="list-group list-group-flush"> <a href="app-emailbox.html" class="list-group-item active d-flex align-items-center"><i class='bx bxs-inbox me-3 font-20'></i><span>Inbox</span><span class="badge bg-primary rounded-pill ms-auto">7,513</span></a>
                      <a href="javascript:;" class="list-group-item d-flex align-items-center"><i class='bx bxs-star me-3 font-20'></i><span>Starred</span></a>
                      <a href="javascript:;" class="list-group-item d-flex align-items-center"><i class='bx bxs-envelope-open me-3 font-20'></i><span>Tous</span></a>
                      <a href="javascript:;" class="list-group-item d-flex align-items-center"><i class='bx bxs-trash-alt me-3 font-20'></i><span>Corbeille</span></a>
                    </div>
                  </div>
                  <div class="email-meeting">
                    <div class="list-group list-group-flush">
                      <div class="list-group-item"><span>Qui ?</span>
                      </div> <a href="javascript:;" class="list-group-item d-flex align-items-center"><i class='bx bxs-video me-3 font-20'></i><span>Start a meeting</span></a>
                      <a href="javascript:;" class="list-group-item d-flex align-items-center"><i class='bx bxs-group me-3 font-20'></i><span>Join a meeting</span></a>
                      <div class="list-group-item email-hangout cursor-pointer border-top">
                        <div class="d-flex align-items-center">
                          <div class="chat-user-online">
                            <img src="assets/images/avatars/avatar-1.png" width="42" height="42" class="rounded-circle" alt="" />
                          </div>
                          <div class="flex-grow-1 ms-2">
                            <p class="mb-0">${admin.nom }</p>
                          </div>
                          <div class="dropdown">
                            <div class="font-24 dropdown-toggle dropdown-toggle-nocaret" data-bs-toggle="dropdown"><i class='bx bx-plus'></i>
                            </div>
                            <div class="dropdown-menu dropdown-menu-right">	<a class="dropdown-item" href="javascript:;">Settings</a>
                              <div class="dropdown-divider"></div>	<a class="dropdown-item" href="javascript:;">Help & Feedback</a>
                              <a class="dropdown-item" href="javascript:;">Enable Split View Mode</a>
                              <a class="dropdown-item" href="javascript:;">Keyboard Shortcuts</a>
                              <div class="dropdown-divider"></div>	<a class="dropdown-item" href="javascript:;">Sign Out</a>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="email-header d-xl-flex align-items-center">
                <div class="d-flex align-items-center">
                  <div class="email-toggle-btn"><i class='bx bx-menu'></i>
                  </div>
                  <div class="btn btn-white">
                    <input class="form-check-input" type="checkbox">
                  </div>
                  <div class="">
                    <button type="button" class="btn btn-white ms-2"><i class="bi bi-arrow-repeat me-0"></i>
                    </button>
                  </div>
                  <div class="">
                    <button type="button" class="btn btn-white ms-2"><i class="bi bi-cloud-download-fill me-0"></i>
                    </button>
                  </div>
                  <div class="d-none d-md-flex">
                    <button type="button" class="btn btn-white ms-2"><i class="bi bi-file-earmark-bar-graph-fill me-0"></i>
                    </button>
                  </div>
                  <div class="">
                    <button type="button" class="btn btn-white ms-2"><i class="bi bi-trash-fill me-0"></i>
                    </button>
                  </div>
                </div>
                <div class="flex-grow-1 mx-xl-2 my-2 my-xl-0">
                  <div class="input-group">	<span class="input-group-text bg-transparent"><i class="bi bi-search"></i></span>
                    <input type="text" class="form-control" placeholder="Search mail">
                  </div>
                </div>
                <div class="ms-auto d-flex align-items-center">
                  <button class="btn btn-sm btn-light">1-50 of 8,740</button>
                  <button class="btn btn-white px-2 ms-2"><i class="bi bi-chevron-left me-0"></i>
                  </button>
                  <button class="btn btn-white px-2 ms-2"><i class="bi bi-chevron-right me-0"></i>
                  </button>
                </div>
              </div>
              
              <div class="email-content">
                <div class="email-read-box p-3">
                  <h4><%= ct.getSujet() %></h4>            
                  <hr>
                  <div class="d-flex align-items-center">
<!--                     <img src="assets/images/avatars/avatar-1.png" width="42" height="42" class="rounded-circle" alt="" /> -->
                    <div class="flex-grow-1 ms-2">
						<div class="flex-grow-1 ms-2">
                      		<p class="mb-0 font-weight-bold"><%= ct.getU().getPrenom() + " " + ct.getU().getNom() %></p>
                      	</div>
                    </div>
                    <p class="mb-0 chat-time ps-5 ms-auto"><%= ct.getDate() %></p>
                  </div>
                  <div class="email-read-content px-md-5 py-5">
					<p><%= ct.getMessage() %></p>
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
                        <input type="text" class="form-control" placeholder="To" value="<%= ct.getEmail() %>" />
                      </div>
                      <div class="mb-3">
                        <input type="text" class="form-control" placeholder="Subject" value="<%= ct.getSujet()%>"/>
                      </div>
                      <div class="mb-3">
                        <textarea class="form-control" placeholder="Message" value="<%= ct.getMessage() %> </br> réponse: " rows="10" cols="10"><%= ct.getMessage() %></textarea>
                      </div>
                      <div class="mb-0">
                        <div class="d-flex align-items-center">
                          <div class="">
                            <div class="btn-group">
                              <button type="button" class="btn btn-primary">Envoyer</button>
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
  <script src="assets/js/app-emailread.js"></script>
  

<script>
  
</script>

</body>

</html>