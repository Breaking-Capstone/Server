package com.capstone.newtral.entity.ConnectionTable;

import com.capstone.newtral.entity.Category;
import com.capstone.newtral.entity.Topic;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "category_topic_tb")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryTopic {

    //구성요소
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_topic_id")
    private Long categoryTopicId;

    @Column(name = "category_topic_date")
    private LocalDateTime categoryTopicDate;


    //매핑관계
    @ManyToOne
    @JoinColumn( name = "category_id")
    Category category;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    Topic topic;



}
