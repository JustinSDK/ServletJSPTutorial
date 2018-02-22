package cc.openhome.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cc.openhome.model.AccountDAO;
import cc.openhome.model.AccountDAOFileImpl;
import cc.openhome.model.MessageDAO;
import cc.openhome.model.MessageDAOFileImpl;
import cc.openhome.model.UserService;

@WebListener
public class GossipInitializer implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String USERS = sce.getServletContext().getInitParameter("USERS");
        AccountDAO acctDAO = new AccountDAOFileImpl(USERS);
        MessageDAO messageDAO = new MessageDAOFileImpl(USERS);
        context.setAttribute("userService", new UserService(acctDAO, messageDAO));
    }
}
