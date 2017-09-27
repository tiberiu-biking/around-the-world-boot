package com.tpo.world.web.impl.request;

import com.tpo.world.core.encrypt.api.EncryptService;
import com.tpo.world.persistence.repository.MarkerRepository;
import com.tpo.world.persistence.repository.PasswordRepository;
import com.tpo.world.persistence.repository.UserRepository;
import com.tpo.world.web.api.ServerActionsEnum;
import com.tpo.world.web.api.request.IServerRequest;
import com.tpo.world.web.impl.exceptions.RequestException;
import com.tpo.world.web.impl.response.base.envelope.IResponseEnvelope;
import com.tpo.world.web.impl.response.impl.ErrorServerResponse;
import com.tpo.world.web.impl.response.impl.marker.*;
import com.tpo.world.web.impl.response.impl.user.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestFactory {
    private final static Logger log = LoggerFactory.getLogger(RequestFactory.class);
    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;
    private EncryptService encryptApi;
    private MarkerRepository markerRepository;

    public RequestFactory(UserRepository userRepository, PasswordRepository passwordRepository, MarkerRepository markerRepository, EncryptService encryptApi) {
        this.userRepository = userRepository;
        this.passwordRepository = passwordRepository;
        this.encryptApi = encryptApi;
        this.markerRepository = markerRepository;
    }

    public IResponseEnvelope createResult(IServerRequest aRequest) {

        try {
            log.debug("Create result from factory for request: " + aRequest.getAction());

            if (aRequest.getAction() == ServerActionsEnum.SIGN_IN) {
                return new SignInResponse(aRequest, userRepository, passwordRepository, encryptApi).getResponse();
            } else if (aRequest.getAction() == ServerActionsEnum.GET_MARKERS) {
                return new GetMarkersResponse(aRequest, markerRepository).getResponse();
            } else if (aRequest.getAction() == ServerActionsEnum.GET_TIMELINE) {
                return new GetTimelineResponse(aRequest, markerRepository).getResponse();
            } else if (aRequest.getAction() == ServerActionsEnum.UPDATE_MARKER) {
                return new UpdateMarkerResponse(aRequest, markerRepository).getResponse();
            } else if (aRequest.getAction() == ServerActionsEnum.DELETE_MARKER) {
                return new DeleteMarkersResponse(aRequest, markerRepository).getResponse();
            } else if (aRequest.getAction() == ServerActionsEnum.ADD_MARKERS) {
                return new CreateMarkersResponse(aRequest, markerRepository).getResponse();
            } else if (aRequest.getAction() == ServerActionsEnum.GET_USER_INFO_BY_FOURSQUAREID) {
                return new GetUserByFoursquareIdResponse(aRequest, userRepository).getResponse();
            } else if (aRequest.getAction() == ServerActionsEnum.CREATE_USER) {
                return new CreateUserResponse(aRequest, userRepository, passwordRepository).getResponse();
            } else if (aRequest.getAction() == ServerActionsEnum.GET_USER_INFO) {
                return new GetUserResponse(aRequest, userRepository).getResponse();
            } else if (aRequest.getAction() == ServerActionsEnum.UPDATE_USER) {
                return new UpdateUserResponse(aRequest, userRepository, passwordRepository).getResponse();
            } else {
                throw new RequestException("No such action: " + aRequest.getAction());
            }
        } catch (RequestException e) {

            return new ErrorServerResponse(aRequest, e).getResponse();
        }
    }
}
