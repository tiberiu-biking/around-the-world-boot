package com.tpo.world.web.impl.request;

import com.tpo.world.model.exceptions.RequestException;
import com.tpo.world.persistence.repository.MarkerRepository;
import com.tpo.world.persistence.repository.PasswordRepository;
import com.tpo.world.persistence.repository.UserRepository;
import com.tpo.world.services.encrypt.EncryptService;
import com.tpo.world.web.api.ServerRequest;
import com.tpo.world.web.domain.ServerAction;
import com.tpo.world.web.impl.response.base.envelope.ResponseEnvelope;
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

    public ResponseEnvelope createResult(ServerRequest aRequest) {

        try {
            log.debug("Create result from factory for request: " + aRequest.getAction());

            if (aRequest.getAction() == ServerAction.SIGN_IN) {
                return new SignInResponse(aRequest, userRepository, passwordRepository, encryptApi).getResponse();
            } else if (aRequest.getAction() == ServerAction.GET_MARKERS) {
                return new GetMarkersResponse(aRequest, markerRepository).getResponse();
            } else if (aRequest.getAction() == ServerAction.GET_TIMELINE) {
                return new GetTimelineResponse(aRequest, markerRepository).getResponse();
            } else if (aRequest.getAction() == ServerAction.UPDATE_MARKER) {
                return new UpdateMarkerResponse(aRequest, markerRepository).getResponse();
            } else if (aRequest.getAction() == ServerAction.DELETE_MARKER) {
                return new DeleteMarkersResponse(aRequest, markerRepository).getResponse();
            } else if (aRequest.getAction() == ServerAction.ADD_MARKERS) {
                return new CreateMarkersResponse(aRequest, markerRepository).getResponse();
            } else if (aRequest.getAction() == ServerAction.GET_USER_INFO_BY_FOURSQUAREID) {
                return new GetUserByFoursquareIdResponse(aRequest, userRepository).getResponse();
            } else if (aRequest.getAction() == ServerAction.CREATE_USER) {
                return new CreateUserResponse(aRequest, userRepository, passwordRepository).getResponse();
            } else if (aRequest.getAction() == ServerAction.GET_USER_INFO) {
                return new GetUserResponse(aRequest, userRepository).getResponse();
            } else if (aRequest.getAction() == ServerAction.UPDATE_USER) {
                return new UpdateUserResponse(aRequest, userRepository, passwordRepository).getResponse();
            } else {
                throw new RequestException("No such action: " + aRequest.getAction());
            }
        } catch (RequestException e) {

            return new ErrorServerResponse(aRequest, e).getResponse();
        }
    }
}
