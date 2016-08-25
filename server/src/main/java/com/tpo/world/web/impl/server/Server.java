package com.tpo.world.web.impl.server;

import com.tpo.world.web.api.request.IServerRequest;
import com.tpo.world.web.api.server.IServer;
import com.tpo.world.web.impl.request.RequestFactory;
import com.tpo.world.web.impl.request.ServerRequest;
import com.tpo.world.web.impl.response.base.envelope.IResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server implements IServer {

    private final Logger log = LoggerFactory.getLogger(Server.class);

    private RequestFactory requestFactory;

    public Server(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public IServerRequest createRequest() {
        return new ServerRequest();
    }

    @Override
    public IResponseEnvelope sendRequest(IServerRequest aServerRequest) {
        log.debug("Send request: " + aServerRequest);
        return requestFactory.createResult(aServerRequest);
    }

}
