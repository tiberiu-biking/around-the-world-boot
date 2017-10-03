package com.tpo.world.web.impl.response.impl;

import com.tpo.world.model.exceptions.RequestException;
import com.tpo.world.web.api.ServerRequest;
import com.tpo.world.web.impl.response.base.AbstractServerResponse;
import com.tpo.world.web.impl.response.base.envelope.ResponseEnvelope;

public class ErrorServerResponse extends AbstractServerResponse {

    private RequestException error;

    public ErrorServerResponse(ServerRequest aRequest, RequestException aException) {
        super(aRequest);
        error = aException;
    }

    @Override
    public void doRequest() {
    }

    @Override
    public void buildResponseEnvelope(ResponseEnvelope aResponseEnvelope) {
        aResponseEnvelope.setError(error);
    }

}
