package com.tpo.world.web.impl.response.impl.user;

import com.tpo.world.model.exceptions.RequestException;
import com.tpo.world.persistence.entity.UserEntity;
import com.tpo.world.persistence.repository.PasswordRepository;
import com.tpo.world.persistence.repository.UserRepository;
import com.tpo.world.services.encrypt.EncryptService;
import com.tpo.world.web.api.ServerRequest;
import com.tpo.world.web.constants.Constants;
import com.tpo.world.web.impl.response.base.AbstractServerResponse;
import com.tpo.world.web.impl.response.base.envelope.ResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignInResponse extends AbstractServerResponse {

    private final Logger logger = LoggerFactory.getLogger(SignInResponse.class);

    private UserRepository userRepository;
    private UserEntity userEntity;
    private PasswordRepository passwordRepository;
    private EncryptService encryptApi;

    public SignInResponse(ServerRequest aRequest, UserRepository userRepository, PasswordRepository passwordRepository, EncryptService encryptApi) {
        super(aRequest);
        this.userRepository = userRepository;
        this.passwordRepository = passwordRepository;
        this.encryptApi = encryptApi;
    }

    @Override
    public void doRequest() throws RequestException {
        String username = getRequest().getString(Constants.USERNAME);
        String password = getRequest().getString(Constants.PASSWORD);
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
    public void buildResponseEnvelope(ResponseEnvelope aEnvelope) {
        if (userEntity == null) {
            aEnvelope.setErrors("User/password incorrect!");
        } else {
            aEnvelope.addData(Constants.USER_ID, userEntity.getId());
            aEnvelope.addData(Constants.FIRST_NAME, userEntity.getFirstName());
            aEnvelope.addData(Constants.DROPBOX_TOKEN, userEntity.getDropboxToken());
            aEnvelope.addData(Constants.FOURSQUARE_TOKEN, userEntity.getFoursquareToken());
        }
    }

}
