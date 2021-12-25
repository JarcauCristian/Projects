import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

enum LOAD_TYPE {
    HARDCODAT, KEYBOARD, FILE
}

enum DISPLAY_TYPE  {
    CONSOLA, FISIER, GUI
}

class Main {
    public static void main(String[] args)
    {
        Settings.initApplication();
        IDataLoader dataLoader = Settings.dataloader.get(Settings.loadType);
        ArrayList<Student> students = new ArrayList<Student>(Arrays.asList(dataLoader.createStudentsData()));
        ArrayList<Profesor> teachers = new ArrayList<Profesor>(Arrays.asList(dataLoader.createProfesorData()));
        JFrame frame = new JFrame("Main Page");
        frame.setContentPane(new FirstPageGUI(frame,students,teachers).getMainPanel());
        frame.setVisible(true);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}