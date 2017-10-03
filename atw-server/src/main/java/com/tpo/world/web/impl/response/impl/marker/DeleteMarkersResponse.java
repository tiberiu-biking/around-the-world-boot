package com.tpo.world.web.impl.response.impl.marker;

import com.tpo.world.model.exceptions.RequestException;
import com.tpo.world.persistence.repository.MarkerRepository;
import com.tpo.world.web.api.ServerRequest;
import com.tpo.world.web.constants.Constants;
import com.tpo.world.web.impl.response.base.AbstractServerResponse;
import com.tpo.world.web.impl.response.base.envelope.ResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteMarkersResponse extends AbstractServerResponse {

    private final static Logger logger = LoggerFactory.getLogger(DeleteMarkersResponse.class);

    private MarkerRepository markerRepository;

    public DeleteMarkersResponse(ServerRequest aRequest, MarkerRepository markerRepository) {
        super(aRequest);
        this.markerRepository = markerRepository;
    }

    @Override
    public void doRequest() throws RequestException {
        Long markerId = getRequest().getLong(Constants.MARKER_ID);
        logger.info("Deleting marker with id '{}'", markerId);
        markerRepository.delete(markerId);
    }

    @Override
    public void buildResponseEnvelope(ResponseEnvelope aResponseEnvelope) {
        aResponseEnvelope.addData(Constants.MESSAGE, "Delete successful!");
    }

}
