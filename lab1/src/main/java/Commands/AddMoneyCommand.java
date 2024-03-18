package Commands;

import Entity.Accounts.IAccount;
import Entity.Users.User;
import Tools.CommandExecutingException;
import Tools.CommandRollbackException;
import Tools.DebitWithdrawException;
import Tools.InvalidValueException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;
@RequiredArgsConstructor
public class AddMoneyCommand implements ICommand {

    @NonNull private Integer currentAccountId;
    @NonNull private IAccount currentAccount;
    @NonNull private Double money;
    private boolean canRollback = false;
    @Override
    public void execute(Map<Integer, IAccount> accounts, User user) throws CommandExecutingException, InvalidValueException {

        if (!accounts.containsValue(currentAccountId) || canRollback) throw new CommandExecutingException();

        currentAccount.addMoney(money);
        canRollback = true;

    }

    @Override
    public void rollback() throws CommandRollbackException, InvalidValueException, DebitWithdrawException {

        if (!canRollback) throw new CommandRollbackException();
        currentAccount.withdrawMoney(money);
        canRollback = false;

    }
}
