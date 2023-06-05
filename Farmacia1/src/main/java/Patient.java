
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Patient extends Person {

    public Patient() {
    	super();
    }

    public Patient(String mail, String name) {
        super(mail, name);
    }

    public void load(String id) {
        BBDD bbdd = new BBDD();
        bbdd.conectar();

        String query = "SELECT * FROM patient WHERE mail = '" + id + "'";

        ResultSet resultSet = bbdd.loadSelect(query);

        try {
            if (resultSet.next()) {
                String patientMail = resultSet.getString("mail");
                String patientName = resultSet.getString("name");

                // Actualiza los atributos de la instancia actual
                this.setMail(patientMail);
                this.setName(patientName);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar los datos del Patient: " + e.getMessage());
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar el ResultSet: " + e.getMessage());
            }
        }
    }
}

