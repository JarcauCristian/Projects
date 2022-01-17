import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TeacherStrategy implements MenuStrategy {

    public Profesor profesor;

    public TeacherStrategy() {
    }

    TeacherStrategy(Profesor p) {
        this.profesor = p;
    }

    @Override
    public UserAccountType getAccountType() {
        return UserAccountType.TEACHER;
    }

    @Override
    public HashMap<String, String> getAccountHolderInformation() {
        return new HashMap<>() {{
            put(profesor.nume, profesor.prenume);
        }};
    }

    public void ShowMenuOption() {
        System.out.println("Taught Courses: ");
        ArrayList<String> list = Application.getInstance().manager.ShowCoursesOfTeacher(profesor);
        int t, counter, x;
        boolean ok = false;
        try (Scanner cin = new Scanner(System.in)) {
            do {
                if (ok) {
                    System.out.println("Taught Courses: ");
                    Application.getInstance().manager.ShowCoursesOfTeacher(profesor);
                }
                else
                    ok = true;
                t = cin.nextInt();
                if (t != list.size() + 1) {
                    String course = list.get(t - 1);
                    ArrayList<String> listStudents = Application.getInstance().manager.StudentiCurs(new Curs(course));
                    int IndexOfCourse = Application.getInstance().manager.getCursuri().indexOf(new Curs(course));
                    do {
                        x = cin.nextInt();
                        counter = 1;
                        if (x != listStudents.size() + 1) {
                            Student s = new Student(listStudents.get(x - 1).split(" ")[1], listStudents.get(x - 1).split(" ")[2]);
                            System.out.println("Grade the student:");
                            System.out.println("1. 1");
                            System.out.println("2. 2");
                            System.out.println("3. 3");
                            System.out.println("4. 4");
                            System.out.println("5. 5");
                            System.out.println("6. 6");
                            System.out.println("7. 7");
                            System.out.println("8. 8");
                            System.out.println("9. 9");
                            System.out.println("10. 10");
                            x = cin.nextInt();
                            if (x != 11)
                                Application.getInstance().manager.getCursuri().get(IndexOfCourse).noteazaStudent(s, x);
                            for (Student student : Application.getInstance().manager.getCursuri().get(IndexOfCourse).studenti) {
                                System.out.println(counter + ". " + student.getNume() + " " + student.getPrenume() + ": " + Application.getInstance().manager.getCursuri().get(IndexOfCourse).note.get(student));
                            }
                            System.out.println(listStudents.size() + 1 + ". " + "Go back to courses");
                        }
                    } while (x != listStudents.size() + 1);
                }
            }
            while (t != list.size() + 1);
            Application.getInstance().displayManager.displayCourses(Application.getInstance().manager.getCursuriArray());
            if(t == list.size() + 1)
            {
                System.out.println("\nProcess finished with exit code 0");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
