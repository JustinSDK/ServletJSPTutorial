package cc.openhome.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class IfTag extends SimpleTagSupport {
    private boolean test;

    public void setTest(boolean test) {
        this.test = test;
    }

    @Override
    public void doTag() throws JspException {
        if (test) {
            try {
                getJspBody().invoke(null);
            } catch (IOException ex) {
                throw new JspException("IfTag 執行錯誤", ex);
            }
        }
    }
}
