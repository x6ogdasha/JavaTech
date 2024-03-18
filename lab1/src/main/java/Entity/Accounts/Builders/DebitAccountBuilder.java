package Entity.Accounts.Builders;

import Entity.Accounts.DebitAccount;
import Entity.Accounts.IAccount;

public class DebitAccountBuilder implements IAccountBuilder{
    private double myPercent;
    private int myAccountId;
    public IAccount build(){
        return new DebitAccount(myAccountId, myPercent);
    }
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
        myPercent = percent;
        return this;
    }

    @Override
    public IAccountBuilder setCommission(Double commission) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IAccountBuilder setLowMiddleHighPercents(Double lowPercent, Double middlePercent, Double highPercent) {
        throw new UnsupportedOperationException();
    }
}
