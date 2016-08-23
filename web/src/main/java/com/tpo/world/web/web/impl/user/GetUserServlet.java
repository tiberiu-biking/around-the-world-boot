package com.tpo.world.web.web.impl.user;

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
public class GetUserServlet extends AbstractServerRequestServlet {

    public GetUserServlet(IServer serverIf) {
        super(serverIf);
    }

    @RequestMapping(value = "/GetUserServlet", method = RequestMethod.GET)
    public void doGet(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doGet(aRequest, aResponse);
    }

    @RequestMapping(value = "/GetUserServlet", method = RequestMethod.POST)
    public void doPost(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doPost(aRequest, aResponse);
    }

    @Override
    protected ServerActionsEnum getServerAction() {
        return ServerActionsEnum.GET_USER_INFO;
    }

    @Override
    protected void buildServerRequest(IServerRequest aServerRequest) {
        aServerRequest.addField(RequestConstants.USER_ID, Long.parseLong(getHttpParam(RequestConstants.USER_ID)));
    }

}
