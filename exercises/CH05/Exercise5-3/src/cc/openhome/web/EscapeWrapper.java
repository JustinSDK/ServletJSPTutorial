package cc.openhome.web;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.owasp.encoder.Encode;

public class EscapeWrapper extends HttpServletRequestWrapper {
    private Map<String, String> escapeMap;

    public EscapeWrapper(HttpServletRequest request, Map<String, String> escapeMap) {
        super(request);
        this.escapeMap = escapeMap;
    }
    
    @Override
    public String getParameter(String name) {
        return Optional.ofNullable(getRequest().getParameter(name))
                .map(Encode::forHtml)
                .map(this::doEscape)
                .orElse(null);
    }
    
    private String doEscape(String value) {
        return escapeMap.keySet()
                 .stream()
                 .reduce(value, 
                     (result, origin) -> result.replaceAll(origin, escapeMap.get(origin))
                 );
    }
}
