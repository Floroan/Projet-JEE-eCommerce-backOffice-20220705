<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>

  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" href="assets/images/favicon-32x32.png" type="image/png" />
  <!--plugins-->
  <link href="assets/plugins/vectormap/jquery-jvectormap-2.0.2.css" rel="stylesheet"/>
  <link href="assets/plugins/simplebar/css/simplebar.css" rel="stylesheet" />
  <link href="assets/plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet" />
  
    <link href="assets/plugins/datetimepicker/css/classic.css" rel="stylesheet" />
	<link href="assets/plugins/datetimepicker/css/classic.time.css" rel="stylesheet" />
	<link href="assets/plugins/datetimepicker/css/classic.date.css" rel="stylesheet" />
  <link rel="stylesheet" href="assets/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.min.css">
  
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

<title>Top of the Top</title>
	
	<style type="text/css">
			#chart99 {
			max-width: 800px;
			margin: 20px auto;
			}
	</style>

</head>
<body>

<% ArrayList<String> tables = (ArrayList<String>) request.getAttribute("tables"); %>


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

				<form method="post">
					<label for="validationCustom01" class="form-label">
						Des infos sur:
						<select name="selectedTable" required>
						<% for(String tb : tables){ %>
							<option name="selectedTable"><%= tb  %></option>
						<%} %>
						</select>
					</label>
<!-- 					<input class="result form-control" type="text" id="date" placeholder="Date Picker..." data-dtp="dtp_9WbNa"> -->
<!-- 					<input class="result form-control" type="text" id="date" placeholder="Date Picker..." data-dtp="dtp_QWzSz"> -->
					
<!-- 					<input type="text" class="form-control datepicker picker__input" readonly="" name="dt1" id="P1926380751" aria-haspopup="true" aria-readonly="false" aria-owns="P1926380751_root"> -->
<!-- 					<input type="text" class="form-control datepicker picker__input" readonly=""  name="dt2" id="P1926380751" aria-haspopup="true" aria-readonly="false" aria-owns="P1926380751_root"> -->
					<div class="col-md-4">
					<label>Entre les dates suivantes:</label>
					<div class="col-md-4">
						<label>Début</label><input type="date" name="dt1" placehoder="début" class="form-control" required>
						<label>Fin:</label><input type="date" name="dt2" placehoder="fin" class="form-control" required>
					</div>
						<button type="submit" name="topOf" class="btn btn-outline-primary px-5">Voir</button>
					</div>
				</form>

			<div id="chart99">
			<label>${resultat }</label>
			</div>

				<script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
				
				<script>
	var options = {
			  chart: {
			    height: 380,
			    width: "100%",
			    type: "area",
			    animations: {
			      initialAnimation: {
			        enabled: false
			      }
			    }
			  },
			  series: [
			    {
			      name: "quantité",
			      data: [
					${topOfResults}
			      ]
			    }
			  ],
			  xaxis: {
			    type: "datetime"
			  }
			};

			var chart = new ApexCharts(document.querySelector("#chart99"), options);

			chart.render();

	</script>
	
		<form method="post">
			<button type="submit" name="retourDash">Retour au dashboard</button>
		</form>
	
		</main>

	</div>
	
 
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

	<!-- Bootstrap bundle JS -->
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<!--plugins-->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/plugins/simplebar/js/simplebar.min.js"></script>
	<script src="assets/plugins/metismenu/js/metisMenu.min.js"></script>
	<script src="assets/plugins/perfect-scrollbar/js/perfect-scrollbar.js"></script>
	<script src="assets/js/pace.min.js"></script>
	
  	<script src="assets/plugins/datetimepicker/js/legacy.js"></script>
	<script src="assets/plugins/datetimepicker/js/picker.js"></script>
	<script src="assets/plugins/datetimepicker/js/picker.time.js"></script>
	<script src="assets/plugins/datetimepicker/js/picker.date.js"></script>
	
	<script src="assets/plugins/bootstrap-material-datetimepicker/js/moment.min.js"></script>
	<script src="assets/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.min.js"></script>
  	<script src="assets/js/form-date-time-pickes.js"></script>

	<script src="assets/plugins/apexcharts-bundle/js/apexcharts.min.js"></script>
  
    <!--app-->
  <script src="assets/js/app.js"></script>
  <script src="assets/js/index2.js"></script>
  <script>
    new PerfectScrollbar(".best-product")
 </script>
 
</body>
</html>