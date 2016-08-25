package master.pam.world.servlet.notifications;

import com.tpo.world.api.geo.notifications.NotificationPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

@Controller
public class NotificationSenderServlet {

    private String senderName;

    protected final Logger logger = LoggerFactory.getLogger(NotificationSenderServlet.class);

    @RequestMapping(value = "/NotificationSenderServlet", method = RequestMethod.GET)
    protected void doGet(HttpServletRequest aReq, HttpServletResponse aResp) throws ServletException, IOException {
        this.doPost(aReq, aResp);
    }

    @RequestMapping(value = "/NotificationSenderServlet", method = RequestMethod.POST)
    protected void doPost(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {

        senderName = Calendar.getInstance().getTime().toString();

        logger.debug("Starting notification system for " + senderName);

        NotificationPool.getPool().setPoolOpen(true);

        logger.debug("Notification pool opened!");

        aResponse.setContentType("text/event-stream");

        aResponse.setCharacterEncoding("UTF-8");

        PrintWriter writer = aResponse.getWriter();

        NotificationPool notificationPool = NotificationPool.getPool();

        while (notificationPool.isPoolOpen()) {
            String notification = notificationPool.getNotification();
            if (notification != null) {
                logger.debug(senderName + " sending push notification: " + notification);

                writer.write("data: " + notification + "\n\n");

                writer.flush();
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }

        writer.close();

        logger.debug("Notification pool closed!");

    }

}
