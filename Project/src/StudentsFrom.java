import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentsFrom {

    public StudentsFrom(JFrame owner)
    {
        this.owner = owner;
        for(String s : Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().keySet()) {
            String prenume = Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().get(s);
            String[] list = Application.getInstance().manager.SerchStudentInCours(new Student(s,prenume));
            owner.setTitle("Welcome: " + Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().keySet().toString() + " " + Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().values().toString() + " Anul: " + Application.getInstance().manager.getStudentYear(new Student(s,prenume)));
            listCourses.setListData(list);
        }
        btnMedia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btnMedia)
                {
                    for(String s : Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().keySet()) {
                        String prenume = Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().get(s);
                        JOptionPane.showMessageDialog(null,"Media pe acest an universitar este: " + Application.getInstance().manager.MediaStudent(new Student(s,prenume)));
                    }
                }
            }
        });
    }

    public JPanel getStudentFormPanel() {
        return StudentFormPanel;
    }

    private JPanel StudentFormPanel;
    private JLabel lblCourses;
    private JList listCourses;
    private JButton btnMedia;
    private JFrame owner;
}
