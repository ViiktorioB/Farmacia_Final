

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Servlet implementation class Release
 */
public class Release extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Release() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  String mail = request.getParameter("mail");
    	    String session = request.getParameter("session");
    	    String idXip = request.getParameter("idXip");
    	    String nombreMed = request.getParameter("medicine");
    	    String date = request.getParameter("fechaLimite");
    	    String nombrePaciente = request.getParameter("patient");
    	  BBDD bd = new BBDD();
    	  bd.conectar();

    	  try {
    	    // Obtener el ID del medicamento según el nombre
    	    String queryMed = "SELECT id FROM medicine WHERE name = '" + nombreMed + "'";
    	    ResultSet resultSetMed = bd.loadSelect(queryMed);
    	    resultSetMed.next();
    	    String idMed = resultSetMed.getString("id");

    	    // Obtener el correo del paciente según el nombre
    	    String queryPac = "SELECT mail FROM patient WHERE name = '" + nombrePaciente + "'";
    	    ResultSet resultSetPac = bd.loadSelect(queryPac);
    	    resultSetPac.next();
    	    String mailP = resultSetPac.getString("mail");
    	    
    	    String query = "INSERT INTO xip (id, doctor_mail, id_medicine, id_patient, date) VALUES (" + idXip + ", '" + mail + "', " + idMed + ", '" + mailP + "', '" + date + "')";
    	    bd.executeInsert(query);
    	    System.out.print(query);
    	    response.getWriter().print("Alta realizada correctamente");
    	  } catch (SQLException e) {
    	    response.getWriter().print("Error al realizar el alta: " + e.getMessage());
    	  }
    	}
}