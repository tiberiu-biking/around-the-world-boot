package master.pam.server.impl.response.impl.marker;

import com.tpo.world.domain.entity.MarkerEntity;
import com.tpo.world.persistence.repository.MarkerRepository;
import master.pam.server.api.request.IServerRequest;
import master.pam.server.api.response.ResponseConstants;
import master.pam.server.impl.exceptions.RequestException;
import master.pam.server.impl.response.base.AbstractResponse;
import master.pam.server.impl.response.base.envelope.IResponseEnvelope;
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
