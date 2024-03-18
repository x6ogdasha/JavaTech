package Entity.Accounts;

import Tools.CreditBalanceException;
import Tools.CreditLimitException;
import Tools.DebitWithdrawException;
import Tools.InvalidValueException;

public interface IAccount {
    public void withdrawMoney(Double money) throws InvalidValueException, DebitWithdrawException;
    public void addMoney(Double money) throws InvalidValueException;
    public void serviceAccount() throws InvalidValueException, DebitWithdrawException;
    public void calculateCommission() throws CreditLimitException, CreditBalanceException;
}
