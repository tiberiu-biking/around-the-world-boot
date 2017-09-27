package com.tpo.world.web.impl.response.impl.user;

import com.tpo.world.persistence.entity.UserEntity;
import com.tpo.world.persistence.repository.UserRepository;
import com.tpo.world.web.api.request.IServerRequest;
import com.tpo.world.web.api.request.RequestConstants;
import com.tpo.world.web.api.response.ResponseConstants;
import com.tpo.world.web.impl.exceptions.RequestException;
import com.tpo.world.web.impl.response.base.AbstractResponse;
import com.tpo.world.web.impl.response.base.envelope.IResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetUserResponse extends AbstractResponse {

    private static final Logger logger = LoggerFactory.getLogger(GetUserResponse.class);

    private final UserRepository userRepository;
    private UserEntity user;

    public GetUserResponse(IServerRequest aRequest, UserRepository userRepository) {
        super(aRequest);
        this.userRepository = userRepository;
    }

    @Override
    public void doRequest() throws RequestException {
        long userId = getRequest().getLong(RequestConstants.USER_ID);
        logger.info("Get info for user: " + userId);

        user = userRepository.getOne(userId);
        logger.info("User found: " + user);
    }

    @Override
    public void buildResponseEnvelope(IResponseEnvelope aEnvelope) {
        aEnvelope.addData(ResponseConstants.USER, user);
    }

}
