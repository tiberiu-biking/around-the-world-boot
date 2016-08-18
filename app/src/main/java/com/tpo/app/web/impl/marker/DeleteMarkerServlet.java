package com.tpo.app.web.impl.marker;

import com.tpo.app.web.base.AbstractServerRequestServlet;
import master.pam.server.api.ServerActionsEnum;
import master.pam.server.api.request.IServerRequest;
import master.pam.server.api.request.RequestConstants;
import master.pam.server.api.server.IServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class DeleteMarkerServlet extends AbstractServerRequestServlet {
    private static final long serialVersionUID = 7735454047270919067L;

    public DeleteMarkerServlet(IServer serverIf) {
        super(serverIf);
    }

    @RequestMapping(value = "/DeleteMarkerServlet", method = RequestMethod.GET)
    public void doGet(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doGet(aRequest, aResponse);
    }

    @RequestMapping(value = "/DeleteMarkerServlet", method = RequestMethod.POST)
    public void doPost(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doPost(aRequest, aResponse);
    }


    @Override
    protected ServerActionsEnum getServerAction() {
        return ServerActionsEnum.DELETE_MARKER;
    }

    @Override
    protected void buildServerRequest(IServerRequest aServerRequest) {
        aServerRequest.addLong(RequestConstants.MARKER_ID, Long.parseLong(getHttpParam(RequestConstants.MARKER_ID)));
    }
}
