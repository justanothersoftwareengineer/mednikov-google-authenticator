package net.mednikov.vertxexamples.GoogleAuthenticatorExample.repositories;

import net.mednikov.vertxexamples.GoogleAuthenticatorExample.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements IUsersRepository {

    private List<User> users;

    public UsersRepositoryImpl(){
        users = new ArrayList<>();
    }

    @Override
    public User add(String username, String password, String key) {
        User user = new User(username, password, key);
        users.add(user);
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        for (User user: users){
            if (user.getUsername().equalsIgnoreCase(username)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
}
