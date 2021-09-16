package com.hcmue.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hcmue.helper.DBWorldQuery;
import com.hcmue.models.*;

import java.sql.ResultSet;
import java.util.ArrayList;
/**
 */
@WebServlet("/getbookdata.do")
public class GetBookData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetBookData() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession s = request.getSession();
		
		if (getServletConfig().getServletContext().getAttribute("BookStoreDB") != null) {
			DBManager dbm = (DBManager)getServletConfig().getServletContext().getAttribute("BookStoreDB");
			
			try {
				if (!dbm.isConnected())
				{
					if (!dbm.openConnection())
						throw new IOException("Could not connect to database and open connection");
				}
				
				//Get all Book
				String query = DBWorldQuery.getBooks();
				ArrayList<Book> allBooks = new ArrayList<Book>();
				
				ResultSet rs = dbm.ExecuteResultSet(query);
				
				while (rs.next())
				{
					Book b = new Book();
					b.setId(rs.getInt("id"));
					b.setTitle(rs.getString("title"));
					b.setSummaryContent(rs.getString("summaryContent"));
					b.setPrice(rs.getInt("price"));
					b.setAuthor(rs.getString("author"));
					b.setPublicationDate(rs.getDate("publicationDate"));
					b.setImage(rs.getString("image"));
					b.setCategoryId(rs.getInt("categoryId"));
					
					allBooks.add(b);
				}
				
				//Set session
				s.setAttribute("bookData", allBooks);
				s.setAttribute("message", null);
			} catch (Exception ex) {
				throw new IOException("Query could not be executed to get all books");
			}
			response.sendRedirect(getServletContext().getContextPath() + "/Protected/listBooks.jsp");
			return;
		} else {
			response.sendRedirect("login.jsp");
		}
		
	}
}
