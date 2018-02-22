package cc.openhome.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class RemoveTag extends TagSupport {
    private String var;
    private String scope = "page";
    
    public void setVar(String var) {
        this.var = var;
    }
    
    public void setScope(String scope) {
        this.scope = scope;
    }
    
    @Override
    public int doStartTag() throws JspException {
        switch(scope) {
        case "page":
            this.pageContext.removeAttribute(var, PageContext.PAGE_SCOPE);
            break;
        case "request":
            this.pageContext.removeAttribute(var, PageContext.REQUEST_SCOPE);
            break;
        case "session":
            this.pageContext.removeAttribute(var, PageContext.SESSION_SCOPE);
            break;
        case "application":
            this.pageContext.removeAttribute(var, PageContext.APPLICATION_SCOPE);
        }
        
        return SKIP_BODY;
    }
}
