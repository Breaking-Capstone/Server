package com.capstone.newtral.entity.ConnectionTable;

import com.capstone.newtral.entity.Article;
import com.capstone.newtral.entity.Category;
import com.capstone.newtral.entity.Topic;
import com.capstone.newtral.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "topic_article_tb")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class TopicArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_article_id")
    private Long topicArticleId;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

}
