import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class FileDataManager implements IDataLoader{

    public ManagerCursuri manager = new ManagerCursuri(createCoursesData());
    public Random rand = new Random();
    public int minimumRequiredStudents = 5;
    public Student[] dataSetOfStudent = createStudentsData();
    public Profesor[] dataSetOfProfesor = createProfesorData();

    @Override
    public Student[] createStudentsData() {
        try
        {
            FileInputStream fis = new FileInputStream("studenti.xml");
            XMLDecoder xd = new XMLDecoder(fis);

            Student[] s = (Student[]) xd.readObject();
            xd.close();
            fis.close();
            return s;
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return new Student[0];
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
        try
        {
            FileInputStream fis = new FileInputStream("profesori.xml");
            XMLDecoder xd = new XMLDecoder(fis);

            Profesor[] p = (Profesor[]) xd.readObject();
            xd.close();
            fis.close();
            return p;
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return new Profesor[0];
    }

    @Override
    public Curs[] createCoursesData() {
        try
        {
            FileInputStream fis = new FileInputStream("cursuri.xml");
            XMLDecoder xd = new XMLDecoder(fis);

            Curs[] c = (Curs[]) xd.readObject();
            xd.close();
            fis.close();
            return c;
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return new Curs[0];
    }
}
