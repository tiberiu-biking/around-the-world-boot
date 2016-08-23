package com.tpo.world.web.server.impl.response.impl.user;

import com.tpo.world.encrypt.api.Encryptor;
import com.tpo.world.model.entity.PasswordEntity;
import com.tpo.world.model.entity.UserEntity;
import com.tpo.world.persistence.repository.PasswordRepository;
import com.tpo.world.persistence.repository.UserRepository;
import com.tpo.world.web.server.api.request.IServerRequest;
import com.tpo.world.web.server.api.request.RequestConstants;
import com.tpo.world.web.server.api.response.ResponseConstants;
import com.tpo.world.web.server.impl.exceptions.RequestException;
import com.tpo.world.web.server.impl.response.base.AbstractResponse;
import com.tpo.world.web.server.impl.response.base.envelope.IResponseEnvelope;

public class CreateUserResponse extends AbstractResponse {

    private UserEntity newUser;
    private UserRepository userRepository;
    private PasswordRepository passwordRepository;
    private Encryptor encryptor;

    public CreateUserResponse(IServerRequest aRequest, UserRepository userRepository, PasswordRepository passwordRepository, Encryptor encryptor) {
        super(aRequest);
        this.userRepository = userRepository;
        this.passwordRepository = passwordRepository;
        this.encryptor = encryptor;
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
            passwordEntity.setPassword(encryptor.hash(password));
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
