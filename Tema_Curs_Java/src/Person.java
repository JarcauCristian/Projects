import java.util.Date;

public class Person {
    private Long registrationNumber;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private Integer yearOfDriverLicenseEmited;

    public Person(Long registrationNumber, String firstName, String lastName, String gender, Integer yearOfDriverLicenseEmited) {
        this.registrationNumber = registrationNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.yearOfDriverLicenseEmited = yearOfDriverLicenseEmited;
    }

    public Person() {
        this.registrationNumber = Long.valueOf(0);
        this.dateOfBirth = new Date();
        this.firstName = "";
        this.lastName = "";
        this.gender = "";
        this.yearOfDriverLicenseEmited = 1900;
    }
}
