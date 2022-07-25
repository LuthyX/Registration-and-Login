package com.example.registrationsignup.demo.Registration;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {


    private RegistrationService registrationService;



    @PostMapping
    public String register(
            @RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }

    @GetMapping(path = "/confirm")
    public String confirm(
            @RequestParam("token") String token
    ){
        return registrationService.confirmToken(token);
    }

    @GetMapping(path= "/emailforgotpassword")
    public String emailForgotPassword(@RequestParam String email){
        return registrationService.sendEmailToken(email);
    }

    @PostMapping(path = "/passwordreset")
    public String passwordReset(HttpServletRequest request){
        String tokenReset = request.getParameter("token");
        String password = request.getParameter("password");
        System.out.println(tokenReset);
        System.out.println(password);
        return registrationService.resetPassword(password, tokenReset);
    }

}
