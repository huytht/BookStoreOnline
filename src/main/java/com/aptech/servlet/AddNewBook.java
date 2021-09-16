package com.aptech.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.aptech.helper.DBWorldQuery;
import com.aptech.models.*;

@WebServlet("/addNewBook.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)
public class AddNewBook extends HttpServlet {
       
	private static final long serialVersionUID = 1L;

	public AddNewBook() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("bookTitle");
		String sumContent = request.getParameter("bookSumContent");
		String price = request.getParameter("bookPrice");
		String author = request.getParameter("bookAuthor");
		String pubDate = request.getParameter("bookPubDate");
		Part filePart = request.getPart("bookImage");
	    String image = Functions.extractFileName(filePart);
		image = new File(image).getName();

		String category = request.getParameter("bookCategoryId");
		
		if (title == null || title.equals("") 
				|| sumContent == null || sumContent.equals("")
				|| price == null || price.equals("")
				|| author == null || author.equals("")
				|| pubDate == null || pubDate.equals("")
				|| image == null || image.equals("")
				|| category == null || category.equals("")) 
		{
			response.sendRedirect(getServletContext().getContextPath() + "/Protected/addBook.jsp");
		} else {
			try {
				Book b = new Book();
				b.setTitle(title);
				b.setAuthor(author);
				b.setCategoryId(Integer.parseInt(category));
				b.setImage(image);
				b.setPrice(Integer.parseInt(price));
				b.setPublicationDate(Date.valueOf(pubDate));
				b.setSummaryContent(sumContent);
				
				if (getServletConfig().getServletContext().getAttribute("BookStoreDB") != null) {
					DBManager dbm = (DBManager)getServletConfig().getServletContext().getAttribute("BookStoreDB");
					
					try {
						if (!dbm.isConnected())
						{
							if (!dbm.openConnection())
								throw new IOException("Could not connect to database and open connection");
						}
						filePart.write(System.getProperty("user.dir") + "/" + getServletContext().getContextPath() + "/src/main/webapp/image/" + image);

						String query = DBWorldQuery.insertBook(b);
						
						dbm.ExecuteNonQuery(query);


					} catch (Exception ex) {
						throw new IOException("Query could not be executed to get all categories");
					}
					HttpSession s = request.getSession();
					s.setAttribute("bookData", null);
					s.setAttribute("message", "Add new book successful!!");
					response.sendRedirect(getServletContext().getContextPath() + "/Protected/addBook.jsp");
					return;
				} else {
					response.sendRedirect("login.jsp");
				}
			} catch (Exception ex) {
				response.sendRedirect(getServletContext().getContextPath() + "/errorHandler.jsp");
			}
		}
		
	}
}
