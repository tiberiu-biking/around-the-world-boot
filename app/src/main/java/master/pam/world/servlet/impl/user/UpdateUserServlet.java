package master.pam.world.servlet.impl.user;

import com.tpo.world.domain.entity.UserEntity;
import com.tpo.world.domain.util.GsonHelper;
import master.pam.server.api.ServerActionsEnum;
import master.pam.server.api.request.IServerRequest;
import master.pam.server.api.request.RequestConstants;
import master.pam.server.api.server.IServer;
import master.pam.world.servlet.base.AbstractServerRequestServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UpdateUserServlet extends AbstractServerRequestServlet {

    public UpdateUserServlet(IServer serverIf) {
        super(serverIf);
    }

    @RequestMapping(value = "/UpdateUserServlet", method = RequestMethod.GET)
    public void doGet(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doGet(aRequest, aResponse);
    }

    @RequestMapping(value = "/UpdateUserServlet", method = RequestMethod.POST)
    public void doPost(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doPost(aRequest, aResponse);
    }

    @Override
    protected ServerActionsEnum getServerAction() {
        return ServerActionsEnum.UPDATE_USER;
    }

    @Override
    protected void buildServerRequest(IServerRequest aServerRequest) {
        UserEntity userDto = GsonHelper.fromGson(getHttpParam(RequestConstants.USER), UserEntity.class);
        aServerRequest.addField(RequestConstants.DTO, userDto);
        aServerRequest.addField(RequestConstants.PASSWORD, getHttpParam(RequestConstants.PASSWORD));
    }

}
