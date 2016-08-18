package master.pam.server.impl.response.impl.user;

import com.tpo.world.domain.entity.UserEntity;
import com.tpo.world.persistence.repository.UserRepository;
import master.pam.server.api.request.IServerRequest;
import master.pam.server.api.request.RequestConstants;
import master.pam.server.api.response.ResponseConstants;
import master.pam.server.impl.exceptions.RequestException;
import master.pam.server.impl.response.base.AbstractResponse;
import master.pam.server.impl.response.base.envelope.IResponseEnvelope;
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
