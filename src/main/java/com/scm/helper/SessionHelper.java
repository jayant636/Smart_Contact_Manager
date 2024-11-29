package com.scm.helper;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

@Component
public class SessionHelper {

    public static  void removeMessage(){
       try{
           HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
           session.removeAttribute("message");
       }catch(Exception e){
            System.out.println("Error in session helper"+e);
            e.printStackTrace();
       }

    }
}
