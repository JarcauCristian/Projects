import java.util.Date;

public class Person {
    private String CNP;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private Integer yearOfDriverLicenseEmited;

    public Person(String CNP, String firstName, String lastName, String gender, Integer yearOfDriverLicenseEmited) {
        if(CNP.length() == 13)
        {
            this.CNP = CNP;
        }
        this.lastName = lastName;
        this.gender = gender;
        this.yearOfDriverLicenseEmited = yearOfDriverLicenseEmited;
    }

    public Person() {
        this.CNP = "";
        this.dateOfBirth = new Date();
        this.firstName = "";
        this.lastName = "";
        this.gender = "";
        this.yearOfDriverLicenseEmited = 1900;
    }
}
