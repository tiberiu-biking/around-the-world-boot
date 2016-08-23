package com.tpo.world.web.server.impl.response.impl.user;

import com.tpo.world.model.entity.UserEntity;
import com.tpo.world.persistence.repository.UserRepository;
import com.tpo.world.web.server.api.request.IServerRequest;
import com.tpo.world.web.server.api.request.RequestConstants;
import com.tpo.world.web.server.api.response.ResponseConstants;
import com.tpo.world.web.server.impl.exceptions.RequestException;
import com.tpo.world.web.server.impl.response.base.AbstractResponse;
import com.tpo.world.web.server.impl.response.base.envelope.IResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetUserByFoursquareIdResponse extends AbstractResponse {

    private static final Logger logger = LoggerFactory.getLogger(GetUserByFoursquareIdResponse.class);

    private UserEntity user;
    private UserRepository userRepository;

    public GetUserByFoursquareIdResponse(IServerRequest aRequest, UserRepository userRepository) {
        super(aRequest);
        this.userRepository = userRepository;
    }

    @Override
    public void doRequest() throws RequestException {
        String userFoursquareId = getRequest().getString(RequestConstants.USER_FOURSQUARE_ID);
        logger.info("Get info for user with foursquare id: " + userFoursquareId);
        user = userRepository.findByFoursquareId(userFoursquareId);
        logger.info("User found: " + user);
    }

    @Override
    public void buildResponseEnvelope(IResponseEnvelope aEnvelope) {
        aEnvelope.addData(ResponseConstants.USER, user);
    }

}
