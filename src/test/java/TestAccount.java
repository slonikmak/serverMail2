import accountService.AccountService;
import db.DBException;

/**
 * Created by Anton on 25.09.2016.
 */
public class TestAccount {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        try {
            accountService.add("antonll", "jjj");
            System.out.println(accountService.isRegistred("antonll"));
        } catch (DBException e) {
            e.printStackTrace();
        }

    }
}
