package com.model;

public class Product {

    private long id;
    private String name;
    private double price;
    private String description;
    private int version; // Version number for optimistic locking
    
	public Product(long id, String name, double price, String description, int version) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.version = version;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

    // Constructor, getters, setters
    
    
}
