package com.example.expense.expense_tracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Category")

public class Category {
	@Id

	@Column(name="categoryid")
	private int categoryid;

	@Column(name="userid")
	private long userid;

	@Column(name="name")
	private String name;

	@Column(name="type")
	private String type;
	
   public Category() {
	   
   }
	public Category(int categoryid,long userid, String name, String type) {
		this.categoryid = categoryid;
		this.userid=userid;
		this.name = name;
		this.type = type;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int catogryid) {
		this.categoryid = catogryid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
}
