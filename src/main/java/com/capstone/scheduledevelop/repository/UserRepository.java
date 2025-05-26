package com.capstone.scheduledevelop.repository;

import com.capstone.scheduledevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    // 유저명에 맞는 유저 조회 후 없는 경우 NOT_FOUND 상태코드 리턴
    default User findUserByUsernameOrElseThrow(String username) {
        return findUserByUsername(username)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist username = " + username));
    }

    // ID에 맞는 유저 조회 후 없는 경우 NOT_FOUND 상태코드 리턴
    default User findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id = " + id));
    }

    Optional<User> findByEmail(String email);

    // email에 맞는 유저 조회 후 없는 경우 NOT_FOUND 상태코드 리턴
    default User findByEmailElseThrow(String email) {
        return findByEmail(email)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists email = " + email));
    }
}
