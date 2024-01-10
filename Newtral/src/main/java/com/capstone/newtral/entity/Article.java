package com.capstone.newtral.entity;

import com.capstone.newtral.entity.ConnectionTable.CategoryTopic;
import com.capstone.newtral.entity.ConnectionTable.TopicArticle;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "article_tb")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_label")
    private Long articleLabel;

    @Column(name = "article_title")
    private String articleTitle;

    @Column(name = "article_company")
    private String articleCompany;

    @Column(name = "article_content")
    private String articleContent;

    @Column(name = "registration_time")
    private LocalDateTime registrationTime;

    @Column(name = "edit_time")
    private LocalDateTime editTime;

    //builder 패턴
    @Builder
    private Article(String articleTitle, String articleCompany, String articleContent, LocalDateTime registrationTime, LocalDateTime editTime){
        this.articleTitle=articleTitle;
        this.articleCompany = articleCompany;
        this.articleContent = articleContent;
        this.registrationTime = registrationTime;
        this.editTime = editTime;
    }

    //수정 관련
    private void editArticle(String articleTitle, String articleCompany, String articleContent, LocalDateTime registrationTime, LocalDateTime editTime){
        this.articleTitle=articleTitle;
        this.articleCompany = articleCompany;
        this.articleContent = articleContent;
        this.registrationTime = registrationTime;
        this.editTime = editTime;
    }

    //매핑관계
    @OneToMany(mappedBy = "article")
    private List<TopicArticle> topicArticles = new ArrayList<>();
}
