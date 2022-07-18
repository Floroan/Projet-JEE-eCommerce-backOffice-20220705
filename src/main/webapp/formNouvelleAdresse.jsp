<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

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