
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

	public class Medicine {
	    private int id;
	    private String name;
	    private float tmax;
	    private float tmin;

	    public Medicine() {
	    }

	    public Medicine(int id, String name, float tmax, float tmin) {
	        this.id = id;
	        this.name = name;
	        this.tmax = tmax;
	        this.tmin = tmin;
	    }

	    
	    
	    public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public float getTmax() {
			return tmax;
		}

		public void setTmax(float tmax) {
			this.tmax = tmax;
		}

		public float getTmin() {
			return tmin;
		}

		public void setTmin(float tmin) {
			this.tmin = tmin;
		}
		
		

		public void load(int id) {
	        BBDD bbdd = new BBDD();
	        bbdd.conectar();

	        String query = "SELECT * FROM medicine WHERE id = " + id;

	        ResultSet resultSet = bbdd.loadSelect(query);

	        try {
	            if (resultSet.next()) {
	                int medicineId = resultSet.getInt("id");
	                String medicineName = resultSet.getString("name");
	                float tmax = resultSet.getFloat("tmax");
	                float tmin = resultSet.getFloat("tmin");

	                this.id = medicineId;
	                this.name = medicineName;
	                this.tmax = tmax;
	                this.tmin = tmin;
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al cargar los datos del Medicine: " + e.getMessage());
	        } finally {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	                System.out.println("Error al cerrar el ResultSet: " + e.getMessage());
	            }
	        }
	    }
	}

