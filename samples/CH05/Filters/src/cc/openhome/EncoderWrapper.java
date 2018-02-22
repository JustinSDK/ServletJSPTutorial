package cc.openhome;

import java.util.*;
import javax.servlet.http.*;

import org.owasp.encoder.Encode;

public class EncoderWrapper extends HttpServletRequestWrapper {
    public EncoderWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        return Optional.ofNullable(getRequest().getParameter(name))
                       .map(Encode::forHtml)
                       .orElse(null);
    }

}