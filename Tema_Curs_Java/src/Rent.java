import java.sql.*;

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
        return false;
    }

    @Override
    public void viewTheRentedVehiclesOfASpecificUser(Person person) {

    }
}
