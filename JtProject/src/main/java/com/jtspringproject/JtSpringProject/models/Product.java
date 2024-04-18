package com.jtspringproject.JtSpringProject.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

//@Entity
//@Table(name = "PRODUCT")
@Entity(name="PRODUCT")
public class Product {
	@Id
	@Column(name = "product_id")
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String image;
	
	@OneToOne(cascade = CascadeType.ALL)
//	@ManyToOne
	@JoinColumn(name = "category_id",referencedColumnName = "category_id")
	private Category category;
	
	private int quantity;
	
	private int price;
	
//	private int weight;
	
	private String description;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private User customer;

	// Новые поля для начала и конца даты, а также процента скидки
	private LocalDate  startDate;

	private LocalDate endDate;

	private int discount;

	public LocalDate  getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate  getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discountPercentage) {
		this.discount = discountPercentage;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

//	public int getWeight() {
//		return weight;
//	}
//
//	public void setWeight(int weight) {
//		this.weight = weight;
//	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
