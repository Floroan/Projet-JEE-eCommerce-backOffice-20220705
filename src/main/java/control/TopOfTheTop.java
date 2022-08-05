package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.Database;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import dao.GenericDAO;

/**
 * Servlet implementation class TopOfTheTop
 */
public class TopOfTheTop extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private GenericDAO genDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopOfTheTop() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Database.Connect();
		genDao = new GenericDAO<>();
		ArrayList<String> tables  = genDao.getNamesTablesContainsType("date");
		
		if(request.getParameter("topOf") != null) {
			System.out.println(request.getParameter("dt1"));
			System.out.println(request.getParameter("dt2"));
			System.out.println(request.getParameter("selectedTable"));
			
			LinkedHashMap<Date, Integer> topsOf = new LinkedHashMap<Date, Integer>();
			topsOf = genDao.getResultByDate(request.getParameter("selectedTable"),
					request.getParameter("dt1"),
					request.getParameter("dt2"));
			
			System.out.println(topsOf);
			String topOfResults = "";
			for(Map.Entry<Date, Integer> entry : topsOf.entrySet()){
	
				 SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
				 String dt = df.format(entry.getKey());
				 //System.out.println("date:" +  dt);
				 topOfResults += "{ x: "+ "\"" + dt + " GMT" + "\"" + ", y: " + entry.getValue() + "},"; 
				 System.out.println(topOfResults);
				}	
			request.setAttribute("topOfResults", topOfResults);
		}
		
		if(request.getParameter("retourDash") != null) {
			response.sendRedirect("Dashboard");
		}
		
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		
		
			request.setAttribute("resultat", "Résultat pour la table " + request.getParameter("selectedTable") + "du " + request.getParameter("dt1") + " au " + request.getParameter("dt2"));

		request.setAttribute("tables", tables);
		try {
			request.getRequestDispatcher("/topOfTheTop.jsp").forward(request, response);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
