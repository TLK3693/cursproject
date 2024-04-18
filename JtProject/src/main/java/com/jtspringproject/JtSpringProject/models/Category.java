package com.jtspringproject.JtSpringProject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
//@Table(name = "CATEGORY")
@Entity(name="CATEGORY")
public class Category {
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "category_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	public Category(){}
	public Category(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
