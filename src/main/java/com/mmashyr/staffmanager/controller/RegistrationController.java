package com.mmashyr.staffmanager.controller;

import com.mmashyr.staffmanager.model.Role;
import com.mmashyr.staffmanager.model.User;
import com.mmashyr.staffmanager.model.UserRoleType;
import com.mmashyr.staffmanager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Mark on 12.11.2016.
 */
@Controller
public class RegistrationController {

    @Autowired
    @Qualifier("userService")
    UserService userService;


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(Model model){
        User user = new User();
        Role userRole = new Role();
        userRole.setRole(UserRoleType.USER);
        user.getRoles().add(userRole);
        model.addAttribute("user", user);

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationForm(@Valid @ModelAttribute("user") User user, BindingResult result){

        if(result.hasErrors()){
            return "registration";
        }
        System.out.println(user.getLogin() + "!!" + user.getPassword() + "!!" + user.getRoles().size());
        userService.add(user);
        System.out.println("after saving");
        return "index";
    }


}
