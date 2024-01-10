package com.capstone.newtral.service;

import com.capstone.newtral.Dto.user.RequestSignInDto;
import com.capstone.newtral.Dto.user.RequestSignUpDto;
import com.capstone.newtral.Dto.user.ResponseUserInfoDto;
import com.capstone.newtral.config.exception.DuplicatedException;
import com.capstone.newtral.config.exception.LoginErrorException;
import com.capstone.newtral.config.security.JwtProvider;
import com.capstone.newtral.entity.User;
import com.capstone.newtral.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public List<ResponseUserInfoDto> getAllUsers(){
        log.info("[getAllUsers] 회원 전체조회 시작");
        List<ResponseUserInfoDto> userList = userRepository.findAll().stream()
                        .map( user -> user.toDto()
                ).collect(Collectors.toList());
        log.info("[getAllUsers] 회원 전체조회 완료");
        return userList;
    }

    public void signUp(RequestSignUpDto requestSignUpDto){
        log.info("[SignUp] 회원가입 로직 시작");

        if(userRepository.findByUserId(requestSignUpDto.getUserId()).isPresent()){
            log.error("[signUp] 중복된 아이디로 인한 로그인 실패");
            throw new DuplicatedException("이미 존재하는 아이디입니다.");
        }
        else{
            userRepository.save(requestSignUpDto.toEntity(requestSignUpDto, passwordEncoder.encode(requestSignUpDto.getUserPassword())));
            log.info("[SignUp] 로그인 성공");
        }
    }

    public String signIn(RequestSignInDto requestSignInDto){
        log.info("[SignIn] 로그인 로직 시작");

        log.info("[SignIn] 아이디/비밀번호를 올바르게 작성하였는지 확인");
        User user = userRepository.findByUserId(requestSignInDto.getUserId())
                .orElseThrow(()-> new LoginErrorException("아이디 혹은 비밀번호가 틀렸습니다."));

        if(!passwordEncoder.matches(requestSignInDto.getUserPassword(), user.getUserPassword())){
            log.error("[SingIn] 비밀번호가 틀렸습니다.");
            throw new LoginErrorException("아이디 혹은 비밀번호가 틀렸습니다.");
        }
        else{
            String token = jwtProvider.createToken(user.getUserId(),user.getUserLabel(), user.getUserRoles());
            log.info("토큰 생성 완료. 토큰: {}", token);
            return token;
        }


    }
}
