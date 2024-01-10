package com.capstone.newtral.entity;

import com.capstone.newtral.entity.ConnectionTable.CategoryTopic;
import com.capstone.newtral.entity.ConnectionTable.TopicArticle;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "topic_tb")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="topic_label")
    private Long topicLabel;

    @Column(name="topic_name")
    private String topicName;

    @Column(name="topic_description")
    private String topicDescription;

    //builder 패턴
    @Builder
    public Topic(String topicName, String topicDescription){
        this.topicName = topicName;
        this.topicDescription = topicDescription;
    }

    //수정 관련
    public void editTopic(String topicName, String topicDescription){
        this.topicName = topicName;
        this.topicDescription = topicDescription;
    }

    //매핑관계
    @OneToMany(mappedBy = "topic")
    private List<CategoryTopic> categoryTopicList = new ArrayList<>();

    @OneToMany(mappedBy = "topic")
    private List<TopicArticle> topicArticles = new ArrayList<>();
}
