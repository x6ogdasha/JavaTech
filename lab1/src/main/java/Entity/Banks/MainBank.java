package Entity.Banks;

import Entity.Users.User;
import Tools.CreditBalanceException;
import Tools.CreditLimitException;
import Tools.DebitWithdrawException;
import Tools.InvalidValueException;

import java.util.ArrayList;

/**
 * Центральный банк (хранение всех банков и всех пользователей)
 */
public class MainBank {

    private ArrayList<Bank> banks = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    /**
     * Ускорение времени для рассчета коммиссий
     * @param days
     */
    public void speedTime(int days) {

        for(int i = 0; i < days; i++) {
            banks.stream().forEach( bank -> {
                bank.accounts.values().forEach(account -> {
                    try {
                        account.calculateCommission();
                    } catch (CreditLimitException | CreditBalanceException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        account.serviceAccount();
                    } catch (InvalidValueException | DebitWithdrawException e) {
                        throw new RuntimeException(e);
                    }
                });
            });
        }
    }

    public Bank addNewBank(Bank bank) {

        banks.add(bank);
        return bank;

    }

    public User addNewUser(User user) {

        users.add(user);
        return user;

    }
}
