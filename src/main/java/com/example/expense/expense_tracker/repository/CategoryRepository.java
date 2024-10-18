package com.example.expense.expense_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expense.expense_tracker.model.Category;

import jakarta.transaction.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
    boolean existsByUseridAndCategoryid(int categoryid,long userid);
    @Transactional
   void deleteByCategoryidAndUserid(int categoryid,long userid);
}
