import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        Rental rental = new Rental();
        rental.readInputFiles("vehicles.txt", "persoane.txt");
        new Rent().viewTheRentedVehiclesOfASpecificUser(rental.getPersons().get(0));
    }
}
