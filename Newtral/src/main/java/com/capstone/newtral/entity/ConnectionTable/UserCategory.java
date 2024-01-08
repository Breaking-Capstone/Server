package com.capstone.newtral.entity.ConnectionTable;

import com.capstone.newtral.entity.Category;
import com.capstone.newtral.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "interest_tb")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class UserCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interest_id")
    private Long interestId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
