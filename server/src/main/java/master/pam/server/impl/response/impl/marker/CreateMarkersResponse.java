package master.pam.server.impl.response.impl.marker;

import com.tpo.world.model.entity.MarkerEntity;
import com.tpo.world.persistence.repository.MarkerRepository;
import master.pam.server.api.request.IServerRequest;
import master.pam.server.api.request.RequestConstants;
import master.pam.server.api.response.ResponseConstants;
import master.pam.server.api.response.ResponseType;
import master.pam.server.impl.exceptions.RequestException;
import master.pam.server.impl.response.base.AbstractResponse;
import master.pam.server.impl.response.base.envelope.IResponseEnvelope;
import master.pam.server.impl.util.ServerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CreateMarkersResponse extends AbstractResponse {

    private final static Logger logger = LoggerFactory.getLogger(CreateMarkersResponse.class);

    private MarkerRepository markerRepository;
    private List<MarkerEntity> markers;
    private int newMarkers;

    public CreateMarkersResponse(IServerRequest aRequest, MarkerRepository markerRepository) {
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

        ResponseType responseType = getRequest().get(RequestConstants.RETURN_TYPE, ResponseType.class);
        Long userId = getRequest().get(RequestConstants.USER_ID, Long.class);
        if (responseType == ResponseType.RETURN_ALL) {
            this.markers = markerRepository.findByUserId(userId);
        }
    }

    @Override
    public void buildResponseEnvelope(IResponseEnvelope aResponseEnvelope) {
        aResponseEnvelope
                .addData(ResponseConstants.MARKERS, markers)
                .addData(ResponseConstants.CENTER_POINT, ServerUtil.calculateCenterPoint(markers))
                .addDataMessage(newMarkers + " new marker(s) added.");
    }
}
