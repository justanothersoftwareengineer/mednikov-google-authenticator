package net.mednikov.vertxexamples.GoogleAuthenticatorExample;

import com.google.inject.AbstractModule;
import io.vertx.core.Vertx;
import net.mednikov.vertxexamples.GoogleAuthenticatorExample.modules.AppModule;
import net.mednikov.vertxexamples.GoogleAuthenticatorExample.services.AuthService;

public class App {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        AbstractModule module = new AppModule(vertx);
        vertx.deployVerticle(new AuthService(module));
    }
}
