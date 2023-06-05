

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONArray;

/**
 * Servlet implementation class ServeMedicines
 */
public class ServeMedicines extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServeMedicines() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 	String mail = request.getParameter("mail");
	        String session = request.getParameter("session");
	        ArrayList<String> medicineList = new ArrayList<>();
	        Doctor d = new Doctor();
	        BBDD bd = new BBDD();
	        bd.conectar();
	       
	        try {
	        	System.out.println(" Cargando medicinas");
	            String query = "SELECT id, name FROM medicine";
	            ResultSet resultSet = bd.loadSelect(query);

	            while (resultSet.next()) {
	                String medicine = resultSet.getString("id");
	                String medicineName = resultSet.getString("name");

	                medicineList.add(medicineName);
	                medicineList.add(medicine);
	            }

	            resultSet.close();
	        	} catch (Exception e) {
	            System.out.println("Error al obtener la lista de pacientes: " + e.getMessage());
	        }

	        JSONArray jsonArray = new JSONArray(medicineList);

	        response.setContentType("application/json");
	        PrintWriter out = response.getWriter();
	        out.print(jsonArray);
	        out.flush();
	    }
	}