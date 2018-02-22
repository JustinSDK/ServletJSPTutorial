package cc.openhome.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class ToUpperCaseTag extends BodyTagSupport {
    @Override
    public int doEndTag() throws JspException {
        String upper = this.getBodyContent().getString().toUpperCase();
        try {
            pageContext.getOut().write(upper);
        } catch (IOException ex) {
            throw new JspException(ex);
        }
        return EVAL_PAGE;
    }
} 