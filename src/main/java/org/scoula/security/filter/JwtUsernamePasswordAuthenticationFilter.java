package org.scoula.security.filter;

import lombok.extern.log4j.Log4j2;
import org.scoula.security.dto.LoginDTO;
import org.scoula.security.handler.LoginFailureHandler;
import org.scoula.security.handler.LoginSuccessHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
@Component
public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, LoginSuccessHandler loginSuccessHandler, LoginFailureHandler loginFailureHandler) {
        super(authenticationManager);
        setFilterProcessesUrl("/v1/auth/login");
        setAuthenticationSuccessHandler(loginSuccessHandler);
        setAuthenticationFailureHandler(loginFailureHandler);
    }

    //로그인 요청 url인 경우 로그인 작업 처리
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response)
    throws AuthenticationException {

        //요청BODY의 JSON에서 username,passwordLoginDTO
        LoginDTO login= LoginDTO.of(request);

        //인증토큰(UsernamePasswordAuthenticationToken)구성
        UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(login.getUser_id(), login.getPassword());

        //AuthenticationManager에게인증요청
        return getAuthenticationManager().authenticate(authenticationToken);
    }
}
