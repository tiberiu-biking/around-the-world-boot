package master.pam.world.servlet.impl.marker;

import master.pam.crosscutting.dto.impl.MarkerDto;
import master.pam.crosscutting.gson.GsonHelper;
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
public class UpdateMarkerServlet extends AbstractServerRequestServlet {
    private static final long serialVersionUID = 7735454047270919067L;

    public UpdateMarkerServlet(IServer serverIf) {
        super(serverIf);
    }

    @RequestMapping(value = "/UpdateMarkerServlet", method = RequestMethod.GET)
    public void doGet(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doGet(aRequest, aResponse);
    }

    @RequestMapping(value = "/UpdateMarkerServlet", method = RequestMethod.POST)
    public void doPost(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doPost(aRequest, aResponse);
    }

    @Override
    protected ServerActionsEnum getServerAction() {
        return ServerActionsEnum.UPDATE_MARKER;
    }

    @Override
    protected void buildServerRequest(IServerRequest aServerRequest) {
        aServerRequest.addField(RequestConstants.DTO, GsonHelper.fromGson(getHttpParam(RequestConstants.MARKER), MarkerDto.class));
    }

}
