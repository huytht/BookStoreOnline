package com.aptech.models;

import java.sql.Date;

public class Book {
	private int id;
	private String title;
	private String summaryContent;
	private long price;
	private String author;
	private Date publicationDate;
	private String image;
	private int categoryId;
	
	public Book() {
		super();
	}

	public Book(int id, String title, String summaryContent, long price, String author, Date publicationDate,
			String image, int categoryId) {
		super();
		this.id = id;
		this.title = title;
		this.summaryContent = summaryContent;
		this.price = price;
		this.author = author;
		this.publicationDate = publicationDate;
		this.image = image;
		this.categoryId = categoryId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummaryContent() {
		return summaryContent;
	}

	public void setSummaryContent(String summaryContent) {
		this.summaryContent = summaryContent;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
