package master.pam.world.servlet.impl.marker;

import com.tpo.world.persistence.entity.MarkerEntity;
import com.tpo.world.services.util.GsonHelper;
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
public class UpdateMarkerServlet extends AbstractServerRequestServlet {
    private static final long serialVersionUID = 7735454047270919067L;

    public UpdateMarkerServlet(Server serverIf) {
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
    protected ServerAction getServerAction() {
        return ServerAction.UPDATE_MARKER;
    }

    @Override
    protected void buildServerRequest(ServerRequest aServerRequest) {
        aServerRequest.addField(Constants.DTO, GsonHelper.fromGson(getHttpParam(Constants.MARKER), MarkerEntity.class));
    }

}
