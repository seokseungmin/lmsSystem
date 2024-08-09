package com.springboot.lmssystem.configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;


public class UserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String msg = "로그인에 실패하였습니다.";

        if (exception instanceof InternalAuthenticationServiceException) {
            msg = exception.getMessage();
        }

        //setUseForward(true); // 포워딩을 사용
        setDefaultFailureUrl("/member/login?error=true"); // 기본 실패 URL 설정
        request.setAttribute("errorMessage", msg); // 오류 메시지를 request 속성에 설정

        // 디버깅을 위한 메시지 출력
        System.out.println("로그인에 실패하였습니다: " + msg);

        super.onAuthenticationFailure(request, response, exception);
    }
}