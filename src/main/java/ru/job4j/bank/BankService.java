package ru.job4j.bank;

import java.util.*;

/**
 * Класс описывает работу программы банковские переводы
 * @author DMITRIY SHAROVATOV
 * @version 1.0
 */
public class BankService {
    /**
     * Храненение осуществляется в коллекции Map
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод описывает добавления нового пользователя
     * Метод проверяет существует ли пользователь в системе или нет
     * если нет - то добавляет нового пользователя
     * @param user - пользователь, который метод получает на входе
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод описывает добавления нового счета пользователя
     * Метод проверяет существует такой пользователь в системе по номеру паспорту
     * и проверяет сушествует ли у него счет который нужно лобавить,если нет,
     * то добаляет новый счет пользователю
     * @param passport - номер паспорта,который метод получает на входе
     * @param account- счет,который метод получает на входе
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);

        if (user != null) {
            List<Account> userAccounts = users.get(user);
            if (!userAccounts.contains(account)) {
                userAccounts.add(account);
            }
        }
    }

    /**
     * Метод описывает поиск пользователя в системе по номеру паспорта
     * @param passport - номер паспорта,который метод получает на вход
     * @return - возращает пользователя по номеру паспорта, если  он есть в системе
     */
    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Метод описывает поиск счета по реквизитам
     * Метод проверяет существует такой пользователь в системе по номеру паспорту
     * и проверяет принадлежит ему реквизит счета
     * @param passport - номер паспорта,который метод получает на вход
     * @param requisite - реквизит счета, который метод получает на вход
     * @return - возращает счет , принадлежащий пользователю
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);

        if (user != null) {
            for (Account userAccount : users.get(user)) {
                if (userAccount.getRequisite().equals(requisite)) {
                    return userAccount;
                }
            }
        }
        return null;
    }

    /**
     * Метод описывает перечисления средств с одного счета на другой
     * проверяется существуют или нет счета в системе по номерам паспорта пользователя и реквизитам счетов,
     * и счет отправителя должен быть больше или равен сумме перевода
     * потом следует проверка на баланс счетов, оно
     * @param srcPassport - номер паспорта пользователя,со счета которого надо сделать списание
     * @param srcRequisite - реквизиты счета пользователя,с которого надо сделать списание
     * @param destPassport - номер паспорта пользователя,на счета которого надо сделать пополнение
     * @param destRequisite - реквизиты счета пользователя,на который надо сделать пополнение
     * @param amount - сумма перевода средств
     * @return - возращает true, если операция получилась,и false - если нет
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;

        Account srcAcc = findByRequisite(srcPassport, srcRequisite);
        Account destAcc = findByRequisite(destPassport, destRequisite);

        if (srcAcc != null && destAcc != null && srcAcc.getBalance() >= amount) {
            srcAcc.setBalance(destAcc.getBalance() - amount);
            destAcc.setBalance(destAcc.getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}
