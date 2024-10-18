package com.example.expense.expense_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expense.expense_tracker.model.Income;

public interface IncomeRepository extends JpaRepository<Income, Integer>{
	

}
