package com.tpo.world.web.web.impl.importer;

import com.dropbox.core.DbxException;
import com.tpo.world.api.dropbox.api.IDropboxSource;
import com.tpo.world.model.util.GsonHelper;
import com.tpo.world.web.server.api.ServerActionsEnum;
import com.tpo.world.web.server.api.request.IServerRequest;
import com.tpo.world.web.server.api.request.RequestConstants;
import com.tpo.world.web.server.api.server.IServer;
import com.tpo.world.web.web.base.AbstractServerRequestServlet;
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

    public DropboxImportServlet(IServer serverIf, IDropboxSource dropboxAPI) {
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
    protected ServerActionsEnum getServerAction() {
        return ServerActionsEnum.ADD_MARKERS;
    }

    @Override
    protected void buildServerRequest(IServerRequest aServerRequest) {
        String code = getHttpParam(RequestConstants.CODE);
        String[] files = GsonHelper.fromGson(getHttpParam(RequestConstants.FILES), String[].class);

        List<String> paths = new ArrayList<>();

        for (String file : files) {
            String[] splited = StringUtils.split(file, "/");
            StringBuilder pathBuilder = new StringBuilder();
            for (int i = 5; i < splited.length; i++)
                pathBuilder.append("/").append(splited[i]);
            paths.add(pathBuilder.toString());
        }

        long userId = Long.parseLong(getHttpParam(RequestConstants.USER_ID));

        try {
            aServerRequest.addField(RequestConstants.DTO_LIST, dropboxAPI.getMarkers(code, paths, userId));
        } catch (DbxException e) {
            // TODO
            e.printStackTrace();
        }
    }
}
