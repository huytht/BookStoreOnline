package com.hcmue.helper;

import com.hcmue.models.Book;
import com.hcmue.models.Category;
import com.hcmue.models.WebUser;

public class DBWorldQuery {
	
	
	//Table book
	public static String getBooks() {
		return "SELECT * FROM book";
	}
	
	public static String insertBook(Book b) {
		return String.format("INSERT INTO book (title, summaryContent, price, author, publicationDate, image, categoryId) VALUES ('%s', '%s', %d, '%s', '%tF', '%s', %d)", 
				b.getTitle(),
				b.getSummaryContent(),
				b.getPrice(),
				b.getAuthor(),
				b.getPublicationDate(),
				b.getImage(),
				b.getCategoryId());
	}
	
	public static String getBookFromId(int id) {
		return "SELECT * FROM book WHERE id = " + id;
	}
	
	public static String updateBookHaveImage(Book b, int id) {
		return String.format("UPDATE book SET title='%s', summaryContent='%s', price=%d, author='%s', publicationDate='%tF', image='%s', categoryId=%d WHERE id=%d"
				, b.getTitle()
				, b.getSummaryContent()
				, b.getPrice()
				, b.getAuthor()
				, b.getPublicationDate()
				, b.getImage()
				, b.getCategoryId()
				, id);
	}
	
	public static String updateBookNoImage(Book b, int id) {
		return String.format("UPDATE book SET title='%s', summaryContent='%s', price=%d, author='%s', publicationDate='%tF', categoryId=%d WHERE id=%d"
				, b.getTitle()
				, b.getSummaryContent()
				, b.getPrice()
				, b.getAuthor()
				, b.getPublicationDate()
				, b.getCategoryId()
				, id);
	}
	
	public static String deleteBook(int id) {
		return String.format("DELETE FROM book where id=%d", id);
	}
	
	//Table category
	public static String getCategories() {
		return "SELECT * FROM category";
	}
	
	public static String insertCategory(Category c) {
		return String.format("INSERT INTO category (name) VALUES ('%s')", c.getName());
	}
	
	public static String updateCategory(Category c, int id) {
		return String.format("UPDATE category SET name='%s' WHERE id=%d", c.getName(), id);
	}
	
	public static String getCategoryFromId(int id) {
		return "SELECT * FROM category WHERE id = " + id;
	}
	
	public static String deleteCategory(int id) {
		return String.format("DELETE FROM category where id=%d", id);
	}
	
	//Table webUser
	public static String getWebUserByUsernameAndPassword(String username, String password) {
		return String.format("SELECT * FROM webUser WHERE uid = '%s' AND password='%s'" , username, password);
	}
	
	public static String getWebUserByUID(String uid) {
		return String.format("SELECT * FROM webUser WHERE uid = '%s'", uid);
	}
	
	public static String getWebUsers() {
		return "SELECT * FROM webUser";
	}
	
	public static String insertUser(WebUser w) {
		return String.format("INSERT INTO webUser (uid, password, authLevel) VALUES ('%s', '%s', %d)", w.getUid(), w.getPwd(), w.getAuthLevel());
	}
	
	public static String deleteUser(String uid) {
		return String.format("DELETE FROM webUser where uid='%s'", uid);
	}
	
	//Table role
	public static String getRoles() {
		return "SELECT * FROM role";
	}
	
	public static String updateUser(WebUser w, String uid) {
		return String.format("UPDATE webUser SET password='%s', authLevel=%d WHERE uid='%s'", w.getPwd(), w.getAuthLevel(), uid);
	}
}
