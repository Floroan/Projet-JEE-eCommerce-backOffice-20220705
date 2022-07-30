package control;

import java.io.IOException;

import dao.AdminDAO;
import dao.CoordonneesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Admin;
import model.Coordonnee;
import tools.Database;

/**
 * Servlet implementation class SideBar
 */
public class SideBar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SideBar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(true);
		Admin as = (Admin) session.getAttribute("admin");
		int id = as.getId();
		
		Database.Connect();
		
		CoordonneesDAO cd = new CoordonneesDAO();
		AdminDAO ad = new AdminDAO();
		
		Coordonnee cb = cd.getById(1);
		Admin ab = ad.getById(id);
		
		request.setAttribute("cb", cb);
		request.setAttribute("privileges", ab.getPrivileges());
		request.getRequestDispatcher("/sidebar.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
