package com.tpo.world.web.api.server;

import com.tpo.world.web.api.request.IServerRequest;
import com.tpo.world.web.impl.response.base.envelope.IResponseEnvelope;

public interface IServer {

    IServerRequest createRequest();

    IResponseEnvelope sendRequest(IServerRequest serverRequest);
}
