package Entity.Accounts;

import Tools.DebitWithdrawException;
import Tools.InvalidValueException;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DebitAccount implements IAccount {

    private final Integer months = 12;
    @Getter Double balance = 0.0;
    @NonNull private Integer ownerId;
    @NonNull private Double percent;
    private Double monthPayment;

    @Override
    public void withdrawMoney(Double money) throws InvalidValueException, DebitWithdrawException {
        if (money < 0) throw new InvalidValueException();
        if (money > balance) throw new DebitWithdrawException();
        balance -= money;
    }

    @Override
    public void addMoney(Double money) throws InvalidValueException {
        if (money < 0) throw new InvalidValueException();
        balance += money;
    }

    @Override
    public void serviceAccount() throws InvalidValueException, DebitWithdrawException {
        withdrawMoney(monthPayment);
        monthPayment = 0.0;
    }

    @Override
    public void calculateCommission() {
        monthPayment = (balance * percent) / months;
    }
}
