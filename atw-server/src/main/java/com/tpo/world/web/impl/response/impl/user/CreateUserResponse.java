package com.tpo.world.web.impl.response.impl.user;

import com.tpo.world.core.encrypt.util.hash.HashUtil;
import com.tpo.world.persistence.entity.PasswordEntity;
import com.tpo.world.persistence.entity.UserEntity;
import com.tpo.world.persistence.repository.PasswordRepository;
import com.tpo.world.persistence.repository.UserRepository;
import com.tpo.world.web.api.request.IServerRequest;
import com.tpo.world.web.api.request.RequestConstants;
import com.tpo.world.web.api.response.ResponseConstants;
import com.tpo.world.web.impl.exceptions.RequestException;
import com.tpo.world.web.impl.response.base.AbstractResponse;
import com.tpo.world.web.impl.response.base.envelope.IResponseEnvelope;

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
