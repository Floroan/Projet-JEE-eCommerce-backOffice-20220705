package control;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
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
		
		String names[] = session.getValueNames();
		for ( int i = 0, n = names.length; i < n; i++ ) {
			String key = names[i];
			System.out.println("Session key (Header) : " + key + " -> Session value : " + session.getValue(key));
		}
		
		session.removeAttribute("admin");
		session.removeAttribute("isConnected");
		
		String names2[] = session.getValueNames();
		for ( int i = 0, n = names2.length; i < n; i++ ) {
			String key = names2[i];
			System.out.println("Session key (Header) : " + key + " -> Session value : " + session.getValue(key));
		}
		
		response.sendRedirect("Signin");
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
