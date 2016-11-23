package com.mmashyr.staffmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mark on 23.11.2016.
 */
@Controller
@RequestMapping("/")
public class IndexController
{

    @RequestMapping(method= RequestMethod.GET)
    public String index()
    {
        return "index";
    }

}