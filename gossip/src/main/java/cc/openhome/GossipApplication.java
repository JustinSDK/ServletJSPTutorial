package cc.openhome;

import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@PropertySource("classpath:mail.properties")
@PropertySource("classpath:path.properties")
@SpringBootApplication(scanBasePackages= {
        "cc.openhome",
        "cc.openhome.controller",
        "cc.openhome.model"
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
}
