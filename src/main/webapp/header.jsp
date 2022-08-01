<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Admin"%>
<%@page import="model.Contact"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<% 
ArrayList<Contact> contacts = (ArrayList<Contact>) request.getAttribute("contacts");
Admin abSession = (Admin) request.getAttribute("abSession");
%>
      <header class="top-header">        
        <nav class="navbar navbar-expand gap-3">
          <div class="mobile-toggle-icon fs-3">
              <i class="bi bi-list"></i>
            </div>
            <form class="searchbar">
                <div class="position-absolute top-50 translate-middle-y search-icon ms-3"><i class="bi bi-search"></i></div>
                <input class="form-control" type="text" placeholder="Type here to search">
                <div class="position-absolute top-50 translate-middle-y search-close-icon"><i class="bi bi-x-lg"></i></div>
            </form>
            
            <div class="top-navbar-right ms-auto">
              <ul class="navbar-nav align-items-center">
                <li class="nav-item search-toggle-icon">
                  <a class="nav-link" href="#">
                    <div class="">
                      <i class="bi bi-search"></i>
                    </div>
                  </a>
              </li>
              <li class="nav-item dropdown dropdown-user-setting">
                <a class="nav-link dropdown-toggle dropdown-toggle-nocaret" href="#" data-bs-toggle="dropdown">
                  <div class="user-setting d-flex align-items-center">
                    <img src="assets/images/avatars/avatar-1.png" class="user-img" alt="">
                  </div>
                </a>
                <ul class="dropdown-menu dropdown-menu-end">
                  <li>
                     <a class="dropdown-item" href="#">
                       <div class="d-flex align-items-center">
                          <img src="assets/images/avatars/avatar-1.png" alt="" class="rounded-circle" width="54" height="54">
                          <div class="ms-3">
                            <h6 class="mb-0 dropdown-user-name">${admin.nom }</h6>
                            <%
							String[] typeEmploye = abSession.getPrivileges().split(","); 
							%>
                            <small class="mb-0 dropdown-user-designation text-secondary"><%= typeEmploye[0] %></small><br>
                            <small class="mb-0 dropdown-user-designation text-secondary">${admin.email }</small>
                          </div>
                       </div>
                     </a>
                   </li>
                   <li><hr class="dropdown-divider"></li>
                   <li>
                      <a class="dropdown-item" href="pages-user-profile.html">
                         <div class="d-flex align-items-center">
                           <div class=""><i class="bi bi-person-fill"></i></div>
                           <div class="ms-3"><span>Profile</span></div>
                         </div>
                       </a>
                    </li>
                    <li>
                      <a class="dropdown-item" href="#">
                         <div class="d-flex align-items-center">
                           <div class=""><i class="bi bi-gear-fill"></i></div>
                           <div class="ms-3"><span>Setting</span></div>
                         </div>
                       </a>
                    </li>
                    <li>
                      <a class="dropdown-item" href="Dashboard?">
                         <div class="d-flex align-items-center">
                           <div class=""><i class="bi bi-speedometer"></i></div>
                           <div class="ms-3"><span>Dashboard</span></div>
                         </div>
                       </a>
                    </li>
<!--                     <li> -->
<!--                       <a class="dropdown-item" href="#"> -->
<!--                          <div class="d-flex align-items-center"> -->
<!--                            <div class=""><i class="bi bi-piggy-bank-fill"></i></div> -->
<!--                            <div class="ms-3"><span>Earnings</span></div> -->
<!--                          </div> -->
<!--                        </a> -->
<!--                     </li> -->
<!--                     <li> -->
<!--                       <a class="dropdown-item" href="#"> -->
<!--                          <div class="d-flex align-items-center"> -->
<!--                            <div class=""><i class="bi bi-cloud-arrow-down-fill"></i></div> -->
<!--                            <div class="ms-3"><span>Downloads</span></div> -->
<!--                          </div> -->
<!--                        </a> -->
<!--                     </li> -->
                    <li><hr class="dropdown-divider"></li>
                    <li>
                      <a class="dropdown-item" href="Logout?">
                         <div class="d-flex align-items-center">
                           <div class=""><i class="bi bi-lock-fill"></i></div>
                           <div class="ms-3"><span>Logout</span></div>
                         </div>
                       </a>
                    </li>
                </ul>
              </li>
              
              <%= request.getAttribute("privileges") %>
              
              <li class="nav-item dropdown dropdown-large">
                <a class="nav-link dropdown-toggle dropdown-toggle-nocaret" href="#" data-bs-toggle="dropdown">
                  <div class="messages">
                    <span class="notify-badge"><%= contacts.size() %></span>
                    <i class="bi bi-chat-right-fill"></i>
                  </div>
                </a>
                <div class="dropdown-menu dropdown-menu-end p-0">
                  <div class="p-2 border-bottom m-2">
                      <h5 class="h5 mb-0">Messages</h5>
                  </div>
                 <div class="header-message-list p-2">
                 <% for (Contact c : contacts){ %>
                     <a class="dropdown-item" href="DetailMessage?id=<%=c.getId()%>">
                       <div class="d-flex align-items-center">
<!--                           <img src="assets/images/avatars/avatar-1.png" alt="" class="rounded-circle" width="50" height="50"> -->
                          <div class="ms-3 flex-grow-1">
                            <h6 class="mb-0 dropdown-msg-user"><%= c.getU().getNom() %><span class="msg-time float-end text-secondary"> <%= c.getDate() %></span></h6>
                            <small class="mb-0 dropdown-msg-text text-secondary d-flex align-items-center"><%= c.getSujet() %></small>
                          </div>
                       </div>
                     </a>
                     <%} %> 
                </div>
                
                <div class="p-2">
                  <div><hr class="dropdown-divider"></div>
                    <a class="dropdown-item" href="GestionMessagerie?">
                      <div class="text-center">View All Messages</div>
                    </a>
                </div>
               </div>
              </li>
              
<!--               <li class="nav-item dropdown dropdown-large"> -->
<!--                 <a class="nav-link dropdown-toggle dropdown-toggle-nocaret" href="#" data-bs-toggle="dropdown"> -->
<!--                   <div class="notifications"> -->
<!--                     <span class="notify-badge">8</span> -->
<!--                     <i class="bi bi-bell-fill"></i> -->
<!--                   </div> -->
<!--                 </a> -->
<!--                 <div class="dropdown-menu dropdown-menu-end p-0"> -->
<!--                   <div class="p-2 border-bottom m-2"> -->
<!--                       <h5 class="h5 mb-0">Notifications</h5> -->
<!--                   </div> -->
<!--                   <div class="header-notifications-list p-2"> -->

<!--                     <a class="dropdown-item" href="#"> -->
<!--                       <div class="d-flex align-items-center"> -->
<!--                         <div class="notification-box bg-light-bronze text-bronze"><i class="bi bi-briefcase-fill"></i></div> -->
<!--                          <div class="ms-3 flex-grow-1"> -->
<!--                            <h6 class="mb-0 dropdown-msg-user">All Documents Uploaded <span class="msg-time float-end text-secondary">1 mo</span></h6> -->
<!--                            <small class="mb-0 dropdown-msg-text text-secondary d-flex align-items-center">Sussessfully uploaded all files</small> -->
<!--                          </div> -->
<!--                       </div> -->
<!--                     </a> -->
<!--                  </div> -->
<!--                  <div class="p-2"> -->
<!--                    <div><hr class="dropdown-divider"></div> -->
<!--                      <a class="dropdown-item" href="#"> -->
<!--                        <div class="text-center">View All Notifications</div> -->
<!--                      </a> -->
<!--                  </div> -->
<!--                 </div> -->
<!--               </li> -->
              
              </ul>
              </div>
        </nav>
      </header>