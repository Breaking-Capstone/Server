package com.capstone.newtral.entity;

import com.capstone.newtral.entity.ConnectionTable.CategoryTopic;
import com.capstone.newtral.entity.ConnectionTable.TopicArticle;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String registrationTime;

    //매핑관계
    @OneToMany(mappedBy = "article")
    private List<TopicArticle> topicArticles = new ArrayList<>();
}
