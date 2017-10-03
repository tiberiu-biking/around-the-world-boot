package com.tpo.world.web.impl.response.impl.user;

import com.tpo.world.model.exceptions.RequestException;
import com.tpo.world.persistence.entity.UserEntity;
import com.tpo.world.persistence.repository.UserRepository;
import com.tpo.world.web.api.ServerRequest;
import com.tpo.world.web.constants.Constants;
import com.tpo.world.web.impl.response.base.AbstractServerResponse;
import com.tpo.world.web.impl.response.base.envelope.ResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetUserByFoursquareIdResponse extends AbstractServerResponse {

    private static final Logger logger = LoggerFactory.getLogger(GetUserByFoursquareIdResponse.class);

    private UserEntity user;
    private UserRepository userRepository;

    public GetUserByFoursquareIdResponse(ServerRequest aRequest, UserRepository userRepository) {
        super(aRequest);
        this.userRepository = userRepository;
    }

    @Override
    public void doRequest() throws RequestException {
        String userFoursquareId = getRequest().getString(Constants.USER_FOURSQUARE_ID);
        logger.info("Get info for user with foursquare id: " + userFoursquareId);
        user = userRepository.findByFoursquareId(userFoursquareId);
        logger.info("User found: " + user);
    }

    @Override
    public void buildResponseEnvelope(ResponseEnvelope aEnvelope) {
        aEnvelope.addData(Constants.USER, user);
    }

}
