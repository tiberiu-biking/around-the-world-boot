package com.tpo.app.web.impl.importer;

import com.tpo.app.web.base.AbstractServerRequestServlet;
import com.tpo.world.model.entity.MarkerEntity;
import com.tpo.world.model.entity.UserEntity;
import fi.foyt.foursquare.api.FoursquareApiException;
import master.pam.foursquare.api.impl.IFoursquareSource;
import master.pam.server.api.ServerActionsEnum;
import master.pam.server.api.request.IServerRequest;
import master.pam.server.api.request.RequestConstants;
import master.pam.server.api.response.ResponseConstants;
import master.pam.server.api.response.ResponseType;
import master.pam.server.api.server.IServer;
import master.pam.server.impl.response.base.envelope.IResponseEnvelope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class FoursquareImportServlet extends AbstractServerRequestServlet {

    private static final long serialVersionUID = -7904700628455673567L;

    private IFoursquareSource foursquareAPI;

    public FoursquareImportServlet(IServer serverIf, IFoursquareSource foursquareAPI) {
        super(serverIf);
        this.foursquareAPI = foursquareAPI;
    }

    @RequestMapping(value = "/FoursquareImportServlet", method = RequestMethod.GET)
    public void doGet(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doGet(aRequest, aResponse);
    }

    @RequestMapping(value = "/FoursquareImportServlet", method = RequestMethod.POST)
    public void doPost(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doPost(aRequest, aResponse);
    }


    @Override
    protected ServerActionsEnum getServerAction() {
        return ServerActionsEnum.ADD_MARKERS;
    }

    @Override
    protected void buildServerRequest(IServerRequest aServerRequest) {
        String token = getHttpParam(RequestConstants.CODE);

        IResponseEnvelope requestUserResponse = getServer().sendRequest(builGetUserInfoRequest());
        UserEntity userDto = requestUserResponse.getData(ResponseConstants.USER, UserEntity.class);

        if (userDto.getFoursquareToken() == null) {
            userDto.setFoursquareToken(token);
            try {
                userDto.setFoursquareId(foursquareAPI.getUserId(token));
            } catch (Exception exception) {
                // TODO
                exception.printStackTrace();
            }

            getServer().sendRequest(builSaveUserInfoRequest(userDto));
        }

        try {
            Long userId = Long.parseLong(getHttpParam(RequestConstants.USER_ID));

            List<MarkerEntity> foursquareMarkers = foursquareAPI.getMarkers(token, userId);

            aServerRequest.addField(RequestConstants.DTO_LIST, foursquareMarkers);

        } catch (FoursquareApiException e) {
            e.printStackTrace();
        }

        aServerRequest.addField(RequestConstants.RETURN_TYPE, ResponseType.RETURN_ALL);
        aServerRequest.addField(RequestConstants.USER_ID, Long.parseLong(getHttpParam(RequestConstants.USER_ID)));
    }

    private IServerRequest builGetUserInfoRequest() {
        IServerRequest codeRequest = getServer().createRequest();
        codeRequest.setAction(ServerActionsEnum.GET_USER_INFO);
        codeRequest.addField(RequestConstants.USER_ID, Long.parseLong(getHttpParam(RequestConstants.USER_ID)));
        return codeRequest;
    }

    private IServerRequest builSaveUserInfoRequest(UserEntity user) {
        IServerRequest codeRequest = getServer().createRequest();
        codeRequest.setAction(ServerActionsEnum.UPDATE_USER);
        codeRequest.addField(RequestConstants.DTO, user);
        return codeRequest;
    }

}
