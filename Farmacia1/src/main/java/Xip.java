
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Xip {
	    private int id;
	    private Medicine medicine;
	    private Patient patient;
	    private Date endDate;

	    public Xip() {
	    }

	    public Xip(int id, Medicine medicine, Patient patient, Date endDate) {
	        this.id = id;
	        this.medicine = medicine;
	        this.patient = patient;
	        this.endDate = endDate;
	    }
	    
	    
	    

	    public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Medicine getMedicine() {
			return medicine;
		}

		public void setMedicine(Medicine medicine) {
			this.medicine = medicine;
		}

		public Patient getPatient() {
			return patient;
		}

		public void setPatient(Patient patient) {
			this.patient = patient;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		
		

		public void load(int id) {
	        BBDD bd = new BBDD();
	        bd.conectar();

	        String query = "SELECT * FROM xip WHERE id = " + id;

	        ResultSet resultSet = bd.loadSelect(query);

	        try {
	            if (resultSet.next()) {
	                int xipId = resultSet.getInt("id");
	                int medicineId = resultSet.getInt("id_medicine");
	                String patientMail = resultSet.getString("id_patient");
	                Date endDate = resultSet.getDate("date");

	             
	                this.id = xipId;
	                this.medicine = medicine;
	                this.patient = patient;
	                this.endDate = endDate;
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al cargar los datos del Xip: " + e.getMessage());
	        } finally {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	                System.out.println("Error al cerrar el ResultSet: " + e.getMessage());
	            }
	        }
	    }
	}
