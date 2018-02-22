package cc.openhome.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class WhenTag extends SimpleTagSupport {
    private boolean test;
    
    public void setTest(boolean test) {
        this.test = test;
    }    

    @Override
    public void doTag() throws JspException {
        JspTag parent = null;
        if (!((parent = getParent()) instanceof ChooseTag)) {
            throw new JspTagException("必須置於 choose 標籤中");
        }

        if (((ChooseTag) parent).isMatched()) {
            return;
        }

        if (test) {
            ((ChooseTag) parent).setMatched(true);
            try {
                this.getJspBody().invoke(null);
            } catch (java.io.IOException ex) {
                throw new JspException("WhenTag 執行錯誤", ex);
            }
        }
    }
}