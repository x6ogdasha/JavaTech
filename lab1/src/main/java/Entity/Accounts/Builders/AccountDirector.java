package Entity.Accounts.Builders;

import Entity.Accounts.AccountType;
import Tools.InvalidValueException;

public class AccountDirector {
    public static IAccountBuilder create(AccountType type) throws InvalidValueException {
        switch (type) {
            case Debit -> { return new DebitAccountBuilder(); }
            case Credit -> { return new CreditAccountBuilder(); }
            case Deposit -> { return new DepositAccountBuilder(); }
            default -> throw new InvalidValueException();
        }
    }
}
