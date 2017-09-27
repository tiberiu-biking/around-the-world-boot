package master.pam.world.servlet.impl.user;

import com.tpo.world.web.api.ServerActionsEnum;
import com.tpo.world.web.api.request.IServerRequest;
import com.tpo.world.web.api.request.RequestConstants;
import com.tpo.world.web.api.server.IServer;
import master.pam.world.servlet.base.AbstractServerRequestServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class SignInServlet extends AbstractServerRequestServlet {

    public SignInServlet(IServer serverIf) {
        super(serverIf);
    }

    @RequestMapping(value = "/SignInServlet", method = RequestMethod.GET)
    public void doGet(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doGet(aRequest, aResponse);
    }

    @RequestMapping(value = "/SignInServlet", method = RequestMethod.POST)
    public void doPost(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doPost(aRequest, aResponse);
    }

    @Override
    protected ServerActionsEnum getServerAction() {
        return ServerActionsEnum.SIGN_IN;
    }

    @Override
    protected void buildServerRequest(IServerRequest aServerRequest) {
        aServerRequest.addField(RequestConstants.USERNAME, getHttpParam(RequestConstants.USERNAME));
        aServerRequest.addField(RequestConstants.PASSWORD, getHttpParam(RequestConstants.PASSWORD));
    }

}
