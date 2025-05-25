package com.capstone.scheduledevelop.service;

import com.capstone.scheduledevelop.config.PasswordEncoder;
import com.capstone.scheduledevelop.dto.LoginResponseDto;
import com.capstone.scheduledevelop.dto.SignUpResponseDto;
import com.capstone.scheduledevelop.dto.UserResponseDto;
import com.capstone.scheduledevelop.entity.Schedule;
import com.capstone.scheduledevelop.entity.User;
import com.capstone.scheduledevelop.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    public SignUpResponseDto signUp(String username, String email, String password) {

        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(username, email, encodedPassword);

        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    public UserResponseDto findById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id : " + id); // 404
        }

        User findUser = optionalUser.get();

        return new UserResponseDto(findUser.getUsername(), findUser.getEmail());
    }

    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        if (!passwordEncoder.matches(oldPassword, findUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        String encodedNewPassword = passwordEncoder.encode(newPassword);
        findUser.updatePassword(encodedNewPassword);

    }

    public void delete(Long id) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        userRepository.delete(findUser);

    }

    public LoginResponseDto login(String email, String password, HttpServletRequest request) {
        User user = userRepository.findByEmailElseThrow(email);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        return new LoginResponseDto(user.getId());
    }

}
