package com.springboot.lmssystem.member.controller;

import com.springboot.lmssystem.components.MailComponents;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final MailComponents mailComponents; // 필드는 클래스 수준에서 선언

    @RequestMapping("/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();

        String msg = "<html>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "</head>" +
                "<body>" +
                "<p>hello</p> <p>fastlms website!!!</p>" +
                "<p>안녕하세요!!!</p>" +
                "</body>" +
                "</html>";

        printWriter.write(msg);
        printWriter.close();
    }

    @RequestMapping("/")
    public String index() {

//        String email = "seoksmin@naver.com";
//        String subject = "안녕하세요. 제로베이스 입니다.";
//        String text = "<p>안녕하세요.</p><p>반갑습니다.</p>";
//        mailComponents.sendMail(email, subject, text);

        return "index";
    }
}
