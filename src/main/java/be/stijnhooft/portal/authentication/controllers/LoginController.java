package be.stijnhooft.portal.authentication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(name="redirectTo", required=false, defaultValue="http://portal.stijnhooft.be") String redirectTo, Model model) {
        model.addAttribute("redirectTo", redirectTo);
        return "login";
    }

}
