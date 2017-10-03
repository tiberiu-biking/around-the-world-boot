package com.tpo.world.web.impl.server;

import com.tpo.world.web.api.Server;
import com.tpo.world.web.api.ServerRequest;
import com.tpo.world.web.impl.request.InternalServerRequest;
import com.tpo.world.web.impl.request.RequestFactory;
import com.tpo.world.web.impl.response.base.envelope.ResponseEnvelope;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InternalServer implements Server {

    private RequestFactory requestFactory;

    public InternalServer(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public ServerRequest createRequest() {
        return new InternalServerRequest();
    }

    @Override
    public ResponseEnvelope sendRequest(ServerRequest aServerRequest) {
        log.debug("Send request: " + aServerRequest);
        return requestFactory.createResult(aServerRequest);
    }

}
