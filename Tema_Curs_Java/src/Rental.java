import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Rental implements VehicleMethods{

    private ArrayList<Person> persons;
    private ArrayList<Vehicle> vehicles;

    public Rental()
    {
        this.persons = new ArrayList<>();
        this.vehicles = new ArrayList<>();
    }

    public void setPersons(Person persons) {
        this.persons.add(persons);
    }

    public void setVehicles(Vehicle vehicles) {
        this.vehicles.add(vehicles);
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public void readInputFiles(String fileVehicle, String filePersons)
    {
        try
        {
            Scanner scanner = new Scanner(new File(fileVehicle));
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                setVehicles(new Vehicle(line.split(",")[0],line.split(",")[1].replace(" ", ""),line.split(",")[2].replace(" ", ""),Integer.parseInt(line.split(",")[3].replace(" ", "")),line.split(",")[4].replace(" ", "")));
            }
            scanner.close();
            scanner = new Scanner(new File(filePersons));
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                setPersons(new Person(Long.parseLong(line.split(",")[0]),line.split(",")[1].replace(" ", ""),line.split(",")[2].replace(" ", ""),line.split(",")[3].replace(" ", ""),Integer.parseInt(line.split(",")[4].replace(" ", ""))));
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void viewVehicleOrderByManufactureYear() {
        vehicles.sort(new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                if (o1.getManufactureYear() < o2.getManufactureYear())
                    return -1;
                else
                    if (o1.getManufactureYear() > o2.getManufactureYear())
                        return 1;
                return 0;
            }
        });

        vehicles.forEach((n) -> System.out.println(n));
    }

    @Override
    public void searchAVehicleByType(String type) {
        for (Vehicle v : vehicles) {
            if (v.getType().equals(type))
            {
                System.out.println(v);
            }
        }
    }
}
