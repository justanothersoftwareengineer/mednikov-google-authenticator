package net.mednikov.vertxexamples.GoogleAuthenticatorExample.services;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.common.template.TemplateEngine;
import io.vertx.ext.web.handler.BodyHandler;
import net.mednikov.vertxexamples.GoogleAuthenticatorExample.repositories.IUsersRepository;
import net.mednikov.vertxexamples.GoogleAuthenticatorExample.entities.User;

import java.util.Optional;


public class AuthService extends AbstractVerticle {

    @Inject private TemplateEngine templateEngine;
    @Inject private IUsersRepository usersRepository;
    @Inject private GoogleAuthenticator authenticator;

    public AuthService(AbstractModule module){
        Guice.createInjector(module).injectMembers(this);
    }

    @Override
    public void start() throws Exception {
        super.start();
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.get("/login").handler(this::showLogin);
        router.get("/signup").handler(this::showSignup);
        router.route("/api/*").handler(BodyHandler.create());
        router.post("/api/login").handler(this::doLogin);
        router.post("/api/signup").handler(this::doSignup);
        server.requestHandler(router).listen(4567, res->{
            if (res.succeeded()){
                System.out.println("Server listens to port 4567");
            } else {
                System.out.println("Something went wrong");
                System.out.println(res.cause().getLocalizedMessage());
            }
        });
    }

    private void showSignup(RoutingContext ctx){
        templateEngine.render(ctx.data(), "templates/signup.ftl", res->{
            if (res.succeeded()){
                ctx.response().end(res.result());
            }
        });
    }

    private void showLogin(RoutingContext ctx){
        templateEngine.render(ctx.data(), "templates/login.ftl", res->{
            if (res.succeeded()){
                ctx.response().end(res.result());
            }
        });
    }

    private void doLogin(RoutingContext ctx){
        JsonObject req = ctx.getBodyAsJson();
        String username = req.getString("username");
        Optional<User> query = usersRepository.findByUsername(username);
        if (query.isPresent()){
            User user = query.get();
            String password = req.getString("password");
            boolean doPasswordsMatch = password.equalsIgnoreCase(user.getPassword());
            if (doPasswordsMatch){
                String key = user.getKey();
                String codeString = req.getString("code");
                int code = Integer.parseInt(codeString);
                boolean isAuthorized = authenticator.authorize(key, code);
                if (isAuthorized){
                    ctx.response().setStatusCode(200).end();
                } else {
                    ctx.response().setStatusCode(403).end();
                }
            } else {
                ctx.response().setStatusCode(403).end();
            }
        } else {
            ctx.response().setStatusCode(404).end();
        }
    }

    private void doSignup(RoutingContext ctx){
        JsonObject req = ctx.getBodyAsJson();
        String username = req.getString("username");
        String password = req.getString("password");
        System.out.println(req);

        GoogleAuthenticatorKey authKey = authenticator.createCredentials();
        String key = authKey.getKey();

        User user  = usersRepository.add(username, password, key);

        JsonObject res = new JsonObject().put("key", key);
        ctx.response().setStatusCode(200).end(res.encode());
    }
}
