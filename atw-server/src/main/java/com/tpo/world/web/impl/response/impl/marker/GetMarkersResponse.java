package com.tpo.world.web.impl.response.impl.marker;

import com.tpo.world.model.exceptions.RequestException;
import com.tpo.world.persistence.entity.MarkerEntity;
import com.tpo.world.persistence.repository.MarkerRepository;
import com.tpo.world.services.util.Geo;
import com.tpo.world.web.api.ServerRequest;
import com.tpo.world.web.constants.Constants;
import com.tpo.world.web.impl.response.base.AbstractServerResponse;
import com.tpo.world.web.impl.response.base.envelope.ResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetMarkersResponse extends AbstractServerResponse {

    private static final Logger logger = LoggerFactory.getLogger(GetMarkersResponse.class);
    private List<MarkerEntity> markers;

    private MarkerRepository markerRepository;

    public GetMarkersResponse(ServerRequest aRequest, MarkerRepository markerRepository) {
        super(aRequest);
        this.markerRepository = markerRepository;
    }

    @Override
    public void doRequest() throws RequestException {
        Long userId = getRequest().getLong(Constants.USER_ID);
        Long markerId = getRequest().getLong(Constants.MARKER_ID);

        logger.info("Getting markers for user '{}' and marker {}", userId, markerId);

        if (markerId != null) {
            markers = markerRepository.findByIdAndUserId(markerId, userId);
        } else {
            markers = markerRepository.findByUserId(userId);
        }
    }

    @Override
    public void buildResponseEnvelope(ResponseEnvelope aEnvelope) {
        aEnvelope
                .addData(Constants.MARKERS, markers)
                .addData(Constants.CENTER_POINT, Geo.calculateCenterPoint(markers));
    }
}
