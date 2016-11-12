package com.aaron.water.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * jsp自定义标签使用示例
 * Created by aaron.qiu on 2016/11/12.
 */
public class HelloWorldTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        final JspWriter out = getJspContext().getOut();
        out.println( "Hello JSP Custom Tag!" );
    }


}
