package Entity.Users;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Пользователь (имя, адресс, пасспорт и пин)
 */
@RequiredArgsConstructor

public class User {

    @NonNull private String name;
    @NonNull private String address;
    @NonNull private Integer passport;
    @NonNull private Integer pin;
    private boolean verified;

    public Boolean isVerified() { return address != null && passport != null; }
}
