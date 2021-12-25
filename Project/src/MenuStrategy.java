import java.util.HashMap;

public interface MenuStrategy {
    public UserAccountType getAccountType();
    public HashMap<String,String> getAccountHolderInformation();
    public String[] getAccountMenuOptions();
    public void nextMenuOption();
}
