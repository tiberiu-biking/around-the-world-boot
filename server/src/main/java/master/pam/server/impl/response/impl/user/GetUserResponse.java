package master.pam.server.impl.response.impl.user;

import com.tpo.world.model.entity.UserEntity;
import com.tpo.world.persistence.repository.UserRepository;
import master.pam.server.api.request.IServerRequest;
import master.pam.server.api.request.RequestConstants;
import master.pam.server.api.response.ResponseConstants;
import master.pam.server.impl.exceptions.RequestException;
import master.pam.server.impl.response.base.AbstractResponse;
import master.pam.server.impl.response.base.envelope.IResponseEnvelope;
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
