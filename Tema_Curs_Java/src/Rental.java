import java.io.File;
import java.io.FileNotFoundException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Rental implements VehicleMethods, RentalMethods{

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
                setPersons(new Person(line.split(",")[0],line.split(",")[1].replace(" ", ""),line.split(",")[2].replace(" ", ""),line.split(",")[3].replace(" ", ""),Integer.parseInt(line.split(",")[4].replace(" ", ""))));
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void viewVehicleOrderByManufactureYear() {
        ArrayList<Vehicle> list = (ArrayList<Vehicle>) vehicles.stream().sorted(Comparator.comparing(Vehicle::getManufactureYear)).collect(toList());

        list.forEach((n) -> System.out.println(n));
    }

    @Override
    public void searchAVehicleByType(String type) {
        vehicles.stream().filter(d -> d.getType() == Type.valueOf(type)).forEach(System.out::println);
    }

    @Override
    public void showHistoryOfRentalVehiclesAndTheirClient() {
        if (DBClass.connection()) {
            try {
                CallableStatement callable = DBClass.conn.prepareCall("{call sp_getAllVehiclesAndTheirUsers()}");
                callable.execute();
                ResultSet resultSet = callable.getResultSet();

                System.out.printf("%-10s | %-10s | %-7s | %-9s | %-7s | %-10s | %-11s | %-4s | %-13s | %-15s | %-15s | %-10s | %-6s | %-4s", "Start Date", "End Date", "Kilometers", "RegistNr", "Type", "Brand", "Color", "MaYe", "CNP", "First Name", "Last Name", "Birth Day", "Gender", "DVLN");
                System.out.println();
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                while (resultSet.next()) {
                    System.out.printf("%-10s | %-10s | %-7s | %-9s | %-7s | %-10s | %-11s | %-4s | %-13s | %-15s | %-15s | %-10s | %-6s | %-4s", resultSet.getDate("startdate"), resultSet.getDate("enddate"), resultSet.getDouble("kilometers"), resultSet.getString("registationnumber"),
                            resultSet.getString("type"), resultSet.getString("brand"), resultSet.getString("color"), resultSet.getString("manufactureryear"), resultSet.getString("CNP"),
                            resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getDate("dateofbirth"), resultSet.getString("gender"), resultSet.getInt("driverlincese"));
                    System.out.println();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    DBClass.conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public boolean rentAVehicle() {
        if (DBClass.connection())
        {
            Statement statement;
            try {
                statement = DBClass.conn.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            Scanner cin = new Scanner(System.in);
            System.out.println("Enter the start date of the rental period: ");
            String startDate = cin.nextLine();
            System.out.println("Enter the end date of the rental period: ");
            String endDate = cin.nextLine();
            System.out.println("Enter the borrower of the car CNP: ");
            String personCNP = cin.nextLine();
            System.out.println("Enter the vehicle registration number: ");
            String vehicleRN = cin.nextLine();
            int indexPerson =  0;
            indexPerson = searchForPerson(personCNP);
            int indexVehicle = 0;
            indexVehicle = searchForVehicle(vehicleRN);

            if (indexPerson != 0 && indexVehicle != 0)
            {
                try {
                    statement.executeUpdate("insert into RentalData(startdate,enddate,kilometers,idClients,idVehicles) values('" + startDate + "','" + endDate + "'," + '0' + ",'" + indexPerson + "','" + indexVehicle + "');");
                    return true;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                DBClass.conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @Override
    public void viewTheRentedVehiclesOfASpecificUser(Person person) {
        if (DBClass.connection())
        {
            try {
                CallableStatement statement = DBClass.conn.prepareCall("{call sp_getAllTheVehiclesRentedByASpecificUser(?)}");
                int indexPerson = 0;
                indexPerson = searchForPerson(person.getCNP());
                if (indexPerson != 0)
                {
                    statement.setInt(1, indexPerson);
                }
                statement.execute();
                ResultSet resultSet = statement.getResultSet();
                System.out.println("Vehicles rented by: " + person.getFirstName() + " " + person.getLastName());
                System.out.printf("%-10s | %-10s | %-7s | %-9s | %-7s | %-15s | %-4s", "Start Date", "End date", "Km", "RegistNr", "Type", "Brand", "MNYR");
                System.out.println();
                while (resultSet.next())
                {
                    System.out.printf("%-10s | %-10s | %-7s | %-9s | %-7s | %-15s | %-4s", resultSet.getDate("startdate"), resultSet.getDate("enddate"), resultSet.getDouble("kilometers"), resultSet.getString("registationnumber"), resultSet.getString("type"), resultSet.getString("brand"), resultSet.getInt("manufactureryear"));
                    System.out.println();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            finally {
                try {
                    DBClass.conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private int searchForPerson(String personCNP)
    {
        readInputFiles("vehicles.txt", "persoane.txt");
        int indexPerson = 0;
        for (int i = 0; i < persons.size(); i++)
        {
            if (personCNP.equals(persons.get(i).getCNP()))
            {
                indexPerson = i + 4;
                break;
            }
        }
        return indexPerson;
    }

    private int searchForVehicle(String vehicleRN)
    {
        readInputFiles("vehicles.txt", "persoane.txt");
        int indexVehicle = 0;
        for (int i = 0; i < vehicles.size(); i++)
        {
            if (vehicleRN.equals(vehicles.get(i).getRegistrationNumber()))
            {
                indexVehicle = i + 1;
                break;
            }
        }
        return indexVehicle;
    }
}
