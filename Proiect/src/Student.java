import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Student {
    private String nume;
    private String prenume;
    private int grupa;

    //Constructor Student
    public Student(String nume, String prenume, int grupa) {
        this.nume = nume;
        this.prenume = prenume;
        this.grupa = grupa;
    }

    public Student(String[] splituri)
    {
        this.nume = splituri[0];
        this.prenume = splituri[1];
        this.grupa = Integer.parseInt(splituri[2]);
    }

    public void CitireDateStudent()
    {

        String nume = "";
        String prenume = "";
        int grupa = 0;
        Scanner cin = new Scanner(System.in);
        boolean continueInput = true;

        do {
            try {
                System.out.print("Introduceti numele studentului: ");

                nume = cin.nextLine();

                System.out.print("Introduceti prenumele studentului: ");

                prenume = cin.nextLine();

                System.out.print("Introduceti grupa studentului: ");

                grupa = cin.nextInt();
                continueInput = false;
            } catch (InputMismatchException ex) {
                System.out.println(ex + " Try again!");
                cin.nextLine();
            }
        }while(continueInput);

        this.nume = nume;
        this.prenume = prenume;
        this.grupa = grupa;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public int getGrupa() {
        return grupa;
    }

    @Override
    public String toString() {
        return "Grupa: " + this.grupa + " Studentul: " + this.nume + " " + this.prenume;
    }

    //Funtie de comparare studenti dupa nume
    public int comperTo(Student s)
    {
        if(this.nume.compareTo(s.nume) < 0)
            return -1;
        else
        if(this.nume.compareTo(s.nume) > 0)
            return 1;
        return 0;
    }

    //Funtie de comparare studenti dupa grupa
    public int comperToGrupa(Student s)
    {
        if(this.grupa < s.grupa)
            return -1;
        else
            if(this.grupa > s.grupa)
                return 1;
        return 0;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return grupa == student.grupa && Objects.equals(nume, student.nume) && Objects.equals(prenume, student.prenume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, prenume, grupa);
    }
}
