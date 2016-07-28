package master.pam.world.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppServletContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent aServletContextEvent) {
    }

    @Override
    public void contextInitialized(ServletContextEvent aServletContextEvent) {
    }
}