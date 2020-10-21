package com.dell.springboot.controller;

import com.dell.springboot.entities.User;
import com.dell.springboot.service.LoginService;
import com.dell.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    LoginService ls;
    @Autowired
    UserService us;
    @RequestMapping(value = {"/","/random"})
    public String random(Model model){
        List<User> list = us.getAll();
        model.addAttribute("choice",list.get(list.size()-1));
        list.remove(list.size()-1);
        model.addAttribute("users",list);
//        System.out.println("有人访问");
//        System.out.println(new Date());
        return "randomCall";
    }
}
