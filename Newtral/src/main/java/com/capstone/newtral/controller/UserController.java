package com.capstone.newtral.controller;

import com.capstone.newtral.Dto.CommonResponseDto;
import com.capstone.newtral.Dto.user.RequestSignInDto;
import com.capstone.newtral.Dto.user.RequestSignUpDto;
import com.capstone.newtral.common.CommonResponse;
import com.capstone.newtral.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/userlist")
    public ResponseEntity<CommonResponseDto> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponseDto(CommonResponse.SUCCESS, "유저 전체 조회 성공", userService.getAllUsers()));
    }


    @PostMapping("/sign-api/sign-up")
    public ResponseEntity<CommonResponseDto> signUp(@RequestBody @Valid RequestSignUpDto requestSignUpDto){
        userService.signUp(requestSignUpDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CommonResponseDto(CommonResponse.SUCCESS, "회원가입 완료", null));
    }

    @PostMapping("sign-api/sign-in")
    public ResponseEntity<CommonResponseDto> signIn(@RequestBody RequestSignInDto requestSignInDto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CommonResponseDto(CommonResponse.SUCCESS, "로그인 성공", userService.signIn(requestSignInDto)));
    }
}
