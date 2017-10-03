package com.tpo.world.web.impl.response.impl.marker;

import com.tpo.world.model.exceptions.RequestException;
import com.tpo.world.persistence.entity.MarkerEntity;
import com.tpo.world.persistence.repository.MarkerRepository;
import com.tpo.world.services.util.Geo;
import com.tpo.world.web.api.ServerRequest;
import com.tpo.world.web.constants.Constants;
import com.tpo.world.web.domain.ResponseType;
import com.tpo.world.web.impl.response.base.AbstractServerResponse;
import com.tpo.world.web.impl.response.base.envelope.ResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CreateMarkersResponse extends AbstractServerResponse {

    private final static Logger logger = LoggerFactory.getLogger(CreateMarkersResponse.class);

    private MarkerRepository markerRepository;
    private List<MarkerEntity> markers;
    private int newMarkers;

    public CreateMarkersResponse(ServerRequest aRequest, MarkerRepository markerRepository) {
        super(aRequest);
        this.markerRepository = markerRepository;
    }

    @Override
    public void doRequest() throws RequestException {

        List<MarkerEntity> markers = getRequest().getDtoList(MarkerEntity.class);
        if (markers == null) {
            markers = new ArrayList<>();
            MarkerEntity dto = getRequest().getDto(MarkerEntity.class);
            if (dto != null) {
                markers.add(dto);
            }
        }

        this.markers = new ArrayList<>();

        for (MarkerEntity marker : markers) {
            markerRepository.saveAndFlush(marker);
            this.markers.add(marker);
        }

        newMarkers = this.markers.size();

        ResponseType responseType = getRequest().get(Constants.RETURN_TYPE, ResponseType.class);
        Long userId = getRequest().get(Constants.USER_ID, Long.class);
        if (responseType == ResponseType.RETURN_ALL) {
            this.markers = markerRepository.findByUserId(userId);
        }
    }

    @Override
    public void buildResponseEnvelope(ResponseEnvelope aResponseEnvelope) {
        aResponseEnvelope
                .addData(Constants.MARKERS, markers)
                .addData(Constants.CENTER_POINT, Geo.calculateCenterPoint(markers))
                .addDataMessage(newMarkers + " new marker(s) added.");
    }
}
