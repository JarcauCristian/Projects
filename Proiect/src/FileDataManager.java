import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileDataManager implements IDataLoader{

    public ManagerCursuri manager = new ManagerCursuri();
    public Random rand = new Random();
    public int minimumRequiredStudents = 5;
    public Student[] dataSetOfStudent = createStudentsData();
    public Profesor[] dataSetOfProfesor = createProfesorData();

    @Override
    public Student[] createStudentsData() {
        ArrayList<Student> s = new ArrayList<>();
        try(Scanner cin = new Scanner(new File(Settings.STUDENTS_PATH)))
        {
            cin.nextLine();
            String line;
            while (cin.hasNextLine())
            {
                line = cin.nextLine();
                s.add(new Student(line.split(",")));
            }
            cin.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        Student[] students = (Student[]) s.toArray();
        return students;
    }

    public Set<Student> createRandomSetOfStudents() {
        int studentInscrisiLaCurs = minimumRequiredStudents + rand.nextInt(dataSetOfStudent.length - minimumRequiredStudents);
        Set<Student> setOfStudents = new HashSet<Student>();
        for (int i = 0; i < studentInscrisiLaCurs; i++) {
            int randomStudentIndex = rand.nextInt(dataSetOfStudent.length);
            setOfStudents.add(dataSetOfStudent[randomStudentIndex]);
        }
        return setOfStudents;
    }

    @Override
    public Profesor[] createProfesorData() {
        ArrayList<Profesor> p = new ArrayList<>();
        try(Scanner cin = new Scanner(new File(Settings.TEACHERS_PATH)))
        {
            cin.nextLine();
            String line;
            while (cin.hasNextLine())
            {
                line = cin.nextLine();
                p.add(new Profesor(line.split(",")));
            }
            cin.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        Profesor[] teachers = (Profesor[]) p.toArray();
        return teachers;
    }

    @Override
    public Curs[] createCoursesData() {
        ArrayList<Curs> c = new ArrayList<>();
        try(Scanner cin = new Scanner(new File(Settings.COURSES_PATH)))
        {
            cin.nextLine();
            String line;
            Set<Student> studentsData = new HashSet<>();
            Profesor profesor;
            while (cin.hasNextLine())
            {
                line = cin.nextLine();
                studentsData = createRandomSetOfStudents();
                profesor = dataSetOfProfesor[rand.nextInt(dataSetOfProfesor.length)];
                c.add(new Curs(line.split(","),profesor,studentsData));
            }
            cin.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        Curs[] courses = (Curs[]) c.toArray();
        return courses;
    }
}
