package com.springboot.lmssystem.member.service;

import com.springboot.lmssystem.member.model.MemberInput;

public interface MemberService {

    boolean register(MemberInput memberInput);

    /**
     * uuid에 해당하는 계정을 활성화 함.
     */
    boolean emailAuth(String uuid);
}
