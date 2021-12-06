package ru.job4j.ex;

public class UserStore {
    public static User findUser(User[] users, String login) throws UserNotFoundException {
        for (User user : users) {
            if (user.getUsername().equals(login)) {
                return user;
            } else {
                throw new UserNotFoundException("User is not found");
            }
        }
        return null;
    }

    public static boolean validate(User user) throws UserInvalidException {
        if (user.isValid() && user.getUsername().length() <= 3) {
            throw new UserInvalidException("User is not valid");
        }
        return user.isValid();
    }

    public static void main(String[] args) {
        User[] users = {
                new User("Pet", true)
        };
        try {
            User user = findUser(users, "Pet");
            if (validate(user)) {
                System.out.println("This user has an access");
            }
            } catch (UserInvalidException ui) {
                ui.printStackTrace();
            } catch (UserNotFoundException un) {
                un.printStackTrace();
            }
        }
    }