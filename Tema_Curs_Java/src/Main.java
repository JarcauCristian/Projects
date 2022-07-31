import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        new Rent().rentAVehicle();
    }
}
