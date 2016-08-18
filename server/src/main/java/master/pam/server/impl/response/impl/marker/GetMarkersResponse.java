package master.pam.server.impl.response.impl.marker;

import com.tpo.world.domain.entity.MarkerEntity;
import com.tpo.world.persistence.repository.MarkerRepository;
import master.pam.server.api.request.IServerRequest;
import master.pam.server.api.request.RequestConstants;
import master.pam.server.api.response.ResponseConstants;
import master.pam.server.impl.exceptions.RequestException;
import master.pam.server.impl.response.base.AbstractResponse;
import master.pam.server.impl.response.base.envelope.IResponseEnvelope;
import master.pam.server.impl.util.ServerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetMarkersResponse extends AbstractResponse {

    private static final Logger logger = LoggerFactory.getLogger(GetMarkersResponse.class);
    private List<MarkerEntity> markers;

    private MarkerRepository markerRepository;

    public GetMarkersResponse(IServerRequest aRequest, MarkerRepository markerRepository) {
        super(aRequest);
        this.markerRepository = markerRepository;
    }

    @Override
    public void doRequest() throws RequestException {
        Long userId = getRequest().getLong(RequestConstants.USER_ID);
        Long markerId = getRequest().getLong(RequestConstants.MARKER_ID);

        logger.info("Getting markers for user '{}' and marker {}", userId, markerId);

        if (markerId != null) {
            markers = markerRepository.findByIdAndUserId(markerId, userId);
        } else {
            markers = markerRepository.findByUserId(userId);
        }
    }

    @Override
    public void buildResponseEnvelope(IResponseEnvelope aEnvelope) {
        aEnvelope
                .addData(ResponseConstants.MARKERS, markers)
                .addData(ResponseConstants.CENTER_POINT, ServerUtil.calculateCenterPoint(markers));
    }
}
