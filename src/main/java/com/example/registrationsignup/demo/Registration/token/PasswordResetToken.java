package com.example.registrationsignup.demo.Registration.token;

import com.example.registrationsignup.demo.AppUser.AppUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PasswordResetToken {
    @Id
    @SequenceGenerator(
            name = "password_reset_sequence",
            sequenceName = "password_reset_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "password_reset_sequence"
    )
    private Long id;
    private String tokenReset;
    @Column(
            nullable = false
    )
    private LocalDateTime createdAt;
    @Column(
            nullable = false
    )
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;

    public PasswordResetToken(String tokenReset,
                              LocalDateTime createdAt,
                              LocalDateTime expiresAt,
                              AppUser appUser) {
        this.tokenReset = tokenReset;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }
}
