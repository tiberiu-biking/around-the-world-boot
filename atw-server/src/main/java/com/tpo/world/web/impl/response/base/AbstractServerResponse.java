package com.tpo.world.web.impl.response.base;

import com.tpo.world.model.exceptions.RequestException;
import com.tpo.world.web.api.ServerRequest;
import com.tpo.world.web.api.ServerResponse;
import com.tpo.world.web.impl.response.base.envelope.InternalResponseEnvelope;
import com.tpo.world.web.impl.response.base.envelope.ResponseEnvelope;

public abstract class AbstractServerResponse implements ServerResponse {

    public abstract void doRequest() throws RequestException;

    public abstract void buildResponseEnvelope(ResponseEnvelope aResponseEnvelope);

    private ServerRequest request;
    private InternalResponseEnvelope internalResponseEnvelope;

    public AbstractServerResponse(ServerRequest aRequest) {
        super();
        request = aRequest;
    }

    public ResponseEnvelope getResponse() throws RequestException {
        doRequest();
        buildResponseEnvelope(getEnvelope());
        return getEnvelope();
    }

    public ServerRequest getRequest() {
        return request;
    }

    protected ResponseEnvelope getEnvelope() {
        if (internalResponseEnvelope == null)
            internalResponseEnvelope = new InternalResponseEnvelope();
        return internalResponseEnvelope;
    }
}
