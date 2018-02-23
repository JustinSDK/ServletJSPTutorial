package cc.openhome;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;

import cc.openhome.model.UserService;
import cc.openhome.model.Account;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
    @Autowired
    private UserService userService;
         
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/member", "/new_message", "/del_message", "/logout").access("hasRole('ROLE_MEMBER')")  
            .anyRequest().permitAll()
            .and()
                .formLogin().loginPage("/").loginProcessingUrl("/login")
                .successHandler((request, response, auth) -> {
                    request.getSession().setAttribute("login", auth.getName());
                    response.sendRedirect("/member");
                })
               .failureHandler((request, response, auth) -> {
                    request.setAttribute("errors", Arrays.asList("登入失敗"));
                    request.getRequestDispatcher("/").forward(request, response);
               })
            .and()
                .logout().logoutUrl("/logout")
                .addLogoutHandler((request, response, auth) -> {
                    request.getSession().invalidate();
                    try {
                        response.sendRedirect("/");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
            .and()
                .csrf().disable();
    }

    private class CustomAuthenticationProvider implements AuthenticationProvider {
   
      @Override
      public Authentication authenticate(Authentication authentication) 
        throws AuthenticationException {
          String name = authentication.getName();
          String password = authentication.getCredentials().toString();
          
          Optional<Account> optionalAcct = userService.accountByName(name);
          
          if(optionalAcct.isPresent() && optionalAcct.get().isEnabled() & userService.login(name, password)) {
              return new UsernamePasswordAuthenticationToken(
                      name, password, AuthorityUtils.createAuthorityList("ROLE_MEMBER"));
          }
          
          return null;
      }
   
      @Override
      public boolean supports(Class<?> authentication) {
          return authentication.equals(UsernamePasswordAuthenticationToken.class);
      }
  }
    
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new CustomAuthenticationProvider());
    }
}
