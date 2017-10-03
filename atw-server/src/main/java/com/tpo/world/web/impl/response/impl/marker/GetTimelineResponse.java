package com.tpo.world.web.impl.response.impl.marker;

import com.tpo.world.model.TimelineItem;
import com.tpo.world.model.exceptions.RequestException;
import com.tpo.world.persistence.entity.MarkerEntity;
import com.tpo.world.persistence.repository.MarkerRepository;
import com.tpo.world.services.util.DateFormatUtil;
import com.tpo.world.web.api.ServerRequest;
import com.tpo.world.web.constants.Constants;
import com.tpo.world.web.impl.response.base.AbstractServerResponse;
import com.tpo.world.web.impl.response.base.envelope.ResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GetTimelineResponse extends AbstractServerResponse {

    private static final Logger logger = LoggerFactory.getLogger(GetTimelineResponse.class);

    private MarkerRepository markerRepository;
    private List<ITimelineItemDto> timeline;

    public GetTimelineResponse(ServerRequest aRequest, MarkerRepository markerRepository) {
        super(aRequest);
        this.markerRepository = markerRepository;
    }

    @Override
    public void doRequest() throws RequestException {
        Long userId = getRequest().getLong(Constants.USER_ID);
        logger.info("Getting markers for user '{}'", userId);

        List<MarkerEntity> markers = markerRepository.findByUserIdOrderByDateDesc(userId);

        Integer currentYear = null;
        TimelineItem currentTimelineItem = null;

        timeline = new ArrayList<>();

        for (MarkerEntity marker : markers) {
            Integer year = DateFormatUtil.getYear(marker.getDate());

            if (!year.equals(currentYear)) {
                currentYear = year;
                currentTimelineItem = new TimelineItem(currentYear);
                timeline.add(currentTimelineItem);
            }
            currentTimelineItem.addMarker(marker);
        }
    }

    @Override
    public void buildResponseEnvelope(ResponseEnvelope aEnvelope) {
        aEnvelope.addData(Constants.TIMELINE, timeline);
    }
}
