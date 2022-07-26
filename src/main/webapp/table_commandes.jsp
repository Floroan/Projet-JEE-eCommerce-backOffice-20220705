
<%@page import="tools.Constantes"%>
<%@page import="model.Commande"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!-- <!doctype html> -->
<!-- <html lang="en"> -->

<!-- <head> -->
<!--   <!-- Required meta tags -->
<!--   <meta charset="utf-8"> -->
<!--   <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!--   <link rel="icon" href="assets/images/favicon-32x32.png" type="image/png" /> -->
<!--   <!--plugins-->
<!--   <link href="assets/plugins/simplebar/css/simplebar.css" rel="stylesheet" /> -->
<!--   <link href="assets/plugins/perfect-scrollbar/css/perfect-scrollbar.css" rel="stylesheet" /> -->
<!--   <link href="assets/plugins/metismenu/css/metisMenu.min.css" rel="stylesheet" /> -->
<!--   <link href="assets/plugins/datatable/css/dataTables.bootstrap5.min.css" rel="stylesheet" /> -->
<!--   <!-- Bootstrap CSS -->
<!--   <link href="assets/css/bootstrap.min.css" rel="stylesheet" /> -->
<!--   <link href="assets/css/bootstrap-extended.css" rel="stylesheet" /> -->
<!--   <link href="assets/css/style.css" rel="stylesheet" /> -->
<!--   <link href="assets/css/icons.css" rel="stylesheet"> -->
<!--   <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet"> -->
<!--   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"> -->

<!--   <!-- loader--> 
<!-- 	<link href="assets/css/pace.min.css" rel="stylesheet" /> -->


<!--   <!--Theme Styles--> 
<!--   <link href="assets/css/dark-theme.css" rel="stylesheet" /> -->
<!--   <link href="assets/css/light-theme.css" rel="stylesheet" /> -->
<!--   <link href="assets/css/semi-dark.css" rel="stylesheet" /> -->
<!--   <link href="assets/css/header-colors.css" rel="stylesheet" /> -->

<!--   <title>Onedash - Bootstrap 5 Admin Template</title> -->
<!-- </head> -->

<!-- <body> -->

<% ArrayList<Commande> commandes = (ArrayList<Commande>) request.getAttribute("commandes"); %>

				<h6 class="mb-0 text-uppercase">Table des commandes</h6>
				<hr/>
				<div class="card">
					<div class="card-body">
						<div class="table-responsive">
							<table id="example" class="table table-striped table-bordered" style="width:100%">
								<thead>
									<tr>
										<th>ID</th>
										<th>Date</th>
										<th>Client</th>
										<th>Email</th>
										<th>Total</th>
										<th>Etat</th>
										<th>Statut Archiver</th>
										<th>Actions</th>
									</tr>
								</thead>
								<tbody>
								
								<% for(Commande cmd: commandes){ %>
									<tr>
<!-- 									<form method="form" action="TableCommandes"> -->
<%-- 										<input type="hidden" name="<%= Constantes.idcommande %>" value="<%= cmd.getId()%>"/> --%>
										<td><%= cmd.getId() %></td>
										<td><%= cmd.getDate() %></td>
										<td><%= cmd.getU().getNom() + " " + cmd.getU().getPrenom() %></td>
										<td><%= cmd.getU().getEmail() %></td>
										<td><%= cmd.getTotal() %></td>
										<td><%= cmd.getEtat() %></td>
										<td><%= cmd.getArchiver() %></td>
										<td>
										<form method="post" action="TableCommandes">
										<input type="hidden" name="<%= Constantes.idcommande %>" value="<%= cmd.getId()%>"/>
	                                    <div class="d-flex align-items-center gap-3 fs-6">
	                                      	<a href="DetailCommande?id=<%=cmd.getId() %>" class="text-warning" data-bs-toggle="tooltip" data-bs-placement="bottom" title="" data-bs-original-title="Edit info" aria-label="Edit" type="submit"><i class="bi bi-pencil-fill"></i></a>
	                                      	<button name="archiveCommande" type="submit" class="text-danger" data-bs-toggle="tooltip" data-bs-placement="bottom" title="" data-bs-original-title="Archiver" aria-label="Delete"><i class="lni lni-archive"></i></button>                                 
	                                     </form>
	                                    </div>
	                                   </td>
	                                   
									</tr>
									<%} %>
								</tbody>
								<tfoot>
									<tr>
										<th>ID</th>
										<th>Date</th>
										<th>Total</th>
										<th>Etat</th>
										<th>Statut Archiver</th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
				
				
<!-- 				<h6 class="mb-0 text-uppercase">DataTable Import</h6> -->
<!-- 				<hr/> -->
<!-- 				<div class="card"> -->
<!-- 					<div class="card-body"> -->
<!-- 						<div class="table-responsive"> -->
<!-- 							<table id="example2" class="table table-striped table-bordered"> -->
<!-- 								<thead> -->
<!-- 									<tr> -->
<!-- 										<th>Name</th> -->
<!-- 										<th>Position</th> -->
<!-- 										<th>Office</th> -->
<!-- 										<th>Age</th> -->
<!-- 										<th>Start date</th> -->
<!-- 										<th>Salary</th> -->
<!-- 									</tr> -->
<!-- 								</thead> -->
<!-- 								<tbody> -->
<!-- 									<tr> -->
<!-- 										<td>Tiger Nixon</td> -->
<!-- 										<td>System Architect</td> -->
<!-- 										<td>Edinburgh</td> -->
<!-- 										<td>61</td> -->
<!-- 										<td>2011/04/25</td> -->
<!-- 										<td>$320,800</td> -->
<!-- 									</tr> -->
<!-- 								</tbody> -->
<!-- 								<tfoot> -->
<!-- 									<tr> -->
<!-- 										<th>Name</th> -->
<!-- 										<th>Position</th> -->
<!-- 										<th>Office</th> -->
<!-- 										<th>Age</th> -->
<!-- 										<th>Start date</th> -->
<!-- 										<th>Salary</th> -->
<!-- 									</tr> -->
<!-- 								</tfoot> -->
<!-- 							</table> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->

  <!-- Bootstrap bundle JS -->
<!--   <script src="assets/js/bootstrap.bundle.min.js"></script> -->
<!--   <!--plugins-->
<!--   <script src="assets/js/jquery.min.js"></script> -->
<!--   <script src="assets/plugins/simplebar/js/simplebar.min.js"></script> -->
<!--   <script src="assets/plugins/metismenu/js/metisMenu.min.js"></script> -->
<!--   <script src="assets/plugins/perfect-scrollbar/js/perfect-scrollbar.js"></script> -->
<!--   <script src="assets/js/pace.min.js"></script> -->
<!--   <script src="assets/plugins/datatable/js/jquery.dataTables.min.js"></script> -->
<!--   <script src="assets/plugins/datatable/js/dataTables.bootstrap5.min.js"></script> -->
<!--   <script src="assets/js/table-datatable.js"></script> -->
	
<!--   <!--app-->
<!--   <script src="assets/js/app.js"></script> -->
  
<!-- </body> -->

<!-- </html> -->