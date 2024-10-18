package com.example.expense.expense_tracker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.expense.expense_tracker.model.Category;
import com.example.expense.expense_tracker.model.Expense;
import com.example.expense.expense_tracker.model.Income;
import com.example.expense.expense_tracker.repository.CategoryRepository;
import com.example.expense.expense_tracker.repository.ExpenseRepository;
import com.example.expense.expense_tracker.repository.IncomeRepository;

import jakarta.transaction.Transactional;
@Service
public class ExpenseService {
	 @Autowired
	    private IncomeRepository incomeRepo;
	 @Autowired
	    private ExpenseRepository expenseRepository;
	 @Autowired
	 	private CategoryRepository categoryrepository;
	 
	 //add income
	    public String addIncome(Income income) {
	    	
	    		if(!incomeRepo.existsById(income.getId())) {
	    		incomeRepo.save(income); 
	            return "Income added successfully";
	    		}
	        
	    	else {
	            return "Give a valid user ID and salary";
	        }
	        
	    }
	    
	    //update income
	    public String updateIncome(Income income) {
	    	if(incomeRepo.existsById(income.getId()) ){
	    		incomeRepo.save(income);
	    		return "income added successfully";
	    	}
	    	else {
	    	return "does not added";
	       }
	    }
	    
	    public String addExpense(Expense expense) {
	    	if(expense.getUserId()>0 && expense.getCategoryId()>0 && expense.getAmount()>0) {
	    		if(!expenseRepository.existsByUserIdAndCategoryId(expense.getUserId(), expense.getCategoryId())) {
		    	expenseRepository.save(expense);
	    		return "expense added successfully";
	    		}
	    		else {
	    			return "userid and category id already exist";
	    		}
	    	}
	    	else {
	    	return "give a valid user id and category id and amount";
	       }
			
	    }
	    
	    public String addCategory(Category category) {
	    	if(category.getCategoryid()>0) {
	    		if(!categoryrepository.existsByUseridAndCategoryid( category.getCategoryid(),category.getUserid())) {
		    	categoryrepository.save(category);
	    		return  "add successfully";
	    		}
	    		else {
	    			return "userid and category id is already exists";
	    		}
	    	}
	    	else {
	    		return "does not add category";
	    	}
	    }
	    
	    public List<Expense> getAllExpense(){
	    	return expenseRepository.findAll();
	    }
	    
	    @Transactional
	   public String deleteExpence(long userid,int categoryid) {
		 expenseRepository.deleteByUserIdAndCategoryId(userid, categoryid);
		 
		     return "expense deleted";
		  }
	    @Transactional
	    public String deleteCategory(int categoryid,long userid) {
	    	categoryrepository.deleteByCategoryidAndUserid(categoryid,userid);
			 
			     return "expense deleted";
			  }
		 
	   
}
