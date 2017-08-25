package com.cu.controller;

import com.cu.model.User;
import com.cu.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * 登录控制
 *
 * @authur 99689
 * @create 2017/8/25.
 */
@Controller
@RequestMapping("")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, Model model){
        user=userService.getUser(user.getAccount(),user.getPassword());
        if (user!=null){
            model.addAttribute(user);
            return "success";
        }
        return "fail";
    }
}
