package org.scoula.security.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.security.account.mapper.UserDetailsMapper;
import org.scoula.security.dto.CustomUser;
import org.scoula.security.dto.AuthResultDTO;
import org.scoula.security.dto.RefreshTokenDTO;
import org.scoula.security.dto.UserInfoDTO;
import org.scoula.security.util.JsonResponse;
import org.scoula.security.util.JwtProcessor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtProcessor jwtProcessor;
    private final UserDetailsMapper mapper;

    private AuthResultDTO makeAuthResult(CustomUser user) {
        String username = user.getUsername();
        String accesstoken = jwtProcessor.generateAccessToken(username);
        String refreshtoken = jwtProcessor.generateRefreshToken(username);
        RefreshTokenDTO refreshTokenDTO = new RefreshTokenDTO(username, refreshtoken);
        mapper.updateRefreshToken(refreshTokenDTO);
        log.info(" Refresh Token DB 저장 완료: {}", refreshtoken);
        return new AuthResultDTO(accesstoken,refreshtoken, UserInfoDTO.of(user.getMember()));
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response,
                                      Authentication authentication) throws IOException,ServletException{
        //인증결과Principal
        CustomUser user=(CustomUser)authentication.getPrincipal();
        //인증성공결과를JSON으로직접응답
        AuthResultDTO result=makeAuthResult(user);
        JsonResponse.send(response,result);
        log.info(" 로그인 성공 - AccessToken, RefreshToken 발급 및 응답 완료");
    }
}
