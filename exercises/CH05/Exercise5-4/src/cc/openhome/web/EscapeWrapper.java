package cc.openhome.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.owasp.encoder.Encode;

public class EscapeWrapper extends HttpServletRequestWrapper {
    private Map<String, String> tags;

    public EscapeWrapper(HttpServletRequest request, Map<String, String> tags) {
        super(request);
        this.tags = tags;
    }
    
    @Override
    public String getParameter(String name) {
        return Optional.ofNullable(getRequest().getParameter(name))
                .map(Encode::forHtml)
                .map(this::escapeBBCode)
                .orElse(null);
    }
    
    
    private String escapeBBCode(String value) {
        return tags.keySet()
                 .stream()
                 .reduce(value, 
                     (result, origin) -> result.replaceAll(origin, tags.get(origin))
                 );
    }
    
    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> parameterMap = new HashMap<>(getRequest().getParameterMap());
        parameterMap.replaceAll((param, values) -> escapeHtmlBBCode(values));
        return parameterMap;
    }

    @Override
    public String[] getParameterValues(String name) {
        return Optional.ofNullable(getRequest().getParameterValues(name))
                       .map(this::escapeHtmlBBCode)
                       .orElse(null);
    }
    
    private String[] escapeHtmlBBCode(String[] values) {
        return Arrays.asList(values)
                     .stream()
                     .map(Encode::forHtml)
                     .map(this::escapeBBCode)
                     .collect(Collectors.toList())
                     .toArray(new String[values.length]);
    }
}
