package com.mmashyr.staffmanager.controller;

import com.mmashyr.staffmanager.model.Role;
import com.mmashyr.staffmanager.model.User;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    @ModelAttribute
    public void addRoleNamesToModel(Model model) {
        List<Role> roles = roleService.getAll();
        List<String> roleNames = new ArrayList<>();
        roles.forEach(role -> roleNames.add(role.getType().name()));

        model.addAttribute("roleNames", roleNames);
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationForm(@RequestParam("chosenRole") String chosenRole, @Valid @ModelAttribute("user") User user,  BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }
        user.getRoles().add(roleService.findByType(chosenRole));
        userService.add(user);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

}
