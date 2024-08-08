package com.springboot.lmssystem.member.controller;

import com.springboot.lmssystem.member.entity.Member;
import com.springboot.lmssystem.member.model.MemberInput;
import com.springboot.lmssystem.member.repository.MemberRepository;
import com.springboot.lmssystem.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

//    @RequiredArgsConstructor가 역할 대신 수행함.
//    public MemberController(MemberService memberService) {
//        this.memberService = memberService;
//    }

    @GetMapping("/member/register")
    public String register() {
        System.out.println("request get");
        return "member/register";
    }


    @PostMapping("/member/register")
    public String registerSubmit(Model model, HttpServletRequest request, HttpServletResponse response, MemberInput memberInput) {

        boolean result = memberService.register(memberInput);
        model.addAttribute("result", result);

        return "member/register-complete";
    }

    // http://www.naver.com/news/list.do?id=123
    // https://
    // 프로토콜://도메인(IP)/news/list.do?쿼리스트링(파라미터)
    // https://www.naver.com/cafe/detail.do
    // https://www.naver.com/cafe/detail.do?id=123

    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request){
        String uuid = request.getParameter("id");

        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);

        return "member/email_auth";
    }
}
