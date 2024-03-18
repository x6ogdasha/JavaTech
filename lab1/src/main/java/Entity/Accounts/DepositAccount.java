package Entity.Accounts;

import Tools.InvalidValueException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DepositAccount implements IAccount{

    private final Integer moneyForLowPercent = 50000;
    private final Integer moneyForHighPercent = 100000;
    private final Integer days = 365;
    private Double balance = 0.0;
    private Double monthPayment;
    @NonNull private Integer ownerId;
    @NonNull private Double lowPercent;
    @NonNull private Double middlePercent;
    @NonNull private Double highPercent;
    private int daysToUnlock;


    @Override
    public void withdrawMoney(Double money) throws InvalidValueException {
        if (money < 0) throw new InvalidValueException();
        balance -= money;
    }

    @Override
    public void addMoney(Double money) throws InvalidValueException {
        if (money < 0) throw new InvalidValueException();
        balance += money;
    }

    @Override
    public void serviceAccount() throws InvalidValueException {
        addMoney(monthPayment);
        monthPayment = 0.0;
    }

    @Override
    public void calculateCommission() {
        if (balance < moneyForLowPercent) {
            monthPayment = (balance * lowPercent) / days;
        } else if (balance > moneyForLowPercent && balance < moneyForHighPercent) {
            monthPayment = (balance * middlePercent) / days;
        } else {
            monthPayment = (balance * highPercent) / days;
        }
    }
}
