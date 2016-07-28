package master.pam.server.impl.response.impl.marker;

import master.pam.crosscutting.dto.api.IMarkerDto;
import master.pam.crud.api.dao.IMarkerDao;
import master.pam.server.api.request.IServerRequest;
import master.pam.server.api.response.ResponseConstants;
import master.pam.server.impl.exceptions.RequestException;
import master.pam.server.impl.response.base.AbstractResponse;
import master.pam.server.impl.response.base.envelope.IResponseEnvelope;

public class UpdateMarkerResponse extends AbstractResponse {

    private IMarkerDao markerDao;

    public UpdateMarkerResponse(IServerRequest aRequest, IMarkerDao markerDao) {
        super(aRequest);
        this.markerDao = markerDao;
    }

    @Override
    public void doRequest() throws RequestException {
        markerDao.update(getRequest().getDto(IMarkerDto.class));
    }

    @Override
    public void buildResponseEnvelope(IResponseEnvelope aResponseEnvelope) {
        aResponseEnvelope.addData(ResponseConstants.MESSAGE, "Update successful!");
    }

}
