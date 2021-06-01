package com.hescha.accountingforeventparticipants.repository;

import com.hescha.accountingforeventparticipants.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByNameIgnoreCase(String name);
}
