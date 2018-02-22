package cc.openhome.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.JspTag;

public class WhenTag extends BodyTagSupport {
    private boolean test;
    
    public void setTest(boolean test) {
        this.test = test;
    }
    
    @Override
    public int doStartTag() throws JspException {
        JspTag parent = null;
        if (!((parent = getParent()) instanceof ChooseTag)) {
            throw new JspTagException("必須置於choose標籤中");
        }

        if (((ChooseTag) parent).isMatched() || !test) {
            return SKIP_BODY;
        }

        ((ChooseTag) parent).setMatched(true);
        return EVAL_BODY_INCLUDE;
    }
} 