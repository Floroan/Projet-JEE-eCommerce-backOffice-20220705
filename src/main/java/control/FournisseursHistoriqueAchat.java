package control;

import java.io.IOException;
import java.util.ArrayList;

import dao.Entree_stockDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Entree_stock;
import tools.Database;

/**
 * Servlet implementation class FournisseursHistoriqueAchat
 */
public class FournisseursHistoriqueAchat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FournisseursHistoriqueAchat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Database.Connect();
		
		Entree_stockDAO ed = new Entree_stockDAO();
		
		ArrayList<Entree_stock> ebCol = ed.getAllArchiver();
		
		request.setAttribute("ebCol", ebCol);
		request.getRequestDispatcher("fournisseursHistoriqueAchat.jsp").forward(request, response);
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
