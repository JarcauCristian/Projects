import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class TeacherForm {

    public TeacherForm(JFrame owner) {
        this.owner = owner;
        owner.setSize(1000, 1000);

        String[] numbers = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        listGrades.setListData(numbers);

        String[] list = new String[Application.getInstance().manager.getCursuri().size()];
        for (String s : Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().keySet()) {
            String prenume = Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().get(s);
            int nr = 0;

            for (Curs c : Application.getInstance().manager.getCursuri()) {
                if (c.prof.nume.equals(s) && c.prof.prenume.equals(prenume)) {
                    list[nr++] = c.nume;
                }
            }
        }
        listCourses.setListData(list);
        btnStudents.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnStudents) {
                    if (listCourses.getSelectedIndex() != -1) {
                        lblStudents.setText("Students from " + listCourses.getSelectedValue());
                        try {
                            Curs c = Application.getInstance().manager.search(new Curs((String) listCourses.getSelectedValue()));
                            String[] list = new String[c.studenti.size()];
                            int nr = 0;
                            for (Student s : c.studenti) {
                                String nota = c.note.get(s) == null ? " " : c.note.get(s).toString();
                                list[nr++] = s.nume + " " + s.prenume + " nota: " + nota;
                            }
                            listStudents.setListData(list);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                    }
                }
            }
        });

        btnGradeStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnGradeStudent) {
                    if (listGrades.getSelectedIndex() != -1 && listStudents.getSelectedIndex() != -1 && listCourses.getSelectedIndex() != -1) {
                        String data = (String) listStudents.getSelectedValue();
                        for (Curs c : Application.getInstance().manager.getCursuri()) {
                            if (c.equals(new Curs((String) listCourses.getSelectedValue()))) {
                                try {
                                    c.noteazaStudent(new Student((String) ((String) listStudents.getSelectedValue()).split(" ")[0], (String) ((String) listStudents.getSelectedValue()).split(" ")[1]), Integer.parseInt((String) listGrades.getSelectedValue()));
                                    try {
                                        Curs curs = Application.getInstance().manager.search(new Curs((String) listCourses.getSelectedValue()));
                                        String[] list = new String[curs.studenti.size()];
                                        int nr = 0;
                                        for (Student s : curs.studenti) {
                                            String nota = curs.note.get(s) == null ? " " : curs.note.get(s).toString();
                                            list[nr++] = s.nume + " " + s.prenume + " nota: " + nota;
                                        }
                                        listStudents.setListData(list);
                                    } catch (Exception ex) {
                                        JOptionPane.showMessageDialog(null, ex.getMessage());
                                    }
                                    Application.getInstance().displayManager.displayCourses(Application.getInstance().manager.getCursuriArray());
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null, ex.getMessage());
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public JPanel getTeacherFormPanel() {
        return TeacherFormPanel;
    }

    private JPanel TeacherFormPanel;
    private JPanel CoursesPanel;
    private JList listCourses;
    private JLabel lblCourses;
    private JList listStudents;
    private JLabel lblStudents;
    private JButton btnStudents;
    private JButton btnGradeStudent;
    private JList listGrades;
    private JLabel lblGrades;
    private JFrame owner;
}
