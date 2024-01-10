package com.capstone.newtral.Dto.topic;

import com.capstone.newtral.entity.Topic;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;

@Getter
public class RequestTopicDto {

    private String topicName;

    private String topicDescription;


    public Topic toEntity(RequestTopicDto requestTopicDto){
        return Topic.builder()
                .topicName(requestTopicDto.getTopicName())
                .topicDescription(requestTopicDto.getTopicDescription())
                .build();
    }

    @Builder
    public RequestTopicDto(String topicName, String topicDescription){
        this.topicName = topicName;
        this.topicDescription = topicDescription;
    }

}
