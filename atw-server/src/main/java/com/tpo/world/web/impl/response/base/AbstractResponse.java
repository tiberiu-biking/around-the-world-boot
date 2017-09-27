package com.tpo.world.web.impl.response.base;

import com.tpo.world.web.api.request.IServerRequest;
import com.tpo.world.web.api.response.IServerResponse;
import com.tpo.world.web.impl.exceptions.RequestException;
import com.tpo.world.web.impl.response.base.envelope.IResponseEnvelope;
import com.tpo.world.web.impl.response.base.envelope.ResponseEnvelope;

public abstract class AbstractResponse implements IServerResponse {

    public abstract void doRequest() throws RequestException;

    public abstract void buildResponseEnvelope(IResponseEnvelope aResponseEnvelope);

    /**
     * The request for which the response envelope is sent.
     */
    private IServerRequest request;
    private ResponseEnvelope responseEnvelope;

    public AbstractResponse(IServerRequest aRequest) {
        super();
        request = aRequest;
    }

    public IResponseEnvelope getResponse() throws RequestException {
        doRequest();
        buildResponseEnvelope(getEnvelope());
        return getEnvelope();
    }

    public IServerRequest getRequest() {
        return request;
    }

    protected IResponseEnvelope getEnvelope() {
        if (responseEnvelope == null)
            responseEnvelope = new ResponseEnvelope();
        return responseEnvelope;
    }
}
