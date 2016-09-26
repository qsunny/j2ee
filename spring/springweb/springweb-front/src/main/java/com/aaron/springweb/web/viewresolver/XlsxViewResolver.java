package com.aaron.springweb.web.viewresolver;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * Created by Administrator on 2016/9/26.
 */
public class XlsxViewResolver implements ViewResolver {

    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        XlsxView view = new XlsxView();
        return view;
    }
}
