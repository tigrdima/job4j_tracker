package ru.job4j.bank;

import java.util.*;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);

        if (users.containsKey(user)) {
            for (Map.Entry<User, List<Account>> entry : users.entrySet()) {
                if (entry.getKey().equals(user)) {
                    List<Account> userAccount = entry.getValue();
                    userAccount.add(account);
                    break;
                }
            }
        }
    }

    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        return null;
    }

    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);

        if (user != null) {
            for (Map.Entry<User, List<Account>> entry : users.entrySet()) {
                User key = entry.getKey();
                if (key.equals(user)) {
                    ArrayList<Account> acc = new ArrayList<>(entry.getValue());

                    for (Account userAccount : acc) {
                        if (userAccount.getRequisite().equals(requisite)) {
                            return userAccount;
                        }
                    }
                }
            }
        }
        return null;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        User user1 = findByPassport(srcPassport);
        User user2 = findByPassport(destPassport);

        Account accUser1 = findByRequisite(srcPassport, srcRequisite);
        Account accUser2 = findByRequisite(destPassport, destRequisite);
        double balUser1 = accUser1.getBalance();
        double balUser2 = accUser2.getBalance();

        if (users.containsKey(user1) && users.containsKey(user2) && balUser1 > balUser2) {
            accUser1.setBalance(balUser1 - amount);
            accUser2.setBalance(balUser2 + amount);
            rsl = true;
        }
        return rsl;
    }
}
