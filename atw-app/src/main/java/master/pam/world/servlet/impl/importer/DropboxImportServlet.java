package master.pam.world.servlet.impl.importer;

import com.dropbox.core.DbxException;
import com.tpo.world.api.dropbox.api.IDropboxSource;
import com.tpo.world.services.util.GsonHelper;
import com.tpo.world.web.api.Server;
import com.tpo.world.web.api.ServerRequest;
import com.tpo.world.web.constants.Constants;
import com.tpo.world.web.domain.ServerAction;
import master.pam.world.servlet.base.AbstractServerRequestServlet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DropboxImportServlet extends AbstractServerRequestServlet {

    private IDropboxSource dropboxAPI;

    public DropboxImportServlet(Server serverIf, IDropboxSource dropboxAPI) {
        super(serverIf);
        this.dropboxAPI = dropboxAPI;
    }

    @RequestMapping(value = "/DropboxImportServlet", method = RequestMethod.GET)
    public void doGet(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doGet(aRequest, aResponse);
    }

    @RequestMapping(value = "/DropboxImportServlet", method = RequestMethod.POST)
    public void doPost(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doPost(aRequest, aResponse);
    }

    @Override
    protected ServerAction getServerAction() {
        return ServerAction.ADD_MARKERS;
    }

    @Override
    protected void buildServerRequest(ServerRequest aServerRequest) {
        String code = getHttpParam(Constants.CODE);
        String[] files = GsonHelper.fromGson(getHttpParam(Constants.FILES), String[].class);

        List<String> paths = new ArrayList<>();

        for (String file : files) {
            String[] splited = StringUtils.split(file, "/");
            StringBuilder pathBuilder = new StringBuilder();
            for (int i = 5; i < splited.length; i++)
                pathBuilder.append("/").append(splited[i]);
            paths.add(pathBuilder.toString());
        }

        long userId = Long.parseLong(getHttpParam(Constants.USER_ID));

        try {
            aServerRequest.addField(Constants.DTO_LIST, dropboxAPI.getMarkers(code, paths, userId));
        } catch (DbxException e) {
            // TODO
            e.printStackTrace();
        }
    }
}
