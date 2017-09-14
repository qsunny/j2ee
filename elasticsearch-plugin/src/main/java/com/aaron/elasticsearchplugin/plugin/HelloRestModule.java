package com.aaron.elasticsearchplugin.plugin;

import com.aaron.elasticsearchplugin.action.HelloRestAction;
import org.elasticsearch.common.inject.AbstractModule;

public class HelloRestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(HelloRestAction.class).asEagerSingleton();
    }
}
