package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Admin;
import tools.Database;
import tools.HashMe;
import tools.RegexValidator;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import dao.AdminDAO;

/**
 * Servlet implementation class Signin
 */
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminDAO adminDAO;
	private Admin admin;
	private HashMe hs;
	int cpt = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Signin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

//		if(request.getParameter("auth") != null) {
//			Database.Connect();
//			String user = request.getParameter("nom");
//			String userMail = request.getParameter("mail");
//			String pass = request.getParameter("pass");
//			
//			hs = new HashMe();
//			String hpass = hs.sha1(pass);
//			
//			adminDAO = new AdminDAO();
//			admin = adminDAO.getOneByNameAndMail(user, userMail, hpass);
//			if(admin.getNom().equals(user) && admin.getPassword().equals(hpass) && admin.getEmail().equals(userMail)) {
//				
//			session.setAttribute("admin", admin);
//			session.setAttribute("isConnected", true);
//			response.sendRedirect("Dashboard");
//			}else {
//				session.setAttribute("authError", "Un probl�me est survenu.");
//				response.sendRedirect("Signin");
//			}
//		}else{
//			request.getRequestDispatcher("/signin.jsp").forward(request, response);
//		}

//		try{
//			
//			Database.Connect();
//			String user = request.getParameter("nom");
//			String userMail = request.getParameter("mail");
//			String pass = request.getParameter("pass");
//			
//			hs = new HashMe();
//			String hpass = hs.sha1(pass);
//			
//			adminDAO = new AdminDAO();
//			admin = adminDAO.getOneByNameAndMail(user, userMail, hpass);
//			
//			if(admin.getNom().equals(user) && admin.getPassword().equals(hpass) && admin.getEmail().equals(userMail)) {
//				session.setAttribute("admin", admin);
//				session.setAttribute("isConnected", true);
//				response.sendRedirect("Dashboard");	
//			}
//				request.setAttribute("authError", "Un probl�me est survenu.");
//			
//			
//		}catch(NullPointerException ex){
//			//session.setAttribute("authError", "Un probl�me est survenu.");
//			
//			request.getRequestDispatcher("/signin.jsp").forward(request, response);
//			
//		}

		HttpSession session = request.getSession(true);
		boolean conn = false;

		if (request.getParameter("auth") != null) {

			//try {
			System.out.println("passe ici signin");
			Database.Connect();
			String user = request.getParameter("nom");
			String userMail = request.getParameter("mail");
			String pass = request.getParameter("pass");

			if (!RegexValidator.nameValidator(user)) {
				session.setAttribute("authError", "Merci de vérifier votre nom.");

			} else if (!RegexValidator.emailValidator(userMail)) {
				session.setAttribute("authError", "Merci de vérifier votre email.");
			} else {

				hs = new HashMe();
				String hpass = hs.sha1(pass);

				adminDAO = new AdminDAO();
				admin = adminDAO.getOneByNameMailPass(user, userMail, hpass);
				System.out.println(admin);

				if (admin == null) {

					cpt++;
					session.setAttribute("tentatives", cpt);

					if (Integer.valueOf(session.getAttribute("tentatives").toString()) == 2) {
						System.out.println(session.getAttribute("tentatives"));
						session.setAttribute("authError", "Attention, plus qu'une tentative pour vous connecter");
					} else if (Integer.valueOf(session.getAttribute("tentatives").toString()) == 3) {
						session.setAttribute("user", user);
						session.setAttribute("userMail", userMail);
						session.removeAttribute("tentatives");
						request.getRequestDispatcher("/test.jsp").forward(request, response);
					} else {
						session.setAttribute("authError", "Un probl�me est survenu.");
					}

				} else {
					session.removeAttribute("tentatives");
					session.setAttribute("admin", admin);
					session.setAttribute("isConnected", true);
					conn = true;
					response.sendRedirect("Dashboard");
				}

			}

//		}catch(SocketException e){
//			request.setAttribute("communicationError", "serveur déconnecté");
//			response.sendRedirect("/error500.jsp");
//		}
		
		
		} // fin if control Regex..

		if (conn == false) {
			request.getRequestDispatcher("/signin.jsp").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
