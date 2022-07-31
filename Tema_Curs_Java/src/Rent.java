import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class Rent implements RentalMethods{
    private int id;
    private String startDate;
    private String endDate;
    private double kilometers;
    private String personCNP;
    private String vehicleRegistrationNumber;

    public Rent(int id, String startDate, String endDate, double kilometers, String personCNP, String vehicleRegistrationNumber) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.kilometers = kilometers;
        this.personCNP = personCNP;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }

    public Rent() {
        this.id = 0;
        this.startDate = "";
        this.endDate = "";
        this.kilometers = 0;
        this.personCNP = "";
        this.vehicleRegistrationNumber = "";
    }

    public int getId() {
        return id;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getKilometers() {
        return kilometers;
    }

    public String getPersonCNP() {
        return personCNP;
    }

    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }

    public void setPersonCNP(String personCNP) {
        this.personCNP = personCNP;
    }

    public void setVehicleRegistrationNumber(String vehicleRegistrationNumber) {
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }

    @Override
    public void showHistoryOfRentalVehiclesAndTheirClient() {

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
        }
        return false;
    }

    @Override
    public void viewTheRentedVehiclesOfASpecificUser(Person person) {

    }

    public void DBConnect() throws SQLException {
        if (DBClass.connection()) {
            Statement statement = DBClass.conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from rentaldata");
            while (resultSet.next()) {
                System.out.println(resultSet.getDate("StartDate"));
            }
        }
    }

    private int searchForPerson(String personCNP)
    {
        Rental rental = new Rental();
        rental.readInputFiles("vehicles.txt", "persoane.txt");
        int indexPerson = 0;
        for (int i = 0; i < rental.getPersons().size(); i++)
        {
            if (personCNP.equals(rental.getPersons().get(i).getCNP()))
            {
                indexPerson = i + 4;
                break;
            }
        }
        return indexPerson;
    }

    private int searchForVehicle(String vehicleRN)
    {
        Rental rental = new Rental();
        rental.readInputFiles("vehicles.txt", "persoane.txt");
        int indexVehicle = 0;
        for (int i = 0; i < rental.getVehicles().size(); i++)
        {
            if (vehicleRN.equals(rental.getVehicles().get(i).getRegistrationNumber()))
            {
                indexVehicle = i + 1;
                break;
            }
        }
        return indexVehicle;
    }

    public void DBInsertInto() throws SQLException
    {
        if (DBClass.connection())
        {
            Statement statement = DBClass.conn.createStatement();
            String sql_statement = "insert into rentaldata(startdate,enddate,kilometers,idClients,idVehicles) ";
            try {
                Scanner cin = new Scanner(new File("inchirieri.txt"));
                Rental rental = new Rental();
                rental.readInputFiles("vehicles.txt", "persoane.txt");
                while (cin.hasNextLine())
                {
                    String line = cin.nextLine();
                    int indexPersons = 0;
                    int indexVehicle = 0;
                    for (int i = 0; i < rental.getPersons().size(); i++)
                    {
                        if (rental.getPersons().get(i).getCNP().equals(line.split(",")[3].replace(" ","")))
                        {
                            indexPersons = i + 4;
                            break;
                        }
                    }
                    for (int i = 0; i < rental.getVehicles().size(); i++)
                    {
                        if (rental.getVehicles().get(i).getRegistrationNumber().equals(line.split(",")[4].replace(" ","")))
                        {
                            indexVehicle = i + 1;
                            break;
                        }
                    }
                    String startdate = line.split(",")[0].replace(" ", "");
                    String enddate = line.split(",")[1].replace(" ", "");
                    String kilometers = line.split(",")[2].replace(" ", "");

                    statement.executeUpdate(sql_statement + "values('" + startdate + "','" + enddate + "','" + kilometers + "','" + indexPersons + "','" + indexVehicle + "');");
                }
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
    }
}
