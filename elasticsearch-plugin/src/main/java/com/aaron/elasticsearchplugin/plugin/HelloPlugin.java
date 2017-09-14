package com.aaron.elasticsearchplugin.plugin;

import org.elasticsearch.common.inject.Module;
import org.elasticsearch.plugins.Plugin;

import java.util.Collection;
import java.util.Collections;

public class HelloPlugin extends Plugin {
    @Override
    public String name() {
        return "example plugin name";
    }

    @Override
    public String description() {
        return "Example Plugin Description";
    }

    @Override
    public Collection<Module> nodeModules() {
        //加入自定义处理模块
        return Collections.<Module> singletonList(new HelloRestModule());
    }

}
