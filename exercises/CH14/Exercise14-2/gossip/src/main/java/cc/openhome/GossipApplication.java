package cc.openhome;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import cc.openhome.web.AccessFilter;


@PropertySource("classpath:mail.properties")
@PropertySource("classpath:path.properties")
@SpringBootApplication(scanBasePackages= {
        "cc.openhome.controller",
        "cc.openhome.model",
        "cc.openhome.web"
        
    })
public class GossipApplication {

	public static void main(String[] args) {
		SpringApplication.run(GossipApplication.class, args);
	}
	
    @Bean
    public static PropertySourcesPlaceholderConfigurer 
                       propertySourcesPlaceholderConfigurer() {
       return new PropertySourcesPlaceholderConfigurer();
    }	
    
    @Bean
    public PolicyFactory policyFactory() {
        return new HtmlPolicyBuilder()
                .allowElements("a", "b", "i", "del", "pre", "code")
                .allowUrlProtocols("http", "https")
                .allowAttributes("href").onElements("a")
                .requireRelNofollowOnLinks()
                .toFactory();
    }    
    
    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AccessFilter());
        registration.addUrlPatterns("/member", "/new_message", "/del_message", "/logout");
        registration.addInitParameter("LOGIN_PATH", "/");
        return registration;
    }

}
