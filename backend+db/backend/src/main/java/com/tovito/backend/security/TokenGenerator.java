package com.tovito.backend.security;

import com.tovito.backend.entity.UserEntity;
import com.tovito.backend.model.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class TokenGenerator {
    @Autowired
    JwtEncoder accessTokenEncoder;

    @Autowired
    @Qualifier("jwtRefreshTokenEncoder")
    JwtEncoder refreshTokenEncoder;

    private String createAccessToken(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("TovitoApp")
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.SECONDS))
                .subject(user.getUsername())
                .build();

        return accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    private String createRefreshToken(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("TovitoApp")
                .issuedAt(now)
                .expiresAt(now.plus(120, ChronoUnit.SECONDS))
                .subject(user.getUsername())
                .build();

        return refreshTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    public TokenModel createToken(Authentication authentication) {
        if (!(authentication.getPrincipal() instanceof UserDetails user)) {  //implicit cast - неявное приведение
                throw new BadCredentialsException(
                        MessageFormat.format("principal {0} is not of UserDetails type", authentication.getPrincipal().getClass())
                );
        }

        TokenModel tokenModel = new TokenModel();
        tokenModel.setUserEmail(user.getUsername());
        tokenModel.setAccessToken(createAccessToken(authentication));

        String refreshToken;
        if (authentication.getCredentials() instanceof Jwt jwt) {           //implicit cast - неявное приведение
            Instant now = Instant.now();
            Instant expiresAt = jwt.getExpiresAt();
            Duration duration = Duration.between(now, expiresAt);
            long secondsUntilExpired = duration.toSeconds();
            if (secondsUntilExpired < 15) {
                refreshToken = createRefreshToken(authentication);
            } else {
                refreshToken = jwt.getTokenValue();
            }
        } else {
            refreshToken = createRefreshToken(authentication);
        }
        tokenModel.setRefreshToken(refreshToken);

        return tokenModel;
    }
}
