package com.example.registrationsignup.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FormController {

    @GetMapping("/login/forgot_password/reset_password/{tokenReset}")
    public String formreset(Model model, @PathVariable ("tokenReset") String tokenReset){
        model.addAttribute("token", tokenReset );
        System.out.println(tokenReset);
        return "formreset";
    }
}
