import java.util.HashMap;
import java.util.Scanner;

public class StudentStrategy implements MenuStrategy{

    public Student student;

    public StudentStrategy() { }

    public StudentStrategy(Student s)
    {
        this.student = s;
    }

    @Override
    public UserAccountType getAccountType() {
        return UserAccountType.STUDENT;
    }

    @Override
    public HashMap<String, String> getAccountHolderInformation() {
        return new HashMap<>() {{
            put(student.nume, student.prenume);
        }};
    }

    @Override
    public void ShowMenuOption()
    {
        String[] list = Application.getInstance().manager.SerchStudentInCours(student);
        for(String s : Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().keySet()) {
            String prenume = Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().get(s);
            System.out.println("Welcome: " + s + " " + prenume + " Anul: " + Application.getInstance().manager.getStudentYear(new Student(s,prenume)));
            double mean = Application.getInstance().manager.MediaStudent(new Student(s,prenume));
            if(mean == 0)
            {
                System.out.println("The mean this year(There are some courses without grade): " + mean);
            }
            else
                System.out.println("The mean this year: " + mean);
        }
        System.out.println("Your Courses: ");
        System.out.println("Course name | Grade");
        int counter = 1;
        for(String s : list)
        {
            if(s == null)
            {
                continue;
            }
            else {
                System.out.println(counter + ". " + s.split(":")[0] + ": " + s.split(":")[1]);
                counter++;
            }
        }
        System.out.println(counter + ". " + "Exit Program");
        try (Scanner cin = new Scanner(System.in)){
            int t = cin.nextInt();
            if(t == counter)
            {
                System.out.println("\nProcess finished with exit code 0");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
