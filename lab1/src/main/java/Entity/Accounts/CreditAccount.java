package Entity.Accounts;

import Tools.CreditBalanceException;
import Tools.CreditLimitException;
import Tools.InvalidValueException;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Кредитный аккаунт (имеет кредитный лимит, айди хозяина и комиссию)
 */
@RequiredArgsConstructor
public class CreditAccount implements IAccount{

    private Double balance = 0.0;
    @NonNull Integer creditLimit;
    @NonNull private Integer ownerId;
    @Getter @NonNull Double commission;


    /**
     * Снять деньги со счета
     * @param money
     * @throws InvalidValueException
     */
    @Override
    public void withdrawMoney(Double money) throws InvalidValueException {
        if (money < 0) throw new InvalidValueException();
        balance -= money;
    }

    /**
     * Пополнение счета
     * @param money
     * @throws InvalidValueException
     */
    @Override
    public void addMoney(Double money) throws InvalidValueException {
        if (money < 0) throw new InvalidValueException();
        balance += money;
    }

    /**
     * Обслуживание аккаунта
     * @throws InvalidValueException
     */
    @Override
    public void serviceAccount() throws InvalidValueException {
        addMoney(commission);
        commission = 0.0;
    }

    /**
     * Рассчет коммисии
     * @throws CreditLimitException
     * @throws CreditBalanceException
     */
    @Override
    public void calculateCommission() throws CreditLimitException, CreditBalanceException {
        if (balance < 0) throw new CreditBalanceException();
        if (balance < creditLimit) throw new CreditLimitException();
    }
}
