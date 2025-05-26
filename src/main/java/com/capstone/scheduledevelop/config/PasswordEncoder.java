package com.capstone.scheduledevelop.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    // 입력받은 비밀번호(rawPassword)를 BCrypt 해싱 알고리즘으로 암호화하는 메서드
    public String encode(String rawPassword) {
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    // 입력받은 비밀번호와 저장된 암호화된 비밀번호가 일치하는지 검증하는 메서드
    public boolean matches(String rawPassword, String encodedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }
}
