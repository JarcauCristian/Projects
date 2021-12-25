import java.util.HashMap;

public class StudentStrategy implements MenuStrategy{

    public Student student;

    public StudentStrategy() { }

    public StudentStrategy(Student s)
    {
        this.student = s;
    }

    @Override
    public UserAccountType getAccountType() {
        return UserAccountType.STUDENT;
    }

    @Override
    public HashMap<String, String> getAccountHolderInformation() {
        return new HashMap<>() {{
            put(student.nume, student.prenume);
        }};
    }

    @Override
    public String[] getAccountMenuOptions() {
        return new String[0];
    }

    @Override
    public void nextMenuOption() {

    }
}
