package com.tovito.backend.security;

import com.tovito.backend.entity.UserEntity;
import com.tovito.backend.model.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
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
        //получаем пароль, но не совсем понятно как запихиваем в юзера
        UserEntity user = (UserEntity) authentication.getPrincipal();
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("TovitoApp")
                .issuedAt(now)
                .expiresAt(now.plus(30, ChronoUnit.SECONDS))
                .subject(String.valueOf(user.getUser_id()))     //эта штука идентифицирует пароль, т.е тут идентифицируем пароль по айди
                .build();

        return accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    private String createRefreshToken(Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("TovitoApp")
                .issuedAt(now)
                .expiresAt(now.plus(90, ChronoUnit.SECONDS))
                .subject(String.valueOf(user.getUser_id()))
                .build();

        return refreshTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    //не, это говно какое=то, по новой разобрать надо
    public TokenModel createToken(Authentication authentication) {
        if (!(authentication.getPrincipal() instanceof UserEntity user)) {
            throw new BadCredentialsException(
                    MessageFormat.format("principal {0} is not of UserEntity type", authentication.getPrincipal().getClass())
            );
        }

        TokenModel tokenDTO = new TokenModel();
        tokenDTO.setUserId(user.getUser_id());
        tokenDTO.setAccessToken(createAccessToken(authentication));

        String refreshToken;
        if (authentication.getCredentials() instanceof Jwt jwt) {       //разобраться почему НЕ ПАРОЛЬ хранится в jwt, точнее найти где я это делаю)))
            Instant now = Instant.now();                                //какого лешего вообще название переменной указывается, как оно понимает че подтягивать
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
        tokenDTO.setRefreshToken(refreshToken);

        return tokenDTO;
    }
}
