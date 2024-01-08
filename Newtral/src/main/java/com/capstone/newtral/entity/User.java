package com.capstone.newtral.entity;

import com.capstone.newtral.entity.ConnectionTable.UserCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user_tb")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_label")
    private Long userLabel;

    @Column(name="user_id")
    private String userID;

    @Column(name="user_password")
    private String userPassword;

    @Column(name="user_name")
    private String userName;

    @Column(name="user_age")
    private String userAge;

    @OneToMany(mappedBy = "user")
    private List<UserCategory> userCategoryList = new ArrayList<>();

}
