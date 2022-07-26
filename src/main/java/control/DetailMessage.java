package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Contact;
import tools.Constantes;

import java.io.IOException;

import dao.ContactDAO;

/**
 * Servlet implementation class DetailMessage
 */
public class DetailMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ContactDAO conDao;
	private Contact contact;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		conDao = new ContactDAO();
		
		if(request.getParameter("editEtat") != null) {
			System.out.println(request.getParameter("etatMessage") + " " + request.getParameter("idContact"));
			Contact c = conDao.getById( Integer.parseInt(request.getParameter(Constantes.idContact)));
			switch (request.getParameter("etatMessage")) {
			
			case "1": System.out.println("encours");
						c.setEtat(Constantes.enCours);
						conDao.save(c);
				break;
			case "2":  System.out.println("reso");
						c.setEtat(Constantes.resolu);
						conDao.save(c);
				break;
			case "3":  System.out.println("noreso");
						c.setEtat(Constantes.nonlu);
						conDao.save(c);
				break;
				default: System.out.println("probleme");
			
			}
			
		}
		
		
		request.setAttribute("contact", conDao.getById(Integer.parseInt(request.getParameter("id"))));
		request.getRequestDispatcher("/detailMessage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
