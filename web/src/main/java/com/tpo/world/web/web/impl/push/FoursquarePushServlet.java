package com.tpo.world.web.web.impl.push;

import com.tpo.world.api.foursquare.util.FoursquareUtil;
import com.tpo.world.model.entity.MarkerEntity;
import com.tpo.world.model.entity.UserEntity;
import com.tpo.world.model.util.GsonHelper;
import com.tpo.world.web.notifications.NotificationPool;
import com.tpo.world.web.server.api.ServerActionsEnum;
import com.tpo.world.web.server.api.request.IServerRequest;
import com.tpo.world.web.server.api.request.RequestConstants;
import com.tpo.world.web.server.api.response.ResponseConstants;
import com.tpo.world.web.server.api.server.IServer;
import com.tpo.world.web.server.impl.response.base.envelope.IResponseEnvelope;
import com.tpo.world.web.web.base.AbstractServerRequestServlet;
import com.tpo.world.web.web.exception.WrongRequestException;
import fi.foyt.foursquare.api.entities.Checkin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class FoursquarePushServlet extends AbstractServerRequestServlet {

    private static final Logger logger = LoggerFactory.getLogger(FoursquarePushServlet.class);

    public FoursquarePushServlet(IServer serverIf) {
        super(serverIf);
    }

    @RequestMapping(value = "/FoursquarePushServlet", method = RequestMethod.GET)
    public void doGet(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doGet(aRequest, aResponse);
    }

    @RequestMapping(value = "/FoursquarePushServlet", method = RequestMethod.POST)
    public void doPost(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doPost(aRequest, aResponse);
    }

    @Override
    protected ServerActionsEnum getServerAction() {
        return ServerActionsEnum.ADD_MARKERS;
    }

    @Override
    protected void buildServerRequest(IServerRequest aServerRequest) throws WrongRequestException {
        String checkinGson = getHttpParam(RequestConstants.FOURSQUARE_CHECKIN);

        logger.debug("Recieved Foursquare push notification: " + checkinGson);

        Checkin checkin = GsonHelper.fromGson(checkinGson, Checkin.class);

        IServerRequest userRequest = builGetUserInfoRequest(checkin.getUser().getId());
        IResponseEnvelope requestUserResponse = getServer().sendRequest(userRequest);
        UserEntity user = requestUserResponse.getData(ResponseConstants.USER, UserEntity.class);

        if (user != null) {
            MarkerEntity marker = FoursquareUtil.checkinToMarker(checkin, user.getId());
            aServerRequest.addField(RequestConstants.DTO, marker);
        }
    }

    @Override
    protected String buildResult(IResponseEnvelope aResponseEnvelope) {
        String newMarkers = super.buildResult(aResponseEnvelope);

        logger.debug("Add notification to the pool: " + newMarkers);

        NotificationPool.getPool().addNotification(newMarkers);

        return "";
    }

    private IServerRequest builGetUserInfoRequest(String aFoursquareUserId) {
        IServerRequest userRequest = getServer().createRequest();
        userRequest.setAction(ServerActionsEnum.GET_USER_INFO_BY_FOURSQUAREID);
        userRequest.addField(RequestConstants.USER_FOURSQUARE_ID, aFoursquareUserId);
        return userRequest;
    }


}
