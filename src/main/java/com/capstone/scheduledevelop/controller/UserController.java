package com.capstone.scheduledevelop.controller;

import com.capstone.scheduledevelop.dto.login.LoginRequestDto;
import com.capstone.scheduledevelop.dto.login.LoginResponseDto;
import com.capstone.scheduledevelop.dto.user.SignUpRequestDto;
import com.capstone.scheduledevelop.dto.user.SignUpResponseDto;
import com.capstone.scheduledevelop.dto.user.UpdateUserRequestDto;
import com.capstone.scheduledevelop.dto.user.UserResponseDto;
import com.capstone.scheduledevelop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    // 생성자를 통한 UserService 의존성 주입
    private final UserService userService;

    // 유저 생성 API (회원 가입)
    // json 데이터 -> validation 진행 -> SignUpRequestDto에 할당 -> SignUpRequestDto의 username, email, password를 service layer의 signUp 메서드를 통해 DB에 저장
    // DB 저장된 데이터를 SignUpResponseDto로 만들고 CREATED 상태코드와 함께 클라이언트로 리턴
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto signUpRequestDto) {

        SignUpResponseDto signUpResponseDto =
                userService.signUp(
                        signUpRequestDto.getUsername(),
                        signUpRequestDto.getEmail(),
                        signUpRequestDto.getPassword()
                );

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    // 유저 단건 조회 API
    // requestParam으로 받은 일정 ID를 PathVariable로 id 변수에 받음
    // service layer의 findById 메서드를 통해 일정 단건 조회 로직 실행 후, 200 OK 상태코드 + 조회한 일정 정보를 UserResponseDto로 리턴
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {

        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    // 유저 전체 조회 API
    // service layer의 findAll 메서드를 통해 일정 전체 조회 로직 실행 후, 200 상태코드 + 조회한 일정 정보 리스트를 userResponseDtoList로 리턴
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> userResponseDtoList = userService.findAll();

        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    // 유저 수정 API
    // requestParam으로 받은 일정 ID를 PathVariable로 id 변수에 받음. RequestBody를 통해 json데이터를 UpdateUserRequestDto로 받음
    // service layer의 updatePassword 메서드를 통해 유저(비밀번호) 수정 로직 실행 루 200 OK 상태코드 리턴
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody @Valid UpdateUserRequestDto updateUserRequestDto) {

        userService.updatePassword(id,updateUserRequestDto.getOldPassword(), updateUserRequestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 유저 삭제 API
    // requestParam으로 받은 일정 ID를 PathVariable로 id 변수에 받음
    // service layer의 delete 메서드를 통해 유저 삭제 로직 실행 후 200 OK 상태코드 리턴
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletRequest request) {

        LoginResponseDto loginResponseDto = userService.login(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword(),
                request
        );

        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

}
