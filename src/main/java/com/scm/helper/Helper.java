package com.scm.helper;

import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;

import java.security.Principal;

public class Helper {

    public static String getEmailOfLoggedInUser(Principal principal){

        if(principal instanceof OAuth2AuthenticatedPrincipal){

        }else{
            return principal.getName();
        }

        return "";
    }
}
