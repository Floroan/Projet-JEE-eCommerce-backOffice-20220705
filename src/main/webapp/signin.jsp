<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" href="assets/images/favicon-32x32.png" type="image/png" />
  <!-- Bootstrap CSS -->
  <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
  <link href="assets/css/bootstrap-extended.css" rel="stylesheet" />
  <link href="assets/css/style.css" rel="stylesheet" />
  <link href="assets/css/icons.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">

  <!-- loader-->
	<link href="assets/css/pace.min.css" rel="stylesheet" />

  <title>Onedash - Bootstrap 5 Admin Template</title>
  
</head>

<body>

  <!--start wrapper-->
  <div class="wrapper">
    
       <!--start content-->
       <main class="authentication-content">
        <div class="container-fluid">
          <div class="authentication-card">
            <div class="card shadow rounded-0 overflow-hidden">
              <div class="row g-0">
                <div class="col-lg-6 bg-login d-flex align-items-center justify-content-center">
                  <img src="assets/images/error/login-img.jpg" class="img-fluid" alt="">
                </div>
                <div class="col-lg-6">
                  <div class="card-body p-4 p-sm-5">
                    <h5 class="card-title">Signin</h5>
                    <h5 class="card-text mb-5">Merci de vous authentifier</h5>
                    <form class="form-body">
<!--                       <div class="d-grid"> -->
<!--                         <a class="btn btn-white radius-30" href="javascript:;"><span class="d-flex justify-content-center align-items-center"> -->
<!--                             <img class="me-2" src="assets/images/icons/search.svg" width="16" alt=""> -->
<!--                             <span>Sign up with Google</span> -->
<!--                           </span> -->
<!--                         </a> -->
<!--                       </div> -->
                      <div class="login-separater text-center mb-4"> <span><h6>Vos nom, email, mot de passe sont obligatoires</h6></span>
                        <hr>
                      </div>
                        <div class="row g-3">
                          <div class="col-12 ">
                            <label for="inputName" class="form-label">Nom</label>
                            <div class="ms-auto position-relative">
                              <div class="position-absolute top-50 translate-middle-y search-icon px-3"><i class="bi bi-person-circle"></i></div>

                              <input value="Manas" name="nom" type="text" class="form-control radius-30 ps-5" id="inputName" placeholder="Entrer votre nom">

                            </div>
                          </div>
                          <div class="col-12">
                            <label for="inputEmailAddress" class="form-label">Email</label>
                            <div class="ms-auto position-relative">
                              <div class="position-absolute top-50 translate-middle-y search-icon px-3"><i class="bi bi-envelope-fill"></i></div>

                              <input value="tanguy.manas@gmail.com" name="mail" type="email" class="form-control radius-30 ps-5" id="inputEmailAddress" placeholder="Entrer votre email">

                            </div>
                          </div>
                          <div class="col-12">
                            <label for="inputChoosePassword" class="form-label">Mot de passe</label>
                            <div class="ms-auto position-relative">
                              <div class="position-absolute top-50 translate-middle-y search-icon px-3"><i class="bi bi-lock-fill"></i></div>

                              <input value="000" name="pass" type="password" class="form-control radius-30 ps-5" id="inputChoosePassword" placeholder="Entrer votre mot de passe">

                            </div>
                          </div>

                          <div class="col-12">
                            <div class="d-grid">
                              <button name="auth" type="submit" class="btn btn-primary radius-30">Sign in</button>
                            </div>
                          </div>
                          <div class="col-12">
                            <p class="mb-0">Mot de passe <a href="AccountRecovery?"> perdu?</a></p>
                            <p class="mb-0"> ${authError }</p>
                          </div>
                        </div>
                    </form>
                 </div>
                </div>
              </div>
            </div>
          </div>
        </div>
       </main>
        
       <!--end page main-->

  </div>
  <!--end wrapper-->


  <!--plugins-->
  <script src="assets/js/jquery.min.js"></script>
  <script src="assets/js/pace.min.js"></script>

  
</body>

</html>