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

@WebServlet("/editBook.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)

public class EditBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditBook() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	HttpSession s = request.getSession();
    	String query = "";
    	int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("bookTitle");
		String sumContent = request.getParameter("bookSumContent");
		String price = request.getParameter("bookPrice");
		String author = request.getParameter("bookAuthor");
		String pubDate = request.getParameter("bookPubDate");
		String category = request.getParameter("bookCategoryId");
		
		
		if (title == null || title.equals("") 
				|| sumContent == null || sumContent.equals("")
				|| price == null || price.equals("")
				|| author == null || author.equals("")
				|| pubDate == null || pubDate.equals("")
				|| category == null || category.equals("")) 
		{
			response.sendRedirect(getServletContext().getContextPath() + "/Protected/editBook.jsp?id=" + id);
		} else {
			try {
				Book b = new Book();
				b.setTitle(title);
				b.setAuthor(author);
				b.setCategoryId(Integer.parseInt(category));
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
						Part filePart = request.getPart("bookImage");
					    String image = Functions.extractFileName(filePart);
						image = new File(image).getName();	

						if (image == null || image.equals("")) {
							query = DBWorldQuery.updateBookNoImage(b, id);
						} else {
							b.setImage(image);
							query = DBWorldQuery.updateBookHaveImage(b, id);
							filePart.write(System.getProperty("user.dir") + "/" + getServletContext().getContextPath() + "/src/main/webapp/image/" + image);
						}
						dbm.ExecuteNonQuery(query);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					s.setAttribute("bookDataEdit", null);
					s.setAttribute("bookData", null);
					s.setAttribute("message", "Edit successful!!");
					response.sendRedirect(getServletContext().getContextPath() + "/Protected/editBook.jsp?id=" + id);
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
