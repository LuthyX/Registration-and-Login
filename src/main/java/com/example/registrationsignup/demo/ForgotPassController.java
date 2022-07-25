package com.example.registrationsignup.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class ForgotPassController {
        @GetMapping("/login/forgot_password")
        public String forgotpassword(){
            return "forgotpassword";
        }

}
