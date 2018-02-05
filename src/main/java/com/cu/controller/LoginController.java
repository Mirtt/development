package com.cu.controller;

import com.cu.model.User;
import com.cu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

/**
 * 登录控制
 *
 * @authur 99689
 * @create 2017/8/25.
 */
@Controller
@RequestMapping("")
@SessionAttributes("user_id")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String gettest() {
        return "test";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("current_user");
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, HttpSession session, Model model) {
        model.addAttribute("user_name", user.getUser_name());
        model.addAttribute("user_id", user.getId());
        user = userService.getUser(user.getUser_name(), user.getPassword());
        if (user != null) {
            model.addAttribute("user", user);
            session.setAttribute("current_user", user);//在session中存储user信息
            return "redirect:/query/";
        }
        String msg = "用户名或密码错误";
        model.addAttribute("msg", msg);
        return "index";
    }


}
