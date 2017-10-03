package com.tpo.world.web.api;

import com.tpo.world.web.impl.response.base.envelope.ResponseEnvelope;

public interface Server {

    ServerRequest createRequest();

    ResponseEnvelope sendRequest(ServerRequest serverRequest);
}
