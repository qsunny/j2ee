package com.aaron.mybatisgenerator.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URL;

public class TemplateUtil
{

    protected static Configuration getConfiguration()
            throws Exception
    {
        Configuration cfg = new Configuration();
        cfg.setDefaultEncoding("UTF-8");
        File file = new File("template");
//        if (!file.exists()) {
//            file = new File(ClassLoader.getSystemResource("template").toURI());
//        }
//        cfg.setDirectoryForTemplateLoading(file);
        cfg.setClassForTemplateLoading(TemplateUtil.class, "/template");

        cfg.setObjectWrapper(new DefaultObjectWrapper());
        return cfg;
    }

    public static String getText(String templateFile, Object params) throws Exception {
        System.out.println("=========templateFile=="+templateFile);
        Template template = getConfiguration().getTemplate(templateFile,"UTF-8");

        Writer out = new StringWriter();
        template.process(params, out);

        return out.toString();
    }
}
