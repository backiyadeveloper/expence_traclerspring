package com.example.expense.expense_tracker.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expense.expense_tracker.model.Category;
import com.example.expense.expense_tracker.model.Expense;

import com.example.expense.expense_tracker.model.Income;
import com.example.expense.expense_tracker.service.ExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class ExpenseController {

    @Autowired
    private ExpenseService service; // Fixing the variable name to match the service class
    @Autowired
    private ObjectMapper objectmapper;
    
    @PostMapping("/adddata")
    public String addIncome(@RequestBody Income income) {
           return service.addIncome(income);
            
    }
    
    @PostMapping("/updateincome")
    public String updateIncome(@RequestBody Income income) {
    	return service.updateIncome(income);
		
    	}
    @PostMapping("/addexpensecategory")
    public String addExpenseCategory(@RequestBody Map<String,Object> request) {
    	
		Expense expense=objectmapper.convertValue(request.get("expense"),Expense.class);
		Category category=objectmapper.convertValue(request.get("category"),Category.class);
		
		String addexpense= service.addExpense(expense);
		String addCategory=service.addCategory(category);
		
		if(addexpense.equals("expense added successfully") && addCategory.equals("add successfully")) {
			return "expense and category added successfully";
		}
		else if (addexpense.equals("userid and category id already exist") && addCategory.equals("userid and category id is already exists")) {
			return "userid and category id already exist";
		}
		else {
			return "expense and category does not added successfully";
		}
    	
    	
    }
  @DeleteMapping("/delete/{userid}/{categoryid}")
  public String deletexpence(@PathVariable long userid,@PathVariable int categoryid) {
	  String deletexp= service.deleteCategory(categoryid,userid);
	  String deletcat=service.deleteExpence(userid,categoryid);
	   if(deletexp.equals("expense deleted") && deletcat.equals("expense deleted")) {
		   return "deleted succesfully";	  
		   }
	   else {
		   return "not deleted";
	   }
	
	  
  }
    
//    @PutMapping("/upExpence/{id}")
//    public String updateExpense(@PathVariable(value="id") int id,@RequestBody Expense expense) {
//    	expense.setId(id);
//		return service.updateExpense(expense);
//    	
//    }
    
   
    @GetMapping("/getExpebnses")
    public List<Expense> getExpense(){
    	
        return service.getAllExpense();
		
    	
    }
}
