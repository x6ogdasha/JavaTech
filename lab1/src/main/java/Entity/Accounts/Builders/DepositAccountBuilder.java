package Entity.Accounts.Builders;

import Entity.Accounts.DepositAccount;
import Entity.Accounts.IAccount;

public class DepositAccountBuilder implements IAccountBuilder{
    private int myAccountId;
    private double myLowPercent;
    private double myMiddlePercent;
    private double myHighPercent;
    @Override
    public IAccountBuilder setAccountId(Integer accountId) {
        myAccountId = accountId;
        return this;
    }

    @Override
    public IAccountBuilder setCreditLimit(Integer creditLimit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IAccountBuilder setDaysToUnlock(Integer days) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IAccountBuilder setPercent(Double percent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IAccountBuilder setCommission(Double commission) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IAccountBuilder setLowMiddleHighPercents(Double lowPercent, Double middlePercent, Double highPercent) {
        myLowPercent = lowPercent;
        myMiddlePercent = middlePercent;
        myHighPercent = highPercent;
        return this;
    }

    @Override
    public IAccount build() {
        return new DepositAccount(myAccountId, myLowPercent, myMiddlePercent, myHighPercent);
    }
}
