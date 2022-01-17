import java.util.HashMap;

public interface MenuStrategy {
    public UserAccountType getAccountType();
    public HashMap<String,String> getAccountHolderInformation();
    public void ShowMenuOption();
}
