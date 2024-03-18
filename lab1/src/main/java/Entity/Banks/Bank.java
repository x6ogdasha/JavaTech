package Entity.Banks;

import Commands.ICommand;
import Entity.Accounts.AccountType;
import Entity.Accounts.Builders.AccountDirector;
import Entity.Accounts.Builders.CreditAccountBuilder;
import Entity.Accounts.IAccount;
import Entity.Users.User;
import Tools.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Банк (хранение пользователей и счетов, также бановкские характеристики (проценты, комиссии, кредитные лимиты и тд))
 */
@AllArgsConstructor
public class Bank {

    @Getter Map<User, ArrayList<Integer>> users;
    @Getter Map<Integer, IAccount> accounts;

    @Getter String name;
    @Getter Integer pin;
    private Double percent;
    private Double lowPercent;
    private Double middlePercent;
    private Double highPercent;
    private Double commission;
    private Integer creditLimit;
    private Integer daysToUnlockDeposit;

    /**
     * Обновление информации об аккаунте
     * @param user
     * @param accountId
     * @param account
     */
    private void updateAccountInformation(User user, Integer accountId, IAccount account) {
        if (users.containsKey(user)) {

            ArrayList<Integer> userAccountIds = users.get(user);
            userAccountIds.add(accountId);

            users.put(user, userAccountIds);

        } else {

            ArrayList<Integer> newAccountIds = new ArrayList<>();
            newAccountIds.add(accountId);

            users.put(user, newAccountIds);
        }

    }

    /**
     * @param user
     * @param accountId
     * @return Новый объект дебетового счета
     * @throws InvalidValueException
     */
    public IAccount createDebitAccount(User user, Integer accountId) throws InvalidValueException {

        IAccount debitAccount = AccountDirector.create(AccountType.Credit)
                .setAccountId(accountId)
                .setPercent(percent)
                .build();

        accounts.put(accountId, debitAccount);

        updateAccountInformation(user, accountId, debitAccount);

        return debitAccount;

    }

    public IAccount createCreditAccount(User user, Integer accountId) throws InvalidValueException {
        IAccount creditAccount = AccountDirector.create(AccountType.Credit)
                .setAccountId(accountId)
                .setCommission(commission)
                .setCreditLimit(creditLimit)
                .build();

        accounts.put(accountId, creditAccount);

        updateAccountInformation(user, accountId, creditAccount);

        return creditAccount;
    }

    public IAccount createDepositAccount(User user, Integer accountId) throws InvalidValueException {
        IAccount depositAccount = AccountDirector.create(AccountType.Deposit)
                .setAccountId(accountId)
                .setDaysToUnlock(daysToUnlockDeposit)
                .setLowMiddleHighPercents(lowPercent, middlePercent, highPercent)
                .build();

        accounts.put(accountId, depositAccount);

        updateAccountInformation(user, accountId, depositAccount);

        return depositAccount;
    }

    public void addNewUser(User user) throws InvalidValueException {

        if (users.containsKey(user)) throw new InvalidValueException();
        ArrayList<Integer> newUserAccounts = new ArrayList<>();
        users.put(user, newUserAccounts);
    }

    public void handleCommand(ICommand command, User user) throws UnverifiedUserException, InvalidValueException, DebitWithdrawException, CommandRollbackException {

        if (!user.isVerified()) throw new UnverifiedUserException();

        try {

            command.execute(accounts, user);

        } catch (CommandExecutingException e) {

            command.rollback();

        }
    }

}
