package net.mednikov.vertxexamples.GoogleAuthenticatorExample.modules;

import com.google.inject.AbstractModule;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import io.vertx.core.Vertx;
import io.vertx.ext.web.common.template.TemplateEngine;
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine;
import net.mednikov.vertxexamples.GoogleAuthenticatorExample.repositories.IUsersRepository;
import net.mednikov.vertxexamples.GoogleAuthenticatorExample.repositories.UsersRepositoryImpl;

public class AppModule extends AbstractModule {

    private Vertx vertx;

    public AppModule(Vertx vertx){
        this.vertx = vertx;
    }

    @Override
    protected void configure() {
        super.configure();
        bind(IUsersRepository.class).toInstance(new UsersRepositoryImpl());
        bind(GoogleAuthenticator.class).toInstance(new GoogleAuthenticator());
        bind(TemplateEngine.class).toInstance(FreeMarkerTemplateEngine.create(vertx));
    }
}
