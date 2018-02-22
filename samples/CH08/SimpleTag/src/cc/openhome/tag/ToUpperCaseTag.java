package cc.openhome.tag;

import java.io.IOException;
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ToUpperCaseTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException {
        StringWriter writer = new StringWriter();
        writeTo(writer);
        String upper = writer.toString().toUpperCase();
        print(upper);
    }

    private void writeTo(StringWriter writer) throws JspException {
        try {
            this.getJspBody().invoke(writer);
        } catch (IOException e) {
            throw new JspException("ToUpperCaseTag 執行錯誤", e);
        }
    }
    
    private void print(String upper) throws JspException {
        try {
            this.getJspContext().getOut().print(upper);
        } catch (IOException e) {
            throw new JspException("ToUpperCaseTag 執行錯誤", e);
        }
    }
}
