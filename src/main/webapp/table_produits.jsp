
<%@page import="tools.Constantes"%>
<%@page import="model.Produit"%>
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

<% ArrayList<Produit> prods = (ArrayList<Produit>) request.getAttribute("prods"); %>

               <div class="card">
                 <div class="card-body">
                   <div class="d-flex align-items-center">
                   
                      <h5 class="mb-0">Customer Details</h5>
                       <form class="ms-auto position-relative">
<!--                          <div class="position-absolute top-50 translate-middle-y search-icon px-3"><i class="bi bi-search"></i></div> -->
<!--                          <input class="form-control ps-5" type="text" placeholder="search"> -->
                       </form>
                   </div>
                   <div class="table-responsive">
                     <table id="example" class="table align-middle">
                       <thead class="table-secondary">
                         <tr>
                          <th>ID</th>
                          <th>Image principale</th>
                          <th>Titre</th>
<!--                           <th>Description</th> -->
                          <th>PU</th>
                          <th>sous-catégorie</th>
                          <th>Stock</th>
                          <th>archivé ?</th>
                          <th>Actions</th>
                         </tr>
                       </thead>
                       <tbody>
                       <% for (Produit p : prods){ %>
                         <tr>
                         
                          <td><%=p.getId() %></td>
                         
                           <td>
                             <div class="d-flex align-items-center gap-3 cursor-pointer">
                             	<a href="FicheProduit?id=<%= p.getId()%>" title="Voir informations & statistiques">
                                <img src="<%= p.getImage() %>" class="rounded-circle" width="44" height="44" alt="">
                                </a>
                             </div>
                           </td>
                           
                           <td><%= p.getTitre() %></td>
                           
<%--                            <td><%= p.getDescription().substring(0, 30) %></td> --%>
                           
                           <td><%= p.getPrix() %></td>
                           
                           <td><%= p.getFk_sous_categorie() %></td>
                           
                           <td><%= p.getStock() %></td>
                           
                           <td><%= p.getArchiver() %></td>
                           
                           <td>
                           <form method="post" action="TableProduits">
                            <input type="hidden" name="<%= Constantes.idProd%>" value="<%= p.getId() %>" />
                             <div class="table-actions d-flex align-items-center gap-3 fs-6">
                               <a href="FicheProduit?id=<%=p.getId() %>" class="text-primary" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Voir informations & statistiques"><i class="bi bi-eye-fill"></i></a>
                               <a href="DetailProduit?id=<%=p.getId() %>" class="text-warning" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Editer"><i class="bi bi-pencil-fill"></i></a>
                               <button type="submit" name="<%= Constantes.archiver %>" class="text-danger" data-bs-toggle="tooltip" data-bs-placement="bottom" title="Archiver"><i class="lni lni-archive"></i></button>
                             </div>
                             </form>
                           </td>
                           
                         </tr>
                         <% } %>
                         
                       </tbody>
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