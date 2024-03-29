package com.capstone.newtral.entity;

import com.capstone.newtral.entity.ConnectionTable.CategoryTopic;
import com.capstone.newtral.entity.ConnectionTable.UserCategory;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "category_tb")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryLabel;

    @Column(name = "category_name")
    private String categoryName;

    //builder 패턴
    @Builder
    private Category(String categoryName){
        this.categoryName = categoryName;
    }

    //수정 관련
    private void editCategory(String categoryName){
        this.categoryName = categoryName;
    }

    //매핑관계
    @OneToMany(mappedBy = "category")
    private List<CategoryTopic> categoryTopics = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<UserCategory> userCategories = new ArrayList<>();
}
