package com.tpo.world.web.server.api.server;

import com.tpo.world.web.server.api.request.IServerRequest;
import com.tpo.world.web.server.impl.response.base.envelope.IResponseEnvelope;

public interface IServer {

    IServerRequest createRequest();

    IResponseEnvelope sendRequest(IServerRequest serverRequest);
}
