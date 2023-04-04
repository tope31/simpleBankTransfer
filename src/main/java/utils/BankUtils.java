package utils;

import model.Users;

public class BankUtils {
    static Users users = new Users();

    public static Integer depositBalance(Integer deposit) {
        Integer balance = users.getBalance();
        balance = balance + deposit;
        return balance;
    }

}
