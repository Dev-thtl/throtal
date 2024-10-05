package com.throtl.user.service.implementation;

import com.throtl.user.controller.AuthController;
import com.throtl.user.entity.UserProfile;
import com.throtl.user.repository.UserProfileRepository;
import com.throtl.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImplementation implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

    @Autowired
    UserProfileRepository userProfileRepository;
    @Override
    public Long findLoggedInCustomerId() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (null != auth) {
                UserProfile user = userProfileRepository.getUserProfileByPhoneNumber(auth.getName());
                if (null != user) {
                    /* return user.getOid(); */
                    return user.getUserId();
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
//            logger.error("", e);
        }

        return null;
    }


}
