package master.pam.world.servlet.impl.user;

import com.tpo.world.services.notifications.NotificationPool;
import com.tpo.world.web.constants.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = -3798279328944200873L;

    protected final Logger logger = LoggerFactory.getLogger(LogoutServlet.class);

    @Override
    protected void doPost(HttpServletRequest aReq, HttpServletResponse aResp) throws ServletException, IOException {
        logger.debug("Logout of user " + aReq.getParameter(Constants.USER_ID));

        NotificationPool.getPool().setPoolOpen(false);
    }

}
