package Entity.Users;

import Tools.InvalidValueException;

public class UserBuilder {
    private String myFirstName;
    private String mySecondName;
    private String myAddress;
    private int myPassport;

    public UserBuilder setFirstName(String firstName) throws InvalidValueException {
        if (firstName.isEmpty()) throw new InvalidValueException();
        myFirstName = firstName;
        return this;
    }

    public UserBuilder setSecondName(String secondName) throws InvalidValueException {
        if (secondName.isEmpty()) throw new InvalidValueException();
        mySecondName = secondName;
        return this;
    }

    public UserBuilder setAddress(String address) throws InvalidValueException {
        if (address.isEmpty()) throw new InvalidValueException();
        myAddress = address;
        return this;
    }

    public UserBuilder setPassport(Integer passport) throws InvalidValueException {
        if (passport < 0) throw new InvalidValueException();
        myPassport = passport;
        return this;
    }
    public User create() {
        return new User(myFirstName, myAddress, myPassport, 0);
    }
}
