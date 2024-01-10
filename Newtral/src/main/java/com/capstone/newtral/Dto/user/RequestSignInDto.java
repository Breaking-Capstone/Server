package com.capstone.newtral.Dto.user;

import com.capstone.newtral.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
public class RequestSignInDto {

    private String userId;

    private String userPassword;

}
