package com.icwd.serviceregistry.scm20.controllers;

import ch.qos.logback.core.model.Model;
import com.icwd.serviceregistry.scm20.helpers.Helper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController
{
    @RequestMapping("/dashboard")
    public String userDashboard()
    {
        System.out.println("User dashboard");
        return "user/dashboard";
    }
    @RequestMapping("/profile")
    public String userProfile(Model model, Authentication authentication)
    {
        String username = Helper.getEmailOfLoggedInUser(authentication);
        //get user from db
        System.out.println("User profile");
        return "user/profile";
    }

}
