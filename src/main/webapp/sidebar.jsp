<%@page import="tools.Constantes"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<aside class="sidebar-wrapper" data-simplebar="true">
          <div class="sidebar-header">
            <div>
              <img src="assets/images/logo-icon.png" class="logo-icon" alt="logo icon">
            </div>
            <div>
              <h4 class="logo-text">Onedash</h4>
            </div>
            <div class="toggle-icon ms-auto"> <i class="bi bi-list"></i>
            </div>
          </div>
          <!--navigation-->
          <ul class="metismenu" id="menu">
              <a href="Dashboard?" >
                <div class="parent-icon"><i class="bi bi-house-fill"></i>
                </div>
                <div class="menu-title">Dashboard</div>
              </a>
            <li>
              <a href="javascript:;" class="has-arrow">
                <div class="parent-icon"><i class="bi bi-grid-fill"></i>
                </div>
                <div class="menu-title">Application</div>
              </a>
              <ul>
                <li> <a href="GestionMessagerie?"><i class="bi bi-circle"></i>Messagerie</a>
                </li>
                <li> <a href="app-chat-box.html"><i class="bi bi-circle"></i>Chat Box</a>
                </li>
                <li> <a href="app-file-manager.html"><i class="bi bi-circle"></i>File Manager</a>
                </li>
                <li> <a href="app-to-do.html"><i class="bi bi-circle"></i>Todo List</a>
                </li>
                <li> <a href="app-invoice.html"><i class="bi bi-circle"></i>Invoice</a>
                </li>
                <li> <a href="app-fullcalender.html"><i class="bi bi-circle"></i>Calendar</a>
                </li>
              </ul>
            </li>
            <li class="menu-label">Gestion g�n�rale</li>
            <li>
              <a href="javascript:;" class="has-arrow">
                <div class="parent-icon"><i class="bi bi-droplet-fill"></i>
                </div>
                <div class="menu-title">Widgets</div>
              </a>
              <ul>
                <li> <a href="widgets-static-widgets.html"><i class="bi bi-circle"></i>Static Widgets</a>
                </li>
                <li> <a href="widgets-data-widgets.html"><i class="bi bi-circle"></i>Data Widgets</a>
                </li>
              </ul>
            </li>
            <li>
              <a href="javascript:;" class="has-arrow">
                <div class="parent-icon"><i class="bi bi-basket2-fill"></i>
                </div>
                <div class="menu-title">eCommerce</div>
              </a>
              <ul>
                <li> <a href="TableProduits?"><i class="bi bi-circle"></i>Liste des produits</a>
                </li>
<!--                 <li> <a href="ecommerce-products-grid.html"><i class="bi bi-circle"></i>Products Grid</a> -->
<!--                 </li> -->
                <li> <a href="AjoutProduit?"><i class="bi bi-circle"></i>Ajouter un produit</a>
                </li>
                <li> <a href="GestionCategorie?"><i class="bi bi-circle"></i>Categories</a>
                </li>
                <li> <a href="GestionSousCategorie?"><i class="bi bi-circle"></i>Sous-categories</a>
                </li>
              </ul>
            </li>
            <li>
              <a class="has-arrow" href="javascript:;">
                <div class="parent-icon"><i class="bi bi-award-fill"></i>
                </div>
                <div class="menu-title">Commandes</div>
              </a>
              <ul>
                <li> <a href="TableCommandes?"><i class="bi bi-circle"></i>Toutes les commandes</a>
                </li>
                <li> <a href="TableCommandesByEtat?etat=<%= Constantes.cmdEnCours%>"><i class="bi bi-circle"></i>Les commandes � pr�parer</a>
                </li>
                <li> <a href="TableCommandesByEtat?etat=<%= Constantes.cmdValidee%>"><i class="bi bi-circle"></i>Les commandes � livrer</a>
                </li>
                <li> <a href="TableCommandesByEtat?etat=<%= Constantes.cmdLivree%>"><i class="bi bi-circle"></i>Les commandes livr�es</a>
                </li> 
                <li> <a href="TableCommandesByEtat?etat=<%= Constantes.cmdSignalee %>"><i class="bi bi-circle"></i>Les commandes signal�es</a>
                </li> 
              </ul>
            </li>
            <li>
              <a class="has-arrow" href="javascript:;">
                <div class="parent-icon"><i class="bi bi-award-fill"></i>
                </div>
                <div class="menu-title">Achats</div>
              </a>
              <ul>
                <li> <a href="TableCommandes?"><i class="bi bi-circle"></i>Les achats</a>
                </li>
                <li> <a href="ecommerce-orders-detail.html"><i class="bi bi-circle"></i>Passer un achat</a>
                </li>
              </ul>
            </li>
            <li>
              <a class="has-arrow" href="javascript:;">
                <div class="parent-icon"><i class="bi bi-cloud-arrow-down-fill"></i>
                </div>
                <div class="menu-title">Marketing</div>
              </a>
              <ul>
                <li> <a href="TopRecherches?"><i class="bi bi-circle"></i>Top recherches</a>
                </li>
                <li> <a href="TopOfTheTop"><i class="bi bi-circle"></i>Top of the tops</a>
                </li>
                <li> <a href="icons-feather-icons.html"><i class="bi bi-circle"></i>??</a>
                </li>
              </ul>
            </li>
            <li class="menu-label">Forms & Tables</li>
            <li>
              <a class="has-arrow" href="javascript:;">
                <div class="parent-icon"><i class="bi bi-file-earmark-break-fill"></i>
                </div>
                <div class="menu-title">Forms</div>
              </a>
              <ul>
                <li> <a href="form-elements.html"><i class="bi bi-circle"></i>Form Elements</a>
                </li>
                <li> <a href="form-input-group.html"><i class="bi bi-circle"></i>Input Groups</a>
                </li>
                <li> <a href="form-layouts.html"><i class="bi bi-circle"></i>Forms Layouts</a>
                </li>
                <li> <a href="form-validations.html"><i class="bi bi-circle"></i>Form Validation</a>
                </li>
                <li> <a href="form-wizard.html"><i class="bi bi-circle"></i>Form Wizard</a>
                </li>
                <li> <a href="form-date-time-pickes.html"><i class="bi bi-circle"></i>Date Pickers</a>
                </li>
                <li> <a href="form-select2.html"><i class="bi bi-circle"></i>Select2</a>
                </li>
              </ul>
            </li>
            <li>
              <a class="has-arrow" href="javascript:;">
                <div class="parent-icon"><i class="bi bi-file-earmark-spreadsheet-fill"></i>
                </div>
                <div class="menu-title">Tables</div>
              </a>
              <ul>
                <li> <a href="table-basic-table.html"><i class="bi bi-circle"></i>Basic Table</a>
                </li>
                <li> <a href="table-advance-tables.html"><i class="bi bi-circle"></i>Advance Tables</a>
                </li>
                <li> <a href="table-datatable.html"><i class="bi bi-circle"></i>Data Table</a>
                </li>
              </ul>
            </li>
            <li class="menu-label">Pages</li>
            <li>
              <a class="has-arrow" href="javascript:;">
                <div class="parent-icon"><i class="bi bi-lock-fill"></i>
                </div>
                <div class="menu-title">Authentication</div>
              </a>
              <ul>
                <li> <a href="authentication-signin.html" target="_blank"><i class="bi bi-circle"></i>Sign In</a>
                </li>
                <li> <a href="authentication-signup.html" target="_blank"><i class="bi bi-circle"></i>Sign Up</a>
                </li>
                <li> <a href="authentication-signin-with-header-footer.html" target="_blank"><i class="bi bi-circle"></i>Sign In with Header & Footer</a>
                </li>
                <li> <a href="authentication-signup-with-header-footer.html" target="_blank"><i class="bi bi-circle"></i>Sign Up with Header & Footer</a>
                </li>
                <li> <a href="authentication-forgot-password.html" target="_blank"><i class="bi bi-circle"></i>Forgot Password</a>
                </li>
                <li> <a href="authentication-reset-password.html" target="_blank"><i class="bi bi-circle"></i>Reset Password</a>
                </li>
              </ul>
            </li>
            <li>
              <a href="pages-user-profile.html">
                <div class="parent-icon"><i class="bi bi-person-lines-fill"></i>
                </div>
                <div class="menu-title">User Profile</div>
              </a>
            </li>
            <li>
              <a href="pages-timeline.html">
                <div class="parent-icon"><i class="bi bi-collection-play-fill"></i>
                </div>
                <div class="menu-title">Timeline</div>
              </a>
            </li>
            <li>
              <a class="has-arrow" href="javascript:;">
                <div class="parent-icon"><i class="bi bi-exclamation-triangle-fill"></i>
                </div>
                <div class="menu-title">Errors</div>
              </a>
              <ul>
                <li> <a href="pages-errors-404-error.html" target="_blank"><i class="bi bi-circle"></i>404 Error</a>
                </li>
                <li> <a href="pages-errors-500-error.html" target="_blank"><i class="bi bi-circle"></i>500 Error</a>
                </li>
                <li> <a href="pages-errors-coming-soon.html" target="_blank"><i class="bi bi-circle"></i>Coming Soon</a>
                </li>
                <li> <a href="pages-blank-page.html" target="_blank"><i class="bi bi-circle"></i>Blank Page</a>
                </li>
              </ul>
            </li>
            <li>
              <a href="pages-faq.html">
                <div class="parent-icon"><i class="bi bi-question-lg"></i>
                </div>
                <div class="menu-title">FAQ</div>
              </a>
            </li>
            <li>
              <a href="pages-pricing-tables.html">
                <div class="parent-icon"><i class="bi bi-tags-fill"></i>
                </div>
                <div class="menu-title">Pricing Tables</div>
              </a>
            </li>
            <li class="menu-label">Gestion Commentaires, Visites & Favoris</li>
            <li>
              <a class="has-arrow" href="javascript:;">
                <div class="parent-icon"><i class="bi bi-bar-chart-line-fill"></i>
                </div>
                <div class="menu-title">Charts</div>
              </a>
              <ul>
                <li> <a href="charts-apex-chart.html"><i class="bi bi-circle"></i>Commentaires</a>
                </li>
                <li> <a href="charts-chartjs.html"><i class="bi bi-circle"></i>Visites</a>
                </li>
                <li> <a href="charts-highcharts.html"><i class="bi bi-circle"></i>Favoris</a>
                </li>
              </ul>
            </li>
            <li>
              <a class="has-arrow" href="javascript:;">
                <div class="parent-icon"><i class="bi bi-pin-map-fill"></i>
                </div>
                <div class="menu-title">Maps</div>
              </a>
              <ul>
                <li> <a href="map-google-maps.html"><i class="bi bi-circle"></i>Google Maps</a>
                </li>
                <li> <a href="map-vector-maps.html"><i class="bi bi-circle"></i>Vector Maps</a>
                </li>
              </ul>
            </li>
            <li class="menu-label">Others</li>
            <li>
              <a class="has-arrow" href="javascript:;">
                <div class="parent-icon"><i class="bi bi-music-note-list"></i>
                </div>
                <div class="menu-title">Menu Levels</div>
              </a>
              <ul>
                <li> <a class="has-arrow" href="javascript:;"><i class="bi bi-circle"></i>Level One</a>
                  <ul>
                    <li> <a class="has-arrow" href="javascript:;"><i class="bi bi-circle"></i>Level Two</a>
                      <ul>
                        <li> <a href="javascript:;"><i class="bi bi-circle"></i>Level Three</a>
                        </li>
                      </ul>
                    </li>
                  </ul>
                </li>
              </ul>
            </li>
            <li>
              <a href="https://codervent.com/skodash/documentation/index.html" target="_blank">
                <div class="parent-icon"><i class="bi bi-file-code-fill"></i>
                </div>
                <div class="menu-title">Documentation</div>
              </a>
            </li>
            <li>
              <a href="https://themeforest.net/user/codervent" target="_blank">
                <div class="parent-icon"><i class="bi bi-telephone-fill"></i>
                </div>
                <div class="menu-title">Support</div>
              </a>
            </li>
          </ul>
          <!--end navigation-->
       </aside>