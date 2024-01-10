package com.capstone.newtral.Dto.user;

import com.capstone.newtral.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
public class RequestSignUpDto {
    @Schema(description = "클라이언트가 로그인 할 때 사용하는 아이디",example = "inu1234")
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 5, max = 15, message="아이디는 최소 5자 이상, 최대 15자 이하의 길이를 충족해야 합니다.")
    private String userId;

    @Schema(description = "클라이언트가 로그인 할 때 사용하는 비밀번호", example = "password1234@")
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{5,20}", message = "비밀번호는 최소 5자 이상, 최대 20자 이하의 대소문자, 숫자, 특수문자로 구성되어야 합니다.")
    private String userPassword;

    @Schema(description = "클라이언트 실제 이름. 닉네임아님주의.", example = "홍길동")
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String userName;

    @Schema(description = "유저의 나이. Integer값이다.", example = "10")
    @NotNull(message = "나이는 필수 입력 값입니다.")
    private Integer userAge;

    @Schema(description = "클라이언트 이메일",example = "example@google.com")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email
    private String userEmail;

    @Schema(description = "클라이언트의 권한역할. 일반 유저는 ROLE_USER, 관리자는 ROLE_ADMIN", example = "[\"ROLE_USER\"]")
    @NotNull(message = "유저 권한은 필수 입력 값입니다.")
    private List<String> userRoles;


    public User toEntity(RequestSignUpDto requestSignUpDto, String password){
        return User.builder()
                .userId(requestSignUpDto.getUserId())
                .userPassword(password)
                .userName(requestSignUpDto.getUserName())
                .userEmail(requestSignUpDto.getUserEmail())
                .userAge(requestSignUpDto.getUserAge())
                .userRoles(requestSignUpDto.getUserRoles())
                .build();
    }

    @Builder
    public RequestSignUpDto(String userId, String userPassword, String userName, Integer userAge,String userEmail ,List<String> userRoles){
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userRoles = userRoles;
    }
}
