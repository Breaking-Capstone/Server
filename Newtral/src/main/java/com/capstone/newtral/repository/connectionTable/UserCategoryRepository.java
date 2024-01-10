package com.capstone.newtral.repository.connectionTable;

import com.capstone.newtral.entity.ConnectionTable.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
}
