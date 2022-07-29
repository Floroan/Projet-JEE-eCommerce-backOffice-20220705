package tools;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RedirectError500 {

	
	public static void redirect500(HttpSession session, HttpServletRequest req, HttpServletResponse res ) {
	
		if(session.getAttribute("isConnected") == null || session == null ) {	
					try {
						req.getRequestDispatcher("/error500.jsp").forward(req, res);
					} catch (ServletException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
		
		}
	
	}

