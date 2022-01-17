import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI {

    public LoginGUI(JFrame owner)
    {
        this.owner = owner;
        owner.setResizable(false);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtUsername.getText() != null && pwdPassword.getPassword() != null)
                {
                    try {
                        Application.getInstance().login(new User(txtUsername.getText(), SHA_256.toHexString(SHA_256.getSHA(new String(pwdPassword.getPassword())))));
                        if(Application.getInstance().currentUser.menuStrategy.getAccountType() == UserAccountType.STUDENT)
                        {
                            JOptionPane.showMessageDialog(null,"You are now logged in!");
                            LoginPanel.setVisible(false);
                            owner.setContentPane(new StudentsFrom(owner).getStudentFormPanel());
                        }
                        else if(Application.getInstance().currentUser.menuStrategy.getAccountType() == UserAccountType.TEACHER)
                             {
                                 JOptionPane.showMessageDialog(null,"You are now logged in!");
                                 LoginPanel.setVisible(false);
                                 owner.setContentPane(new TeacherForm(owner).getTeacherFormPanel());
                                 owner.setTitle("Welcome: " + Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().keySet().toString() + " " + Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().values().toString());
                             }
                    }
                    catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog(null,ex.getMessage(),"Empty Field",JOptionPane.WARNING_MESSAGE);
                    }
                }
                else
                    JOptionPane.showMessageDialog(null,"Some field is empty!","Empty Field",JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public JPanel getLoginPanel() {
        return LoginPanel;
    }

    private JPanel LoginPanel;
    private JLabel lblUsername;
    private JPanel UsernamePanel;
    private JTextField txtUsername;
    private JPanel PasswordPanel;
    private JLabel lblPassword;
    private JPasswordField pwdPassword;
    private JButton btnLogin;
    private JPanel btnLoginPanel;
    private JFrame owner;
}
