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
import org.apache.commons.lang3.StringUtils;

public class UpdateUserResponse extends AbstractServerResponse {

    private final UserRepository userRepository;
    private final PasswordRepository passwordRepository;
    private UserEntity updatedUser;

    public UpdateUserResponse(ServerRequest aRequest, UserRepository userRepository, PasswordRepository passwordRepository) {
        super(aRequest);
        this.userRepository = userRepository;
        this.passwordRepository = passwordRepository;
    }

    @Override
    public void doRequest() throws RequestException {
        UserEntity user = getRequest().getDto(UserEntity.class);
        updatedUser = userRepository.saveAndFlush(user);

        String newPassword = getRequest().getString(Constants.PASSWORD);
        if (!StringUtils.isEmpty(newPassword)) {
            PasswordEntity password = new PasswordEntity();
            password.setPassword(HashUtil.getHash(newPassword));
            password.setUserId(updatedUser.getId());
            passwordRepository.saveAndFlush(password);
        }
    }

    @Override
    public void buildResponseEnvelope(ResponseEnvelope aResponseEnvelope) {
        aResponseEnvelope.addData(Constants.MESSAGE, "Update successful");
        aResponseEnvelope.addData(Constants.FIRST_NAME, updatedUser.getFirstName());
    }

}
