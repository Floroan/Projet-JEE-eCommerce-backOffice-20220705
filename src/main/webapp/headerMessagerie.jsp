<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="tools.Constantes"%>
<!DOCTYPE html>

					<div class="email-header d-xl-flex align-items-center">
						<div class="d-flex align-items-center">
							<div class="email-toggle-btn">
								<i class='bx bx-menu'></i>
							</div>
							<div class="btn btn-white">
								<input class="form-check-input" type="checkbox" selected="del">
							</div>
							<div class="">
								<a href="GestionMessagerie" type="button" class="btn btn-white ms-2" title="rafraichir"><i class="bi bi-arrow-repeat me-0"></i></a>
							</div>
<!-- 							<div class=""> -->
<!-- 								<button type="button" class="btn btn-white ms-2"><i class="bi bi-cloud-download-fill me-0"></i> -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 							<div class="d-none d-md-flex"> -->
<!-- 								<button type="button" class="btn btn-white ms-2"><i class="bi bi-file-earmark-bar-graph-fill me-0"></i> -->
<!-- 								</button> -->
<!-- 							</div> -->
<!-- 							<div class=""> -->
<!-- 								<a type="button" class="btn btn-white ms-2"><i class="bi bi-trash-fill me-0"></i></a> -->
<!-- 							</div> -->
						</div>
						<div class="flex-grow-1 mx-xl-2 my-2 my-xl-0">
								
								<form method="post" action="GestionMessagerie">
								<div class="input-group">
										<button class="bi bi-search" name="<%= Constantes.rechercheContact %>"></button>
										<input type="text" class="form-control" name="argRecherche" placeholder="Search mail">
								</div>
								</form>
							
						</div>
						<div class="ms-auto d-flex align-items-center">
							<button class="btn btn-sm btn-light">1-50 of 8,740</button>
							<button class="btn btn-white px-2 ms-2"><i class="bi bi-chevron-left me-0"></i>
							</button>
							<button class="btn btn-white px-2 ms-2"><i class="bi bi-chevron-right me-0"></i>
							</button>
						</div>
					</div>