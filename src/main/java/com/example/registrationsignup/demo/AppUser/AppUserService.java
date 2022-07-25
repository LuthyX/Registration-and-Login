package com.example.registrationsignup.demo.AppUser;

import com.example.registrationsignup.demo.Registration.token.ConfirmationToken;
import com.example.registrationsignup.demo.Registration.token.ConfirmationTokenService;
import com.example.registrationsignup.demo.Registration.token.PasswordResetToken;
import com.example.registrationsignup.demo.Registration.token.PasswordResetTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private AppUserRepository appUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private PasswordResetTokenService passwordResetTokenService;

    private final ConfirmationTokenService confirmationTokenService;
    private final static String userNotFound_Message = "User with email %s does not exist";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       return appUserRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(String.format(userNotFound_Message, email)));
    }
    public String signUp(AppUser appUser){
        boolean exists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if( exists ) {
            throw new IllegalStateException("Email already exists. Try another email");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(30),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;

    }
    public int enableUser(String email){
        return appUserRepository.enableAppUser(email);
    }


//
//    @Transactional
//    public void enableUser(String email){
//        AppUser user = appUserRepository.findByEmail(email).orElseThrow(()->new IllegalStateException("Does not exist"));
//        user.setEnabled(true);
//    }

    public String sendEmailToken(String email) {
        AppUser user = appUserRepository.findByEmail(email).orElseThrow(()-> new IllegalStateException("Email does not exist"));

        String tokenReset = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken(
                tokenReset,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(5),
                user
        );
        passwordResetTokenService.saveTokenReset(passwordResetToken);
        return tokenReset;

    }

}
