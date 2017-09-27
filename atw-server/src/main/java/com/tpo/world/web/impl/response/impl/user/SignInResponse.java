package com.tpo.world.web.impl.response.impl.user;

import com.tpo.world.encrypt.api.EncryptService;
import com.tpo.world.persistence.entity.UserEntity;
import com.tpo.world.persistence.repository.PasswordRepository;
import com.tpo.world.persistence.repository.UserRepository;
import com.tpo.world.web.api.request.IServerRequest;
import com.tpo.world.web.api.request.RequestConstants;
import com.tpo.world.web.api.response.ResponseConstants;
import com.tpo.world.web.impl.exceptions.RequestException;
import com.tpo.world.web.impl.response.base.AbstractResponse;
import com.tpo.world.web.impl.response.base.envelope.IResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignInResponse extends AbstractResponse {

    private final Logger logger = LoggerFactory.getLogger(SignInResponse.class);

    private UserRepository userRepository;
    private UserEntity userEntity;
    private PasswordRepository passwordRepository;
    private EncryptService encryptApi;

    public SignInResponse(IServerRequest aRequest, UserRepository userRepository, PasswordRepository passwordRepository, EncryptService encryptApi) {
        super(aRequest);
        this.userRepository = userRepository;
        this.passwordRepository = passwordRepository;
        this.encryptApi = encryptApi;
    }

    @Override
    public void doRequest() throws RequestException {
        String username = getRequest().getString(RequestConstants.USERNAME);
        String password = getRequest().getString(RequestConstants.PASSWORD);
        String hashedPassword = encryptApi.hash(password);

        logger.info("Try to login user: " + username + "/" + password + "/" + hashedPassword);

        userEntity = userRepository.findByEmailIgnoreCase(username);

        if (userEntity == null) {
            logger.info("User incorrect!");
        } else {
            logger.info("User found");
//            PasswordEntity passwordEntity = passwordRepository.findByUserId(userEntity.getId());
//
//            if (!hashedPassword.equals(passwordEntity.getPassword())) {
//                userEntity = null;
//                logger.trace("Password incorrect!");
//            }
        }
        logger.info("User found: " + userEntity);
    }

    @Override
    public void buildResponseEnvelope(IResponseEnvelope aEnvelope) {
        if (userEntity == null) {
            aEnvelope.setErrors("User/password incorrect!");
        } else {
            aEnvelope.addData(ResponseConstants.USER_ID, userEntity.getId());
            aEnvelope.addData(ResponseConstants.FIRST_NAME, userEntity.getFirstName());
            aEnvelope.addData(ResponseConstants.DROPBOX_TOKEN, userEntity.getDropboxToken());
            aEnvelope.addData(ResponseConstants.FOURSQUARE_TOKEN, userEntity.getFoursquareToken());
        }
    }

}
