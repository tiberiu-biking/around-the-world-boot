package com.tpo.world.web.server.impl.response.impl.marker;

import com.tpo.world.model.entity.MarkerEntity;
import com.tpo.world.model.timeline.TimelineItem;
import com.tpo.world.model.util.DateFormatUtil;
import com.tpo.world.persistence.repository.MarkerRepository;
import com.tpo.world.web.server.api.request.IServerRequest;
import com.tpo.world.web.server.api.request.RequestConstants;
import com.tpo.world.web.server.api.response.ResponseConstants;
import com.tpo.world.web.server.impl.exceptions.RequestException;
import com.tpo.world.web.server.impl.response.base.AbstractResponse;
import com.tpo.world.web.server.impl.response.base.envelope.IResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GetTimelineResponse extends AbstractResponse {

    private static final Logger logger = LoggerFactory.getLogger(GetTimelineResponse.class);

    private MarkerRepository markerRepository;
    private List<TimelineItem> timeline;

    public GetTimelineResponse(IServerRequest aRequest, MarkerRepository markerRepository) {
        super(aRequest);
        this.markerRepository = markerRepository;
    }

    @Override
    public void doRequest() throws RequestException {
        Long userId = getRequest().getLong(RequestConstants.USER_ID);
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
    public void buildResponseEnvelope(IResponseEnvelope aEnvelope) {
        aEnvelope.addData(ResponseConstants.TIMELINE, timeline);
    }
}
