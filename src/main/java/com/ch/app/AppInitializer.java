package com.ch.app;

import com.ch.rest.AccountService;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class AppInitializer extends Application {
    private Set<Object> singletons = new HashSet<Object>();

    public AppInitializer() {
        singletons.add(new AccountService());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
