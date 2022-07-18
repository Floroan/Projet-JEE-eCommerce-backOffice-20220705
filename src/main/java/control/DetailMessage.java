package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Contact;

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
