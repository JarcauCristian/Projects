import java.util.Date;
import java.util.Objects;

public class Vehicle {
    private String registrationNumber;
    private String type;
    private String brand;
    private String color;
    private Integer manufactureYear;

    public Vehicle(String registrationNumber, String type, String brand, Integer manufactureYear, String color) {
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.manufactureYear = manufactureYear;
    }

    public Vehicle() {
        this.brand = "";
        this.color = "";
        this.manufactureYear = 0;
        this.registrationNumber = "";
        this.type = "";
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public Integer getManufactureYear() {
        return manufactureYear;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", manufactureYear=" + manufactureYear +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return registrationNumber.equals(vehicle.registrationNumber) && type.equals(vehicle.type) && brand.equals(vehicle.brand) && color.equals(vehicle.color) && manufactureYear.equals(vehicle.manufactureYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber, type, brand, color, manufactureYear);
    }
}
