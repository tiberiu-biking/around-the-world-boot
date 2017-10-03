package master.pam.world.servlet.impl.user;

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
public class SignInServlet extends AbstractServerRequestServlet {

    public SignInServlet(Server serverIf) {
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
    protected ServerAction getServerAction() {
        return ServerAction.SIGN_IN;
    }

    @Override
    protected void buildServerRequest(ServerRequest aServerRequest) {
        aServerRequest.addField(Constants.USERNAME, getHttpParam(Constants.USERNAME));
        aServerRequest.addField(Constants.PASSWORD, getHttpParam(Constants.PASSWORD));
    }

}
