package cc.openhome;

import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

@WebListener
public class Avatar2Initializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce)  { 
        ServletContext context = sce.getServletContext();
        String AVATAR = context.getInitParameter("AVATAR");
        ServletRegistration.Dynamic servlet = 
                context.addServlet(
                        "Avatar2", 
                        new Avatar2(Paths.get(AVATAR))
                );
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/avatar2");
    }	
}
