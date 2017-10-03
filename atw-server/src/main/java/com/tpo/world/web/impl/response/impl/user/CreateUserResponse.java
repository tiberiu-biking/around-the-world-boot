package com.tpo.world.web.impl.response.impl.user;

import com.tpo.world.model.exceptions.RequestException;
import com.tpo.world.persistence.entity.PasswordEntity;
import com.tpo.world.persistence.entity.UserEntity;
import com.tpo.world.persistence.repository.PasswordRepository;
import com.tpo.world.persistence.repository.UserRepository;
import com.tpo.world.services.encrypt.util.hash.HashUtil;
import com.tpo.world.web.api.ServerRequest;
import com.tpo.world.web.constants.Constants;
import com.tpo.world.web.impl.response.base.AbstractServerResponse;
import com.tpo.world.web.impl.response.base.envelope.ResponseEnvelope;

public class CreateUserResponse extends AbstractServerResponse {

    private UserEntity newUser;
    private UserRepository userRepository;
    private PasswordRepository passwordRepository;

    public CreateUserResponse(ServerRequest aRequest, UserRepository userRepository, PasswordRepository passwordRepository) {
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

            String password = getRequest().getString(Constants.PASSWORD);
            PasswordEntity passwordEntity = new PasswordEntity();
            passwordEntity.setUserId(newUser.getId());
            passwordEntity.setPassword(HashUtil.getHash(password));
            passwordRepository.saveAndFlush(passwordEntity);
        }
    }

    @Override
    public void buildResponseEnvelope(ResponseEnvelope aResponseEnvelope) {
        if (newUser != null) {
            aResponseEnvelope.addData(Constants.USER_ID, newUser.getId());
        } else {
            aResponseEnvelope.setErrors("Someone already has registered with this email");
        }

    }

}
