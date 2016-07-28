package master.pam.server.impl.request;

import com.master.pam.encrypt.api.IEncryptApi;
import master.pam.crud.api.dao.IMarkerDao;
import master.pam.crud.api.dao.IPasswordDao;
import master.pam.crud.api.dao.IUserDao;
import master.pam.server.api.ServerActionsEnum;
import master.pam.server.api.request.IServerRequest;
import master.pam.server.impl.exceptions.RequestException;
import master.pam.server.impl.response.base.envelope.IResponseEnvelope;
import master.pam.server.impl.response.impl.ErrorServerResponse;
import master.pam.server.impl.response.impl.marker.*;
import master.pam.server.impl.response.impl.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestFactory {
    private final static Logger log = LoggerFactory.getLogger(RequestFactory.class);
    private IUserDao userDao;
    private IEncryptApi encryptApi;
    private IMarkerDao markerDao;
    private IPasswordDao passwordDao;

    public RequestFactory(IUserDao userDao, IEncryptApi encryptApi, IMarkerDao markerDao, IPasswordDao passwordDao) {
        this.userDao = userDao;
        this.encryptApi = encryptApi;
        this.markerDao = markerDao;
        this.passwordDao = passwordDao;
    }

    public IResponseEnvelope createResult(IServerRequest aRequest) {

        try {
            log.debug("Create result from factory for request: " + aRequest.getAction());

            if (aRequest.getAction() == ServerActionsEnum.SIGN_IN)
                return new SignInResponse(aRequest, userDao, encryptApi).getResponse();

            else if (aRequest.getAction() == ServerActionsEnum.GET_MARKERS)
                return new GetMarkersResponse(aRequest, markerDao).getResponse();

            else if (aRequest.getAction() == ServerActionsEnum.GET_TIMELINE)
                return new GetTimelineResponse(aRequest, markerDao).getResponse();

            else if (aRequest.getAction() == ServerActionsEnum.UPDATE_MARKER)
                return new UpdateMarkerResponse(aRequest, markerDao).getResponse();

            else if (aRequest.getAction() == ServerActionsEnum.DELETE_MARKER)
                return new DeleteMarkersResponse(aRequest, markerDao).getResponse();

            else if (aRequest.getAction() == ServerActionsEnum.ADD_MARKERS)
                return new CreateMarkersResponse(aRequest, markerDao).getResponse();

            else if (aRequest.getAction() == ServerActionsEnum.GET_USER_INFO_BY_FOURSQUAREID)
                return new GetUserByFoursquareIdResponse(aRequest).getResponse();

            else if (aRequest.getAction() == ServerActionsEnum.CREATE_USER)
                return new CreateUserResponse(aRequest, userDao).getResponse();

            else if (aRequest.getAction() == ServerActionsEnum.GET_USER_INFO)
                return new GetUserResponse(aRequest, userDao).getResponse();

            else if (aRequest.getAction() == ServerActionsEnum.UPDATE_USER)
                return new UpdateUserResponse(aRequest, userDao, passwordDao).getResponse();

            else
                throw new RequestException("No such action: " + aRequest.getAction());
        } catch (RequestException e) {

            return new ErrorServerResponse(aRequest, e).getResponse();
        }
    }
}
