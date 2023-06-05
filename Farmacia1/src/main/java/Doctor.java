import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Doctor extends Person {
	
	private String pass;
	private LocalDate lastlog; 
	private int session;
	private ArrayList releaseList;
	
	// Constructores
	public Doctor() {
		this.releaseList=new ArrayList<>();
	}
	
	public Doctor(String name, String mail, String pass, LocalDate lastlog, int session) {
		super(name,mail);
		this.pass=pass;
		this.lastlog=lastlog;
		this.session=session;
		this.releaseList=new ArrayList<>();
	}
	
	// Get y set
	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public LocalDate getLastlog() {
		return lastlog;
	}

	public void setLastlog(LocalDate lastlog) {
		this.lastlog = lastlog;
	}

	public Integer getSession() {
		return session;
	}

	public void setSession(Integer session) {
		this.session = session;
	}


	// Metodos 
	
	public void load(String id) {
	    String query = "SELECT * FROM doctor WHERE mail='" + id + "';";
	    BBDD bd = new BBDD();
	    bd.conectar();
	    ResultSet rs = null;
	    
	    try {
	        rs = bd.loadSelect(query);
	        if (rs != null && rs.next()) {
	            this.setName(rs.getString("name"));
	            this.setMail(rs.getString("mail"));
	            this.setPass(rs.getString("pass"));
	        }
	    } catch (SQLException e) {
	        // Manejar la excepción adecuadamente o mostrar un mensaje de error
	        System.out.print("Error a Doctor .load.Class" + e.getMessage());
	    }
	}

	
	public ArrayList<Xip> loadReleaseList() {
	    String query = "SELECT * FROM xip WHERE doctor_mail = '" + mail + "'";
	    ArrayList<Xip> releaseList = new ArrayList<>();
	    BBDD bd = new BBDD();
	    bd.conectar();

	    ResultSet resultSet = bd.loadSelect(query);

	    try {
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            Xip xip = new Xip();
	            xip.load(id);
	            System.out.print("2");

	          
	            int idMedicine = resultSet.getInt("id_medicine");
	            Medicine medicine = new Medicine();
	            medicine.load(idMedicine);
	            xip.setMedicine(medicine);

	   
	            String patientMail = resultSet.getString("id_patient");
	            Patient patient = new Patient();
	            patient.load(patientMail);
	            xip.setPatient(patient);

	            releaseList.add(xip);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener los datos de los xips: " + e.getMessage());
	    } finally {
	        try {
	            resultSet.close();
	        } catch (SQLException e) {
	            System.out.println("Error al cerrar el ResultSet: " + e.getMessage());
	        }
	    }

	    return releaseList;
	}

	
	public String getTable() {
	    StringBuilder htmlTable = new StringBuilder();
	    htmlTable.append("<table>");
	    htmlTable.append("<tr><th>ID del Xip</th><th>Nombre Paciente</th><th>Nombre Medicina</th><th>Fecha</th></tr>");

	    ArrayList<Xip> xips = loadReleaseList();

	    for (int i = 0; i < xips.size(); i++) {
	        Xip xip = xips.get(i);

	        int id = xip.getId();
	        String paciente = xip.getPatient().getMail();
	        String medicina = xip.getMedicine().getName();
	        String fecha = xip.getEndDate().toString();

	        htmlTable.append("<tr>");
	        htmlTable.append("<td>").append(id).append("</td>");
	        htmlTable.append("<td>").append(paciente).append("</td>");
	        htmlTable.append("<td>").append(medicina).append("</td>");
	        htmlTable.append("<td>").append(fecha).append("</td>");
	        htmlTable.append("</tr>");
	    }

	    htmlTable.append("</table>");

	    return htmlTable.toString();
	}
	
	
	

	public void login(String mail, String pass) {
	    String query = "SELECT * FROM doctor WHERE mail='" + mail + "' AND pass='" + pass + "';";
	    BBDD bd = new BBDD();
	    bd.conectar();
	    ResultSet rs = bd.loadSelect(query);
	    
	    try {
	        if (rs != null && rs.next()) {
	        	
	            this.setLastlog(LocalDate.now());
	        
	            
	            Random random = new Random();
	            String  code = "1";
	            for (int i = 0; i < 9; i++) {
	                code = code + Integer.toString(random.nextInt(10));
	            }
	            int session = Integer.parseInt(code);
	            
	            this.setSession(session);
	            System.out.println(this.getSession());
	            
	            System.out.print(getLastlog());
	            System.out.print(getSession());
	            System.out.print(mail);
	            query = "UPDATE doctor SET last_log='" + this.getLastlog() + "', session='" + this.getSession() + "' WHERE mail='" + mail + "';";
	            bd.updateDoctor(query);
	            
	            this.load(mail);
	      }
	        }catch (SQLException e) {
	    	System.out.print("Error a BBDD .exeUpdate.Class"+ e.getMessage());
	   }	
	}
	
	
	
	public boolean isLogged(String email, String session) {
	    String query = "SELECT * FROM doctor WHERE mail = '" + email + "' AND session = '" + session + "';";
	    BBDD bd = new BBDD();

	    try {
	        bd.conectar();
	        ResultSet rs = bd.loadSelect(query);
	        if (rs.next()) {
	            return true;
	        }
	    }catch (SQLException e) {
	    	System.out.print("Error a BBDD .exeIsLogged.Class"+ e.getMessage());
	   }	

	    return false;
	}

	
}
