package com.tpo.world.web.server.impl.response.impl;

import com.tpo.world.web.server.api.request.IServerRequest;
import com.tpo.world.web.server.impl.exceptions.RequestException;
import com.tpo.world.web.server.impl.response.base.AbstractResponse;
import com.tpo.world.web.server.impl.response.base.envelope.IResponseEnvelope;

public class ErrorServerResponse extends AbstractResponse {

    private RequestException error;

    public ErrorServerResponse(IServerRequest aRequest, RequestException aException) {
        super(aRequest);
        error = aException;
    }

    @Override
    public void doRequest() {
    }

    @Override
    public void buildResponseEnvelope(IResponseEnvelope aResponseEnvelope) {
        aResponseEnvelope.setError(error);
    }

}
