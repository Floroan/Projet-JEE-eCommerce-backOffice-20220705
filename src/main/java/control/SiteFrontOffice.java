package control;

import java.io.IOException;

import dao.CoordonneesDAO;
import dao.SliderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import tools.Database;

@WebServlet(name = "SiteFrontOffice", urlPatterns = { "/siteFrontOffice" })
@MultipartConfig(
  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
  maxFileSize = 1024 * 1024 * 10,      // 10 MB
  maxRequestSize = 1024 * 1024 * 100   // 100 MB
)

/**
 * Servlet implementation class SiteFrontOffice
 */
public class SiteFrontOffice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SiteFrontOffice() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		Database.Connect();

		CoordonneesDAO cd = new CoordonneesDAO();
		SliderDAO sd = new SliderDAO();

		// BOUTON ARCHIVER FOURNISSEUR
		if (request.getParameter("archived") != null) {

		}

		request.setAttribute("cb", cd.getById(1));
		request.setAttribute("sbCol", sd.getAll());
		request.getRequestDispatcher("siteFrontOffice.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		Database.Connect();

		CoordonneesDAO cd = new CoordonneesDAO();
		SliderDAO sd = new SliderDAO();

		// COMPANY UPDATE FORM
		if (request.getParameter("updateCompany") != null) {
			

			/* Receive file uploaded to the Servlet from the HTML5 form */
			Part filePart = request.getPart("logoFile");
			String fileName = filePart.getSubmittedFileName();
			System.out.println(fileName);
			
			for (Part part : request.getParts()) {
				
				part.write("/Users/tanguymanas/Documents/developpement/eclipse-202206-workspace-web/Projet-JEE-eCommerce-backOffice-20220705/src/main/webapp/assets/img/logo/" + fileName);
//				part.write("../../assets/img/logo/" + fileName);
				
			}
			
			String n = request.getParameter("name");
			String a = request.getParameter("address");
			String p = request.getParameter("phone");
			String m = request.getParameter("mail");
			
			System.out.println(n + a + p + m);
			
			response.sendRedirect("SiteFrontOffice");

		}
	}

}
