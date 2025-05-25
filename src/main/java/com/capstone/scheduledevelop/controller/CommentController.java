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

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> save(@RequestBody @Valid CommentRequestDto commentRequestDto) {

        CommentResponseDto commentResponseDto = commentService.save(commentRequestDto);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findAll() {
        List<CommentResponseDto> commentResponseDtos = commentService.findAll();

        return new ResponseEntity<>(commentResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<CommentWithUserNameResponseDto> findById(@PathVariable Long id) {

        CommentWithUserNameResponseDto commentWithUserNameResponseDto = commentService.findById(id);

        return new ResponseEntity<>(commentWithUserNameResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateContent(@PathVariable Long id, @RequestBody UpdateCommentRequestDto updateCommentRequestDto) {

        commentService.updateComment(id, updateCommentRequestDto.getContent());

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        commentService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
