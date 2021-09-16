package com.hcmue.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hcmue.helper.DBWorldQuery;
import com.hcmue.models.Book;
import com.hcmue.models.DBManager;

@WebServlet("/getBookDataEdit.do")
public class GetBookDataEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetBookDataEdit() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != "" && request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			HttpSession s = request.getSession();
			
			if (getServletConfig().getServletContext().getAttribute("BookStoreDB") != null) {
				DBManager dbm = (DBManager)getServletConfig().getServletContext().getAttribute("BookStoreDB");
				
				try {
					if (!dbm.isConnected())
					{
						if (!dbm.openConnection())
							throw new IOException("Could not connect to database and open connection");
					}
					
					String query = DBWorldQuery.getBookFromId(id);
					
					ResultSet rs = dbm.ExecuteResultSet(query);
					
					while (rs.next())
					{
						Book b = new Book();
						b.setId(id);
						b.setTitle(rs.getString("title"));
						b.setSummaryContent(rs.getString("summaryContent"));
						b.setPrice(rs.getInt("price"));
						b.setAuthor(rs.getString("author"));
						b.setPublicationDate(rs.getDate("publicationDate"));
						b.setImage(rs.getString("image"));
						b.setCategoryId(rs.getInt("categoryId"));
						s.setAttribute("bookDataEdit", b);
						break;
					}
				} catch (Exception ex) {
					throw new IOException("Query could not be executed to get data of book");
				}
				response.sendRedirect(getServletContext().getContextPath() + "/Protected/editBook.jsp?id=" + id);
				return;
			} else {
				response.sendRedirect("login.jsp");
			}
		}
	}
}
