import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GUIThread implements Runnable {

    IDataLoader dataLoader = Settings.dataloader.get(Settings.loadType);
    ArrayList<Student> students = new ArrayList<Student>(Arrays.asList(dataLoader.createStudentsData()));
    ArrayList<Profesor> teachers = new ArrayList<Profesor>(Arrays.asList(dataLoader.createProfesorData()));
    JFrame frame = new JFrame("Main Page");
    @Override
    public void run() {
        frame.setContentPane(new FirstPageGUI(frame,students,teachers).getMainPanel());
        frame.setVisible(true);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
