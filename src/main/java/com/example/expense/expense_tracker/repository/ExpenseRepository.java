package com.example.expense.expense_tracker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.expense.expense_tracker.model.Expense;

import jakarta.transaction.Transactional;

public interface ExpenseRepository extends JpaRepository<Expense, Integer>{
     boolean existsByUserIdAndCategoryId(int userId,int categoryId);
     @Transactional
     void deleteByUserIdAndCategoryId(long userId,int categoryId);
}
