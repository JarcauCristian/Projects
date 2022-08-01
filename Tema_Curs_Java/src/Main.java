import java.io.FileNotFoundException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        Rental rental = new Rental();
        rental.readInputFiles("vehicles.txt", "persoane.txt");
        //rental.viewVehicleOrderByManufactureYear();
        rental.searchAVehicleByType("MASINA");
    }
}
