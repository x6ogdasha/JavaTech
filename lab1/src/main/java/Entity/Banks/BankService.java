package Entity.Banks;

import Entity.Users.User;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Map;

/**
 *  Сервис банковских методов для авторизации (хранение банков и пользователей)
 */
@RequiredArgsConstructor
public class BankService {

    @NonNull private final ArrayList<Bank> banks;
    @NonNull Map<User, ArrayList<Integer>> users;

    private final Integer emperorPassword = 1902;
    Boolean isEmperorLogged = false;

    /**
     * Проверка входа императора
     * @param password
     */
    public void checkForPermission(Integer password) {

        if (password == emperorPassword) {
            isEmperorLogged = true;
        }

    }

    public Boolean checkBankLogin(String bankName, Integer password) {
        return banks.stream().anyMatch(x -> x.getName() == bankName && x.getPin() == password);
    }

}
