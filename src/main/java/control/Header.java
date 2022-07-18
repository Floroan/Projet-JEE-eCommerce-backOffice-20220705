package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Contact;
import tools.Constantes;
import tools.Database;

import java.io.IOException;
import java.util.ArrayList;

import dao.ContactDAO;

/**
 * Servlet implementation class Header
 */
public class Header extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ContactDAO contactDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Header() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
//		request.setAttribute("nom", "toto");
//		request.setAttribute("mail", "toto@toto.fr");
		Database.Connect();
		contactDao = new ContactDAO();
		ArrayList<Contact> contacts = contactDao.getAllByEtat(Constantes.nonlu);
		
		request.setAttribute("contacts", contacts);
		request.setAttribute("privileges", returnPrivileges());
		request.getRequestDispatcher("/header.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	
	public String returnPrivileges() {
		
		return "<li class=\"nav-item dropdown dropdown-large\">\r\n"
				+ "                <a class=\"nav-link dropdown-toggle dropdown-toggle-nocaret\" href=\"#\" data-bs-toggle=\"dropdown\">\r\n"
				+ "                  <div class=\"projects\">\r\n"
				+ "                    <i class=\"bi bi-grid-3x3-gap-fill\"></i>\r\n"
				+ "                  </div>\r\n"
				+ "                </a>\r\n"
				+ "                <div class=\"dropdown-menu dropdown-menu-end\">\r\n"
				+ "                   <div class=\"row row-cols-3 gx-2\">\r\n"
				+ "                      <div class=\"col\">\r\n"
				+ "                        <a href=\"ecommerce-orders.html\">\r\n"
				+ "                         <div class=\"apps p-2 radius-10 text-center\">\r\n"
				+ "                            <div class=\"apps-icon-box mb-1 text-white bg-gradient-purple\">\r\n"
				+ "                              <i class=\"bi bi-basket2-fill\"></i>\r\n"
				+ "                            </div>\r\n"
				+ "                            <p class=\"mb-0 apps-name\">Orders</p>\r\n"
				+ "                         </div>\r\n"
				+ "                        </a>\r\n"
				+ "                      </div>\r\n"
				+ "                      <div class=\"col\">\r\n"
				+ "                        <a href=\"javascript:;\">\r\n"
				+ "                        <div class=\"apps p-2 radius-10 text-center\">\r\n"
				+ "                           <div class=\"apps-icon-box mb-1 text-white bg-gradient-info\">\r\n"
				+ "                            <i class=\"bi bi-people-fill\"></i>\r\n"
				+ "                           </div>\r\n"
				+ "                           <p class=\"mb-0 apps-name\">Users</p>\r\n"
				+ "                        </div>\r\n"
				+ "                      </a>\r\n"
				+ "                     </div>\r\n"
				+ "                     <div class=\"col\">\r\n"
				+ "                      <a href=\"ecommerce-products-grid.html\">\r\n"
				+ "                      <div class=\"apps p-2 radius-10 text-center\">\r\n"
				+ "                         <div class=\"apps-icon-box mb-1 text-white bg-gradient-success\">\r\n"
				+ "                          <i class=\"bi bi-trophy-fill\"></i>\r\n"
				+ "                         </div>\r\n"
				+ "                         <p class=\"mb-0 apps-name\">Products</p>\r\n"
				+ "                      </div>\r\n"
				+ "                      </a>\r\n"
				+ "                    </div>\r\n"
				+ "                    <div class=\"col\">\r\n"
				+ "                      <a href=\"component-media-object.html\">\r\n"
				+ "                      <div class=\"apps p-2 radius-10 text-center\">\r\n"
				+ "                         <div class=\"apps-icon-box mb-1 text-white bg-gradient-danger\">\r\n"
				+ "                          <i class=\"bi bi-collection-play-fill\"></i>\r\n"
				+ "                         </div>\r\n"
				+ "                         <p class=\"mb-0 apps-name\">Media</p>\r\n"
				+ "                      </div>\r\n"
				+ "                      </a>\r\n"
				+ "                    </div>\r\n"
				+ "                    <div class=\"col\">\r\n"
				+ "                      <a href=\"pages-user-profile.html\">\r\n"
				+ "                      <div class=\"apps p-2 radius-10 text-center\">\r\n"
				+ "                         <div class=\"apps-icon-box mb-1 text-white bg-gradient-warning\">\r\n"
				+ "                          <i class=\"bi bi-person-circle\"></i>\r\n"
				+ "                         </div>\r\n"
				+ "                         <p class=\"mb-0 apps-name\">Account</p>\r\n"
				+ "                       </div>\r\n"
				+ "                      </a>\r\n"
				+ "                    </div>\r\n"
				+ "                    <div class=\"col\">\r\n"
				+ "                      <a href=\"javascript:;\">\r\n"
				+ "                      <div class=\"apps p-2 radius-10 text-center\">\r\n"
				+ "                         <div class=\"apps-icon-box mb-1 text-white bg-gradient-voilet\">\r\n"
				+ "                          <i class=\"bi bi-file-earmark-text-fill\"></i>\r\n"
				+ "                         </div>\r\n"
				+ "                         <p class=\"mb-0 apps-name\">Docs</p>\r\n"
				+ "                      </div>\r\n"
				+ "                      </a>\r\n"
				+ "                    </div>\r\n"
				+ "                    <div class=\"col\">\r\n"
				+ "                      <a href=\"ecommerce-orders-detail.html\">\r\n"
				+ "                      <div class=\"apps p-2 radius-10 text-center\">\r\n"
				+ "                         <div class=\"apps-icon-box mb-1 text-white bg-gradient-branding\">\r\n"
				+ "                          <i class=\"bi bi-credit-card-fill\"></i>\r\n"
				+ "                         </div>\r\n"
				+ "                         <p class=\"mb-0 apps-name\">Payment</p>\r\n"
				+ "                      </div>\r\n"
				+ "                      </a>\r\n"
				+ "                    </div>\r\n"
				+ "                    <div class=\"col\">\r\n"
				+ "                      <a href=\"javascript:;\">\r\n"
				+ "                      <div class=\"apps p-2 radius-10 text-center\">\r\n"
				+ "                         <div class=\"apps-icon-box mb-1 text-white bg-gradient-desert\">\r\n"
				+ "                          <i class=\"bi bi-calendar-check-fill\"></i>\r\n"
				+ "                         </div>\r\n"
				+ "                         <p class=\"mb-0 apps-name\">Events</p>\r\n"
				+ "                      </div>\r\n"
				+ "                    </a>\r\n"
				+ "                    </div>\r\n"
				+ "                    <div class=\"col\">\r\n"
				+ "                      <a href=\"javascript:;\">\r\n"
				+ "                      <div class=\"apps p-2 radius-10 text-center\">\r\n"
				+ "                         <div class=\"apps-icon-box mb-1 text-white bg-gradient-amour\">\r\n"
				+ "                          <i class=\"bi bi-book-half\"></i>\r\n"
				+ "                         </div>\r\n"
				+ "                         <p class=\"mb-0 apps-name\">Story</p>\r\n"
				+ "                        </div>\r\n"
				+ "                      </a>\r\n"
				+ "                    </div>\r\n"
				+ "                   </div><!--end row-->\r\n"
				+ "                </div>\r\n"
				+ "              </li>";
	}
}
