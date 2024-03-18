package Commands;

import Entity.Accounts.IAccount;
import Entity.Users.User;
import Tools.CommandExecutingException;
import Tools.CommandRollbackException;
import Tools.DebitWithdrawException;
import Tools.InvalidValueException;

import java.util.Map;

public interface ICommand {
    public void execute(Map<Integer, IAccount> accounts, User user) throws CommandExecutingException, InvalidValueException, DebitWithdrawException;
    public void rollback() throws CommandRollbackException, InvalidValueException, DebitWithdrawException;
}
