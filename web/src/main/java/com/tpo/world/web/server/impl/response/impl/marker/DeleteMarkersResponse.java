package com.tpo.world.web.server.impl.response.impl.marker;

import com.tpo.world.persistence.repository.MarkerRepository;
import com.tpo.world.web.server.api.request.IServerRequest;
import com.tpo.world.web.server.api.request.RequestConstants;
import com.tpo.world.web.server.api.response.ResponseConstants;
import com.tpo.world.web.server.impl.exceptions.RequestException;
import com.tpo.world.web.server.impl.response.base.AbstractResponse;
import com.tpo.world.web.server.impl.response.base.envelope.IResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteMarkersResponse extends AbstractResponse {

    private final static Logger logger = LoggerFactory.getLogger(DeleteMarkersResponse.class);

    private MarkerRepository markerRepository;

    public DeleteMarkersResponse(IServerRequest aRequest, MarkerRepository markerRepository) {
        super(aRequest);
        this.markerRepository = markerRepository;
    }

    @Override
    public void doRequest() throws RequestException {
        Long markerId = getRequest().getLong(RequestConstants.MARKER_ID);
        logger.info("Deleting marker with id '{}'", markerId);
        markerRepository.delete(markerId);
    }

    @Override
    public void buildResponseEnvelope(IResponseEnvelope aResponseEnvelope) {
        aResponseEnvelope.addData(ResponseConstants.MESSAGE, "Delete successful!");
    }

}
