package cc.openhome.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class ChooseTag extends TagSupport {
    private boolean matched;

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }
    
    @Override
    public int doStartTag() throws JspException {
        matched = false;
        return EVAL_BODY_INCLUDE;
    }

}  