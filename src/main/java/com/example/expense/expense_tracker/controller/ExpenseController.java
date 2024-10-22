package com.example.expense.expense_tracker.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	private ExpenseService service;
	
	@Autowired
	private ObjectMapper objectmapper;

	@PostMapping("/adddata")
	public void addIncome(@RequestBody Income income) {
		service.addIncome(income);

	}

	@PostMapping("/updateincome")
	public String updateIncome(@RequestBody Income income) {
		return service.updateIncome(income);

	}

	@PostMapping("/addexpensecategory")
	public void addExpenseCategory(@RequestBody Map<String, Object> request) {

		Expense expense = objectmapper.convertValue(request.get("expense"), Expense.class);
		Category category = objectmapper.convertValue(request.get("category"), Category.class);

		service.addExpense(expense);
		service.addCategory(category);


	}

	@PutMapping("/updatecategoryexpense")
	public void update(@RequestBody Map<String, Object> request) {
		Expense expense = objectmapper.convertValue(request.get("expense"), Expense.class);
		Category category = objectmapper.convertValue(request.get("category"), Category.class);
		service.updateExpense(expense);
		service.updateCategory(category);
	}

	@DeleteMapping("/delete/{userid}")
	public String deletexpence(@PathVariable("userid") int userid, @RequestBody Map<String, Integer> request) {
		int categoryid =  request.get("categoryid");
		String deletexp = service.deleteCategory(userid, categoryid);
		String deletcat = service.deleteExpence(userid, categoryid);
		if (deletexp.equals("expense deleted") && deletcat.equals("expense deleted")) {
			return "deleted succesfully";
		} else {
			return "not deleted";
		}
	}

	@GetMapping("/getExpenses")
	public List<Map<String, Object>> getExpense() {

		return service.getAllExpense();
	}

	@GetMapping("/getcategory")
	public List<String> getByCategory(@RequestBody Map<String, Object> request) {
		String category = (String) request.get("name");
		return service.getByCategory(category);
	}

	@GetMapping("/bydaterange")
	public List<Expense> getExpensesByDateRange(@RequestBody Map<String, Date> request) {

		Date startDate = request.get("startdate");
		Date enddate = request.get("enddate");

		return service.getExpensesByDateRange(startDate, enddate);
	}

	@GetMapping("/getcategoryreport/{userid}")
	public Map<String, String> getCategoryReport(@PathVariable int userid) {
		return service.getcategoryreport(userid);

	}
}
