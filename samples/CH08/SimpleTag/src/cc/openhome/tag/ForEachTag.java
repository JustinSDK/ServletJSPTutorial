package cc.openhome.tag;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ForEachTag extends SimpleTagSupport {
    private String var;
    private Collection<Object> items;

    public void setVar(String var) {
        this.var = var;
    }

    public void setItems(Collection<Object> items) {
        this.items = items;
    }
    
    @Override 
    public void doTag() throws JspException {
        items.forEach(o -> {
            this.getJspContext().setAttribute(var, o);

            try {
                this.getJspBody().invoke(null);
            } catch (JspException | IOException e) {
                throw new RuntimeException(e);
            }

            this.getJspContext().removeAttribute(var);
        });
    }
}