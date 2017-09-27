package com.tpo.world.web.impl.response.impl.marker;

import com.tpo.world.persistence.entity.MarkerEntity;
import com.tpo.world.persistence.repository.MarkerRepository;
import com.tpo.world.web.api.request.IServerRequest;
import com.tpo.world.web.api.response.ResponseConstants;
import com.tpo.world.web.impl.exceptions.RequestException;
import com.tpo.world.web.impl.response.base.AbstractResponse;
import com.tpo.world.web.impl.response.base.envelope.IResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateMarkerResponse extends AbstractResponse {

    private final static Logger logger = LoggerFactory.getLogger(UpdateMarkerResponse.class);

    private MarkerRepository markerRepository;

    public UpdateMarkerResponse(IServerRequest aRequest, MarkerRepository markerRepository) {
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
    public void buildResponseEnvelope(IResponseEnvelope aResponseEnvelope) {
        aResponseEnvelope.addData(ResponseConstants.MESSAGE, "Update successful!");
    }

}
