import Entity.Accounts.Builders.CreditAccountBuilder;
import Entity.Accounts.Builders.DebitAccountBuilder;
import Entity.Accounts.DebitAccount;
import Entity.Accounts.IAccount;
import Entity.Banks.Bank;
import Entity.Users.User;
import Entity.Users.UserBuilder;
import Tools.DebitWithdrawException;
import Tools.InvalidValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class Lab1Test {

    @Test
    public void addMoneyToAccount() throws InvalidValueException {

        var account = new DebitAccount(1,2.4);
        account.addMoney(100.0);
        Assertions.assertEquals(100.0, account.getBalance());
    }

    @Test
    public void withdrawFromAccount() throws InvalidValueException, DebitWithdrawException {

        var account = new DebitAccount(1,2.4);
        account.addMoney(100.0);
        account.withdrawMoney(40.0);
        Assertions.assertEquals(60.0, account.getBalance());
    }

    @Test
    public void payCommission() throws InvalidValueException, DebitWithdrawException {

        var account = new DebitAccount(1,1.1);
        account.addMoney(100.0);
        account.calculateCommission();
        account.serviceAccount();
        Assertions.assertNotEquals(100.0, account.getBalance());

    }

    @Test
    public void createUser() throws InvalidValueException {
        var builder = new UserBuilder();
        var user = builder.setFirstName("bogdan")
                .setSecondName("kitaev")
                .setPassport(123)
                .setAddress("SaintP")
                .create();

        Assertions.assertTrue(user.isVerified());

    }
}
