import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Rental rental = new Rental();

        try
        {
            Scanner scanner = new Scanner(new File("vehicles.txt"));
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                rental.setVehicles(new Vehicle(line.split(",")[0],line.split(",")[1].replace(" ", ""),line.split(",")[2].replace(" ", ""),Integer.parseInt(line.split(",")[3].replace(" ", "")),line.split(",")[4].replace(" ", "")));
            }
            scanner.close();
            scanner = new Scanner(new File("persoane.txt"));
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                rental.setPersons(new Person(Long.parseLong(line.split(",")[0]),line.split(",")[1].replace(" ", ""),line.split(",")[2].replace(" ", ""),line.split(",")[3].replace(" ", ""),Integer.parseInt(line.split(",")[4].replace(" ", ""))));
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        rental.viewVehicleOrderByManufactureYear();
        System.out.println();
        rental.searchAVehicleByType("RULOTA");
    }
}
