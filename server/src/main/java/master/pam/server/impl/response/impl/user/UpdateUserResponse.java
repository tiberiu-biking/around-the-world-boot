package master.pam.server.impl.response.impl.user;

import com.master.pam.encrypt.api.Encryptor;
import com.tpo.world.model.entity.PasswordEntity;
import com.tpo.world.model.entity.UserEntity;
import com.tpo.world.persistence.repository.PasswordRepository;
import com.tpo.world.persistence.repository.UserRepository;
import master.pam.server.api.request.IServerRequest;
import master.pam.server.api.request.RequestConstants;
import master.pam.server.api.response.ResponseConstants;
import master.pam.server.impl.exceptions.RequestException;
import master.pam.server.impl.response.base.AbstractResponse;
import master.pam.server.impl.response.base.envelope.IResponseEnvelope;
import org.apache.commons.lang3.StringUtils;

public class UpdateUserResponse extends AbstractResponse {

    private UserRepository userRepository;
    private PasswordRepository passwordRepository;
    private Encryptor encryptor;
    private UserEntity updatedUser;

    public UpdateUserResponse(IServerRequest aRequest, UserRepository userRepository, PasswordRepository passwordRepository, Encryptor encryptor) {
        super(aRequest);
        this.userRepository = userRepository;
        this.passwordRepository = passwordRepository;
        this.encryptor = encryptor;
    }

    @Override
    public void doRequest() throws RequestException {
        UserEntity user = getRequest().getDto(UserEntity.class);
        updatedUser = userRepository.saveAndFlush(user);

        String newPassword = getRequest().getString(RequestConstants.PASSWORD);
        if (!StringUtils.isEmpty(newPassword)) {
            PasswordEntity password = new PasswordEntity();
            password.setPassword(encryptor.hash(newPassword));
            password.setUserId(updatedUser.getId());
            passwordRepository.saveAndFlush(password);
        }
    }

    @Override
    public void buildResponseEnvelope(IResponseEnvelope aResponseEnvelope) {
        aResponseEnvelope.addData(ResponseConstants.MESSAGE, "Update successful");
        aResponseEnvelope.addData(ResponseConstants.FIRST_NAME, updatedUser.getFirstName());
    }

}
