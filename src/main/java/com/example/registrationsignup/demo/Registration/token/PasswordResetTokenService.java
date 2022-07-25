package com.example.registrationsignup.demo.Registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PasswordResetTokenService {
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public void saveTokenReset(PasswordResetToken passwordResetToken){
        passwordResetTokenRepository.save(passwordResetToken);
    }

    public Optional<PasswordResetToken> getTokenReset(String tokenReset) {
        return passwordResetTokenRepository.findByTokenReset(tokenReset);
    }
}
