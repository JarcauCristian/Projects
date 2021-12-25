import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RegisterGUI {

    public RegisterGUI(JFrame owner)
    {
        this.owner = owner;
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtUsername.getText() != null && pwdPassword.getPassword() != null && pwdConfPassword != null && txtNume.getText() != null && txtPrenume.getText() != null)
                {
                    if(new String(pwdPassword.getPassword()).equals(new String(pwdConfPassword.getPassword())))
                    {
                        if(FirstPageGUI.students.indexOf(new Student(txtNume.getText(),txtPrenume.getText())) != -1)
                        {
                            try {
                                MenuStrategy mn = new StudentStrategy(new Student(txtNume.getText(),txtPrenume.getText(),FirstPageGUI.students.get(FirstPageGUI.students.indexOf(new Student(txtNume.getText(),txtPrenume.getText()))).grupa));
                                Application.getInstance().SaveNewUsers(txtUsername.getText(), SHA_256.toHexString(SHA_256.getSHA(new String(pwdPassword.getPassword()))),mn);
                                JOptionPane.showMessageDialog(null,"Congratulation! Your registration is complet!");
                                RegisterPanel.setVisible(false);
                                owner.setContentPane(new FirstPageGUI(owner).getMainPanel());
                                owner.setTitle("Main Page");
                            }
                            catch (Exception ex)
                            {
                                JOptionPane.showMessageDialog(null,"Somethin went wrong at saving!");
                            }
                        }
                        else if(FirstPageGUI.teachers.indexOf(new Profesor(txtNume.getText(),txtPrenume.getText())) != -1)
                            {
                                try {
                                    MenuStrategy mn = new TeacherStrategy(new Profesor(txtNume.getText(),txtPrenume.getText()));
                                    Application.getInstance().SaveNewUsers(txtUsername.getText(), SHA_256.toHexString(SHA_256.getSHA(new String(pwdPassword.getPassword()))),mn);
                                    JOptionPane.showMessageDialog(null,"Congratulation! Your registration is complet!");
                                    RegisterPanel.setVisible(false);
                                    owner.setContentPane(new FirstPageGUI(owner).getMainPanel());
                                    owner.setTitle("Main Page");
                                }
                                catch (Exception ex)
                                {
                                    JOptionPane.showMessageDialog(null,"Somethin went wrong at saving!");
                                }
                            }
                            else
                                JOptionPane.showMessageDialog(null,"The student or the teacher dose not exist in our database!","Wrong Person",JOptionPane.WARNING_MESSAGE);
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Passwords dose not match!", "Password Dose Not Match", JOptionPane.WARNING_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(null,"Some field is empty!", "Empty Fields", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public JPanel getRegisterPanel() {
        return RegisterPanel;
    }

    private JPanel RegisterPanel;
    private JPanel UsernamePanel;
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JPanel PasswordPanel;
    private JLabel lblPassword;
    private JPasswordField pwdPassword;
    private JPanel ConfPasswordPanel;
    private JLabel lblConfPassword;
    private JPasswordField pwdConfPassword;
    private JPanel PersonCredentialPanel;
    private JLabel lblNume;
    private JTextField txtNume;
    private JLabel lblPrenume;
    private JTextField txtPrenume;
    private JButton btnRegister;
    private JPanel btnPanel;
    private JFrame owner;
}
