package com.aaron.water.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

/**
 * jsp自定义标签使用示例
 * Created by aaron.qiu on 2016/11/12.
 */
public class SendMessageTag extends SimpleTagSupport {

    private String	from;
    private String	to;

    public void setFrom( final String from ) {
        this.from = from;
    }

    public void setTo( final String to ) {
        this.to = to;
    }

    @Override
    public void doTag() throws JspException, IOException {
        //获取标签体的内容
        final StringWriter sw = new StringWriter();
        getJspBody().invoke( sw );

        getJspContext().getOut().println( "There is a message from '" +
                from + "' to '" + to + "'. Message content is '" +
                sw.toString() + "'" );
    }
}
