package com.capstone.newtral.repository;

import com.capstone.newtral.entity.ConnectionTable.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<UserCategory, Long> {
}
