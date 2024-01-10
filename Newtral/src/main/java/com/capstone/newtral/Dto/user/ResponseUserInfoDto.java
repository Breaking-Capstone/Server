package com.capstone.newtral.Dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
public class ResponseUserInfoDto {

    private Long userLabel;

    private String userId;

    private String userPassword;

    private String userName;

    private Integer userAge;

    private String userEmail;

    private List<String> userRoles;

    @Builder
    public ResponseUserInfoDto(Long userLabel, String userId, String userPassword, String userName, Integer userAge, String userEmail, List<String> userRoles){
        this.userLabel = userLabel;
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userRoles = userRoles;
    }

}
