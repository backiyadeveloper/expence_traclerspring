package com.example.expense.expense_tracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Income")
public class Income {
	
	@Id
	@Column(name="userid")
	private int id;
	
	@Column(name="income")
	private double income;

	// No-argument constructor
	public Income() {
	}

	// Constructor with parameters
	public Income(int id, double income) {
		this.id = id;
		this.income = income;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}
}
