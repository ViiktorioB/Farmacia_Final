

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class ServeXips
 */
public class ServeXips extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeXips() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String mail = request.getParameter("mail");
	     String session = request.getParameter("session");
	     Doctor d = new Doctor();
	     System.out.print("1");
	     
	    try {
	    	if ( d.isLogged(mail,session) == true) {
	    	 d.load(mail);
	    	if(d.loadReleaseList().isEmpty()) {
	    		System.out.print("No tienes xips asignados");
	    	}else {
	    		 response.getWriter().write(d.getTable());	    	}	 
	     }
	     }catch(Exception e) {
	          response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "error server");
	          System.out.print("ERROR LOGIN.GET: " + e.getMessage());
	     }
	     

	}
}
