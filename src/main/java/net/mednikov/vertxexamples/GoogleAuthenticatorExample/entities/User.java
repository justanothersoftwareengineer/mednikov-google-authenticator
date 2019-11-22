package net.mednikov.vertxexamples.GoogleAuthenticatorExample.entities;

public class User {

    private String username;
    private String password;
    private String key;

    public User(String username, String password, String key){
        setKey(key);
        setUsername(username);
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
