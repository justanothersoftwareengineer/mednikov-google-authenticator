package net.mednikov.vertxexamples.GoogleAuthenticatorExample.repositories;
import net.mednikov.vertxexamples.GoogleAuthenticatorExample.entities.User;

import java.util.Optional;

public interface IUsersRepository {

    User add (String username, String password, String key);

    Optional<User> findByUsername (String username);
}
