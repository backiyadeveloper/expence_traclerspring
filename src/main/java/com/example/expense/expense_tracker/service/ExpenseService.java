package com.example.expense.expense_tracker.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//import org.jcp.xml.dsig.internal.MacOutputStream;
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
	 
	
	 @Transactional
	    public String addIncome(Income income) {
	    	
	    		if(!incomeRepo.existsByUserId(income.getUserId())) {
	    		incomeRepo.save(income); 
	           return "Income added successfully";
	    		}
	        
	    	else {
	           return "Give a valid user ID and salary";
	        }
	        
	    }
	    
	    public String updateIncome(Income income) {
	    	
	    	if(incomeRepo.existsById(income.getUserId()) ){
	    		incomeRepo.save(income);
	    		return "income added successfully";
	    	}
	    	else {
	    	return "id doest not added";
	       }
	    }
	    
	    public void addExpense(Expense expense) {
	    	if(expense.getUserId()>0 && expense.getCategoryId()>0 && expense.getAmount()>0) {
	    		if(!expenseRepository.existsByUserIdAndCategoryId(expense.getUserId(), expense.getCategoryId())) {
		    	    expenseRepository.save(expense);    		
	    		}
	    		
	    	}
	    }
	    
	    public void addCategory(Category category) {
	  
	    		if(!categoryrepository.existsByUseridAndCategoryid( category.getUserid(), category.getCategoryid())) {
		    	categoryrepository.save(category);
	    		//return  "add successfully";
	    		}
	    		else {
	    		//	return "userid and category id is already exists";
	    		}
	    }
	    
	    public void updateExpense(Expense expense) {
	    	
	    	List<Expense> existingExpense=expenseRepository.findByUserIdAndCategoryId(expense.getUserId(), expense.getCategoryId());
	    	
	    	if(!existingExpense.isEmpty()) {
	    		for (Expense existCategory : existingExpense) {
	    			existCategory.setAmount(expense.getAmount());
	    			existCategory.setDate(expense.getDate());
	    			existCategory.setDescription(expense.getDescription());
	    			expenseRepository.save(existCategory);
	    	
	    	
	    	}
	    	}
	    }
	   
	    public void updateCategory(Category category) {
	    
	        List<Category> existCategory = categoryrepository.findByUseridAndCategoryid(category.getUserid(), category.getCategoryid());

	        if (!existCategory.isEmpty()) {
	       
	            for (Category existingCategory : existCategory) {
	                existingCategory.setName(category.getName());
	                existingCategory.setType(category.getType());
	                categoryrepository.saveAll(existCategory);
	            }
	           
	           
	        }
	    }


	    
	    public List<Map<String, Object>> getAllExpense(){
            List<Map<String, Object>> response = new ArrayList<>();
	        
	        List<Expense> expenses = expenseRepository.findAll();
	        List<Category> categories = categoryrepository.findAll();
	        
	        Map<Integer, Category> categoryMap= new LinkedHashMap<>();
	        
	        for (Category category : categories) {
	            
	            categoryMap.put(category.getId(), category);
	            
	        }
	        
	        for(Expense expense : expenses) {
	            
	            Category category = categoryMap.get(expense.getId());
	            
	            if(category != null) {
	                
	                Map<String , Object> resultData = new LinkedHashMap<>();
	                
	                resultData.put("id", expense.getId());
	                resultData.put("userId", expense.getUserId());
	                resultData.put("categoryId", expense.getCategoryId()); 
	                resultData.put("amount", expense.getAmount());
	                resultData.put("categoryName", category.getName());
	                resultData.put("description", expense.getDescription());               
	                resultData.put("categoryType", category.getType());
	                resultData.put("date", expense.getDate());

	                response.add(resultData);
	            }
	            
	            }
	        
	        return response;
	    }
	    
	    @Transactional
	   public String deleteExpence(int userid,int categoryid) {
		 expenseRepository.deleteByUserIdAndCategoryId(userid, categoryid);
		 
		     return "expense deleted";
	    	
		  }
	    @Transactional
	    public String deleteCategory(int categoryid,int userid) {
	    	categoryrepository.deleteByCategoryidAndUserid(userid,categoryid);
			 
			     return "expense deleted";
			  }
	    
	    public List<String> getByCategory(String category) {
	        List<Category> categoryList = categoryrepository.findAllByName(category);
	        List<String> resultList = new ArrayList<>();

	        for (Category categoryItem : categoryList) {
	            int id = (int) categoryItem.getUserid();
	            int categoryid = categoryItem.getCategoryid();
	            String name = categoryItem.getName();

	            
	            List<Expense> expenses = expenseRepository.findByUserIdAndCategoryId(id, categoryid);

	            
	            if (!expenses.isEmpty()) {
	                for (Expense expense : expenses) { 
	                    double amount = expense.getAmount();
	                    Date day = expense.getDate();
	                    String description = expense.getDescription();

	                    String result = "{id=" + id + ",amount=" + amount + ",description=" + description
	                            + ",category=" + name + ",date=" + day + "}";
	                    resultList.add(result);
	                }
	            }
	        }

	        return resultList;
	    }

	    
	    public List<Expense> getExpensesByDateRange(Date startDate, Date endDate) {
	        return expenseRepository.findAllByDateRange(startDate, endDate);
	    }

		public Map<String, String> getcategoryreport(int userid) {
			double income=incomeRepo.getIncomeByUserId(userid);
			List<Object[]> report=expenseRepository.getCategoryReport(userid);
			Map<String, String> result=new LinkedHashMap<String, String>();
			for(Object[] tempObjects : report) {
				String type=(String) tempObjects[0];
				double amount=(double) tempObjects[1];
				String percentage=(int) ((amount/income)*100)+"%";
				result.put(type, percentage);
			}
			return result;
			
			
		}

		 
	   
}


