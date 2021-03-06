package com.tpo.world.web.web.impl.marker;

import com.tpo.world.web.server.api.ServerActionsEnum;
import com.tpo.world.web.server.api.request.IServerRequest;
import com.tpo.world.web.server.api.request.RequestConstants;
import com.tpo.world.web.server.api.server.IServer;
import com.tpo.world.web.web.base.AbstractServerRequestServlet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class GetMarkersServlet extends AbstractServerRequestServlet {

    private static final long serialVersionUID = 1606559514981505001L;

    public GetMarkersServlet(IServer serverIf) {
        super(serverIf);
    }

    @RequestMapping(value = "/GetMarkersServlet", method = RequestMethod.GET)
    public void doGet(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doGet(aRequest, aResponse);
    }

    @RequestMapping(value = "/GetMarkersServlet", method = RequestMethod.POST)
    public void doPost(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doPost(aRequest, aResponse);
    }

    @Override
    protected ServerActionsEnum getServerAction() {
        return ServerActionsEnum.GET_MARKERS;
    }

    @Override
    protected void buildServerRequest(IServerRequest aServerRequest) {
        String userId = getHttpParam(RequestConstants.USER_ID);
        String markerId = getHttpParam(RequestConstants.MARKER_ID);

        aServerRequest.addLong(RequestConstants.USER_ID, Long.parseLong(userId));

        if (markerId != null)
            aServerRequest.addLong(RequestConstants.MARKER_ID, Long.parseLong(markerId));
    }

}
