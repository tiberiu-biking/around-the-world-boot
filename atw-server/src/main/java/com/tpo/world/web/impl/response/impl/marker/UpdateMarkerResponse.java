package com.tpo.world.web.impl.response.impl.marker;

import com.tpo.world.model.exceptions.RequestException;
import com.tpo.world.persistence.entity.MarkerEntity;
import com.tpo.world.persistence.repository.MarkerRepository;
import com.tpo.world.web.api.ServerRequest;
import com.tpo.world.web.constants.Constants;
import com.tpo.world.web.impl.response.base.AbstractServerResponse;
import com.tpo.world.web.impl.response.base.envelope.ResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateMarkerResponse extends AbstractServerResponse {

    private final static Logger logger = LoggerFactory.getLogger(UpdateMarkerResponse.class);

    private MarkerRepository markerRepository;

    public UpdateMarkerResponse(ServerRequest aRequest, MarkerRepository markerRepository) {
        super(aRequest);
        this.markerRepository = markerRepository;
    }

    @Override
    public void doRequest() throws RequestException {
        MarkerEntity marker = getRequest().getDto(MarkerEntity.class);
        logger.trace("Updating marker with id '{}'", marker.getId());
        marker = markerRepository.findOne(marker.getId());
        markerRepository.saveAndFlush(marker);
    }

    @Override
    public void buildResponseEnvelope(ResponseEnvelope aResponseEnvelope) {
        aResponseEnvelope.addData(Constants.MESSAGE, "Update successful!");
    }

}
