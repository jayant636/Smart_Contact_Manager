package com.scm.config;

import com.scm.entity.UserEntity;
import com.scm.enums.Providers;
import com.scm.helper.AppConstants;
import com.scm.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
       DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();

       String email = user.getAttribute("email").toString();
       String name = user.getAttribute("name").toString();
       String picture = user.getAttribute("picture").toString();

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setName(name);
        userEntity.setProfilePic(picture);
        userEntity.setPassword("password");
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setProvider(Providers.GOOGLE);
        userEntity.setEnabled(true);
        userEntity.setEmailVerified(true);
        userEntity.setProviderUserId(userEntity.getName());
        userEntity.setRoleList(List.of(AppConstants.ROLE_USER));
        userEntity.setAbout("This account is created using Google");

       UserEntity userEntity1=  userRepository.findByEmail(email).orElse(null);
        if(userEntity1 == null){
            userRepository.save(userEntity);
        }

       new DefaultRedirectStrategy().sendRedirect(request,response,"/user/profile");
    }
}
