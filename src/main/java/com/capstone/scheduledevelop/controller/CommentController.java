package com.capstone.scheduledevelop.controller;

import com.capstone.scheduledevelop.dto.*;
import com.capstone.scheduledevelop.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    // 생성자를 통한 CommentService 의존성 주입
    private final CommentService commentService;

    // 댓글 생성 API 
    // json 데이터 -> validation 진행 -> CommentRequestDto에 할당 -> CommentRequestDto를 service layer의 save 메서드를 통해 DB에 저장
    // DB 저장된 데이터를 CommentResponseDto로 만들고 CREATED 상태코드와 함께 클라이언트로 리턴
    @PostMapping
    public ResponseEntity<CommentResponseDto> save(@RequestBody @Valid CommentRequestDto commentRequestDto) {

        CommentResponseDto commentResponseDto = commentService.save(commentRequestDto);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    // 댓글 전체 조회 API
    // service layer의 findAll 메서드를 통해 댓글 전체 조회 로직 실행 후, 200 상태코드 + 조회한 일정 정보 리스트를 commentResponseDtoList로 리턴
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAll() {
        List<CommentResponseDto> commentResponseDtoList = commentService.findAll();

        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    // 댓글 단건 조회 API
    // requestParam으로 받은 일정 ID를 PathVariable로 id 변수에 받음
    // service layer의 findById 메서드를 통해 댓글 단건 조회 로직 실행 후, 200 OK 상태코드 + 조회한 일정 정보를 CommentWithUserNameResponseDto로 리턴
    @GetMapping("/{id}")
    public  ResponseEntity<CommentWithUserNameResponseDto> findById(@PathVariable Long id) {

        CommentWithUserNameResponseDto commentWithUserNameResponseDto = commentService.findById(id);

        return new ResponseEntity<>(commentWithUserNameResponseDto, HttpStatus.OK);
    }

    // 댓글 수정 API
    // requestParam으로 받은 댓글 ID를 PathVariable로 id 변수에 받음. RequestBody를 통해 json데이터를 UpdateCommentRequestDto로 받음
    // service layer의 updateComment 메서드를 통해 댓글(내용) 수정 로직 실행 루 200 OK 상태코드 리턴
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateContent(@PathVariable Long id, @RequestBody UpdateCommentRequestDto updateCommentRequestDto) {

        commentService.updateComment(id, updateCommentRequestDto.getContent());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 댓글 삭제 API
    // requestParam으로 받은 댓글 ID를 PathVariable로 id 변수에 받음
    // service layer의 delete 메서드를 통해 댓글 삭제 로직 실행 후 200 OK 상태코드 리턴
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        commentService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
