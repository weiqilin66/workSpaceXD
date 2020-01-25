package com.lwq.springsecurity.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: LinWeiQi
 */
@Service
public class SecurityService {

    @PreAuthorize("hasRole('admin')")
    public String admin(){
        String resStr = "";
        resStr = "hello admin ";
        return resStr;
    }

   @Secured("ROLE_user")  // 和admin()方法注解类似
   public String user(){

       return "hello user";
   }

   @PreAuthorize("hasAnyRole('admin','user')")
   public String hello(){

       return "hello security";
   }
}
