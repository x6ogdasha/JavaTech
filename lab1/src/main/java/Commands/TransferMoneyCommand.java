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
public class TransferMoneyCommand implements ICommand {

    @NonNull private Integer currentAccountId;
    @NonNull private IAccount currentAccount;
    @NonNull private IAccount recipientAccount;
    @NonNull private Double money;
    private boolean canRollback = false;
    @Override
    public void execute(Map<Integer, IAccount> accounts, User user) throws CommandExecutingException, InvalidValueException, DebitWithdrawException {

        if (!accounts.containsValue(currentAccountId) || canRollback) throw new CommandExecutingException();
        currentAccount.withdrawMoney(money);
        recipientAccount.addMoney(money);
        canRollback = true;

    }

    @Override
    public void rollback() throws CommandRollbackException, InvalidValueException, DebitWithdrawException {

        if (!canRollback) throw new CommandRollbackException();
        recipientAccount.withdrawMoney(money);
        currentAccount.addMoney(money);
        canRollback = false;

    }
}
