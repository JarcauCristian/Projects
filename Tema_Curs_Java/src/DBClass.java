import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class DBClass {
    public static Connection conn;
    public static boolean connection()
    {
        try {
            try(Scanner cin = new Scanner(new File("file.conn"))) {
                String line;
                if(cin.hasNextLine())
                {
                    line = cin.nextLine();
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:7369/rental", "root", line);
                }
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }
}
