package com.mmashyr.staffmanager.controller;

import com.mmashyr.staffmanager.model.Role;
import com.mmashyr.staffmanager.model.User;
import com.mmashyr.staffmanager.model.UserRoleType;
import com.mmashyr.staffmanager.services.RoleService;
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
import javax.xml.bind.SchemaOutputResolver;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Mark on 12.11.2016.
 */
@Controller
public class RegistrationController {

    @Autowired
    @Qualifier("userService")
    UserService userService;

    @Autowired
    @Qualifier("roleService")
    RoleService roleService;


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(Model model){
        User user = new User();
        model.addAttribute("user", user);

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationForm(@Valid @ModelAttribute("user") User user, BindingResult result){
        System.out.println("Inside");
        if(result.hasErrors()){
            System.out.println("ERRORS : !!!!!!!!!!");
            result.getFieldErrors().stream().forEach(System.out::println);
            return "registration";
        }
        Role userRole = new Role();
        userRole.setRole(UserRoleType.USER);
        user.getRoles().add(userRole);
        roleService.add(userRole);


        System.out.println(user.getLogin() + "!!" + user.getPassword() + "!!" + user.getRoles().size());
        userService.add(user);
        System.out.println(userService.findByLogin("123456").getPassword());
        System.out.println("after saving");
        return "index";
    }


}
