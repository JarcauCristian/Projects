import java.beans.XMLEncoder;
import java.io.*;

public class FileDisplay implements IDisplayManager{
    @Override
    public void displayStudents(Student[] students)
    {
        try {
            FileOutputStream fos = new FileOutputStream("studenti.xml");
            XMLEncoder xe = new XMLEncoder(fos);

            xe.writeObject(students);
            xe.close();
            fos.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void displayTeachers(Profesor[] profesors)
    {
        try {
            FileOutputStream fos = new FileOutputStream("profesori.xml");
            XMLEncoder xe = new XMLEncoder(fos);

            xe.writeObject(profesors);
            xe.close();
            fos.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void displayCourses(Curs[] cursuri)
    {
        try {
            FileOutputStream fos = new FileOutputStream("cursuri.xml");
            XMLEncoder xe = new XMLEncoder(fos);

            xe.writeObject(cursuri);
            xe.close();
            fos.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
