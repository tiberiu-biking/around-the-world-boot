package master.pam.server.impl.response.impl.marker;

import com.tpo.world.domain.entity.MarkerEntity;
import com.tpo.world.domain.util.DateFormatUtil;
import com.tpo.world.persistence.repository.MarkerRepository;
import master.pam.crosscutting.dto.api.ITimelineItemDto;
import master.pam.crosscutting.dto.timeline.TimelineItemDto;
import master.pam.server.api.request.IServerRequest;
import master.pam.server.api.request.RequestConstants;
import master.pam.server.api.response.ResponseConstants;
import master.pam.server.impl.exceptions.RequestException;
import master.pam.server.impl.response.base.AbstractResponse;
import master.pam.server.impl.response.base.envelope.IResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GetTimelineResponse extends AbstractResponse {

    private static final Logger logger = LoggerFactory.getLogger(GetTimelineResponse.class);

    private MarkerRepository markerRepository;
    private List<ITimelineItemDto> timeline;

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
        TimelineItemDto currentTimelineItem = null;

        timeline = new ArrayList<>();

        for (MarkerEntity marker : markers) {
            Integer year = DateFormatUtil.getYear(marker.getDate());

            if (!year.equals(currentYear)) {
                currentYear = year;
                currentTimelineItem = new TimelineItemDto(currentYear);
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
