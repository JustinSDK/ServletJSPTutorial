package cc.openhome.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class SetTag extends TagSupport {
    private String var;
    private Object value;
    private String scope = "page";
    
    public void setVar(String var) {
        this.var = var;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    
    @Override
    public int doStartTag() throws JspException {
        switch(scope) {
        case "page":
            this.pageContext.setAttribute(var, value, PageContext.PAGE_SCOPE);
            break;
        case "request":
            this.pageContext.setAttribute(var, value, PageContext.REQUEST_SCOPE);
            break;
        case "session":
            this.pageContext.setAttribute(var, value, PageContext.SESSION_SCOPE);
            break;
        case "application":
            this.pageContext.setAttribute(var, value, PageContext.APPLICATION_SCOPE);
        }

        return SKIP_BODY;
    }
}
