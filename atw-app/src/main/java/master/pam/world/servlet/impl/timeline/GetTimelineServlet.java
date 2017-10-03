package master.pam.world.servlet.impl.timeline;

import com.tpo.world.web.api.Server;
import com.tpo.world.web.api.ServerRequest;
import com.tpo.world.web.constants.Constants;
import com.tpo.world.web.domain.ServerAction;
import master.pam.world.servlet.base.AbstractServerRequestServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class GetTimelineServlet extends AbstractServerRequestServlet {

    public GetTimelineServlet(Server serverIf) {
        super(serverIf);
    }

    @RequestMapping(value = "/GetTimelineServlet", method = RequestMethod.GET)
    public void doGet(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doGet(aRequest, aResponse);
    }

    @RequestMapping(value = "/GetTimelineServlet", method = RequestMethod.POST)
    public void doPost(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doPost(aRequest, aResponse);
    }

    @Override
    protected ServerAction getServerAction() {
        return ServerAction.GET_TIMELINE;
    }

    @Override
    protected void buildServerRequest(ServerRequest aServerRequest) {
        String userId = getHttpParam(Constants.USER_ID);
        aServerRequest.addLong(Constants.USER_ID, Long.parseLong(userId));
    }

}
