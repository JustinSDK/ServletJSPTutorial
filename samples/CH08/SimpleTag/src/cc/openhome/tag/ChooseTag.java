package cc.openhome.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ChooseTag extends SimpleTagSupport {
    private boolean matched;
    
    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }
    
    @Override
    public void doTag() throws JspException {
        try {
            this.getJspBody().invoke(null);
        } catch (java.io.IOException ex) {
            throw new JspException("ChooseTag 執行錯誤", ex);
        }
    }
} 