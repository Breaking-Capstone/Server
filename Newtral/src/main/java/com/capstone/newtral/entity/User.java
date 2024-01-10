package com.capstone.newtral.entity;

import com.capstone.newtral.Dto.user.ResponseUserInfoDto;
import com.capstone.newtral.common.Time;
import com.capstone.newtral.entity.ConnectionTable.UserCategory;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="user_tb")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Time implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_label")
    private Long userLabel;

    @Column(name="user_id")
    private String userId;

    @Column(name="user_password")
    private String userPassword;

    @Column(name="user_name")
    private String userName;

    @Column(name="user_age")
    private Integer userAge;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name="user_role")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> userRoles;


    //연관관계
    @OneToMany(mappedBy = "user")
    private List<UserCategory> userCategoryList = new ArrayList<>();

    //builder 패턴, toDto
    @Builder
    public User(String userId, String userPassword, String userName, Integer userAge,String userEmail ,List<String> userRoles){
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userRoles = userRoles;
    }

    public ResponseUserInfoDto toDto(){
        return ResponseUserInfoDto.builder()
                .userLabel(userLabel)
                .userAge(userAge)
                .userEmail(userEmail)
                .userId(userId)
                .userName(userName)
                .userRoles(userRoles)
                .userPassword(userPassword)
                .build();
    }

    //수정 관련
    public void editUser(String userName, Integer userAge){
        this.userName = userName;
        this.userAge = userAge;
    }

    public void editPassword(String password){
        this.userPassword = password;
    }


    //userDetails 오버라이드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userRoles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
    @Override
    public String getUsername() {  // 계정의 이름을 리턴, 일반적으로 id
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {  // 계정이 만료되었는지 리턴, true는 만료 되지 않음.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {  // 계정이 잠겨있는지 여부
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {   // 비밀번호가 만료되었는지 여부
        return true;
    }

    @Override
    public boolean isEnabled() {    // 계정이 활성회 되어있는지 여부
        return true;
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

}
