package com.icwd.serviceregistry.scm20.Config;

import com.icwd.serviceregistry.scm20.Repository.UserRepo;
import com.icwd.serviceregistry.scm20.entities.Providers;
import com.icwd.serviceregistry.scm20.entities.User;
import com.icwd.serviceregistry.scm20.helpers.AppConstants;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{
    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);
    @Autowired
    private UserRepo userRepo;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        logger.info("OAuthAuthenticationSuccessHandler.onAuthenticationSuccess()");

        var oauth2AuthenicationToken = (OAuth2AuthenticationToken) authentication;
        String authorizedClientRegistrationId = oauth2AuthenicationToken.getAuthorizedClientRegistrationId();
        logger.info(authorizedClientRegistrationId);
        var oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
        oauthUser.getAttributes().forEach((key, value) -> {
            logger.info(key + " : " + value);
        });
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        user.setEmailVerified(true);
        user.setEnabled(true);
        user.setPassword("dummy");

        if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {

            // google
            // google attributes

            user.setEmail(oauthUser.getAttribute("email").toString());
            user.setProfilePic(oauthUser.getAttribute("picture").toString());
            user.setName(oauthUser.getAttribute("name").toString());
            user.setProviderUserId(oauthUser.getName());
            user.setProvider(Providers.GOOGLE);
            user.setAbout("This account is created using google.");

        } else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {

            // github
            // github attributes
            String email = oauthUser.getAttribute("email") != null ? oauthUser.getAttribute("email").toString()
                    : oauthUser.getAttribute("login").toString() + "@gmail.com";
            String picture = oauthUser.getAttribute("avatar_url").toString();
            String name = oauthUser.getAttribute("login").toString();
            String providerUserId = oauthUser.getName();

            user.setEmail(email);
            user.setProfilePic(picture);
            user.setName(name);
            user.setProviderUserId(providerUserId);
            user.setProvider(Providers.GITHUB);

            user.setAbout("This account is created using github");
        }
        else {
            logger.info("OAuthAuthenicationSuccessHandler: Unknown provider");
        }
        User user2 = userRepo.findByEmail(user.getEmail()).orElse(null);
        if (user2 == null) {
            userRepo.save(user);
            System.out.println("user saved:" + user.getEmail());
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }
}
