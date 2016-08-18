package master.pam.server.impl.response.impl.user;

import com.master.pam.encrypt.util.hash.HashUtil;
import com.tpo.world.domain.entity.PasswordEntity;
import com.tpo.world.domain.entity.UserEntity;
import com.tpo.world.persistence.repository.PasswordRepository;
import com.tpo.world.persistence.repository.UserRepository;
import master.pam.server.api.request.IServerRequest;
import master.pam.server.api.request.RequestConstants;
import master.pam.server.api.response.ResponseConstants;
import master.pam.server.impl.exceptions.RequestException;
import master.pam.server.impl.response.base.AbstractResponse;
import master.pam.server.impl.response.base.envelope.IResponseEnvelope;

public class CreateUserResponse extends AbstractResponse {

    private UserEntity newUser;
    private UserRepository userRepository;
    private PasswordRepository passwordRepository;

    public CreateUserResponse(IServerRequest aRequest, UserRepository userRepository, PasswordRepository passwordRepository) {
        super(aRequest);
        this.userRepository = userRepository;
        this.passwordRepository = passwordRepository;
    }

    @Override
    public void doRequest() throws RequestException {
        UserEntity user = getRequest().getDto(UserEntity.class);
        UserEntity existingUser = userRepository.findByEmailIgnoreCase(user.getEmail());
        if (existingUser == null) {
            newUser = userRepository.saveAndFlush(user);

            String password = getRequest().getString(RequestConstants.PASSWORD);
            PasswordEntity passwordEntity = new PasswordEntity();
            passwordEntity.setUserId(newUser.getId());
            passwordEntity.setPassword(HashUtil.getHash(password));
            passwordRepository.saveAndFlush(passwordEntity);
        }
    }

    @Override
    public void buildResponseEnvelope(IResponseEnvelope aResponseEnvelope) {
        if (newUser != null) {
            aResponseEnvelope.addData(ResponseConstants.USER_ID, newUser.getId());
        } else {
            aResponseEnvelope.setErrors("Someone already has registered with this email");
        }

    }

}
