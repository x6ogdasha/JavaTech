package Entity.Accounts.Builders;

import Entity.Accounts.IAccount;

public interface IAccountBuilder {
    public IAccountBuilder setAccountId(Integer ownerId);
    public IAccountBuilder setCreditLimit(Integer creditLimit);
    public IAccountBuilder setDaysToUnlock(Integer days);
    public IAccountBuilder setPercent(Double percent);
    public IAccountBuilder setCommission(Double commission);
    public IAccountBuilder setLowMiddleHighPercents(Double lowPercent, Double middlePercent, Double highPercent);
    public IAccount build();
}
