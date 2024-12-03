package com.tdd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/user")
public class UserController {


    @GetMapping(value = "/{id}")
    @ResponseBody
    public String getById(@PathVariable Long id, HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        if (id == null)
            return "failed";
        return "Tom";
    }

    @GetMapping(value = "/getByName")
    @ResponseBody
    public String getByName(String name, HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        return name;
    }

}
