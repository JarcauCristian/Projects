import javax.swing.*;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.util.*;
import java.io.*;

public class Application {
    private static Application singleInstance = null;
    private List<User> userList = new ArrayList<>();
    public User currentUser = null;
    public IDataLoader dataLoader = null;
    public IDisplayManager displayManager = null;
    public ManagerCursuri manager = new ManagerCursuri();

    private Application()
    {
        this.initUsers();
    }

    public static Application getInstance()
    {
        if (singleInstance == null)
        {
            singleInstance = new Application();
        }
        return singleInstance;
    }

    public void SaveNewUsers(String username, String password, MenuStrategy mn)
    {
        this.initUsers();
        userList.add(new User(username,password,mn));
        try(FileOutputStream fos = new FileOutputStream("users.xml")) {
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(userList);
            encoder.close();
            fos.close();
        }
        catch (FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    private void initUsers() {
        try (FileInputStream fis = new FileInputStream("users.xml")) {
            XMLDecoder decoder = new XMLDecoder(fis);
            this.userList = (ArrayList<User>)decoder.readObject();
            decoder.close();
            fis.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    public void login(User user) throws Exception {
        int index = userList.indexOf(user);
        if ( index != -1 ) {
            Application.getInstance().currentUser = userList.get(index);
            Application.getInstance().dataLoader = Settings.dataloader.get(Settings.loadType);
            Application.getInstance().displayManager = Settings.displayloader.get(Settings.displayType);
            Application.getInstance().manager.setCurs(dataLoader.createCoursesData());
        } else {
            throw new Exception("Username sau parola este gresita!");
        }
    }
}
