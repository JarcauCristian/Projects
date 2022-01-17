import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ConsoleThread implements Runnable {

    IDataLoader dataLoader = Settings.dataloader.get(Settings.loadType);
    ArrayList<Student> students = new ArrayList<Student>(Arrays.asList(dataLoader.createStudentsData()));
    ArrayList<Profesor> teachers = new ArrayList<Profesor>(Arrays.asList(dataLoader.createProfesorData()));

    @Override
    public void run() {
        try (Scanner cin = new Scanner(System.in)) {
            System.out.print("Username: ");
            String username = cin.nextLine();
            System.out.print("Password: ");
            String password = cin.nextLine();
            Application.getInstance().login(new User(username, SHA_256.toHexString(SHA_256.getSHA(password))));
            if(Application.getInstance().currentUser.menuStrategy.getAccountType() == UserAccountType.STUDENT)
            {
                Application.getInstance().currentUser.menuStrategy.ShowMenuOption();
            }
            else
                if(Application.getInstance().currentUser.menuStrategy.getAccountType() == UserAccountType.TEACHER)
                {
                    for (String s : Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().keySet()) {
                        String prenume = Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().get(s);
                        System.out.println("Welcome: " + s + " " + prenume);
                    }
                    Application.getInstance().currentUser.menuStrategy.ShowMenuOption();
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

