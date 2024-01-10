package com.capstone.newtral.repository.connectionTable;

import com.capstone.newtral.entity.ConnectionTable.TopicArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicArticleRepository extends JpaRepository<TopicArticle, Long> {
}
