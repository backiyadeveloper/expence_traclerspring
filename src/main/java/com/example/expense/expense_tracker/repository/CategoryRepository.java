package com.example.expense.expense_tracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.expense.expense_tracker.model.Category;

import jakarta.transaction.Transactional;

@EnableJpaRepositories
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	boolean existsByUseridAndCategoryid(int userid, int categoryid);

	@Transactional
	void deleteByCategoryidAndUserid(int userid, int categoryid);

	List<Category> findAllByName(String name);

	List<Category> findByUseridAndCategoryid(int userid, int categoryid);

}
